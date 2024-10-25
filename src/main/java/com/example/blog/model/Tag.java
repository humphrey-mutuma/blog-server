package com.example.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tags")
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "title is required!")
    @Size(min = 1, max = 20 , message = "Tag cannot be more than 20 character or less")
    @Column(nullable = false, updatable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;
//
//    @ManyToMany(mappedBy = "tags")
//    private Set<Article>  articles;
//

}
