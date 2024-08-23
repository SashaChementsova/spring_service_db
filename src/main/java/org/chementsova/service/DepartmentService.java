package org.chementsova.service;

import org.chementsova.model.Department;
import org.chementsova.model.Employee;

import java.util.List;

public interface DepartmentService {
    void save(Department department);

    List<Department> getAll();

    Department getById(int id);

    void update(Department department, Employee employee);
}
