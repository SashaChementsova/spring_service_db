package org.chementsova;

import org.chementsova.model.Department;
import org.chementsova.model.Employee;
import org.chementsova.service.*;
import org.chementsova.service.implementation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.format.DateTimeFormatter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataConfig.class);

        DepartmentService departmentService = context.getBean(DepartmentServiceImpl.class);
        EmployeeService employeeService = context.getBean(EmployeeServiceImpl.class);
        TicketService ticketService = context.getBean(TicketServiceImpl.class);

        departmentService.save(new Department("hr_department", true));
        employeeService.save(new Employee("Nina", 5,"+6845585500", departmentService.getById(2)));
        departmentService.update(departmentService.getById(2), new Employee("Masha", 45,"+387558"));
        departmentService.getAll().forEach(System.out::println);
        employeeService.getAll().forEach(System.out::println);

        ticketService.initialize();
        ticketService.getAll().forEach(System.out::println);
    }
}
