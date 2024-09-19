package com.astrotenerife.cliente.services;

import com.astrotenerife.cliente.entities.User;

public interface AuthService {
    User login (String email, String password);
}
