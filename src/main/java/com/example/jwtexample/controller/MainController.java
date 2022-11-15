package com.example.jwtexample.controller;

import com.example.jwtexample.dto.AuthInput;
import com.example.jwtexample.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome(){
        return  "welcome to jwt";

    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthInput authInput) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authInput.getUsername(), authInput.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(authInput.getUsername());
    }




}
