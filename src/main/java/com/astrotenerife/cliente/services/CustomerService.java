package com.astrotenerife.cliente.services;

import com.astrotenerife.cliente.entities.Customer;

import java.util.List;

/* Implementando una interfaz para el servicio de clientes CustomerServiceImp*/

public interface CustomerService {

    // Devolver un cliente específico
    Customer getCustomer(Integer id);

    // Devolver todos los clientes
    List<Customer> getAllCustomers();

    // Eliminar Cliente
    void deleteCustomer(Integer id);

    // Agregar Cliente
    void addCustomer(Customer customer);

    // Modificar Cliente
    void updateCustomer(Integer id, Customer updateCustomer);

    // Buscar Cliente
    List<Customer> searchCustomer(String email,String phone,String address,String firstname,String lastname);
}
