package com.utpconnectplatform.search_service.service;

import com.utpconnectplatform.search_service.client.UserClient;
import com.utpconnectplatform.search_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchService {
    @Autowired
    private UserClient userClient;

    public User searchUser(Long id) {
        return userClient.getUserById(id);
    }
}
