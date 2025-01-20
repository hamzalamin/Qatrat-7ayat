package com.wora.qatrat7ayat.models.entities;

import com.wora.qatrat7ayat.models.enumes.BloodType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "blood_type")
    private BloodType bloodType;

    @NotBlank
    @Column(name = "availability_message")
    private String availabilityMessage;

    @NotBlank
    @Column(name = "created_at")
    private String createdAt;

    @NotBlank
    @Column(name = "updated_at")
    private String updatedAt;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;
}
