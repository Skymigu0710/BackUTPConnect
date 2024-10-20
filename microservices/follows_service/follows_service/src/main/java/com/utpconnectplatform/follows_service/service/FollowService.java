package com.utpconnectplatform.follows_service.service;

import com.utpconnectplatform.follows_service.DTO.UserIdDto;
import com.utpconnectplatform.follows_service.FollowsServiceApplication;
import com.utpconnectplatform.follows_service.model.Follow_request;
import com.utpconnectplatform.follows_service.model.Follows;
import com.utpconnectplatform.follows_service.repository.FollowRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    private final FollowRepository followRepository  ;

    public FollowService (FollowRepository followRepository){
        this.followRepository=followRepository;
    }
    @Autowired
    private UserService userService; // Servicio para obtener datos de usuarios

    public Follows saveFollow(Follow_request followRequest){
        if (followRequest.getFollower() == null || followRequest.getFollowed() == null) {
            throw new IllegalArgumentException("Follower or followed user cannot be null");
        }
        //obtenemos los datos del usuario que se sigue
        UserIdDto follower= userService.getUserById(followRequest.getFollower().getId_users());
        UserIdDto followed = userService.getUserById(followRequest.getFollowed().getId_users());
        // Verificar si se obtuvieron los usuarios correctamente
        if (follower == null || followed == null) {
            throw new IllegalArgumentException("Follower or followed user not found");
        }
        Follows follows = new Follows();
        follows.setFollowerId(follower.getId_users());
        follows.setFollowedId(followed.getId_users());
        follows.setMessage(followRequest.getMessage());
        //guarda el objeto en la base de datos
        return followRepository.save(follows);
    }



}
