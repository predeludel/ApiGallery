package com.example.demo.service;

import com.example.demo.model.Base;
import com.example.demo.model.User;
import com.example.demo.repo.BaseRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BaseRepository baseRepository;

    public Page<User> getAllUsersPage(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }
    public Page<User> getAllUsersPageSortByField(int page, int size, String field) {
        return userRepository.findAll(PageRequest.of(page, size, Sort.by(field)));
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public User saveUser(User user) {
        if (user.getId() != null && userRepository.findById(user.getId()).isPresent()) {
            User oldUser = userRepository.findById(user.getId()).get();
            user.setBase(oldUser.getBase());
            user.getBase().setUpdateDateTime(LocalDateTime.now());
        } else {
            Base base = new Base(LocalDateTime.now(), LocalDateTime.now());
            base = baseRepository.save(base);
            user.setBase(base);
        }
        return userRepository.save(user);

    }
}
