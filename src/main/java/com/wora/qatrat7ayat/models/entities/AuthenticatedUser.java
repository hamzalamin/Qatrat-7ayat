package com.wora.qatrat7ayat.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthenticatedUser extends User {
    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Article> articles;
}
