package com.yildiz.soulsprinkle.service;

import com.yildiz.soulsprinkle.model.User;
import com.yildiz.soulsprinkle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User>getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User>getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional <User>getUserByFirstnameAndLastname(String firstname, String lastname){
        return userRepository.findByFirstnameAndLastname(firstname, lastname);
    }

    public Optional<User>getUserByEmailAndUsername(String email, String username){
        return userRepository.findByEmailAndUsername(email, username);
    }

    public List<User> getUserWithPicture() {
        return userRepository.findByPictureUrlIsNotNull();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public List<User>getAllUsers(){
        return userRepository.findAll();
    }
}
