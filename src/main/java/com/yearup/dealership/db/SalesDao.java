package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        // TODO: Implement the logic to add a sales contract
        String insertQuery = "INSERT INTO sales_contracts (VIN, Sale_Date,price) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {


            preparedStatement.setString(1, salesContract.getVin());
            preparedStatement.setObject(2, salesContract.getSaleDate());
            preparedStatement.setDouble(3, salesContract.getPrice());

            preparedStatement.executeUpdate();

            System.out.println("Sales contract added to the database" );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
