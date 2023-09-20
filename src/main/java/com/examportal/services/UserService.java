package com.examportal.services;

import org.springframework.stereotype.Service;

import com.examportal.model.User;
import com.examportal.model.userRole;

import java.util.Set;

public interface UserService {
    public User createUser(User user, Set<userRole> userRoles) throws Exception;
    public User getUser(String uname);
    public void deleteUser(Long userId);
}
