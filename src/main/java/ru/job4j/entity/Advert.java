package ru.job4j.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "adverts")
@NoArgsConstructor
@Getter
@Setter
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private Boolean sold;

    @ManyToOne
    private User author;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Car> car = new HashSet<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "advertId")
    private Set<Image> images = new HashSet<>();

    public static Advert of(String description) {
        Advert advert = new Advert();
        advert.setDescription(description);
        return advert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advert advert = (Advert) o;
        return id == advert.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
