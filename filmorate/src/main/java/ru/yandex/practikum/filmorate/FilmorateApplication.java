package ru.yandex.practikum.filmorate;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.image.DataBufferUShort;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@SpringBootApplication
public class FilmorateApplication {

	public static void main(String[] args) {
        SpringApplication.run(FilmorateApplication.class, args);


    }

}
