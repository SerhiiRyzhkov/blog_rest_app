package com.had0uken.blog.model.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mediafiles")
public class MediaFile implements Serializable {
    @Serial
    private static final long serialVersionUID = 4749415838819372120L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private Double size;

    @Enumerated(EnumType.STRING)
    @JsonProperty("mediatype")
    private MediaType mediaType;
    @JsonProperty("URL")
    private String URL;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaFile mediaFile = (MediaFile) o;
        return id.equals(mediaFile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
