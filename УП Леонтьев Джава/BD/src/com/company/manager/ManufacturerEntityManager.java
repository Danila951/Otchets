package com.company.manager;

import com.company.Main;
import com.company.entity.ManufacturerEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerEntityManager {
    public static void insert(ManufacturerEntity manufacturer) throws SQLException {
        //получаем соединение с базой
        //все что в скобках try будет автоматически закрыто по выходу из блока (вместо c.close)
        try(Connection c = Main.getConnection())
        {
            //строковый запрос, вместе всех подставляемых данных ?
            String sql = "INSERT INTO Manufacturer(Name, StartDate) VALUES(?,?)";

            //получаем объект PreparedStatement, передаем в него строковый запрос
            //RETURN_GENERATED_KEYS нужен чтобы потом получить сгенерированные базой ключи (id...)
            PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //заменяем ? на данные
            ps.setString(1, manufacturer.getName());
            ps.setTimestamp(2, new Timestamp(manufacturer.getStartDate().getTime()));

            //выполняем запрос
            ps.executeUpdate();

            //получаем набор записей со сгенерированными базой ключами
            ResultSet keys = ps.getGeneratedKeys();
            //проверяем, что есть хотя бы 1 запись и переключаемся на нее
            if(((ResultSet) keys).next()) {
                //получаем из записи 1 ключ и устанавливаем его в сущность
                manufacturer.setID(keys.getInt(1));
                return;
            }

            //если из базы не вернулось ни 1 ключа, дропаем ошибку
            throw new SQLException("entity not added");
        }
    }

    public static ManufacturerEntity selectById(int id) throws SQLException
    {
        try(Connection c = Main.getConnection())
        {
            String sql = "SELECT * FROM Manufacturer WHERE id=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                return new ManufacturerEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("Name"),
                        resultSet.getTimestamp("StartDate")
                );
            }

            return null;
        }
    }

    public static List<ManufacturerEntity> selectAll() throws SQLException
    {
        try(Connection c = Main.getConnection())
        {
            String sql = "SELECT * FROM Manufacturer";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<ManufacturerEntity> list = new ArrayList<>();
            while(resultSet.next()) {
                list.add(new ManufacturerEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("Name"),
                        resultSet.getTimestamp("StartDate")
                ));
            }

            return list;
        }
    }

    public static void update(ManufacturerEntity manufacturer) throws SQLException
    {
        try(Connection c = Main.getConnection())
        {
            String sql = "UPDATE Manufacturer SET Name=?, StartDate=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, manufacturer.getName());
            ps.setTimestamp(2, new Timestamp(manufacturer.getStartDate().getTime()));

            ps.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException
    {
        try(Connection c = Main.getConnection())
        {
            String sql = "DELETE FROM Manufacturer WHERE id=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static void delete(ManufacturerEntity book) throws SQLException
    {
        delete(book.getID());
    }
}
