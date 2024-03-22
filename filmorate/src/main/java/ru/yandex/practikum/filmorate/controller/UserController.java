package ru.yandex.practikum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practikum.filmorate.model.User;
import ru.yandex.practikum.filmorate.service.UserService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public User getUserById(@RequestParam int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public void create(@Valid @RequestBody User user) throws ValidationException {
        if (user.getEmail().contains("@") && !user.getLogin().isBlank() && user.getBirthday().isBefore(LocalDate.now())) {
            log.info("Добавлен новый пользователь: " + user);

            if (user.getName().isBlank()) user.setName(user.getLogin());
            userService.createUser(user);
        } else {
            log.error("Ошибка добавления пользователя: " + user);

            throw new ValidationException("неправильное тело запроса");
        }

    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam int id) {
        userService.removeUser(id);
    }

    @PutMapping("users/{id}/friends/{friendId}")
    public void addFriend(@PathVariable int id, @PathVariable int friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("users/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable int id, @PathVariable int friendId) {
        userService.deleteFriend(id, friendId);
    }

    @GetMapping("users/{id}/friends")
    public List<User> getFriends(@PathVariable int id) {
        return userService.getFriends(id);
    }

    @GetMapping("users/{id}/friends/common/{otherId}")
    public Set<Integer> getCommonFriends(@PathVariable int id, @PathVariable int otherId) {
        return new HashSet<>();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> illegalBodyUser(final ValidationException e) {
        return Map.of("errorMessage", e.getMessage());
    }
}
