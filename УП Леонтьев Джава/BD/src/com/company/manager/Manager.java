package com.company.manager;

import com.company.entity.UserEntity;
import com.company.Main;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manager {
    public static void insert(UserEntity user) throws SQLException {
        //получаем соединение с базой
        //все что в скобках try будет автоматически закрыто по выходу из блока (вместо c.close)
        try(Connection c = Main.getConnection())
        {
            //строковый запрос, вместе всех подставляемых данных ?
            String sql = "INSERT INTO User_Danila_golovachev(fio, yearOfBirth, profession) VALUES(?,?,?)";

            //получаем объект PreparedStatement, передаем в него строковый запрос
            //RETURN_GENERATED_KEYS нужен чтобы потом получить сгенерированные базой ключи (id...)
            PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //заменяем ? на данные
            ps.setString(1, user.getFio());
            ps.setInt(2, user.getYearOfBirth());
            ps.setString(3, user.getProfession());

            //выполняем запрос
            ps.executeUpdate();

            //получаем набор записей с сгенерированными базой ключами
            ResultSet keys = ps.getGeneratedKeys();
            //проверяем, что есть хотя бы 1 запись и переключаемся на нее
            if(((ResultSet) keys).next()) {
                //получаем из записи 1 ключ и устанавливаем его в сущность
                user.setId(keys.getInt(1));
                return;
            }

            //если из базы не вернулось ни 1 ключа, дропаем ошибку
            throw new SQLException("entity not added");
        }
    }

    public static UserEntity selectById(int id) throws SQLException
    {
        try(Connection c = Main.getConnection())
        {
            String sql = "SELECT * FROM User_Danila_golovachev WHERE id=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                return new UserEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("fio"),
                        resultSet.getInt("yearOfBirth"),
                        resultSet.getString("profession")
                );
            }

            return null;
        }
    }

    public static List<UserEntity> selectAll() throws SQLException
    {
        try(Connection c = Main.getConnection())
        {
            String sql = "SELECT * FROM User_Danila_golovachev";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<UserEntity> list = new ArrayList<>();
            while(resultSet.next()) {
                list.add(new UserEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("fio"),
                        resultSet.getInt("yearOfBirth"),
                        resultSet.getString("profession")
                ));
            }

            return list;
        }
    }

    public static void update(UserEntity book) throws SQLException
    {
        try(Connection c = Main.getConnection())
        {
            String sql = "UPDATE User_Danila_golovachev SET fio=?, yearOfBirth=?, profession=? WHERE id=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, book.getFio());
            ps.setInt(2, book.getYearOfBirth());
            ps.setString(3, book.getProfession());
            ps.setInt(4, book.getId());

            ps.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException
    {
        try(Connection c = Main.getConnection())
        {
            String sql = "DELETE FROM User_Danila_golovachev WHERE id=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static void delete(UserEntity book) throws SQLException
    {
        delete(book.getId());
    }
}
