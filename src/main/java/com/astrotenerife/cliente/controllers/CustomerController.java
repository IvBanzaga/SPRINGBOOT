/* CustomerController utiliza CustomerService para realizar las operaciones CRUD de los clientes.*/

package com.astrotenerife.cliente.controllers;

import com.astrotenerife.cliente.entities.Customer;
import com.astrotenerife.cliente.services.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CustomerController {

    // La anotación autowired se utiliza para inyectar dependencias en Spring, en este caso, la clase CustomerService.
    @Autowired
    private CustomerServiceImp service;

    // Traer Devolver un cliente específico
    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Integer id) {

        return service.getCustomer(id);
    }

    // Traer Devolver todos los clientes
    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    // Eliminar Cliente
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        service.deleteCustomer(id);
    }

    // Agregar Cliente
    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer customer) {
        service.addCustomer(customer);
    }

    // Modificar Cliente - Actualizar
    @PutMapping("/customer/{id}")
    public void updateCustomer(@PathVariable Integer id, @RequestBody Customer updateCustomer) {
        service.updateCustomer(id, updateCustomer);
    }

    // Buscar Cliente
    @GetMapping("/customer/search")
    public List<Customer> searchCustomer(@RequestParam(name = "email", required = false) String email,
                                         @RequestParam(name = "phone", required = false) String phone, @RequestParam(name = "address", required = false) String address, @RequestParam(name = "firstname", required = false) String firstname, @RequestParam(name = "lastname", required = false) String lastname) {
        return service.searchCustomer(email, phone, address, firstname, lastname);
    }

}
