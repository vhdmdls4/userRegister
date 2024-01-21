package com.example.registercrud.domain.userDomain.controllers;

import com.example.registercrud.domain.userDomain.dto.CreateUserDTO;
import com.example.registercrud.domain.userDomain.dto.UserDTO;
import com.example.registercrud.domain.exceptions.RegisterCrudExceptions;
import com.example.registercrud.domain.userDomain.services.implement.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserServices userServices;

    @GetMapping("/search-by-docnumber/{docNumber}")
    public ResponseEntity<UserDTO> getUserByDocNumber(@PathVariable String docNumber){
        try {
            UserDTO userDTO = userServices.findByDocNumber(docNumber);
            return ResponseEntity.ok().body(userDTO);
        } catch (RegisterCrudExceptions e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        try{
            List<UserDTO> allUsers = userServices.findAll();
            return ResponseEntity.ok().body(allUsers);
        } catch (RegisterCrudExceptions e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid CreateUserDTO createUserDTO){
        var userCreated = userServices.createUser(createUserDTO);
        return ResponseEntity.ok().body(userCreated);
    }

    @DeleteMapping("/{docNumber}")
    public ResponseEntity<String> deleteUser(@PathVariable String docNumber) {
        try {
            userServices.deleteUser(docNumber);
            return ResponseEntity.ok("User deletion was successful");
        } catch (RegisterCrudExceptions e) {
            return ResponseEntity.badRequest().body("Failed to delete user: " + e.getMessage());
        }
    }

    @PutMapping("/{docNumber}")
    public ResponseEntity<UserDTO> editUser(@PathVariable String docNumber, @RequestBody UserDTO userDTO) {
        try {
            UserDTO userDTO1 = userServices.editUser(docNumber, userDTO);
            return ResponseEntity.ok(userDTO1);
        } catch (RegisterCrudExceptions e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id){
        try {
            var userFound = userServices.findUserByIdService(id);
            return ResponseEntity.ok().body(userFound);
        } catch (RegisterCrudExceptions e){
            return ResponseEntity.badRequest().build();
        }
    }
}
