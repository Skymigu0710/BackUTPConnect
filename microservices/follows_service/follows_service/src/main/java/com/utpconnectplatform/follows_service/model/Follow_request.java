package com.utpconnectplatform.follows_service.model;

import com.utpconnectplatform.follows_service.DTO.UserIdDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Follow_request {
    private UserIdDto follower;
    private UserIdDto followed;
    private String message;
}
