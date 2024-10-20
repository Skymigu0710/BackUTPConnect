package com.utpconnectplatform.search_service.service;

import com.utpconnectplatform.search_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    private SearchCacheService userCacheService;

    public User searchUser(Long id) {
        return userCacheService.getUserById(id);
    }
}
