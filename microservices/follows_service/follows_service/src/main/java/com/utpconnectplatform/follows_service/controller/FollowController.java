package com.utpconnectplatform.follows_service.controller;

import com.utpconnectplatform.follows_service.model.Follow_request;
import com.utpconnectplatform.follows_service.model.Follows;
import com.utpconnectplatform.follows_service.service.FollowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/follow")
@CrossOrigin("*") //todos los puertos pueden acceder

public class FollowController {
    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }
    @PostMapping
    public ResponseEntity<Follows> saveFollow (@RequestBody Follow_request followsreques){
        return new ResponseEntity<>(followService.saveFollow(followsreques), HttpStatus.CREATED );
    }
}
