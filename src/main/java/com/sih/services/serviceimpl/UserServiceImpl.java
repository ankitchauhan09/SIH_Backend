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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    public UserDto registerUser(UserDto userDto) {
        UserRole role = this.userRoleRepo.findByRoleName(userDto.getRoleName()).get();
        User user = this.modelMapper.map(userDto, User.class);
        user.setRole(role);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User savedUser = this.userRepo.save(user);
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).get();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        User saved = this.userRepo.save(user);
        return this.modelMapper.map(saved, UserDto.class);
    }

    @Override
    public void deleteUser(UserDto userDto) {
        this.userRepo.delete(this.modelMapper.map(userDto, User.class));
    }

    @Override
    public void deleteUserById(Integer userId) {
        User user = this.userRepo.findById(userId).get();
        this.userRepo.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = this.userRepo.findAll();
        return allUsers.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Integer userId) {
        return this.modelMapper.map(
                this.userRepo.findById(userId).get(),
                UserDto.class
        );
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = this.userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return this.modelMapper.map(user, UserDto.class);
    }
}
