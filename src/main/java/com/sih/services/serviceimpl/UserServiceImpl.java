package com.sih.services.serviceimpl;

import com.sih.dto.UserDto;
import com.sih.entities.User;
import com.sih.entities.UserRole;
import com.sih.repositories.UserRepo;
import com.sih.repositories.UserRoleRepo;
import com.sih.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
        UserRole role = userRoleRepo.findByRoleName(userDto.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + userDto.getRoleName()));

        User user = modelMapper.map(userDto, User.class);
        user.setRole(role);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setContact(userDto.getContact());
        if (userDto.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        }
        UserRole role = userRoleRepo.findByRoleName(userDto.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + userDto.getRoleName()));
        user.setRole(role);

        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public void deleteUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepo.delete(user);
    }

    @Override
    public void deleteUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        userRepo.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return modelMapper.map(user, UserDto.class);
    }
}
