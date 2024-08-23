package org.chementsova.repository;

import org.chementsova.model.Department;
import org.chementsova.model.Employee;

import java.util.List;

public interface DepartmentRepository {

    void createDepartment(Department department);

    Department getDepartmentById(int id);

    List<Department> getAllDepartments();

    void updateDepartment(Department department, Employee employee);
}
