package com.example.demo.repo;

import com.example.demo.model.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
    Optional<Session> findByToken(String token);
}
