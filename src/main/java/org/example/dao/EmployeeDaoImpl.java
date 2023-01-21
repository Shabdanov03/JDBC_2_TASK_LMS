package org.example.dao;

import org.example.config.Configuration;
import org.example.models.Employee;
import org.example.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shabdanov Ilim
 **/
public class EmployeeDaoImpl implements EmployeeDao {
    Connection connection = Configuration.getConnection();

    public EmployeeDaoImpl() throws SQLException {
    }

    @Override
    public void createEmployee() {
        String sql = """
                create table employees (
                id serial primary key,
                first_name varchar,
                last_name varchar,
                age int,
                email varchar,
                job_id int references jobs(id)); """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Successfully created !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = """
                insert into employees (
                first_name,last_name,age,email,job_id)
                values (?,?,?,?,?);""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5, employee.getJobId());
            preparedStatement.executeUpdate();
            System.out.println("Successfully added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        String sql = """
                drop table employees""";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Successfully table drop !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void cleanTable() {
        String sql = """
                truncate employees;""";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Successfully cleaned !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String sql = """
                update employees 
                set first_name = ?,
                last_name = ?,
                age = ?,email = ?,job_id = ? where id = ? """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5, employee.getJobId());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "select * from employees ;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                employeeList.add(new Employee(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getInt(6)));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employeeList;
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee = new Employee();
        String sql = "select * from employees where email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employee;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee, Job> employeeJobMap = new LinkedHashMap<>();
        String sql = """
                select * from employees 
                join public.jobs j on j.id = employees.job_id
                where employees.id = ?;""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeJobMap.put(new Employee(
                                resultSet.getLong(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getInt(4),
                                resultSet.getString(5),
                                resultSet.getInt(6)),
                        new Job(
                                resultSet.getLong(7),
                                resultSet.getString(8),
                                resultSet.getString(9),
                                resultSet.getString(10),
                                resultSet.getInt(11)
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employeeJobMap;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employeeList = new ArrayList<>();
        String sql = """
                 select e.*,j.position from employees as e 
                 join jobs j on j.id = e.job_id 
                 where j.position = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, position);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeList.add(new Employee(
                                resultSet.getLong(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getInt(4),
                                resultSet.getString(5),
                                resultSet.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employeeList;
    }
}
