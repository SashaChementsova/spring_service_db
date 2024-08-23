package org.chementsova.repository.implementation;

import org.chementsova.model.Department;
import org.chementsova.model.Employee;
import org.chementsova.repository.DepartmentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@PropertySource("classpath://../config.properties")
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final SessionFactory sessionFactory;

    @Value("${db.status_of_update_department}")
    private String statusOfUpdateDepartment;

    public DepartmentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void createDepartment(Department department) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    @Transactional (readOnly = true)
    public Department getDepartmentById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Department.class, id);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional (readOnly = true)
    public List<Department> getAllDepartments() {
        try (Session session = sessionFactory.openSession()) {
            Query<Department> query = session.createQuery("from Department", Department.class);
            return query.list();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public void updateDepartment(Department department, Employee employee) {
       try {
           if (statusOfUpdateDepartment.equals("true")) {
               try (Session session = sessionFactory.openSession()) {
                   Transaction transaction = session.beginTransaction();
                   if(department.isNeedOfEmployee()){
                       employee.setDepartment(department);
                       session.save(employee);
                       department.setNeedOfEmployee(false);
                   }
                   session.update(department);
                   transaction.commit();
               }
           } else {
               throw new Exception("Status of Department Update is false");
           }
       } catch (Exception exception) {
           exception.printStackTrace();
       }
    }
}
