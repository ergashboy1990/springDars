package com.springDars.webRest;

import com.springDars.domain.User;
import com.springDars.repository.UserRepository;
import com.springDars.security.JwtTokenProvider;
import com.springDars.webRest.vm.LoginVM;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserJwtController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public UserJwtController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginVM loginVM) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVM.getUserName(), loginVM.getPassword()));
        User user = userRepository.findByLogin(loginVM.getUserName());
        if (user == null) {
            throw new UsernameNotFoundException("Bu foydalanuvchi mavjud emaS");
        }
        String token = jwtTokenProvider.createToken(user.getUserName(), user.getRoles());
        Map<Object, Object> map = new HashMap<>();
        map.put("username", user.getUserName());
        map.put("tokent", token);
        return ResponseEntity.ok(map);
    }
}
