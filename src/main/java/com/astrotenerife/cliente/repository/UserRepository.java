package com.astrotenerife.cliente.repository;

import com.astrotenerife.cliente.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByEmailContainingOrPhoneContainingOrAddressContainingOrFirstnameContainingOrLastnameContaining(
            String email, String phone, String address, String firstname, String lastname);


    @Query("select c from User c where c.email = :email and c.password = :password")
    List<User> findByEmailAndPassword(String email, String password);

}
