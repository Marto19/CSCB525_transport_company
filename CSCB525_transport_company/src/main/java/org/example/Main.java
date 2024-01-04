package org.example;

import org.example.DAO.TransportCompanyDAO;
import org.example.DAO.VehicleDAO;
import org.example.DAO.VehicleTypeDAO;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Customer;
import org.example.entity.TransportCompany;
import org.example.entity.Vehicle;
import org.example.entity.VehicleType;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SessionFactoryUtil.getSessionFactory().openSession();
//    ////////////////////////////////////// COMPANY MAKING ////////////////////////////////////////////////
//
////        TransportCompany transportCompany = new TransportCompany("Wonka ltd.", BigDecimal.valueOf(10000));
////        TransportCompany transportCompany2 = new TransportCompany("Stark Industries", BigDecimal.valueOf(10000));
////        TransportCompanyDAO.saveOrUpdateCompany(transportCompany2);
////        TransportCompanyDAO.deleteCompanyById(4);
//
//        ////////////////////////////////// CREATE OBLIGATIONS ////////////////////////////////////////////
//
//        Salary obligations = new Salary(BigDecimal.valueOf(2800));
//        //TODO: add obligations to the obligation table throught the ObligationDAO
//
//        ////////////////////////////////// MAKING THE QUALIFICATION TYPE SET ////////////////////////////////
//
//        // Create a set of qualification types for the employee
//        Set<QualificationType> qualificationTypeSet = new HashSet<>();
//
//        // Add qualification types to the set (example)
//        QualificationType qualificationType1 = new QualificationType("Type 1");
//        qualificationTypeSet.add(qualificationType1);
//
//        /////////////////////////////////// MAKING EMPLOYEE /////////////////////////////////////////
//        // Create the Employee instance
//
////        Employee employee = new Employee("Martin Trenkov", BigDecimal.valueOf(3000));
//        employee.setTransportCompany(TransportCompanyDAO.getTransportCompanyById(1));
//        Employee employee = new Employee("Simona Velichkova", BigDecimal.valueOf(3000), TransportCompanyDAO.getTransportCompanyById(1));
//        employee.setId(7);
//        EmployeeDAO.createEmployee(employee);



        //TODO: transportCompany_id column in Emloyee doesnt allow records to be
        // save. Think how objects will be saved. Bassically data incompatability doesnt allow records to be recorded

        //1.- CREATE company
        TransportCompany wonkaCompany = new TransportCompany("Wonka ltd.", BigDecimal.valueOf(10000));
//        TransportCompanyDAO.createCompany(wonkaCompany);


        //1.- UPDATE company - change the value of income from 10000 to 20000
        wonkaCompany.setIdTransportCompany(1);        //setId must be after createCompany
        wonkaCompany.setIncome(BigDecimal.valueOf(30000));
        TransportCompanyDAO.updateCompany(wonkaCompany);

        //1.- DELETE company
        //lets create another one in order to be deleted and not leave an empty table
        TransportCompany starkIndustries = new TransportCompany("Stark Industries.", BigDecimal.valueOf(25000));
//        TransportCompanyDAO.createCompany(starkIndustries);
        starkIndustries.setIdTransportCompany(2);


        starkIndustries.setIncome(BigDecimal.valueOf(223000));
        TransportCompanyDAO.saveOrUpdateCompany(starkIndustries);

//        TransportCompanyDAO.deleteCompany(starkIndustries);
        //conclusion - in order to delete/update an entity, you have to have the same id in the constructor
        //still, we need to keep track of the id, because after deleting company we cannot set from the next available in the table, because it will create other two

        ////////////////CREATING CLIENTS//////////////////////////////////////////////////
        //2-CREATE customer
        Customer customer1 = new Customer("Gosho", "Smeshkov");
//        CustomerDAO.createCustomer(customer1);

        //2-UPDATE customer
        customer1.setId(1);
        customer1.setFirstName("Petko");
//        CustomerDAO.updateCustomer(customer1);

        //2-DELETE customer
//        CustomerDAO.deleteCustomer(customer1);


        ////////////3
        //create vehicle type before creating a vehicle
        VehicleType vehicleType = new VehicleType("Hooonda");
//        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType);    //DONT FORGET TO REMOVE IT
        vehicleType.setIdOfVehicleType(1);
//        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType);

        VehicleType vehicleType2 = new VehicleType("SUUUZUUUKIIII");
//        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType2);
        vehicleType2.setIdOfVehicleType(3);
//        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType2);

        VehicleType vehicleType3 = new VehicleType("CHEVIII");
//        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType3);
        vehicleType2.setIdOfVehicleType(4);
//        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType3);

//        VehicleTypeDAO.deleteVehicleType(vehicleType);

        ////////////3
        Vehicle vehicle = new Vehicle(); //remember, the foreign keys are passed as objects, created in the main class
        //set the foreign key through the setter of the entity
        vehicle.setTransportCompany(TransportCompanyDAO.getTransportCompanyById(1));//maybe you can with company.getId too
        vehicle.setVehicleType1(VehicleTypeDAO.getVehicleTypeById(2));//maybe you can with company.getId too

        //Vehicle CREATED TODO: UPDATE & DELETE 3. ot zadanieto
        VehicleDAO.createVehicle(vehicle);










        //update vehicle type table

        //Vehicle vehicle1 = new Vehicle(wonkaCompany, ve)



    }
}
// Simona's code

//TODO: add client table, think about it, connect it with the payments - done
//TODO: watch out for those DTO's properties. You will have to redo them with the properties you need
//TODO: change the enums by making them entities and connect them - done for GoodsType

//HAPPY NEW YEARS UPDATE