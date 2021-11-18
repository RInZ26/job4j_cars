package ru.job4j.store;

import ru.job4j.entity.Advert;
import ru.job4j.entity.Car;
import ru.job4j.entity.CarModel;
import ru.job4j.entity.Image;

import java.util.Collection;

public interface Store {
    /**
     * показать объявления за последний день
     */
    Collection<Advert> findAdvertsByYesterDay();

    /**
     * показать объявления с фото
     */
    Collection<Advert> findAdvertsWithImage();

    /**
     * показать объявления определенной марки.
     */
    Collection<Advert> findAdvertsWithModel(CarModel model);

    void save(Object entity);

    Car findCarById(int id);

    Image findImageById(int id);

    Advert findAdvertById(int id);
}
