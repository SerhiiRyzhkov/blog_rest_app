package com.had0uken.blog.model.post;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.had0uken.blog.model.Tag;
import com.had0uken.blog.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stories")
public class Stories implements Serializable {

    @Serial
    private static final long serialVersionUID = 4361816262692500716L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;


    @ToString.Exclude
    @ManyToMany(mappedBy = "likedStories", cascade = CascadeType.PERSIST)
    private Set<User> likedByUsers = new HashSet<>();

    @ToString.Exclude
    @ManyToMany(mappedBy = "repostedStories", cascade = CascadeType.PERSIST)
    private Set<User> repostedByUsers = new HashSet<>();

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "stories_tags",
            joinColumns = @JoinColumn(name = "stories_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();



    @OneToOne
    @JoinColumn(name = "media_file_id")
    @JsonProperty("mediafile")
    private MediaFile mediaFile;

    private LocalDate created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stories stories = (Stories) o;
        return Objects.equals(id, stories.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
