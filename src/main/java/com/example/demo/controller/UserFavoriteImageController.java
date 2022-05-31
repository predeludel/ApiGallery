package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserFavoriteImage;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserFavoriteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite_image")
public class UserFavoriteImageController {

    @Autowired
    private UserFavoriteImageService userFavoriteImageService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public Iterable<UserFavoriteImage> read() {
        return userFavoriteImageService.getAllUserFavoriteImages();
    }

    @PostMapping
    public UserFavoriteImage save(@RequestBody UserFavoriteImage userFavoriteImage,@RequestHeader("token") String token) {
        return userFavoriteImageService.saveUserFavoriteImage(userFavoriteImage, authenticationService.validate(token));
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id,@RequestHeader("token") String token) {
        return userFavoriteImageService.deleteUserFavoriteImage(id, authenticationService.validate(token));
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
