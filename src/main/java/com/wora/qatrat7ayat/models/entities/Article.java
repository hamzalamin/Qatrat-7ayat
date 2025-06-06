package com.wora.qatrat7ayat.models.entities;


import com.wora.qatrat7ayat.models.enumes.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "articles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
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

    @Column(name = "published_at")
    private LocalDateTime publishedAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne()
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @NotNull
    @JoinColumn(name = "city_id")
    private City city;

    private String imageUrl;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<ArticleTag> articleTags;

    @Column(name = "status", columnDefinition = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @PrePersist
    protected void onCreate(){
        publishedAt = LocalDateTime.now();
        updatedAt = publishedAt;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
