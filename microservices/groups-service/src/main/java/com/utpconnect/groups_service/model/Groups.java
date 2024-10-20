package com.utpconnect.groups_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_groups;

    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<GroupMember> members;
}
