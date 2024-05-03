package com.chegg.userauth.service;

import com.chegg.userauth.model.ServerUser;
import com.chegg.userauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerService {

    @Autowired
    private UserRepository userRepository;


    public List<ServerUser> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Boolean doesUserExists(String UserName,Integer role) {
        return userRepository.listofusers(UserName,role).size()>0;
    }
}