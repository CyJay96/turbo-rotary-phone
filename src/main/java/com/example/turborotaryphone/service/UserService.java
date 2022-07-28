package com.example.turborotaryphone.service;

import com.example.turborotaryphone.model.Role;
import com.example.turborotaryphone.model.User;
import com.example.turborotaryphone.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, String password, String email, Map<String, String> form) {
        updateProfile(user, username, password, email);

        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public void updateProfile(User user, String username, String password, String email) {
        String userUsername = user.getUsername();
        String userEmail = user.getEmail();

        boolean isUsernameChanged = (username != null && !username.equals(userUsername)) ||
                (userUsername != null && !userUsername.equals(username));

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isUsernameChanged && username != null && !username.isEmpty()) {
            user.setUsername(username);
        }

        if (isEmailChanged) {
            user.setEmail(email);
        }

        if (password != null && !password.isEmpty()) {
            user.setPassword(password);
        }

        userRepo.save(user);
    }

}
