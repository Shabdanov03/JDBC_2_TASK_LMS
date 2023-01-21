package org.example.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Shabdanov Ilim
 **/
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private int jobId; // references

    public Employee(String firstName, String lastName, int age, String email, int jobId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return " \nEmployee{" +
                "\nid=" + id +
                "\nfirstName='" + firstName +
                "\nlastName='" + lastName +
                "\nage=" + age +
                "\nemail='" + email +
                "\njobId=" + jobId +
                "\n===============\n";
    }
}
