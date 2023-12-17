package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {

        // TODO: Implement the logic to add a lease contract
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = createInsertStatement(connection, leaseContract);
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    private PreparedStatement createInsertStatement(Connection connection, LeaseContract leaseContract) throws SQLException {
        String sql = "INSERT INTO lease_contracts (VIN, lease_start, lease_end, monthly_payment) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, leaseContract.getVin());
        statement.setDate(2, java.sql.Date.valueOf(leaseContract.getLeaseStart()));
        statement.setDate(3, java.sql.Date.valueOf(leaseContract.getLeaseEnd()));
        statement.setDouble(4, leaseContract.getMonthlyPayment());

        return statement;
    }
}
