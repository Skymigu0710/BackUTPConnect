package com.utpconnectplatform.authentication_service.model;

/*import com.utpconnectplatform.authentication_service.Service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final Userservice userService;

    @Autowired
    public CustomUserDetailsService(Userservice userService) {
        this.userService = userService;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}*/
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;  // Asumiendo que tienes un repositorio de usuarios

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario en el repositorio
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Convertir el UserEntity en un UserDetails (de Spring Security)
        return User.builder()
                .username(userEntity.getUser())
                .password(userEntity.getPass())  // Asegúrate de que la contraseña esté cifrada
                .authorities(userEntity.getRole())  // Aquí defines los roles o las autoridades del usuario
                .build();
    }
}

