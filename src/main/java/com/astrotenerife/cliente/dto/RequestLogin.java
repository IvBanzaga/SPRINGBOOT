package com.astrotenerife.cliente.dto;

import lombok.Data;

@Data
public class RequestLogin {
    private String email;
    private String password;
}
