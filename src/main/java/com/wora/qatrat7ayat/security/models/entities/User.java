package com.wora.qatrat7ayat.security.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "psudo_name")
    private String psudoName;

    @NotBlank
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "blood_type")
    private String bloodType;

    @NotBlank
    @Column(name = "city")
    private String city;

    @NotBlank
    @Column(name = "availability_message")
    private String availabilityMessage;

    @NotBlank
    @Column(name = "created_at")
    private String createdAt;

    @NotBlank
    @Column(name = "updated_at")
    private String updatedAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
