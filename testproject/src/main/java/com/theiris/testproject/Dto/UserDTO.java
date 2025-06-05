package com.theiris.testproject.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserDTO {

    private String id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "phone is required")
    @Length(min = 10,max = 11)
    private String phone;


    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long and include uppercase, lowercase, number, and special character"
    )
    private String password;

}
