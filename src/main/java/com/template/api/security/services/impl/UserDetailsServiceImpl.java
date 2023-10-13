package com.template.api.security.services.impl;

import com.template.api.security.repositories.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (Strings.isBlank(username)) {
            throw new UsernameNotFoundException("Invalid user.");
        }

        final String uname = username.trim();
        var optionalUser = userRepository.findByUsername(uname);
        var user = optionalUser.orElseThrow(() -> new UsernameNotFoundException(String.format("Given user(%s) not found.", uname)));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole().getAuthority())
                .build();
    }
}
