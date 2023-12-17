package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into vehicles (VIN, make, model, year, SOLD, color, vehicleType, odometer, price) values (?,?,?,?,?,?,?,?,?);")) {
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setBoolean(5, vehicle.isSold());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7, vehicle.getVehicleType());
            preparedStatement.setInt(8, vehicle.getOdometer());
            preparedStatement.setDouble(9, vehicle.getPrice());
            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
        String deleteQuery = "DELETE FROM vehicles WHERE vin = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setString(1, VIN);

            try {
                int rows = preparedStatement.executeUpdate();

                if (rows > 0) {
                    System.out.println("Vehicle with VIN " + VIN + " removed from the database.");
                } else {
                    System.out.println("No vehicle found with VIN " + VIN + ".");
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                // Handle foreign key constraint violation
                System.out.println("Cannot delete the vehicle with VIN " + VIN +
                        " because it has associated sales or lease contract.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range

        List<Vehicle> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();

                    vehicle.setVin(resultSet.getString("vin"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setSold(resultSet.getBoolean("sold"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setVehicleType(resultSet.getString("vehicleType"));
                    vehicle.setOdometer(resultSet.getInt("odometer"));
                    vehicle.setPrice(resultSet.getDouble("price"));

                    result.add(vehicle);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        List<Vehicle> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM vehicles WHERE make = ? AND model = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(resultSet.getString("vin"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setSold(resultSet.getBoolean("sold"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setVehicleType(resultSet.getString("vehicleType"));
                    vehicle.setOdometer(resultSet.getInt("odometer"));
                    vehicle.setPrice(resultSet.getDouble("price"));

                    result.add(vehicle);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range

        List<Vehicle> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setInt(1, minYear);
            preparedStatement.setInt(2, maxYear);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(resultSet.getString("vin"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setSold(resultSet.getBoolean("sold"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setVehicleType(resultSet.getString("vehicleType"));
                    vehicle.setOdometer(resultSet.getInt("odometer"));
                    vehicle.setPrice(resultSet.getDouble("price"));

                    result.add(vehicle);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        List<Vehicle> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM vehicles WHERE color = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, color);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(resultSet.getString("vin"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setSold(resultSet.getBoolean("sold"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setVehicleType(resultSet.getString("vehicleType"));
                    vehicle.setOdometer(resultSet.getInt("odometer"));
                    vehicle.setPrice(resultSet.getDouble("price"));

                    result.add(vehicle);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        List<Vehicle> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setInt(1, minMileage);
            preparedStatement.setInt(2, maxMileage);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(resultSet.getString("vin"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setSold(resultSet.getBoolean("sold"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setVehicleType(resultSet.getString("vehicleType"));
                    vehicle.setOdometer(resultSet.getInt("odometer"));
                    vehicle.setPrice(resultSet.getDouble("price"));

                    result.add(vehicle);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        List<Vehicle> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM vehicles WHERE vehicleType = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, type);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(resultSet.getString("vin"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setSold(resultSet.getBoolean("sold"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setVehicleType(resultSet.getString("vehicleType"));
                    vehicle.setOdometer(resultSet.getInt("odometer"));
                    vehicle.setPrice(resultSet.getDouble("price"));

                    result.add(vehicle);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}