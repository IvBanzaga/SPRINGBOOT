package com.astrotenerife.cliente.repository;

import com.astrotenerife.cliente.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByEmailContainingOrPhoneContainingOrAddressContainingOrFirstnameContainingOrLastnameContaining(
            String email, String phone, String address, String firstname, String lastname);
}
