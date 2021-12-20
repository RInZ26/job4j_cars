package ru.job4j.entity.embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Image {
    @Getter
    @Setter
    private String fileName;
}
