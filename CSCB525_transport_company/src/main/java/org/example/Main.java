package org.example;

import org.example.DAO.TransportCompanyDAO;
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
        TransportCompany transportCompany2 = new TransportCompany("Stark Industries", BigDecimal.valueOf(10000));
        //TransportCompanyDAO.saveOrUpdateCompany(transportCompany2);
        TransportCompanyDAO.deleteCompanyById(4);

//        TransportCompanyDAO.saveOrUpdateCompany(transportCompany);
        Employee employee = new Employee(QualificationType.CAR, "Tino Bombino", transportCompany);
//        EmployeeDAO.addEmployeeToCompanyId(employee, 3);

        Employee employee2 = new Employee(QualificationType.TRUCK, "Ivan Gotinov", transportCompany);
//        EmployeeDAO.addEmployeeToCompanyId(employee2, 3);

        Employee employee3 = new Employee(QualificationType.BUS, "Ivan Smotliov", transportCompany);
//        EmployeeDAO.addEmployeeToCompanyId(employee3, 3);

//        EmployeeDAO.createEmployee(employee3);
//        EmployeeDAO.addEmployeeToCompanyId(employee2, 3);

        //EmployeeDAO.addEmployeeToCompanyId(employee, 5);

//        EmployeeDAO.addEmployeeToCompany(employee, transportCompany);


    }
}

//TODO: add client table, think about it, connect it with the payments - done
//TODO: watch out for those DTO's properties. You will have to redo them with the properties you need
//TODO: change the enums by making them entities and connect them - done for GoodsType

//HAPPY NEW YEARS UPDATE