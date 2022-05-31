package com.example.demo.service;

import com.example.demo.model.Session;
import com.example.demo.model.User;
import com.example.demo.model.UserAuthPojo;
import com.example.demo.repo.BaseRepository;
import com.example.demo.repo.SessionRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;

    public Session authenticate(UserAuthPojo userAuthPojo){
        Optional<User> user = userRepository.findByUsername(userAuthPojo.getUsername());
        if (user.isPresent()){
            if ( user.get().getPassword().equals(userAuthPojo.getPassword())){
                return createSession(user.get());
            }
            else  {throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Error");}
        }
        else  {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");}
    }
    public Session createSession(User user){
        Session session = new Session();
        session.setStatus(true);
        session.setUser(user);
        session.setToken(UUID.randomUUID().toString());
        session = sessionRepository.save(session);
        return session;

    }
    public User validate(String token){
        Optional<Session> session = sessionRepository.findByToken(token);
        if (session.isPresent()){
            if ( session.get().getToken().equals(token)){
                return session.get().getUser();
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect token");
    }

}
