package com.wora.qatrat7ayat.security.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthenticatedUser extends User{
    private String email;
    private String password;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
