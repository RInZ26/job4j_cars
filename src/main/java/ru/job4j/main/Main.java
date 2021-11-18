package ru.job4j.main;

import ru.job4j.entity.*;
import ru.job4j.store.AdRepository;
import ru.job4j.store.Store;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Store store = AdRepository.getInst();

        Car mers = Car.of(CarCarcaseType.SEDAN, CarModel.MERCEDES);
        Car lada = Car.of(CarCarcaseType.HATCHBACK, CarModel.LADA);
        Image firstImage = new Image();
        Image secondImage = new Image();

        Advert firstAdvert = Advert.of("merc lada with images but not today");
        Advert secondAdvert = Advert.of("lada noImages but today");

        store.save(mers);
        store.save(lada);
        store.save(firstImage);
        store.save(secondImage);

        firstAdvert.addCar(store.findCarById(1));
        firstAdvert.addCar(store.findCarById(2));
        firstAdvert.addImage(store.findImageById(1));
        firstAdvert.addImage(store.findImageById(2));

        secondAdvert.addCar(store.findCarById(2));

        firstAdvert.setPublishDate(Date.from(Instant.now().minus(10, ChronoUnit.DAYS)));

        store.save(firstAdvert);
        store.save(secondAdvert);

        System.out.println("findAdvertsWithImage");
        store.findAdvertsWithImage().forEach(a -> System.out.println(a.getDescription()));

        System.out.println("findAdvertsByYesterDay");
        store.findAdvertsByYesterDay().forEach(a -> System.out.println(a.getDescription()));

        System.out.println("findAdvertsWithModel LADA");
        store.findAdvertsWithModel(CarModel.LADA)
                .forEach(a -> System.out.println(a.getDescription()));
        System.out.println("findAdvertsWithModel MERCEDES");
        store.findAdvertsWithModel(CarModel.MERCEDES)
                .forEach(a -> System.out.println(a.getDescription()));
    }
}
