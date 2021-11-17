package ru.job4j.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    private CarCarcaseType carcaseType;

    @Enumerated(value = EnumType.STRING)
    private CarModel carModel;

    public static Car of(CarCarcaseType carcaseType, CarModel carModel) {
        Car car = new Car();
        car.carcaseType = carcaseType;
        car.carModel = carModel;
        return car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
