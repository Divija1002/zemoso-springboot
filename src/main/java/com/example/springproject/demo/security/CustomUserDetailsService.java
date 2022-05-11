package com.example.springproject.demo.security;

import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.logging.Logger;

public class CustomUserDetailsService implements UserDetailsService
{
    private static Logger logger=Logger.getLogger(String.valueOf(CustomUserDetails.class));

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userService.findByUsername(username);
        logger.info(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
