package com.utpconnect.groups_service.Controller;

import com.utpconnect.groups_service.model.Groups;
import com.utpconnect.groups_service.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Groups> getGroupById(@PathVariable Long id) {
        return groupRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Groups> createGroup(@RequestBody Groups group) {
        Groups savedGroup = groupRepository.save(group);
        return ResponseEntity.ok(savedGroup);
    }

    @GetMapping
    public List<Groups> getAllGroups() {
        return groupRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
