package com.example.jwt.bootstrap;

import com.example.jwt.entity.Permission;
import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
import com.example.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class Bootstrap implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Permission permission = new Permission();
        permission.setPermission("write");

        Permission permission2 = new Permission();
        permission2.setPermission("read");


        Role role = new Role();
        role.setPermissions(List.of(permission, permission2));
        role.setRole("Admin");

        Role role2 = new Role();
        role2.setPermissions(List.of(permission2));
        role2.setRole("subAdmin");


        User user = new User();
        user.setPassword(passwordEncoder.encode("123"));
        user.setUsername("khadijeh");
        user.setIsEnabled(true);
        user.setRole(List.of(role));

        User user2 = new User();
        user2.setPassword(passwordEncoder.encode("123"));
        user2.setUsername("erfan");
        user2.setIsEnabled(true);
        user2.setRole(List.of(role2));

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

            userRepository.saveAll(users);
//        for (User u :
//                users) {
//        }
//        userRepository.save(user);
//        userRepository.save(user2);

    }
}
