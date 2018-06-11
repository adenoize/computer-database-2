package com.excilys.cdb.service.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.cdb.core.model.User;
import com.excilys.cdb.persistence.dao.UserDao;

@Service
public class UserAuthService implements UserDetailsService {

    private UserDao userDao;

    /**
     * @param userDao
     */
    public UserAuthService(UserDao userDao) {
        super();
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userDao.findUserInfoByUsername(username);
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).roles(user.getRole()).build();

    }

}