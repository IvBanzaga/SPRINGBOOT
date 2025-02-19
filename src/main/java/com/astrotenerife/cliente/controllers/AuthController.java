package com.astrotenerife.cliente.controllers;

import com.astrotenerife.cliente.dto.RequestLogin;
import com.astrotenerife.cliente.entities.User;
import com.astrotenerife.cliente.services.AuthService;
import com.astrotenerife.cliente.utils.JwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // Anotación para indicar que es un controlador
@RequestMapping("/api") // Anotación para indicar la ruta de acceso
@CrossOrigin(origins ="*") // Anotación para permitir el acceso a la API desde cualquier origen

// Clase para autenticar usuarios
public class AuthController {

    @Autowired
    private AuthService service; // Inyectar dependencia

    @PostMapping("/auth/login")
    public String login(@RequestBody RequestLogin request) {  // Método para autenticar/iniciar sesión de los usuarios
        String email = request.getEmail();
        String password = request.getPassword();
        User user = service.login(email, password);
        String token = JwtUtil.generateToken(user);
        return token;
    }
}

















    // Original

    /*
public LoginResponse login(@RequestBody RequestLogin request) {
        String email = request.getEmail();
        String password = request.getPassword();
        User user = service.login(email, password);

        String token = JwtUtil.generateToken(user);
        return new LoginResponse(token, user);

    }

    // Clase interna LoginResponse
    @Data
    public static class LoginResponse {
        private String token;
        private User user;

        public LoginResponse(String token, User user) {
            this.token = token;
            this.user = user;
        }
    }
    *
    * }
    * */
