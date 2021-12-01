package com.company.entity;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@Data
public class UserEntity {
    private int id;
    private String fio;
    private int yearOfBirth;
    private String profession;

    public UserEntity(String fio, int yearOfBirth, String profession) {
        this(-1, fio, yearOfBirth, profession);
    }


}
