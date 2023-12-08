package com.user.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {
    private final RegisterMapper registerMapper;

    @Autowired
    public RegisterService(RegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }

    public User findUser(Integer id) {
        Optional<User> user_id = this.registerMapper.findUser(id);
        if (user_id.isPresent()) {
            return user_id.get();
        } else {
            throw new UserNotFoundException("user:" + id + " not found");
        }
    }

    public User findUserFromAddress(Integer id, Integer addressId) {
        Optional<User> user_IdAndAddress = this.registerMapper.findByIdAndAddressId(id, addressId);
        if (user_IdAndAddress.isPresent()) {
            return user_IdAndAddress.get();
        } else {
            throw new UserNotFoundException("user:" + id + " not found");
        }
    }

    public User insert(String name, String email, Integer addressId, Integer age) {
        Optional<User> userOptional = this.registerMapper.findByEmail(email);
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("email already exists");
        }
        User user = new User(null, name, email, addressId, age);
        registerMapper.insert(user);
        return user;
    }
}