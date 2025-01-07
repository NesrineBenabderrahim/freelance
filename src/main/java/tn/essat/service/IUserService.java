package tn.essat.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import tn.essat.model.User;

public interface IUserService extends UserDetailsService {
    User findByUsername(String username);
    User findById(Integer id);
    List<User> getAllUsers(); 

    User getCurrentUser(); 

}
