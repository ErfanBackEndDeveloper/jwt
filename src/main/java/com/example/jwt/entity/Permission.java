package com.example.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String permission;


//    @ManyToMany(mappedBy = "permissions",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @ManyToMany(mappedBy = "permissions",cascade = CascadeType.PERSIST)
    private List<Role> roles = new ArrayList<>();
}
