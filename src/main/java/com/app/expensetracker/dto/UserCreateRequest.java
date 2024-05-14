package com.app.expensetracker.dto;
import com.app.expensetracker.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class UserCreateRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;


    private String password;

    public User to(){
        return User.builder()
                .id(UUID.randomUUID().toString())
                .firstName(this.firstName)
                .lastName(this.lastName)
                .password(this.password)
                .build();
    }
}