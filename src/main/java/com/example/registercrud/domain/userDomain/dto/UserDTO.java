package com.example.registercrud.domain.userDomain.dto;

import com.example.registercrud.domain.userDomain.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private Long id;
    @NotNull(message = "name is obrigatory")
    private String fullName;
    private String email;
    @JsonIgnore
    private String password;
    private BigDecimal balance;
    private PersonType personType;
    private String docNumber;
}
