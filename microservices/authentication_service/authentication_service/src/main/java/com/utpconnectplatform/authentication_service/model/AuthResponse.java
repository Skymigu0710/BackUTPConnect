package com.utpconnectplatform.authentication_service.model;

import com.utpconnectplatform.authentication_service.DTO.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SecondaryRow;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
    private UserDto userDetails;
}
