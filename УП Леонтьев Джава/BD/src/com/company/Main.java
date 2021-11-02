package com.company;

import com.company.entity.UserEntity;
import com.company.manager.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        UserEntity book = new UserEntity("Danila", 1995, "programmist");

        try {

            System.out.println(book);
            Manager.insert(book);
            System.out.println(book);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            System.out.println(book);
            Manager.delete(2);
            System.out.println(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





    public static Connection getConnection() throws SQLException
    {
        // протокол     адрес           порт название базы   пользователь         пароль
        return DriverManager.getConnection("jdbc:mysql://116.202.236.174:3306/DemoExam", "DemoExam", "DemoExam");

    }


}

