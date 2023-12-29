package org.example;

import org.example.DAO.EmployeeDAO;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Employee;
import org.example.entity.TransportCompany;
import org.example.enums.QualificationType;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SessionFactoryUtil.getSessionFactory().openSession();


        TransportCompany transportCompany = new TransportCompany("Wonka ltd.", BigDecimal.valueOf(10000));
//        TransportCompanyDAO.saveOrUpdateCompany(transportCompany);
        Employee employee = new Employee(QualificationType.CAR, "Tino Bombino", transportCompany);
        EmployeeDAO.createEmployee(employee);

        Employee employee2 = new Employee(QualificationType.CAR, "Ivan Gotinov", transportCompany);
//        EmployeeDAO.createEmployee(employee);
        EmployeeDAO.addEmployeeToCompanyId(employee2, 5);

        EmployeeDAO.addEmployeeToCompanyId(employee, 5);

//        EmployeeDAO.addEmployeeToCompany(employee, transportCompany);
    }
}

//TODO: add client table, think about it, connect it with the payments