package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.UserInfoDetails;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println(username);
    	if (username == null) {
            throw new UsernameNotFoundException("Username cannot be null");
        }
    	try {
            Optional<UserInfo> userDetail = repository.findByUsername(username);

            return userDetail
                .map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        } catch (UsernameNotFoundException ex) {
            System.out.println("User not found with username: " + username);
            throw ex;  // Rethrow the exception
        } catch (Exception ex) {
            System.out.println("An unexpected error occurred: " + ex.getMessage());
            throw new UsernameNotFoundException("An unexpected error occurred", ex);
        }
    }

    public String addUser(UserInfo userInfo) {
        // Encode password before saving the user
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }
}
