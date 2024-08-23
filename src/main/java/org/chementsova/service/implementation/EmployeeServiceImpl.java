package org.chementsova.service.implementation;

import org.chementsova.model.Employee;
import org.chementsova.repository.EmployeeRepository;
import org.chementsova.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(Employee employee){
        employeeRepository.createEmployee(employee);
    }

    @Override
    public List<Employee> getAll(){
        return employeeRepository.getAllEmployees();
    }

    @Override
    public Employee getById(int id){
        return employeeRepository.getEmployeeById(id);
    }
}
