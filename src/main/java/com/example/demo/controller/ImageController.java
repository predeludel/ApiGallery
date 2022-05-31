package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public Iterable<Image> read() {
        return imageService.getAllImages();
    }

    @GetMapping("/{id}")
    public Image read(@PathVariable Long id) {
        return imageService.getImageById(id);
    }

    @PostMapping
    public Image save(@RequestBody Image image,@RequestHeader("token") String token) {
        return imageService.saveImage(image, authenticationService.validate(token));
    }

    @PostMapping("/{id}/attach")
    public Image attachFile(@PathVariable Long id, @RequestBody MultipartFile file,@RequestHeader("token") String token) {
        return imageService.attachFile(id, file, authenticationService.validate(token));
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id,@RequestHeader("token") String token) {
        return imageService.deleteImage(id,authenticationService.validate(token));
    }

    @GetMapping("/{page}/{size}")
    public Page<Image> read(@PathVariable Integer page, @PathVariable Integer size) {
        return imageService.getAllImagesPage(page, size);
    }

    @GetMapping("/{page}/{size}/sort/{field}")
    public Page<Image> read(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String field) {
        return imageService.getAllImagePageSortByField(page, size, field);
    }

    @GetMapping(value = "/{id}/file", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getFile(@PathVariable Long id) {
        return imageService.getImageResource(id);
    }

}
