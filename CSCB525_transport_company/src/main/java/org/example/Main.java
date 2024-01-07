package org.example;

import org.example.DAO.*;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;


//TODO: connect qualification table with vehicletype table
//my idea is that when we connect them with many to many relation ship a new table will be created
// a new table will be created, and if somehow we take an id of one table it will correspond to a
//record from other table, therefor not letting the employee drive something that he doesnt have a -
//qualification for

//todo: do the math
//todo: make it so that when a an order is placed


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SessionFactoryUtil.getSessionFactory().openSession();

          ////////////////////////////////////////////   COMPANY  1.  //////////////////////////////
        TransportCompany dhl = new TransportCompany("DHL");
        dhl.setIdTransportCompany(1);
        TransportCompany speedy = new TransportCompany("Speedy");
        speedy.setIdTransportCompany(2);
        TransportCompany fedEX = new TransportCompany("FedEX");
        fedEX.setIdTransportCompany(3);
        TransportCompany ups = new TransportCompany("UPS");
        ups.setIdTransportCompany(4);

        //1.- CREATE company
        TransportCompanyDAO.saveOrUpdateCompany(dhl);
        TransportCompanyDAO.saveOrUpdateCompany(speedy);
        TransportCompanyDAO.saveOrUpdateCompany(fedEX);
        TransportCompanyDAO.saveOrUpdateCompany(ups);

        //1. - UPDATE companies by setting
        dhl.setGoodsTripPrice(BigDecimal.valueOf(5));
        dhl.setPassengerTripPrice(BigDecimal.valueOf(10));

        speedy.setGoodsTripPrice(BigDecimal.valueOf(2.5));
        speedy.setPassengerTripPrice(BigDecimal.valueOf(10));

        fedEX.setGoodsTripPrice(BigDecimal.valueOf(3));
        fedEX.setPassengerTripPrice(BigDecimal.valueOf(10));

        ups.setGoodsTripPrice(BigDecimal.valueOf(2));
        ups.setPassengerTripPrice(BigDecimal.valueOf(10));

        TransportCompanyDAO.saveOrUpdateCompany(dhl);
        TransportCompanyDAO.saveOrUpdateCompany(speedy);
        TransportCompanyDAO.saveOrUpdateCompany(fedEX);
        TransportCompanyDAO.saveOrUpdateCompany(ups);


        //display them all
//        TransportCompanyDAO.getCompanies().stream().forEach(System.out::println);

        //by using DTO
//        TransportCompanyDAO.getCompaniesDTO().stream().forEach(System.out::println);

        //1. - DELETING
//        TransportCompanyDAO.deleteCompany(ups);
//        TransportCompanyDAO.deleteTransportCompanyById(4);

        //count companies
        System.out.println("Total number of companies: " + TransportCompanyDAO.countCompanies());

        // 8. trying to add an invalid company - everything works fine here
//        TransportCompany invCompany = new TransportCompany("toomuchcharactersinthiscompanynameidkwheretostartidkwheretostartmustchangeitlatersperhapsorperhapsnot");
//        TransportCompany invCompany2 = new TransportCompany("econt");
//        TransportCompanyDAO.createCompany(invCompany);
//        TransportCompanyDAO.createCompany(invCompany2);


        /////////////////////////////////////////   2. EMPLOYEE     ///////////////////////////////////
        Employee employee1 = new Employee("Petar Petrov");
        employee1.setId(1);
        employee1.setTransportCompany(dhl);
        employee1.setSalary(BigDecimal.valueOf(2200));

        Employee employee2 = new Employee("Ivan Ivanov");
        employee2.setId(2);
        employee2.setTransportCompany(fedEX);
        employee2.setSalary(BigDecimal.valueOf(6500));


        Employee employee3 = new Employee("Gosheto Qkiq");
        employee3.setId(3);
        employee3.setTransportCompany(speedy);
        employee3.setSalary(BigDecimal.valueOf(3200));


        Employee employee4 = new Employee("Smotlio Smotlev");
        employee4.setId(4);
        employee4.setTransportCompany(ups);
        employee4.setSalary(BigDecimal.valueOf(2300));


        EmployeeDAO.saveOrUpdateEmployee(employee1);
        EmployeeDAO.saveOrUpdateEmployee(employee2);
        EmployeeDAO.saveOrUpdateEmployee(employee3);
        EmployeeDAO.saveOrUpdateEmployee(employee4);

        //list all employees
//        EmployeeDAO.getEmployeesDTO().stream().forEach(System.out::println);//todo:fix it;

        /////////////////////////////////////// 3.CUSTOMER  //////////////////////////////////////////

        Customer customer1 = new Customer("Tosho", "Gosho");
        customer1.setId(1);
        Customer customer2 = new Customer("Petar", "Stoqnov");
        customer2.setId(2);

        CustomerDAO.saveOrUpdateCustomer(customer1);
        CustomerDAO.saveOrUpdateCustomer(customer2);

        // 2. display clients
        CustomerDAO.getClientsDTO().stream().forEach(System.out::println);

        /////////////////////////////////////// 4. TripDetails  //////////////////////////////////////
        LocalDate currentDate = LocalDate.now();

        TripDetails tripDetails1 = new TripDetails("Main Str.", "Park Ave.",
                currentDate, LocalDate.of(2024, 4, 15),
                TransportCompanyDAO.getTransportCompanyById(1));
        tripDetails1.setId(1);

        TripDetails tripDetails2 = new TripDetails("Elm Str.", "Oak Str.",
                currentDate, LocalDate.of(2024, 5, 20),
                TransportCompanyDAO.getTransportCompanyById(2));
        tripDetails2.setId(2);

        TripDetails tripDetails3 = new TripDetails("Pine Str.", "Maple Str.",
                currentDate, LocalDate.of(2024, 6, 25),
                TransportCompanyDAO.getTransportCompanyById(3));
        tripDetails3.setId(3);

        TripDetails tripDetails4 = new TripDetails("Cherry Str.", "Walnut Str.",
                currentDate, LocalDate.of(2024, 7, 30),
                TransportCompanyDAO.getTransportCompanyById(4));
        tripDetails4.setId(4);

        TripDAO.saveOrUpdateTripDetails(tripDetails1);
        TripDAO.saveOrUpdateTripDetails(tripDetails2);
        TripDAO.saveOrUpdateTripDetails(tripDetails3);
        TripDAO.saveOrUpdateTripDetails(tripDetails4);

        //deletion
//        TripDAO.deleteTrip(TripDAO.getTripDetailsById(3));

        ////////////////////////////  5.  VEHICLE TYPES AND VEHICLES ///////////////////////////////////////
        //CREATING THE TYPES
        VehicleType vehicleType1 = new VehicleType("Bus");
        vehicleType1.setId(1);
        VehicleType vehicleType2 = new VehicleType("Truck");
        vehicleType2.setId(2);
        VehicleType vehicleType3 = new VehicleType("Boat");
        vehicleType3.setId(3);
        VehicleType vehicleType4 = new VehicleType("Plane");
        vehicleType4.setId(4);

        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType1);
        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType2);
        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType3);
        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType4);

        //list them all
        VehicleTypeDAO.getVehicleTypes().stream().forEach(System.out::println);

        //NOW VEHICLES
        Vehicle vehicle1 = new Vehicle(TransportCompanyDAO.getTransportCompanyById(1), VehicleTypeDAO.getVehicleTypeById(1));
        vehicle1.setId(1);
        Vehicle vehicle2 = new Vehicle(TransportCompanyDAO.getTransportCompanyById(2), VehicleTypeDAO.getVehicleTypeById(2));
        vehicle2.setId(2);
        Vehicle vehicle3 = new Vehicle(fedEX, vehicleType3);
        vehicle3.setId(3);

        VehicleDAO.saveOrUpdateVehicle(vehicle1);
        VehicleDAO.saveOrUpdateVehicle(vehicle2);
        VehicleDAO.saveOrUpdateVehicle(vehicle3);

        //deletion
//        VehicleDAO.deleteVehicleById(3);
        //LIST THEM ALL
//        VehicleDAO.getVehicles().forEach(System.out::println);    //todo: fix: .LazyInitializationException
        //TODO: START FROM 106 LINE
        //TODO: REMOVE CUSTOMER OBLIGATIONS TABLE
    }
}