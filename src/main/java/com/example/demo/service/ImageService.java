package com.example.demo.service;

import com.example.demo.model.Base;
import com.example.demo.model.Image;
import com.example.demo.model.User;
import com.example.demo.model.UserFavoriteImage;
import com.example.demo.repo.BaseRepository;
import com.example.demo.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private BaseRepository baseRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private ResizingService resizingService;

    protected static final String IMAGE_FILE_NAME_FORMAT = "%d.png";



    public Page<Image> getAllImagesPage(int page, int size) {
        return imageRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Image> getAllImagePageSortByField(int page, int size, String field) {
        return imageRepository.findAll(PageRequest.of(page, size, Sort.by(field)));
    }


    public Image attachFile(Long id, MultipartFile file, User user) {
        Image image = getImageById(id);
        if ( !image.getUser().getId().equals(user.getId())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
        }
        try {
            String path = fileService.saveFile(file.getBytes(), String.format(IMAGE_FILE_NAME_FORMAT, image.getId()));
            image.setAttachFilePath(path);
            imageRepository.save(image);
            resizingService.resizeImage(image);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return image;
    }

    public Image getImageById(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isPresent()) {
            return image.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Image with id %d not found", id));
        }
    }
    public Iterable<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public boolean deleteImage(Long id, User user) {
        if (imageRepository.existsById(id) && imageRepository.findById(id).get().getUser().getId().equals(user.getId())) {
            imageRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Image saveImage(Image image, User user) {
        if (image.getId() != null && imageRepository.findById(image.getId()).isPresent()) {
            Image oldImage = imageRepository.findById(image.getId()).get();
            image.setBase(oldImage.getBase());
            image.getBase().setUpdateDateTime(LocalDateTime.now());
        } else {
            Base base = new Base(LocalDateTime.now(), LocalDateTime.now());
            base = baseRepository.save(base);
            image.setBase(base);
        }
        image.setUser(user);
        return imageRepository.save(image);
    }

    public Resource getImageResource(Long imageId) {
        Image image = getImageById(imageId);
        return fileService.loadFile(image.getAttachFilePath());
    }

}
