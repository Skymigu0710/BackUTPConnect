package com.utpconnectplatform.search_service.service;


import com.utpconnectplatform.search_service.client.UserClient;
import com.utpconnectplatform.search_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SearchCacheService {
    @Autowired
    private UserClient userClient;

    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        return userClient.getUserById(id);
    }
}
