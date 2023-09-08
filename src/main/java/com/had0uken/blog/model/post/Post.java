package com.had0uken.blog.model.post;

import com.had0uken.blog.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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
    @JoinColumn(name = "post_id")
    private List<Photo> photos = new ArrayList<>();


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
