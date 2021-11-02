package com.company.entity;

import com.company.manager.Manager;

import java.util.Objects;

public class UserEntity {
    private int id;
    private String fio;
    private int yearOfBirth;
    private String profession;

    public UserEntity(int id, String fio, int yearOfBirth, String profession) {
        this.id = id;
        this.fio = fio;
        this.yearOfBirth = yearOfBirth;
        this.profession = profession;
    }

    public UserEntity(String fio, int yearOfBirth, String profession) {
        this(-1, fio, yearOfBirth, profession);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && yearOfBirth == that.yearOfBirth && Objects.equals(fio, that.fio) && Objects.equals(profession, that.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, yearOfBirth, profession);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", profession='" + profession + '\'' +
                '}';
    }
}
