package ru.job4j.controller;

import ru.job4j.entity.Advert;
import ru.job4j.entity.User;
import ru.job4j.entity.embeddable.Account;
import ru.job4j.entity.embeddable.FullName;
import ru.job4j.store.HbmRepository;
import ru.job4j.store.Store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class AdvertController {
    public static Collection<Advert> getAllAdverts() {
        return HbmRepository.getInst().findAllAdverts();
    }
    // FIXME: 22.11.2021

    public static void main(String[] args) {
        Store store = HbmRepository.getInst();


        User elya = User.of("elya", Account.of("elya", "qqq"),
                FullName.of("Elya", "Kalyan", "Beauty"));

        HbmRepository.getInst().save(elya);


        List<Advert> a = new ArrayList<>(HbmRepository.getInst().findAllAdverts());
        a.sort(Comparator.comparing(Advert::getId));
        a.forEach(b -> System.out.println(b.getId()));
    }
}
