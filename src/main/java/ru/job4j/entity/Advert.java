package ru.job4j.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.job4j.entity.embeddable.Image;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "adverts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @Type(type = "yes_no")
    private boolean sold;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Date publishDate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @Getter
    private User author;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY)
    @Getter
    private Car car;

    @ElementCollection
    @JoinColumn(name = "advertId")
    @Getter
    @Setter
    private Set<Image> images = new HashSet<>();

    public static Advert of(String description, Car car, User user) {
        if (car == null || user == null) {
            throw new NullPointerException();
        }

        Advert advert = new Advert();
        advert.setDescription(description);
        advert.publishDate = new Date(System.currentTimeMillis());
        advert.car = car;
        advert.author = user;

        return advert;
    }

    public void addImage(Image image) {
        if (image == null) {
            throw new NullPointerException();
        }
        images.add(image);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advert advert = (Advert) o;
        return id == advert.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
