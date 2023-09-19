package com.had0uken.blog.model.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.had0uken.blog.model.Tag;
import com.had0uken.blog.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;


    @ToString.Exclude
    @ManyToMany(mappedBy = "likedPosts", cascade = CascadeType.PERSIST)
    private Set<User> likedByUsers = new HashSet<>();

    @ToString.Exclude
    @ManyToMany(mappedBy = "repostedPosts", cascade = CascadeType.PERSIST)
    private Set<User> repostedByUsers = new HashSet<>();



    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty("mediafiles")
    private List<MediaFile> mediaFiles = new ArrayList<>();

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();


    private LocalDate created;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
