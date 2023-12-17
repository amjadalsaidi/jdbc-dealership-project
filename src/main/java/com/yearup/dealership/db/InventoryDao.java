package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        // TODO: Implement the logic to add a vehicle from the inventory
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = createInsertStatement(connection, vin, dealershipId);
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = createDeleteStatement(connection, vin);
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement createInsertStatement(Connection connection, String vin, int dealershipId) throws SQLException {
        String sql = "INSERT INTO inventory (dealership_id, VIN) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, dealershipId);
        statement.setString(2, vin);
        return statement;
    }
    private PreparedStatement createDeleteStatement(Connection connection, String vin) throws SQLException {
        String sql = "DELETE FROM inventory WHERE VIN = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, vin);
        return statement;
    }
}
