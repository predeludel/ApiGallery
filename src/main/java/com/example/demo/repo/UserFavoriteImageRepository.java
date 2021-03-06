package com.example.demo.repo;


import com.example.demo.model.User;
import com.example.demo.model.UserFavoriteImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoriteImageRepository extends CrudRepository<UserFavoriteImage, Long> {
    Page<UserFavoriteImage> findAll(Pageable pageable);
}
