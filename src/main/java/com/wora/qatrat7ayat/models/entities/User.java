package com.wora.qatrat7ayat.models.entities;

import com.wora.qatrat7ayat.models.enumes.BloodType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
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
    private BloodType bloodType;

    @Column(name = "availability_message")
    private String availabilityMessage;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;
}
