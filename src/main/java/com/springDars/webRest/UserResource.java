package com.springDars.webRest;

import com.springDars.domain.User;
import com.springDars.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user){
        if(!checkPasswordLength(user.getPassword())){
            return new ResponseEntity("parol 4 dan kichik", HttpStatus.BAD_REQUEST);
        }
        if(userService.checkUserName(user.getUserName())){
            return new ResponseEntity("Bu user mavjud",HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.create(user));

    }
    public Boolean checkPasswordLength(String password){
        return password.length()>=4;
    }
}
