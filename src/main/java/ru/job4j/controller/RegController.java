package ru.job4j.controller;

import ru.job4j.entity.User;
import ru.job4j.entity.embeddable.Account;
import ru.job4j.entity.embeddable.FullName;
import ru.job4j.store.HbmRepository;

import java.util.Objects;

public class RegController {

    /**
     * Валидация регистрации пользователя на предмет совпадения почтовых ящиков
     *
     * @param email
     * @return true, если valid
     */
    public static boolean validateMail(String email) {
        return Objects.isNull(HbmRepository.getInst().findUserByEmail(email));
    }

    /**
     * Валидация паролей (отдельно, потому что можно накрутить логику
     * проверки наличия цифрк и прочей шляпы)
     *
     * @param passFirst
     * @param passSecond
     * @return true, если valid
     */
    public static boolean validatePasswords(String passFirst, String passSecond) {
        return passFirst.equals(passSecond);
    }


    /**
     * Прослойка, чтобы не вызывать Store из сервлета
     */
    public static void saveUser(String email, String login, String name, String surname, String patronymic, String password) {
        HbmRepository.getInst().save(User.of(login, Account.of(email, password), FullName.of(name, patronymic, surname)));
    }
}
