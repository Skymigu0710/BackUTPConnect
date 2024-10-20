package com.utpconnectplatform.users_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Users implements  Serializable {
    private static final long serialVersionUID = 1L;

    public Users(String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_users;
    @ManyToOne
    @JoinColumn(name = "id_roles", referencedColumnName = "id") // Definir la foreign key
    private Roles id_roles;
    private String name;
    private String last_name;
    private String username;
    private String email;
    private String password;
}
