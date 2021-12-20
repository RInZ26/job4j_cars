package ru.job4j.entity.embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class FullName {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String patronymic;

    @Getter
    @Setter
    private String surname;

    public static FullName of(String name, String patronymic, String surname) {
        FullName fullName = new FullName();
        fullName.name = name;
        fullName.patronymic = patronymic;
        fullName.surname = surname;
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FullName)) {
            return false;
        }
        FullName fullName = (FullName) o;
        return Objects.equals(name, fullName.name) && Objects.equals(patronymic,
                fullName.patronymic)
                && Objects.equals(surname, fullName.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, patronymic, surname);
    }
}
