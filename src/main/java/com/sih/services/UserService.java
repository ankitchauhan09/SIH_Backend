package com.sih.services;

import com.sih.dto.UserDto;
import com.sih.entities.User;

import java.util.List;

public interface UserService {

    public UserDto registerUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto, Integer userId);
    public void deleteUser(UserDto userDto);
    public void deleteUserById(Integer userId);
    public List<UserDto> getAllUsers();
    public UserDto getUser(Integer userId);
    public UserDto getUserByEmail(String email);
}
