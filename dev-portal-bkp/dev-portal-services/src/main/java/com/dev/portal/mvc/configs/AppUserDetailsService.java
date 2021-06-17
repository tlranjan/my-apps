package com.dev.portal.mvc.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import com.dev.portal.dao.client.UserAuthDaoClient;
import com.dev.portal.dao.client.UserDetailsDaoClient;

public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    MongoTemplate mongoTemplate;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AppUserDetails(username,
                PasswordEncoderFactories.createDelegatingPasswordEncoder()
                        .encode(new UserAuthDaoClient(mongoTemplate).getUserAuthModel(username).getPassword()),
                new String[]{new UserDetailsDaoClient(mongoTemplate).getUserDetailsModelByUsername(username).getUserRole()});
    }

}