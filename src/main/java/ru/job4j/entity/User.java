package ru.job4j.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.job4j.entity.embeddable.Account;
import ru.job4j.entity.embeddable.FullName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@Access(value = AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    /**
     * login, но для теста AttributeOverride и конфликта с Fullname - называется именно name
     */
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Embedded
    private Account account;

    @Getter
    @Setter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "FIO_name")),
            @AttributeOverride(name = "surname", column = @Column(name = "FIO_surname")),
            @AttributeOverride(name = "patronymic", column = @Column(name = "FIO_patronymic"))
    })
    private FullName fullName;

    public static User of(String name, Account account, FullName fullName) {
        User user = new User();
        user.name = name;
        user.account = account;
        user.fullName = fullName;
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
