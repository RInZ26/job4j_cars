package ru.job4j.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
    private int id;

    private String description;

    private Boolean sold;

    @Temporal(TemporalType.DATE)
    private Date publishDate;

    @ManyToOne
    private User author;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Car> cars = new HashSet<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "advertId")
    private Set<Image> images = new HashSet<>();

    public static Advert of(String description) {
        Advert advert = new Advert();
        advert.setDescription(description);
        advert.publishDate = new Date(System.currentTimeMillis());
        return advert;
    }

    public void addCar(Car car) {
        if (car == null) {
            throw new NullPointerException();
        }
        cars.add(car);
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
