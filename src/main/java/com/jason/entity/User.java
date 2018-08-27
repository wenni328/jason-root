package com.jason.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Role> roles;
}
