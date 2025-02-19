// El service CustomerService se encarga de la lógica. En este caso, se encarga de gestionar los clientes.

package com.astrotenerife.cliente.services;

import com.astrotenerife.cliente.entities.Customer;
import com.astrotenerife.cliente.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

// Implementación de la interfaz CustomerService, es un contrato que se debe cumplir.
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    // Obtener datos remotos
        public String getRemoteData(String url) {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        }

    // Devolver un cliente específico
    public Customer getCustomer(Integer id) {
        Optional<Customer> customer = repository.findById(id);
        return customer.orElse(null);

    }

    // Devolver todos los clientes
    public List<Customer> getAllCustomers() {
            List<Customer> list = new ArrayList<>();
        Iterable<Customer> customers = repository.findAll();
        for (Customer customer : customers) {
            list.add(customer);
        }
        return list;
    }

    // Eliminar Cliente
    public void deleteCustomer(Integer id) {

        repository.deleteById(id);
    }

    // Agregar Cliente
    public void addCustomer(Customer customer) {
        repository.save(customer);
    }

    // Modificar Cliente
    public void updateCustomer(Integer id, Customer updateCustomer) {
        updateCustomer.setId(id);
        repository.save(updateCustomer);
    }

    // Buscar Cliente
    public List<Customer> searchCustomer(String email, String phone, String address, String firstname, String lastname)
    {
        return repository.findByEmailContainingOrPhoneContainingOrAddressContainingOrFirstnameContainingOrLastnameContaining(email, phone, address, firstname, lastname);

    }
}
