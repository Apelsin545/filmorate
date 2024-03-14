package ru.yandex.practikum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practikum.filmorate.model.User;
import ru.yandex.practikum.filmorate.service.FilmService;
import ru.yandex.practikum.filmorate.service.UserService;
import ru.yandex.practikum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practikum.filmorate.storage.UserStorage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.getUsers();
    }

    @PostMapping("/user")
    public User create(@Valid @RequestBody User user) throws ValidationException {
        if (user.getEmail().contains("@") && !user.getLogin().isBlank() && user.getBirthday().isBefore(LocalDate.now())) {
            if (!userService.getUsers().contains(user)) log.info("Добавлен новый пользователь: " + user);
            else log.info("Обновлен новый пользователь: " + user);

            if (user.getName().isBlank()) user.setName(user.getLogin());
            userService.createUser(user);
        } else {
            if (!userService.getUsers().contains(user)) log.error("Ошибка добавления пользователя: " + user);
            else log.error("Ошибка обновления пользователя: " + user);

            throw new ValidationException("неправильное тело запроса");
        }
        return user;
    }

    @PutMapping("users/{id}/friends/{friendId}")
    public boolean addFriend(@PathVariable int id, @PathVariable int friendId) {
        return userService.addFriend(id, friendId);
    }

    @DeleteMapping("users/{id}/friends/{friendId}")
    public boolean removeFriend(@PathVariable int id, @PathVariable int friendId) {
        return userService.removeFriend(id, friendId);
    }

    @GetMapping("users/{id}/friends")
    public Set<Integer> getFriends(@PathVariable int id) {
        return userService.getFriends(id);
    }

    @GetMapping("users/{id}/friends/common/{otherId}")
    public Set<Integer> getCommonFriends(@PathVariable int id, @PathVariable int otherId) {
        return userService.getCommonFriends(id, otherId);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> illegalBodyUser(final ValidationException e) {
        return Map.of("errorMessage", e.getMessage());
    }
}
