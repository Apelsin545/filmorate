package ru.yandex.practikum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practikum.filmorate.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class UserController {
    Map<Integer, User> users = new HashMap<>();

    @GetMapping("/users")
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @PostMapping("/user")
    public User create(@Valid @RequestBody User user) throws ValidationException {
        if (user.getEmail().contains("@") && !user.getLogin().isBlank() && user.getBirthday().isBefore(LocalDate.now())) {
            if (users.get(user.getId()) == null) log.info("Добавлен новый пользователь: " + user);
            else log.info("Обновлен новый пользователь: " + user);

            if (user.getName().isBlank()) user.setName(user.getLogin());
            users.put(user.getId(), user);
        } else {
            if (users.get(user.getId()) == null) log.error("Ошибка добавления пользователя: " + user);
            else log.error("Ошибка обновления пользователя: " + user);

            throw new ValidationException("неправильное тело запроса");
        }
        return user;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> illegalBodyUser(final ValidationException e) {
        return Map.of("errorMessage", e.getMessage());
    }
}
