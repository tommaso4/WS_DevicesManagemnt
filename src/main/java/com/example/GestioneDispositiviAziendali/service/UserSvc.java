package com.example.GestioneDispositiviAziendali.service;

import com.example.GestioneDispositiviAziendali.enums.Role;
import com.example.GestioneDispositiviAziendali.exceptionHandler.NotFoundException;
import com.example.GestioneDispositiviAziendali.model.entities.User;
import com.example.GestioneDispositiviAziendali.model.request.UserReq;
import com.example.GestioneDispositiviAziendali.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSvc {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    @Qualifier("BCript")
    private PasswordEncoder encoder;

    public User saveUser(UserReq userReq) {
        User user = new User();
        user.setName(userReq.getName());
        user.setSurname(userReq.getSurname());
        user.setUsername(userReq.getUsername());
        user.setPassword(encoder.encode(userReq.getPassword()));
        user.setRole(Role.ADMIN);
        return userRepo.save(user);
    }

    public User getUserById (int id) throws NotFoundException {
        return userRepo.findById(id).orElseThrow(()-> new NotFoundException("User not found"));
    }

    public User getUserByUserName (String username) throws NotFoundException {
        return userRepo.findByUsername(username).orElseThrow(()-> new NotFoundException("UserName/Password not found"));
    }
}
