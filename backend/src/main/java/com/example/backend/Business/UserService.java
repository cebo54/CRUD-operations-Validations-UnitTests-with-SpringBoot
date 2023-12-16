package com.example.backend.Business;

import com.example.backend.dto.UserCreateDto;
import com.example.backend.dto.UserUpdateDto;
import com.example.backend.dto.UserViewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserViewDto getUserById(Long id);
    List<UserViewDto> getAllUsers();
    UserViewDto createUser(UserCreateDto userCreateDto);

    UserViewDto updateUser(Long id, UserUpdateDto userUpdateDto);

    void deleteUser(Long id);

    List<UserViewDto> slice(Pageable pageable);
}
