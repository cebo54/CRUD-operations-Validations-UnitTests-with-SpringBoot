package com.example.backend.Business;

import com.example.backend.DataAccess.UserDao;
import com.example.backend.dto.UserCreateDto;
import com.example.backend.dto.UserUpdateDto;
import com.example.backend.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserManagerTest {
    private UserDao userDao;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userDao = Mockito.mock(UserDao.class);
        userService =new UserManager(userDao);
    }
    public User generateUser(){
        return User.builder()
                .id(1L)
                .username("testUser")
                .firstName("testFirstName")
                .lastName("testLastName").build();
    }

    @Test
    void getUserById() {
        User user=generateUser();
        when(userDao.findById(anyLong())).thenReturn(Optional.of(user));
        var result = userService.getUserById(1L);
        verify(userDao,times(1)).findById(anyLong());
        assertEquals(result.getFirstName(),user.getFirstName());
        assertEquals(result.getLastName(),user.getLastName());

    }
    @Test
    void getUserById_whenCalledByNotExistUser_ItShouldThrowException(){
        when(userDao.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,()->userService.getUserById(1L));
    }

    @Test
    void getAllUsers() {
        User user=generateUser();
        when(userDao.findAll()).thenReturn(List.of(user));
        var result =userService.getAllUsers();
        verify(userDao,times(1)).findAll();
        assertEquals(result.size(),1);


    }


    @Test
    void createUser() {
        UserCreateDto userDto = UserCreateDto.builder()
                .userName("testUser")
                .firstName("testFirstName")
                .lastName("testLastName").build();

        User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getUserName());

        when(userDao.save(any(User.class))).thenReturn(user);

        var result = userService.createUser(userDto);

        assertEquals(result.getFirstName(), userDto.getFirstName());
        assertEquals(result.getLastName(), userDto.getLastName());

    }

    @Test
    void updateUser() {
        User user=generateUser();
        when(userDao.findById(1L)).thenReturn(Optional.of(user));
        UserUpdateDto updateDto=UserUpdateDto.builder()
                .firstName("testFirst")
                .lastName("testLast")
                .build();
        user.setFirstName(updateDto.getFirstName());
        user.setLastName(updateDto.getLastName());
        when(userDao.save(user)).thenReturn(user);
        var result=userService.updateUser(1L,updateDto);
        assertEquals(result.getFirstName(),updateDto.getFirstName());
        assertEquals(result.getLastName(),updateDto.getLastName());
    }

    @Test
    void deleteUser() {
    }

    @Test
    void slice() {
    }
}