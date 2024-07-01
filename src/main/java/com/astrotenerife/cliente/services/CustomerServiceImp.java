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

public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    /*private List<Customer> list = new ArrayList<>();

    public CustomerServiceImp() {
        Customer c1 = new Customer();
        c1.setId(1);
        c1.setFirstname("Lucas");
        c1.setLastname("Gonzalez");
        c1.setEmail("i.bazaga@gmail.com");
        c1.setPhone("922123456");
        c1.setAddress("Calle Falsa 123");

        Customer c2 = new Customer();
        c2.setId(2);
        c2.setFirstname("Juan");
        c2.setLastname("Perez");
        c2.setEmail("andres@gmail.com");
        c2.setPhone("922123456");
        c2.setAddress("Calle Falsa 123");

        list.add(c1);
        list.add(c2);
    }*/

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

        /*for (Customer customer : list) {
            if (Objects.equals(customer.getId(), id)) {
                return customer;
            }
        }*/

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

        /*for (Customer customer : list) {
            if (customer.getId().equals(id)) {
                list.remove(customer);
                break;
            }

        }*/

        repository.deleteById(id);
    }

    // Agregar Cliente
    public void addCustomer(Customer customer) {
        //list.add(customer);
        repository.save(customer);
    }

    // Modificar Cliente
    public void updateCustomer(Integer id, Customer updateCustomer) {
        /*for (Customer customer : list) {
            if (customer.getId().equals(id)) {
                list.remove(customer);
                updateCustomer.setId(id);
                list.add(updateCustomer);
                break;
            }
        }*/
        updateCustomer.setId(id);
        repository.save(updateCustomer);
    }

    // Buscar Cliente
    public List<Customer> searchCustomer(String email, String phone, String address, String firstname, String lastname)
    {
        /*List<Customer> searchResult = new ArrayList<>();
        for (Customer customer : list) {
            if ((email == null || (customer.getEmail() != null && customer.getEmail().contains(email)))
                    && (phone == null || (customer.getPhone() != null && customer.getPhone().contains(phone)))
                    && (address == null || (customer.getAddress() != null && customer.getAddress().contains(address)))
                    && (firstname == null || (customer.getFirstname() != null && customer.getFirstname().contains(firstname)))
                    && (lastname == null || (customer.getLastname() != null && customer.getLastname().contains(lastname)))) {
                searchResult.add(customer);
            }
        }
        return searchResult;
    }*/
        return repository.findByEmailContainingOrPhoneContainingOrAddressContainingOrFirstnameContainingOrLastnameContaining(email, phone, address, firstname, lastname);

    }
}
