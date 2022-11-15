package com.example.jwtexample.service;

import com.example.jwtexample.entity.User;
import com.example.jwtexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        byte[] decodedBytes = Base64.getDecoder().decode(user.getPassword());
        String decodedString = new String(decodedBytes);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),decodedString,new ArrayList<>());
    }
}
