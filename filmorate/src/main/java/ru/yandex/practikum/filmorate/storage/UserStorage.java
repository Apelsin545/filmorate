package ru.yandex.practikum.filmorate.storage;

import ru.yandex.practikum.filmorate.model.*;

public interface UserStorage {
    User add(User user);
    User remove(User user);
    User update(User user);
}
