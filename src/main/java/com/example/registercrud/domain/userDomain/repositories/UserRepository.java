package com.example.registercrud.domain.userDomain.repositories;

import com.example.registercrud.domain.userDomain.enums.PersonType;
import com.example.registercrud.domain.userDomain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Transactional
    @Modifying
    @Query("""
            update User u set u.docNumber = ?1, u.balance = ?2, u.fullName = ?3, u.email = ?4, u.personType = ?5
            where u.docNumber = ?6""")
    int updateDocNumberAndBalanceAndFullNameAndEmailAndPersonTypeByDocNumber(String docNumber, BigDecimal balance, String fullName, String email, PersonType personType, String docNumber1);
    User findByDocNumberContains(String docNumber);
}
