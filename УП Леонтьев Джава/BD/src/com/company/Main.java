package com.company;

import com.company.entity.ManufacturerEntity;
import com.company.entity.ProductEntity;
import com.company.entity.UserEntity;
import com.company.manager.ManufacturerEntityManager;
import com.company.manager.ProuctEntityManager;
import com.company.manager.UserEntytiManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        /*ProductEntity book = new ProductEntity("Danila", 23435.00, "dvdtvtdvd", "ahahahahhaha.jpg",true,25);

        try {

            System.out.println(book);
            ProuctEntityManager.insertProduct(book);
            System.out.println(book);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            System.out.println(book);
            ProuctEntityManager.deletePr(142);
            System.out.println(ProuctEntityManager.selectByIdPr(book.getID()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        ManufacturerEntity man = new ManufacturerEntity("Loh",new Date(123));

        try {
            System.out.println(man);
            ManufacturerEntityManager.insert(man);
            System.out.println(man);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(ManufacturerEntityManager.selectById(66));
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

