package dao;

import db.DatabaseConnection;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // =========================
    // ADD EMPLOYEE
    // =========================

    public void addEmployee(Employee employee) {

        String sql =
                "INSERT INTO employees (id, name, department, salary) " +
                        "VALUES (?, ?, ?, ?)";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getDepartment());
            statement.setDouble(4, employee.getSalary());

            statement.executeUpdate();

            statement.close();
            connection.close();

            System.out.println("Employee added to database!");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    // =========================
    // UPDATE EMPLOYEE
    // =========================

    public void updateEmployee(Employee employee) {

        String sql =
                "UPDATE employees " +
                        "SET name = ?, department = ?, salary = ? " +
                        "WHERE id = ?";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getDepartment());
            statement.setDouble(3, employee.getSalary());
            statement.setInt(4, employee.getId());

            statement.executeUpdate();

            statement.close();
            connection.close();

            System.out.println("Employee updated in database!");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    // =========================
    // DELETE EMPLOYEE
    // =========================

    public void deleteEmployee(int id) {

        String sql =
                "DELETE FROM employees WHERE id = ?";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();

            statement.close();
            connection.close();

            System.out.println("Employee deleted from database!");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    // =========================
    // GET ALL EMPLOYEES
    // =========================

    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM employees";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getDouble("salary")
                );

                employees.add(employee);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return employees;
    }
}