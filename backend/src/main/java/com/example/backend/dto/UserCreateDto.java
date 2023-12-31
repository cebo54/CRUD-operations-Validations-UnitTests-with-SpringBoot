package com.example.backend.dto;

import com.example.backend.validator.UniqueUserName;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
public class UserCreateDto {
    @NotNull(message = "{backend.constraints.username.NotNull.message}")
    @Size(min = 3 , max=24 ,message = "{backend.constraints.username.Size.message}")
    @UniqueUserName
    private String userName;
    @NotNull(message = "{backend.constraints.firstname.NotNull.message}")
    @Size(min=3,max=32,message =  "{backend.constraints.firstname.Size.message}")
    private String firstName;
    @NotNull(message = "{backend.constraints.lastname.NotNull.message}")
    @Size(min=3,max=32,message =  "{backend.constraints.lastname.Size.message}")
    private String lastName;
}
