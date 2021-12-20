package ru.job4j.controller;

import ru.job4j.entity.User;
import ru.job4j.store.HbmRepository;

import java.util.Optional;

public class AuthController {
    /**
     * Валидатор пользователя
     *
     * @param email
     * @param pass
     * @return найден и пароль совпал? user : null
     */
    public static User validateAuth(String email, String pass) {
        User user = HbmRepository.getInst().findUserByEmail(email);
        return Optional.ofNullable(user).filter(u -> u.getAccount().getEmail().equals(email) && u.getAccount().getPassword().equals(pass))
                .orElse(null);
    }
}
