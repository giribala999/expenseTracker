package com.app.expensetracker.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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
}
