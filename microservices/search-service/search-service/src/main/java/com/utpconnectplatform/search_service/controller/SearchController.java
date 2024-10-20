package com.utpconnectplatform.search_service.controller;

import com.utpconnectplatform.search_service.model.User;
import com.utpconnectplatform.search_service.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService userSearchService;

    @GetMapping("/{id}")
    public ResponseEntity<User> searchUser(@PathVariable Long id) {
        User user = userSearchService.searchUser(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}
