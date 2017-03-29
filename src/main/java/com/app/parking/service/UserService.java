package com.app.parking.service;

import com.app.parking.model.User;
import com.app.parking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gatomulesei on 3/28/2017.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
       return userRepository.save(user);
    }
}
