package com.wora.qatrat7ayat.security.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, columnDefinition = "role")
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<AuthenticatedUser> users;

    @Override
    public String getAuthority() {
        return name;
    }

    public Role(String name){
        this.name = name;
    }
}
