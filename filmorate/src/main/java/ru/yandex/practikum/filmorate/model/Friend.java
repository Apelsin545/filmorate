package ru.yandex.practikum.filmorate.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Friend {
    User userFrom;
    User userTo;
    Boolean isAccepted;
}
