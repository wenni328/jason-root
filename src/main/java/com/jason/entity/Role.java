package com.jason.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roleName;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<Permission> permissions;

}
