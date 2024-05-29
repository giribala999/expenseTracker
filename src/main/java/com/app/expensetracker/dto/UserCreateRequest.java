package com.app.expensetracker.dto;

import com.app.expensetracker.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

/**
 * UserCreateRequest is a Data Transfer Object (DTO) for creating a new User.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class UserCreateRequest {

    @NotBlank //The first name of the user cannot be blank.
    private String firstName;

    @NotBlank //The last name of the user cannot be blank.
    private String lastName;

    private String password;

    public User to() {
        return User.builder()
                .user_id(UUID.randomUUID().toString())
                .firstName(this.firstName)
                .lastName(this.lastName)
                .password(this.password)
                .build();
    }
}