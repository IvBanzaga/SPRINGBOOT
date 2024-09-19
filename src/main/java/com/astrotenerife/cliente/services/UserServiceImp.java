// El service UserService se encarga de la lógica. En este caso, se encarga de gestionar los clientes.

package com.astrotenerife.cliente.services;

import com.astrotenerife.cliente.entities.User;
import com.astrotenerife.cliente.repository.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

public class UserServiceImp implements UserService {

    private static final String SECRET_KEY = "AstroTenerife";
    @Autowired
    private UserRepository repository;

    /*private List<User> list = new ArrayList<>();

    public UserServiceImp() {
        User c1 = new User();
        c1.setId(1);
        c1.setFirstname("Lucas");
        c1.setLastname("Gonzalez");
        c1.setEmail("i.bazaga@gmail.com");
        c1.setPhone("922123456");
        c1.setAddress("Calle Falsa 123");

        User c2 = new User();
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
    public User getUser(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElse(null);

        /*for (User user : list) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }*/

    }

    // Devolver todos los clientes
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Iterable<User> users = repository.findAll();
        for (User user : users) {
            list.add(user);
        }
        return list;
    }

    // Eliminar Cliente
    public void deleteUser(Integer id) {

        /*for (User user : list) {
            if (user.getId().equals(id)) {
                list.remove(user);
                break;
            }

        }*/

        repository.deleteById(id);
    }

    // Agregar Cliente
    public void addUser(User user) {
        //list.add(user);

        // Encriptar contraseña
        String originalString = user.getPassword() + SECRET_KEY;
        String sha256hex = Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
        user.setPassword(sha256hex);
        repository.save(user);
    }

    // Modificar Cliente
    public void updateUser(Integer id, User updateUser) {
        /*for (User user : list) {
            if (user.getId().equals(id)) {
                list.remove(user);
                updateUser.setId(id);
                list.add(updateUser);
                break;
            }
        }*/
        updateUser.setId(id);
        repository.save(updateUser);
    }

    // Buscar Cliente
    public List<User> searchUser(String email, String phone, String address, String firstname, String lastname)
    {
        /*List<User> searchResult = new ArrayList<>();
        for (User user : list) {
            if ((email == null || (user.getEmail() != null && user.getEmail().contains(email)))
                    && (phone == null || (user.getPhone() != null && user.getPhone().contains(phone)))
                    && (address == null || (user.getAddress() != null && user.getAddress().contains(address)))
                    && (firstname == null || (user.getFirstname() != null && user.getFirstname().contains(firstname)))
                    && (lastname == null || (user.getLastname() != null && user.getLastname().contains(lastname)))) {
                searchResult.add(user);
            }
        }
        return searchResult;
    }*/
        return repository.findByEmailContainingOrPhoneContainingOrAddressContainingOrFirstnameContainingOrLastnameContaining(email, phone, address, firstname, lastname);

    }
}
