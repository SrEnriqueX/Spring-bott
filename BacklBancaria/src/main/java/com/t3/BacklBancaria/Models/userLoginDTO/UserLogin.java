package com.t3.BacklBancaria.Models.userLoginDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {

    @Email
    @NotBlank
    private String correo;

    @NotBlank
    private String password;
}
