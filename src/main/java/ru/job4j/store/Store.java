package ru.job4j.store;

import ru.job4j.entity.Advert;
import ru.job4j.entity.Car;
import ru.job4j.entity.CarModel;
import ru.job4j.entity.User;

import java.util.Collection;

public interface Store {
    void save(Object entity);

    //****** ADVERT

    Collection<Advert> findAllAdverts();

    /**
     * показать объявления за последний день
     */
    Collection<Advert> findAdvertsByYesterDay();

    /**
     * показать объявления определенной марки.
     */
    Collection<Advert> findAdvertsWithModel(CarModel model);

    Advert findAdvertById(int id);

    //****** Car

    Car findCarById(int id);

    //****** USER

    User findUserByEmail(String email);

}
