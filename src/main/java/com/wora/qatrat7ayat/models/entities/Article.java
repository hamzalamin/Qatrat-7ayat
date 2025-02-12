package com.wora.qatrat7ayat.models.entities;


import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "articles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "content")
    private String content;

    @NotBlank
    @Column(name = "published_at")
    private String publishedAt;

    @ManyToOne()
    @NotNull
    @JoinColumn(name = "user_id")
    private AuthenticatedUser user;

    @ManyToOne()
    @NotNull
    @JoinColumn(name = "city_id")
    private City city;
}
