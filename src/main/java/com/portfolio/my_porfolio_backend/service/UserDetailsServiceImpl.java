package com.portfolio.my_porfolio_backend.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portfolio.my_porfolio_backend.model.User;
import com.portfolio.my_porfolio_backend.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

/**
 * UserDetailsServiceImpl
 */

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements IUserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true,
                Collections.emptyList());
    }

}
