package com.wora.qatrat7ayat.models.entities;

import com.wora.qatrat7ayat.models.enumes.BloodType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;


    @Column(name = "psudo_name")
    private String psudoName;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "blood_type", columnDefinition = "blood_type")
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_suspended")
    private boolean isSuspended = true;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Action> actions;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

}
