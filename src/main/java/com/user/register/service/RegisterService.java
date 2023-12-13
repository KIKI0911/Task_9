package com.user.register.service;

import com.user.register.controller.request.RegisterRequest;
import com.user.register.entity.User;
import com.user.register.exception.UserAlreadyExistsException;
import com.user.register.exception.UserNotFoundException;
import com.user.register.mapper.RegisterMapper;
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

    public User updateUser(Integer id, RegisterRequest updatedUser) {
        User existingUser = this.registerMapper.findUser(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAddressId(updatedUser.getAddressId());
        existingUser.setAge(updatedUser.getAge());

        int affectedRows = registerMapper.updateUser(existingUser);

        if (affectedRows <= 0) {
            // 更新が失敗した場合は例外をスローするか、エラーハンドリングを行う
            throw new RuntimeException("Failed to update user with id: " + id);
        }
        return existingUser;
    }
}
