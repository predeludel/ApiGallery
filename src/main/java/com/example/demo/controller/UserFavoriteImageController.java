package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserFavoriteImage;
import com.example.demo.service.UserFavoriteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public UserFavoriteImage save(@RequestBody UserFavoriteImage userFavoriteImage) {
        return userFavoriteImageService.saveUserFavoriteImage(userFavoriteImage);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return userFavoriteImageService.deleteUserFavoriteImage(id);
    }

    @GetMapping("/{page}/{size}")
    public Page<UserFavoriteImage> read(@PathVariable Integer page, @PathVariable Integer size) {
        return userFavoriteImageService.getAllUserFavoriteImagesPage(page, size);
    }

    @GetMapping("/{page}/{size}/sort/{field}")
    public Page<UserFavoriteImage> read(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String field) {
        return userFavoriteImageService.getAllUsersFavoriteImagePageSortByField(page, size, field);
    }

}
