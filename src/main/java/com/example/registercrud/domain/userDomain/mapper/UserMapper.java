package com.example.registercrud.domain.userDomain.mapper;

import com.example.registercrud.domain.userDomain.dto.CreateUserDTO;
import com.example.registercrud.domain.userDomain.dto.UserDTO;
import com.example.registercrud.domain.userDomain.models.User;

public class UserMapper {
    public static UserDTO toDTO(User user){
        return UserDTO.builder()
                .balance(user.getBalance())
                .docNumber(user.getDocNumber())
                .id(user.getId())
                .personType(user.getPersonType())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }
    public static User toEntity(UserDTO userDTO){
        return User.builder()
                .balance(userDTO.getBalance())
                .docNumber(userDTO.getDocNumber())
                .personType(userDTO.getPersonType())
                .email(userDTO.getEmail())
                .fullName(userDTO.getFullName())
                .build();
    }
    public static User toEntityByCreateUserDTO(CreateUserDTO createUserDTO){
        return User.builder()
                .balance(createUserDTO.getBalance())
                .password(createUserDTO.getPassword())
                .docNumber(createUserDTO.getDocNumber())
                .personType(createUserDTO.getPersonType())
                .email(createUserDTO.getEmail())
                .fullName(createUserDTO.getFullName())
                .build();
    }
}
