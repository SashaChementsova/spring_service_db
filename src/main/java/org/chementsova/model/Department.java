package org.chementsova.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "department")
public class Department {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "name", nullable = false, unique = true)
    private String name;

    @Column (name = "need_of_employee")
    private boolean needOfEmployee;

    @OneToMany (mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }

    @Override
    public String toString() {
        return "Department " +
                "\n id = " + id +
                "\n name = " + name +
                "\n needOfEmployee = " + needOfEmployee;
    }

    public Department(String name, boolean needOfEmployee) {
        this.name = name;
        this.needOfEmployee = needOfEmployee;
    }
}
