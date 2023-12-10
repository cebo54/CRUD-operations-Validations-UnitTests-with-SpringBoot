package com.example.backend.dto;

import com.example.backend.entities.User;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
public final class UserViewDto implements Serializable {
    private static final long serialVersionUID=1L;
    private final String firstName;
    private final String lastName;

    private UserViewDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public static UserViewDto of(User user){
        return new UserViewDto(user.getFirstName(), user.getLastName());
    }
}
