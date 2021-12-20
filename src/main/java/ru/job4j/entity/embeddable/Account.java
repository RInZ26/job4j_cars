package ru.job4j.entity.embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class Account {
    @Column(unique = true)
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    public static Account of(String email, String password) {
        Account account = new Account();
        account.email = email;
        account.password = password;

        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
