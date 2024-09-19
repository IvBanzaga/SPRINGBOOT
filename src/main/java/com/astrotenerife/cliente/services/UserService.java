package com.astrotenerife.cliente.services;

import com.astrotenerife.cliente.entities.User;

import java.util.List;

/* Implementando una interfaz para el servicio de clientes UserServiceImp*/

public interface UserService {

    // Devolver un cliente específico
    User getUser(Integer id);

    // Devolver todos los clientes
    List<User> getAllUsers();

    // Eliminar Cliente
    void deleteUser(Integer id);

    // Agregar Cliente
    void addUser(User user);

    // Modificar Cliente
    void updateUser(Integer id, User updateUser);

    // Buscar Cliente
    List<User> searchUser(String email,String phone,String address,String firstname,String lastname);
}
