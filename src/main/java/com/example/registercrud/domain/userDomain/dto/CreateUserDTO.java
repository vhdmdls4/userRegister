package com.example.registercrud.domain.userDomain.dto;

import com.example.registercrud.domain.userDomain.enums.PersonType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateUserDTO implements Serializable {
    @NotEmpty(message = "Name must be larger than 3 characters")
    @NotNull(message = "Name of the user is obrigatory")
    private String fullName;
    private String email;
    private String password;
    private BigDecimal balance;
    private PersonType personType;
    private String docNumber;

}
