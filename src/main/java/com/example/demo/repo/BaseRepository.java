package com.example.demo.repo;


import com.example.demo.model.Base;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository extends CrudRepository<Base, Long> {
}
