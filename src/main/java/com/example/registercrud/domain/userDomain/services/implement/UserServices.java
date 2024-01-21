package com.example.registercrud.domain.userDomain.services.implement;

import com.example.registercrud.domain.transactionDomain.services.TransactionServices;
import com.example.registercrud.domain.userDomain.dto.UserDTO;
import com.example.registercrud.domain.userDomain.enums.PersonType;
import com.example.registercrud.domain.userDomain.mapper.UserMapper;
import com.example.registercrud.domain.userDomain.models.User;
import com.example.registercrud.domain.userDomain.repositories.UserRepository;
import com.example.registercrud.domain.userDomain.dto.CreateUserDTO;
import com.example.registercrud.domain.exceptions.RegisterCrudExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionServices transactionServices;

    public UserDTO findByDocNumber(String docNumber) throws RegisterCrudExceptions {
        var user = userRepository.findByDocNumberContains(docNumber);
        if(user==null){
            throw new RegisterCrudExceptions("User with this document number was not found");
        }
        return UserMapper.toDTO(user);
    }
    public List<UserDTO> findAll() throws RegisterCrudExceptions{
        List<User> allUsers = userRepository.findAll();
        if(allUsers.isEmpty()){
            throw new RegisterCrudExceptions("No users found");
        }
        var listDTOUsers = allUsers.stream().map(UserMapper::toDTO).toList();
        return listDTOUsers;
    }
    
    public UserDTO createUser(CreateUserDTO createUserDTO){
        var savedUser = userRepository.save(UserMapper.toEntityByCreateUserDTO(createUserDTO));
        return UserMapper.toDTO(savedUser);
    }

    public ResponseEntity<Void> deleteUser(String docNumber) throws RegisterCrudExceptions {
        var user = this.findByDocNumber(docNumber);
        userRepository.deleteById(user.getId());
        return ResponseEntity.noContent().build();
    }

    public UserDTO editUser(String docNumber, UserDTO userDTO) throws RegisterCrudExceptions{
        var userDTOFound = this.findByDocNumber(docNumber);
        userDTOFound.setBalance(userDTO.getBalance());
        userDTOFound.setEmail(userDTO.getEmail());
        userDTOFound.setDocNumber(userDTO.getDocNumber());
        userDTOFound.setFullName(userDTO.getFullName());
        userDTOFound.setPersonType(userDTO.getPersonType());
        var userEdited = userRepository.updateDocNumberAndBalanceAndFullNameAndEmailAndPersonTypeByDocNumber(userDTO.getDocNumber(), userDTO.getBalance(), userDTO.getFullName(), userDTO.getEmail(), userDTO.getPersonType(), docNumber);

        if (userEdited == 0) {
            throw new RegisterCrudExceptions("Error in update, try again later");
        }
        return userDTOFound;
    }

    public UserDTO findUserByIdService(Long id) throws RegisterCrudExceptions{
        var user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new RegisterCrudExceptions("User with this id was not found");
        }
        return UserMapper.toDTO(user.get());
    }

    public void validateTransaction(Long id, BigDecimal amount) throws RegisterCrudExceptions{
        var userDTOFound = this.findUserByIdService(id);
        if(userDTOFound.getBalance().compareTo(amount) < 0){
            throw new RegisterCrudExceptions("Insufficient funds");
        }
        if(userDTOFound.getPersonType().equals(PersonType.PESSOA_JURIDICA)){
            throw new RegisterCrudExceptions("Shopkeeper person cannot make transactions");
        }
    }

    public void saveUserService(UserDTO userDTO) throws RegisterCrudExceptions{
        var savedUser = userRepository.save(UserMapper.toEntity(userDTO));
    }

    private boolean isEmailValid(CreateUserDTO createUserDTO){
        var foundUserByEmail = userRepository.findByEmail(createUserDTO.getEmail());
        return foundUserByEmail==null;
    }

    private boolean isDocNumberValid(CreateUserDTO createUserDTO) throws RegisterCrudExceptions {
        var foundUserByDocNumber = this.findByDocNumber(createUserDTO.getDocNumber());
        return foundUserByDocNumber==null;
    }

    private boolean isUserValid(CreateUserDTO createUserDTO) throws RegisterCrudExceptions{
        return isDocNumberValid(createUserDTO) && isEmailValid(createUserDTO);
    }

}
