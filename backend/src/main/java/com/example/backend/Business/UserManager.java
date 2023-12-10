package com.example.backend.Business;

import com.example.backend.DataAccess.UserDao;
import com.example.backend.dto.UserCreateDto;
import com.example.backend.dto.UserUpdateDto;
import com.example.backend.dto.UserViewDto;
import com.example.backend.entities.User;
import com.example.backend.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService{
    private UserDao userDao;
    @Autowired
    public UserManager(UserDao userDao) {

        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public UserViewDto geUserById(Long id) {
        final User user=userDao.findById(id).
                orElseThrow(()-> new NotFoundException("Not found Exception"));

        return UserViewDto.of(user);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<UserViewDto> getAllUsers() {
        return userDao.findAll().stream().map(UserViewDto::of).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserViewDto createUser(UserCreateDto userCreateDto) {
        final User user=userDao.save(new User(userCreateDto.getFirstName(),userCreateDto.getLastName(),userCreateDto.getUserName()));
        return UserViewDto.of(user);
    }

    @Override
    @Transactional
    public UserViewDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        final User user=userDao.findById(id).orElseThrow(()-> new NotFoundException("Not Found Exception"));
        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());
        final User uptatedUser=userDao.save(user);
        return UserViewDto.of(uptatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        final User user=userDao.findById(id).orElseThrow(()-> new NotFoundException("Not Found Exception"));
        userDao.deleteById(user.getId());
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<UserViewDto> slice(Pageable pageable) {
        return userDao.findAll(pageable).stream().map(UserViewDto::of).collect(Collectors.toList());
    }

}
