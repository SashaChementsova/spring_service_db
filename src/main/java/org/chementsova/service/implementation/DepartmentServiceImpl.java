package org.chementsova.service.implementation;

import org.chementsova.model.Department;
import org.chementsova.model.Employee;
import org.chementsova.repository.DepartmentRepository;
import org.chementsova.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void save(Department department){
        departmentRepository.createDepartment(department);
    }

    @Override
    public List<Department> getAll(){
        return departmentRepository.getAllDepartments();
    }

    @Override
    public Department getById(int id){
        return departmentRepository.getDepartmentById(id);
    }

    @Override
    public void update(Department department, Employee employee){
        departmentRepository.updateDepartment(department,employee);
    }
}
