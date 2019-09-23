package com.example.forum.service;

import com.example.forum.entity.User;
import com.example.forum.logger.Logging;
import com.example.forum.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;
    Logger logger = Logging.getLogger();

    @Override
    public void save(User user) {
        repository.save(user);
        logger.info("Saving topic id="+user.getIdusers());
    }

    @Override
    public User authorisation(String username, String password) {
        User user = repository.findByUsernameAndPassword(username, password);
        logger.info("Authorisation username="+username + " password="+password );
        return user;
    }

    @Override
    public User findById(Integer id) {
        User user = repository.findByIdusers(id);
        logger.info("Getting user by id="+id);
        return user;
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }
}
