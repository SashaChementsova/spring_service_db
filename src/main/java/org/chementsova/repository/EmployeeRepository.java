package org.chementsova.repository;

import org.chementsova.model.Employee;
import java.util.List;

public interface EmployeeRepository {

    void createEmployee(Employee employee);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

}
