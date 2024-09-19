package com.astrotenerife.cliente.services;

import com.astrotenerife.cliente.entities.User;
import com.astrotenerife.cliente.repository.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service

public class AuthServiceImp implements AuthService{

    private static final String SECRET_KEY = "AstroTenerife";

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String email, String password) {
        String originalString = password + SECRET_KEY;
        String sha256hex = Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();

        List<User> result = userRepository.findByEmailAndPassword(email, sha256hex);
        if (result.isEmpty()){
            return null;
        } else {
            return result.get(0);
        }
    }
}
