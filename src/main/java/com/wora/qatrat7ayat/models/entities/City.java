package com.wora.qatrat7ayat.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "citys")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city_name")
    @NotBlank
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Article> articles;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<User> users;
}
