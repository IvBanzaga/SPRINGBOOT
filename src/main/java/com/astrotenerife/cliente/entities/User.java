package com.astrotenerife.cliente.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
// También podemos utilizar @Data para tener todos los anteriores

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;

    // Solo se puede cargar, no se puede leer
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
