package org.example;

import org.example.DAO.EmployeeDAO;
import org.example.DAO.TransportCompanyDAO;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Employee;
import org.example.entity.Obligations;
import org.example.entity.QualificationType;
import org.example.entity.TransportCompany;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SessionFactoryUtil.getSessionFactory().openSession();
    ////////////////////////////////////// COMPANY MAKING ////////////////////////////////////////////////

        TransportCompany transportCompany = new TransportCompany("Wonka ltd.", BigDecimal.valueOf(10000));
        TransportCompany transportCompany2 = new TransportCompany("Stark Industries", BigDecimal.valueOf(10000));
        //TransportCompanyDAO.saveOrUpdateCompany(transportCompany2);
        TransportCompanyDAO.deleteCompanyById(4);

        ////////////////////////////////// CREATE OBLIGATIONS ////////////////////////////////////////////

        Obligations obligations = new Obligations(BigDecimal.valueOf(2800));
        //TODO: add obligations to the obligation table throught the ObligationDAO

        ////////////////////////////////// MAKING THE QUALIFICATION TYPE SET ////////////////////////////////

        // Create a set of qualification types for the employee
        Set<QualificationType> qualificationTypeSet = new HashSet<>();

        // Add qualification types to the set (example)
        QualificationType qualificationType1 = new QualificationType("Type 1");
        qualificationTypeSet.add(qualificationType1);

        /////////////////////////////////// MAKING EMPLOYEE /////////////////////////////////////////
        // Create the Employee instance
        Employee employee = new Employee("John Doe");
        EmployeeDAO.createEmployee(employee);



    }
}

//TODO: add client table, think about it, connect it with the payments - done
//TODO: watch out for those DTO's properties. You will have to redo them with the properties you need
//TODO: change the enums by making them entities and connect them - done for GoodsType

//HAPPY NEW YEARS UPDATE