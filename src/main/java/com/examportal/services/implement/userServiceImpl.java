package com.examportal.services.implement;

import ch.qos.logback.core.net.SyslogOutputStream;

import com.examportal.model.User;
import com.examportal.model.userRole;
import com.examportal.repo.roleRepository;
import com.examportal.repo.userRepository;
import com.examportal.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class userServiceImpl implements UserService {

    @Autowired
    private com.examportal.repo.userRepository userRepository;

    @Autowired
    private com.examportal.repo.roleRepository roleRepository;
    @Override
    public User createUser(User user, Set<userRole> userRoles) throws Exception {

        User local =this.userRepository.findByuserName(user.getUserName());

        if(local !=null)
        {
            System.out.println("User already present");
            throw  new Exception("User already present");
        }
        else
        {
            for(userRole ur:userRoles)
            {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local=this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUser(String uname) {
        return this.userRepository.findByuserName(uname);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

}
