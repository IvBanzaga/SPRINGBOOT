package com.astrotenerife.cliente.repository;

import com.astrotenerife.cliente.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findByEmailContainingOrPhoneContainingOrAddressContainingOrFirstnameContainingOrLastnameContaining(
            String email, String phone, String address, String firstname, String lastname);
}
