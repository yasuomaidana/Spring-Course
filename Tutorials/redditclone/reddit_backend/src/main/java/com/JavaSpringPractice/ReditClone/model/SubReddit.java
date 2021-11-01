package com.JavaSpringPractice.ReditClone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
@Entity
public class SubReddit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Community Name is required")
    private String name;
    @NotBlank(message="Description is required")
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
