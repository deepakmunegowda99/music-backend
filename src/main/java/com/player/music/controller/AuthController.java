package com.player.music.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.player.music.model.Role;
import com.player.music.model.RoleName;
import com.player.music.model.User;
import com.player.music.model.Session;

import com.player.music.payload.LoginRequest;
import com.player.music.payload.SignUpRequest;
import com.player.music.repository.RoleRepository;
import com.player.music.repository.SessionRepository;
import com.player.music.repository.UserRepository;
import com.player.music.response.ApiResponse;
import com.player.music.response.JwtAuthenticationResponse;
import com.player.music.security.JwtTokenProvider;
import com.player.music.security.UserPrincipal;
import com.player.music.util.ListEntities;
import com.player.music.util.PreSignedURL;
import com.player.music.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Transactional
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    Util utils;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

        UserPrincipal userPrin = (UserPrincipal) authentication.getPrincipal();

        User user = new User().userId(userPrin.getId());
        Date date = new Date();

        if (sessionRepository.existsByUserAndLoggedinAndStatus(user, "TRUE", "1")) {
            Session sess = sessionRepository.findByUserAndLoggedinAndStatus(user, "TRUE", "1");
            if (sess != null && date.getTime() < sess.getloggedouttime()) {
                return new ResponseEntity(new ApiResponse(false, "User already logged In"), HttpStatus.BAD_REQUEST);
            }
            sess.setStatus("0");
            sess.setloggedin("FALSE");
            sessionRepository.save(sess);
        }

        Long epochDate = date.getTime();

        String jwt = tokenProvider.generateToken(authentication, date);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Session session = new Session(user, "TRUE", epochDate, epochDate + jwtExpirationInMs);
        sessionRepository.save(session);

        Map<String, Object> map = new HashMap<>();
        map.put("email", userPrin.getEmail());
        map.put("username", userPrin.getName());
        map.put("role", userPrin.getAuthorities());

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, map));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }
        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getEmail(),
                signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
        if (userRole == null) {
            return ResponseEntity.ok().body(new ApiResponse(true, "Role not set"));
        }
        user.setRole(userRole);

        User result = userRepository.save(user);

        return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {

        UserPrincipal userPrin = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Boolean result = utils.SignOut(userPrin.getId());
        if (!result) {
            return new ResponseEntity(new ApiResponse(false, "Try again"), HttpStatus.FAILED_DEPENDENCY);
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().body(new ApiResponse(true, "User logged out successfully"));
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(@RequestParam(name = "key") String key) {
        if (key == null) {
            return ResponseEntity.ok().body(new ApiResponse(false, "key cannot be empty"));
        }
        String url = null;
        try {
            url = PreSignedURL.generateURL(key);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Unable to genearate presigned url", Arrays.asList(url)));
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "successfully generated signed url", Arrays.asList(url)));

    }

    @GetMapping("/data")
    public ResponseEntity<?> checkData(@RequestParam(name = "key", required = false) String key) {

        Object listEntities = new ListEntities().ListOfEntites(key);
        if (listEntities == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(true, "unable to retrive list", listEntities));
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "successfully retrived list", listEntities));

    }

}