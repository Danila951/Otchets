package com.company.manager;

import java.sql.*;

import com.company.Main;
import com.company.entity.ProductEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProuctEntityManager {
    public static void insertProduct(ProductEntity product) throws SQLException {

        try(Connection c = Main.getConnection()) {
            String sql = "INSERT INTO Product(Title, Cost, Description, MainImagePath, IsActive, ManufacturerID) VALUE(?,?,?,?,?,?)";

            PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getTitle());
            ps.setDouble(2, product.getCost());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getMainImagePath());
            ps.setBoolean(5, product.getIsActive());
            ps.setInt(6, product.getManufacturerID());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if(keys.next()) {
                product.setID(keys.getInt(1));
                return;
            }

            throw new SQLException("ID потерялся, его там нет");
        }
    }

    public static ProductEntity selectByIdPr(int id) throws SQLException {

        try(Connection c = Main.getConnection()) {
            String sql = "Select * FROM Product WHERE ID = ?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);


            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                return new ProductEntity(
                    resultSet.getInt("ID"),
                    resultSet.getString("Title"),
                    resultSet.getDouble("Cost"),
                    resultSet.getString("Description"),
                    resultSet.getString("MainImagePath"),
                    resultSet.getBoolean("IsActive"),
                    resultSet.getInt("ManufacturerID")
                );

            }
            return null;
        }
    }

    public static List<ProductEntity> selectAllPr() throws SQLException {

        try(Connection c = Main.getConnection()) {
            String sql = "Select * FROM Product";

            Statement ps = c.createStatement();

            List<ProductEntity> array= new ArrayList<>();

            ResultSet resultSet = ps.executeQuery(sql);
            while (resultSet.next()) {
                array.add( new ProductEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getDouble("Cost"),
                        resultSet.getString("Description"),
                        resultSet.getString("MainImagePath"),
                        resultSet.getBoolean("IsActive"),
                        resultSet.getInt("ManufacturerID")
                ));

            }
            return array;
        }
    }

    public static void UpdatePt(ProductEntity product) throws SQLException {
        try(Connection c = Main.getConnection()) {
            String sql = "UPDATE Product set Title = ?, Cost = ?, Description = ?, MainImagePath = ?, IsActive = ?, ManufacturerID = ?";

            PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getTitle());
            ps.setDouble(2, product.getCost());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getMainImagePath());
            ps.setBoolean(5, product.getIsActive());
            ps.setInt(6, product.getManufacturerID());

            ps.executeUpdate();
        }
    }

    public  static void deletePr(int id) throws SQLException {
        try(Connection c = Main.getConnection()) {
            String sql = "Delete FROM Product WHERE ID = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public  static void deletePr(ProductEntity prouct) throws SQLException {
        deletePr(prouct.getID());
    }

    public static List<ProductEntity> selecPrCost(double cost) throws SQLException {
        try(Connection c = Main.getConnection()) {
            String sql = "Select ID, Title, Cost, Description, MainImagePath, IsActive, ManufacturerID FROM Product WHERE Cost>?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setDouble(1,cost);


            ResultSet resultSet = ps.executeQuery();
            List<ProductEntity> prod = new ArrayList<>();
            while (resultSet.next()){
                prod.add(new ProductEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getDouble("Cost"),
                        resultSet.getString("Description"),
                        resultSet.getString("MainImagePath"),
                        resultSet.getBoolean("IsActive"),
                        resultSet.getInt("ManufacturerID")
                ));
            }
            return prod;
        }
    }
}
