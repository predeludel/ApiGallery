package com.example.demo.service;

import com.example.demo.model.Base;

import com.example.demo.model.User;
import com.example.demo.model.UserFavoriteImage;
import com.example.demo.repo.BaseRepository;
import com.example.demo.repo.UserFavoriteImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserFavoriteImageService {

    @Autowired
    private UserFavoriteImageRepository userFavoriteImageRepository;
    @Autowired
    private BaseRepository baseRepository;

    public Page<UserFavoriteImage> getAllUserFavoriteImagesPage(int page, int size) {
        return userFavoriteImageRepository.findAll(PageRequest.of(page, size));
    }

    public Page<UserFavoriteImage> getAllUsersFavoriteImagePageSortByField(int page, int size, String field) {
        return userFavoriteImageRepository.findAll(PageRequest.of(page, size, Sort.by(field)));
    }

    public Iterable<UserFavoriteImage> getAllUserFavoriteImages() {
        return userFavoriteImageRepository.findAll();
    }

    public boolean deleteUserFavoriteImage(Long id, User user) {
        if (userFavoriteImageRepository.existsById(id) && userFavoriteImageRepository.findById(id).get().getUser().getId().equals(user.getId())) {
            userFavoriteImageRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public UserFavoriteImage saveUserFavoriteImage(UserFavoriteImage userFavoriteImage, User user) {
        if (userFavoriteImage.getId() != null && userFavoriteImageRepository.findById(userFavoriteImage.getId()).isPresent()) {
            UserFavoriteImage oldUserFavoriteImage = userFavoriteImageRepository.findById(userFavoriteImage.getId()).get();
            userFavoriteImage.setBase(oldUserFavoriteImage.getBase());
            userFavoriteImage.getBase().setUpdateDateTime(LocalDateTime.now());
        } else {
            Base base = new Base(LocalDateTime.now(), LocalDateTime.now());
            base = baseRepository.save(base);
            userFavoriteImage.setBase(base);
        }
        userFavoriteImage.setUser(user);
        return userFavoriteImageRepository.save(userFavoriteImage);

    }
}
