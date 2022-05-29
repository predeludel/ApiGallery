package com.example.demo.service;

import com.example.demo.model.Base;
import com.example.demo.model.Image;
import com.example.demo.repo.BaseRepository;
import com.example.demo.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private BaseRepository baseRepository;

    public Iterable<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public boolean deleteImage(Long id) {
        if (imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Image saveImage(Image image) {
        if (imageRepository.findById(image.getId()).isPresent()){
            Image oldImage = imageRepository.findById(image.getId()).get();
            image.setBase(oldImage.getBase());
            image.getBase().setUpdateDateTime(LocalDateTime.now());
        }
        else {
            Base base = new Base(LocalDateTime.now(),LocalDateTime.now());
            base = baseRepository.save(base);
            image.setBase(base);
        }
        return imageRepository.save(image);

    }
}
