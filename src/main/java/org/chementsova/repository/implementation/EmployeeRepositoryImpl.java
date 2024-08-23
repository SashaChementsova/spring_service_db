package org.chementsova.repository.implementation;

import org.chementsova.model.Employee;
import org.chementsova.repository.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final SessionFactory sessionFactory;

    public EmployeeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void createEmployee(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    @Transactional (readOnly = true)
    public Employee getEmployeeById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            return session.get(Employee.class, id);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional (readOnly = true)
    public List<Employee> getAllEmployees() {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            return query.list();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
