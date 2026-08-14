package com.general_Biraj.journalApp.service;

import com.general_Biraj.journalApp.entery.User;
import com.general_Biraj.journalApp.repository.UserRepositry;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j //this inject or create instance when we complie automatically just like @Data for setter and getter
@Service

public class UserService {
    @Autowired
    private UserRepositry userRepositry;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void saveUser(User user) {
        userRepositry.save(user);
    }

    public boolean saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Arrays.asList("USER"));
            userRepositry.save(user);
            return true;
        } catch (Exception e) {
            log.error("Error occur");
            log.info("its Info");
            log.warn("its warn");
            log.debug("its debug");
            log.trace("its trace");
            return false;
        }

    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Arrays.asList("USER", "ADMIN"));
        userRepositry.save(user);
    }


    public List<User> getall() {
        return userRepositry.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepositry.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepositry.deleteById(id);

    }

    public User findByUserName(String userName) {
        return userRepositry.findByUserName(userName);
    }


}


//flow = controller --> service --> Repository extend Monog0Repository