package com.example.jwt.entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //    @Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String password;
    private Boolean isEnabled;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "user_roel",
            joinColumns = {@JoinColumn(name = "user_ID")},
            inverseJoinColumns = {@JoinColumn(name = "Role_ID")}
    )
    private List<Role> role = new ArrayList<>();


    public Set<SimpleGrantedAuthority> grantedAuthorities() {
        return role.stream().flatMap(r -> r.getAuthority().stream())
                     .collect(Collectors.toSet());

    }
}
