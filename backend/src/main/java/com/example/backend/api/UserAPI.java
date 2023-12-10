package com.example.backend.api;

import com.example.backend.Business.UserManager;
import com.example.backend.Business.UserService;
import com.example.backend.dto.UserCreateDto;
import com.example.backend.dto.UserUpdateDto;
import com.example.backend.dto.UserViewDto;
import com.example.backend.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAPI {
    public UserService userService;
    @Autowired
    public UserAPI(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserViewDto>getUserById(@PathVariable Long id){
        final UserViewDto user = userService.geUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserViewDto>> getAllUsers(){
        final List<UserViewDto> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping("v1/user")
    public ResponseEntity<?>createUser(@Valid @RequestBody UserCreateDto userCreateDto){
        userService.createUser(userCreateDto);
        return ResponseEntity.ok(new GenericResponse("User Created"));
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserViewDto>updateUser(@PathVariable Long id,@RequestBody UserUpdateDto userUpdateDto){
        final UserViewDto user=userService.updateUser(id,userUpdateDto);
        return ResponseEntity.ok(user);

    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok(new GenericResponse("User Deleted"));
    }
    @GetMapping("/v1/user/slice")
    public ResponseEntity<List<UserViewDto>>slice(Pageable pageable){
        final List<UserViewDto> users=userService.slice(pageable);
        return ResponseEntity.ok(users);
    }
    
}
