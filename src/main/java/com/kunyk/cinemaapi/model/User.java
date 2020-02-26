package com.kunyk.cinemaapi.model;

import com.kunyk.cinemaapi.messaging.dto.UserKafkaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode
@Entity(name = "User")
@Table(name = "user")
public class User {

    public static final String CURRENT_USER = "current_user_attribute";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "age", nullable = false)
    private Integer age;

    public UserKafkaDto toKafkaDto() {
        return UserKafkaDto.builder()
                .id(id)
                .age(age)
                .build();
    }
}
