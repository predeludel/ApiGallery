package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping
    public Iterable<Image> read() {
        return imageService.getAllImages();
    }

    @PostMapping
    public Image save(@RequestBody Image image) {
        return imageService.saveImage(image);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return imageService.deleteImage(id);
    }

}
