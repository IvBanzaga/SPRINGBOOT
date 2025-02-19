package com.astrotenerife.cliente.config;

import com.astrotenerife.cliente.utils.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

// Anotación @Component, la cual indica que la clase es un componente de Spring
@Component
public class CustomAccessFilter implements Filter {

    public CustomAccessFilter() {
    }

    // Método doFilter, el cual se encarga de filtrar las peticiones que llegan al servidor
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean authorized = isAuthorized(request);
        if (authorized) {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(null, null, Collections.emptyList()));
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    // Método isAuthorized, el cual se encarga de verificar si la petición que llega al servidor está autorizada
    // Si no está autorizada, se devuelve false y no muestra ningún dato, en caso contrario, se devuelve true
    private boolean isAuthorized(HttpServletRequest request) {

        // Se obtiene la URL actual
        String currentUrl = request.getRequestURI();
        String[] availableUrl = new String[] {
                "/api/auth/login",
                "/api/auth/register"
        };

        // Se comprueba si la URL actual está en la lista de URLs disponibles
        boolean authorized = Arrays.asList(availableUrl).contains(currentUrl);
        boolean isApiResource = currentUrl.startsWith("/api/");

        // Si no comienza con /api/ o está en la lista de URLs disponibles, se devuelve true
        if (authorized || !isApiResource) {
            return true;
        }

        // Si no, se comprueba si el token es válido
        try {
            String token = request.getHeader("Authorization");
            String userId = JwtUtil.getUserByToken(token);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}

