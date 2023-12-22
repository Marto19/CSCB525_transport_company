package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAO {

    /**
     * Creates a new employee record in the database.
     *
     * @param employee The employee object to be created.
     * @throws IllegalArgumentException If the provided employee object is null.
     */
    public static void createEmployee(Employee employee){
        if(employee == null){
            throw new IllegalArgumentException("The employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    /**
     * Retrieves a list of all employees from the database.
     *
     * @return List of Employee objects representing all employees in the database.
     */
    public static List<Employee> getEmployees(){
        List<Employee> employeeList;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            employeeList = session.createQuery("select c from Employee c", Employee.class)
                    .getResultList();
            transaction.commit();
        }
        return employeeList;
    }



    /**
     * Updates an existing employee record in the database.
     *
     * @param employee The updated employee object.
     * @throws IllegalArgumentException If the provided employee object is null.
     */
    public static void updateEmployee(Employee employee){
        if(employee == null){
            throw new IllegalArgumentException("The employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        }
    }

    /**
     * Deletes an employee record from the database.
     *
     * @param employee The employee object to be deleted.
     * @throws IllegalArgumentException If the provided employee object is null.
     */
    public static void deleteEmployee(Employee employee){
        if (employee == null){
            throw new IllegalArgumentException("Employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }
}
