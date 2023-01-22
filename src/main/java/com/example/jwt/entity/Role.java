package com.example.jwt.entity;

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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String role;


//    @ManyToMany(mappedBy = "role",cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
//    @ManyToMany(mappedBy = "role",cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @ManyToMany(mappedBy = "role",cascade = CascadeType.PERSIST)
    private List<User> users = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "Role_Permission",
            joinColumns = {@JoinColumn(name = "Role_ID")},
            inverseJoinColumns = {@JoinColumn(name = "Permission_ID")}
    )
    private List<Permission> permissions = new ArrayList<>();



    public Set<SimpleGrantedAuthority> getAuthority() {
        Set<SimpleGrantedAuthority> authorities = this.getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.getRole()));

        return authorities;
    }
}
