package com.utpconnectplatform.users_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")

public class Roles {
    @Id//
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Para que sea autoincrementable
    private long id;
    private String role_name;
    private String description;
}
