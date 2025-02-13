package com.wora.qatrat7ayat.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "citys")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name")
    @NotBlank
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Article> articles;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<User> users;

    public City(String name){
        this.cityName = name;
    }

    public City(@NotNull Long id) {
            this.id = id;
    }
}
