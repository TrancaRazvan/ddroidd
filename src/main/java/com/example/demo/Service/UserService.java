package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public User registerEmployer(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return null;
        }
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User was not found"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
