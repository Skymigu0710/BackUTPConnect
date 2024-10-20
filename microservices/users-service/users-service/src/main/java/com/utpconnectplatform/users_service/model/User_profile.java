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
@Table(name = "user_profile")
public class User_profile {

    @Id
    private long id_usersp; // Esta será la misma que id_users en Users

    private String bio_text;
    private String studies;
    private String birthday;
    private String profile_img_url;
    private String cover_img_url;

    // Relación uno a uno con Users
    @OneToOne
    @MapsId // Esto indica que el id de esta entidad es el mismo que el de la entidad relacionada
    @JoinColumn(name = "id_usersp", referencedColumnName = "id_users") // Foreign key
    private Users user; // Objeto que representa la relación con la entidad Users
}

