package ru.yandex.practikum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class Friend {
    User userFrom;
    User userTo;
    Boolean isAccepted;
}
