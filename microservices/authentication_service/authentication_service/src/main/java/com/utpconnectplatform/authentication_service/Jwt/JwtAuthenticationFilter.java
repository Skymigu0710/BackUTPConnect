package com.utpconnectplatform.authentication_service.Jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService; // Inyectamos el servicio JWT

    @Autowired
    private UserDetailsService userDetailsService; // Inyectamos el servicio para cargar el usuario

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extraer el token desde la solicitud
        final String token = getTokenFromRequest(request);

        // Verificar si el token existe y si no hay una autenticación ya presente
        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Extraer el nombre de usuario desde el token
            String username = jwtService.getUsernameFromToken(token);

            // Si obtenemos el nombre de usuario desde el token, validamos el token
            if (username != null) {

                // Cargamos los detalles del usuario desde la base de datos
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Validar el token con los detalles del usuario
                if (jwtService.isTokenValid(token, userDetails)) {

                    // Crear el objeto de autenticación basado en los detalles del usuario
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    // Establecer el contexto de seguridad de Spring con el usuario autenticado
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        // Continuamos con el siguiente filtro en la cadena
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Verificar si el encabezado tiene el token y comienza con "Bearer "
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Eliminar el prefijo "Bearer "
        }
        return null;
    }
}

