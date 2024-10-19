package com.utpconnect.groups_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="group_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_member;

    private String member_name;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id_groups")
    private Groups group;
}
