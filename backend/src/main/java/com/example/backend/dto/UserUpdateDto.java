package com.example.backend.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class UserUpdateDto {

    private String firstName;
    private String lastName;
}
