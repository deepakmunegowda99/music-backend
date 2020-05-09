package com.player.music.security;

import com.player.music.exception.ResourceNotFoundException;
import com.player.music.model.User;
import com.player.music.repository.UserRepository;
import com.player.music.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Let people login with either username or email
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id, Long time) {
        User user = userRepository.findByUserId(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        if (!sessionRepository.existsByUserAndLoggedinAndLoggedintime(user, "TRUE", time)) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        return UserPrincipal.create(user);
    }
}