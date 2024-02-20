package com.example.GestioneDispositiviAziendali.controller;

import com.example.GestioneDispositiviAziendali.exceptionHandler.BadRequestEx;
import com.example.GestioneDispositiviAziendali.model.entities.User;
import com.example.GestioneDispositiviAziendali.model.request.LoginRequest;
import com.example.GestioneDispositiviAziendali.model.request.UserReq;
import com.example.GestioneDispositiviAziendali.service.UserSvc;
import com.example.GestioneDispositiviAziendali.sicurity.JwtTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCtrl {

    @Autowired
    private UserSvc userSvc;

    @Autowired
    private JwtTools jwtTools;
    @Autowired
    @Qualifier("BCript")
    private PasswordEncoder encoder;


    @PostMapping("/auth/register")
    public ResponseEntity<CustomResponse> register (@RequestBody @Validated UserReq userReq, BindingResult result) throws BadRequestEx {
        if (result.hasErrors()) {
            throw new BadRequestEx(result.getAllErrors().toString());
        }
        User user = userSvc.saveUser(userReq);
        return CustomResponse.success(HttpStatus.OK.toString(),user,HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<CustomResponse> login (@RequestBody @Validated LoginRequest loginUser, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            throw new BadRequestEx(result.getAllErrors().toString());
        }

        User user = userSvc.getUserByUserName(loginUser.getUsername());
        if (encoder.matches(loginUser.getPassword(),user.getPassword())) {
            String token = jwtTools.createToken(user);
            return CustomResponse.success(HttpStatus.OK.toString(),token,HttpStatus.OK);
        }else {
            throw new Exception("UserName/Password not found");
        }
    }
}
