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
        repository.deleteById(id);
    }

    // Agregar Cliente
    public void addUser(User user) {
        String originalString = user.getPassword() + SECRET_KEY;
        String sha256hex = Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
        user.setPassword(sha256hex);
        repository.save(user);
    }

    // Modificar Cliente
    public void updateUser(Integer id, User updateUser) {

        updateUser.setId(id);
        repository.save(updateUser);
    }

    // Buscar Cliente
    public List<User> searchUser(String email, String phone, String address, String firstname, String lastname)
    {
        return repository.findByEmailContainingOrPhoneContainingOrAddressContainingOrFirstnameContainingOrLastnameContaining(email, phone, address, firstname, lastname);

    }
}
