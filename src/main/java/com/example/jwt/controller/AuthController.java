package com.example.jwt.controller;

import com.example.jwt.config.util.JwtTokenUtil;
import com.example.jwt.dto.UserDto;
import com.example.jwt.entity.User;
import com.example.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(UserDto userDto) {

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User result = userRepository.save(convertUserToUserDto(userDto));
        return ResponseEntity.ok().body(
                "token : " + jwtTokenUtil.generateAccessToken(result));

    }


    public User convertUserToUserDto(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
//        user.setRole(userDto.getRoles());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setIsEnabled(userDto.getIsEnabled());
        return user;
    }

}
