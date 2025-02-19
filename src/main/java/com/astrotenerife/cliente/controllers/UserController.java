/* UserController utiliza UserService para realizar las operaciones CRUD de los clientes.*/

package com.astrotenerife.cliente.controllers;

import com.astrotenerife.cliente.entities.User;
import com.astrotenerife.cliente.services.UserServiceImp;
import com.astrotenerife.cliente.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {

    // La anotación autowired se utiliza para inyectar dependencias en Spring, en este caso, la clase UserService.
    @Autowired
    private UserServiceImp service;


    // Traer Devolver un cliente específico
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Integer id) {

        return service.getUser(id);
    }

    // Traer Devolver todos los clientes
    @GetMapping("/user")
    public List<User> getAllUsers() {

        //String userId = JwtUtil.getUserByToken(token);

        return service.getAllUsers();
    }

    // Eliminar Cliente
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Integer id) {
        service.deleteUser(id);
    }

    // Agregar Cliente
    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        service.addUser(user);
    }

    // Modificar Cliente
    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User updateUser) {
        service.updateUser(id, updateUser);
    }

    // Buscar Cliente
    @GetMapping("/user/search")
    public List<User> searchUser(@RequestParam(name = "email", required = false) String email,
                                         @RequestParam(name = "phone", required = false) String phone, @RequestParam(name = "address", required = false) String address, @RequestParam(name = "firstname", required = false) String firstname, @RequestParam(name = "lastname", required = false) String lastname) {
        return service.searchUser(email, phone, address, firstname, lastname);
    }

}
