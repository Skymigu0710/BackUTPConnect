package com.utpconnectplatform.follows_service.model;

import com.utpconnectplatform.follows_service.DTO.UserIdDto;
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
@Table(name = "follows")
public class Follows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrementable
    private Long id_follows;

    @Column(name = "follower_id")
    private Long followerId;

    @Column(name = "followed_id")
    private Long followedId; // Asegúrate de que esto sea un Long si no es una entidad.
    @Column(name = "message")
    private String message;
}
