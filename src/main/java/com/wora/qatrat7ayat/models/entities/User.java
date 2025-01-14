package com.wora.qatrat7ayat.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "first_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;


    @Column(name = "psudo_name")
    private String psudoName;


    @Column(name = "phone")
    private String phone;


    @Column(name = "blood_type")
    private String bloodType;


    @Column(name = "city")
    private String city;


    @Column(name = "availability_message")
    private String availabilityMessage;


    @Column(name = "created_at")
    private String createdAt;


    @Column(name = "updated_at")
    private String updatedAt;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Article> articles;
}
