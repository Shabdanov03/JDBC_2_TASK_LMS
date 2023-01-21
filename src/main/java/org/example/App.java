package org.example;

import org.example.config.Configuration;
import org.example.models.Employee;
import org.example.models.Job;
import org.example.service.EmployeeService;
import org.example.service.EmployeeServiceImpl;
import org.example.service.JobService;
import org.example.service.JobServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {

        Job job = new Job("Instructor", "Java", "Backend developer", 2);
        Job job2 = new Job("Mentor", "JavaScript", "Frontend developer", 3);

        Employee employee = new Employee("Aijamal", "Asangazieva", 18, "aijamal@gmail.com", 1);
        Employee employee2 = new Employee("Mukhamed", "Toichubai uulu", 25, "asantegin@gmail.com", 2);

        EmployeeService employeeService = new EmployeeServiceImpl();
        JobService jobService = new JobServiceImpl();

        while (true) {
            System.out.println("""
                      ++++++++++- JOB COMMANDS -++++++++++
                     1.CREATE JOB TABLE :
                     2.ADD JOB :
                     3.GET JOB BY ID :
                     4.SORT BY EXPERIENCE :
                     5.GET JOB BY EMPLOYEE ID :
                     6.DELETE DESCRIPTION COLUMN :
                     ++++++++++- EMPLOYEE COMMANDS -++++++++++
                     7.CREATE EMPLOYEE TABLE :
                     8.ADD EMPLOYEE :
                     9.DROP EMPLOYEE TABLE :
                     10.CLEAN EMPLOYEE TABLE :
                     11.UPDATE EMPLOYEE :
                     12.GET ALL EMPLOYEE :
                     13.FIND BY EMAIL EMPLOYEE :
                     14.GET EMPLOYEE BY ID :
                     15.GET EMPLOYEE BY POSITION :
                     """);
            System.out.println(" ENTER  BY  COMMANDS :");
            int num = new Scanner(System.in).nextInt();
            switch (num) {
                case 1 -> jobService.createJobTable();
                case 2 -> {
                    System.out.println("Select position (Mentor / Management / Instructor)");
                    String position = new Scanner(System.in).nextLine();
                    System.out.println("Select profession (Java / JavaScript");
                    String profession = new Scanner(System.in).nextLine();
                    System.out.println("Select description ( Backend developer / Fronted developer");
                    String description = new Scanner(System.in).nextLine();
                    System.out.println("Enter experience :");
                    int experience = new Scanner(System.in).nextInt();
                    jobService.addJob(new Job(position,profession,description,experience));
                }
                case 3 -> {
                    System.out.println("Enter by id :");
                    Long jobId = new Scanner(System.in).nextLong();
                    System.out.println(jobService.getJobById(jobId));
                }
                case 4 -> {
                    System.out.println("Select command : (asc / desc");
                    String ascOrDesc = new Scanner(System.in).nextLine();
                    jobService.sortByExperience(ascOrDesc).forEach(System.out::println);
                }
                case 5 -> {
                    System.out.println("Enter by employee id :");
                    Long employeeId = new Scanner(System.in).nextLong();
                    System.out.println(jobService.getJobByEmployeeId(employeeId));
                }
                case 6 -> jobService.deleteDescriptionColumn();
                case 7 -> employeeService.createEmployee();
                case 8 -> employeeService.addEmployee(employee);
                case 9 -> employeeService.dropTable();
                case 10 -> employeeService.cleanTable();
                case 11 -> {
                    System.out.println("Enter by id : ");
                    Long id = new Scanner(System.in).nextLong();
                    employeeService.updateEmployee(id, employee2);
                }
                case 12 -> employeeService.getAllEmployees().forEach(System.out::println);
                case 13 -> {
                    System.out.println("Enter by email : ");
                    String email = new Scanner(System.in).nextLine();
                    System.out.println(employeeService.findByEmail(email));
                }
                case 14 -> {
                    System.out.println("Enter  by id :");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(employeeService.getEmployeeById(id));
                }
                case 15 -> {
                    System.out.println("Enter by position :");
                    String position = new Scanner(System.in).nextLine();
                    employeeService.getEmployeeByPosition(position).forEach(System.out::println);
                }

                default -> System.out.println("No such command ! ");
            }
        }


    }
}
