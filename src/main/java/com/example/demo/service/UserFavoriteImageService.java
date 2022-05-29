package com.example.demo.service;

import com.example.demo.model.Base;

import com.example.demo.model.UserFavoriteImage;
import com.example.demo.repo.BaseRepository;
import com.example.demo.repo.UserFavoriteImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserFavoriteImageService {

        @Autowired
        private UserFavoriteImageRepository userFavoriteImageRepository;
        @Autowired
        private BaseRepository baseRepository;

        public Iterable<UserFavoriteImage> getAllUserFavoriteImages() {
            return userFavoriteImageRepository.findAll();
        }

        public boolean deleteUserFavoriteImage(Long id) {
            if (userFavoriteImageRepository.existsById(id)) {
                userFavoriteImageRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }

        public UserFavoriteImage saveUserFavoriteImage(UserFavoriteImage userFavoriteImage) {
            if (userFavoriteImageRepository.findById(userFavoriteImage.getId()).isPresent()){
                UserFavoriteImage oldUserFavoriteImage = userFavoriteImageRepository.findById(userFavoriteImage.getId()).get();
                userFavoriteImage.setBase(oldUserFavoriteImage.getBase());
                userFavoriteImage.getBase().setUpdateDateTime(LocalDateTime.now());
            }
            else {
                Base base = new Base(LocalDateTime.now(),LocalDateTime.now());
                base = baseRepository.save(base);
                userFavoriteImage.setBase(base);
            }
            return userFavoriteImageRepository.save(userFavoriteImage);

        }
    }
