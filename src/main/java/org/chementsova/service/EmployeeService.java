package org.chementsova.service;

import org.chementsova.model.Employee;

import java.util.List;

public interface EmployeeService {
    void save(Employee employee);

    List<Employee> getAll();

    Employee getById(int id);
}
