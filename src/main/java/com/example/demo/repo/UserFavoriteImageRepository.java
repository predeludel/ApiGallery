package com.example.demo.repo;


import com.example.demo.model.UserFavoriteImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoriteImageRepository extends CrudRepository<UserFavoriteImage, Long> {
}
