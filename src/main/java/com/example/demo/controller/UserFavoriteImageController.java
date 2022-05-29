package com.example.demo.controller;

import com.example.demo.model.UserFavoriteImage;
import com.example.demo.service.UserFavoriteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite_image")
public class UserFavoriteImageController {

    @Autowired
    private UserFavoriteImageService userFavoriteImageService;

    @GetMapping
    public Iterable<UserFavoriteImage> read() {
        return userFavoriteImageService.getAllUserFavoriteImages();
    }

    @PostMapping
    public UserFavoriteImage save(@RequestBody UserFavoriteImage image) {
        return userFavoriteImageService.saveUserFavoriteImage(image);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return userFavoriteImageService.deleteUserFavoriteImage(id);
    }

}
