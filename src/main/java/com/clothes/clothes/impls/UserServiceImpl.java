package com.clothes.clothes.impls;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.clothes.dtos.UpdateUserDTO;
import com.clothes.clothes.entities.User;
import com.clothes.clothes.repositories.UserRepository;
import com.clothes.clothes.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository uRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return uRepository.findByEmail(email);
    }

    @Override
    public void deleteUserById(Long id) {
        uRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return uRepository.findById(id);
    }

    @Override
    public User findUserByIdOrThrow(Long id) {
        return uRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
    }

    @Override
    public User updateUser(UpdateUserDTO userDto, User user) {

        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        uRepository.save(user);

        return user;
    }
}
