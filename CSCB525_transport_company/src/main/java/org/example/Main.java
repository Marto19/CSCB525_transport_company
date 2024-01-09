package org.example;

import org.example.DAO.*;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;


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
        HashSet<QualificationType> qualificationTypesEmployee1 = new HashSet<>();
        qualificationTypesEmployee1.add(QualificationTypeDAO.getQualificationTypeById(4));
        qualificationTypesEmployee1.add(QualificationTypeDAO.getQualificationTypeById(1));

        Employee employee1 = new Employee("Petar Petrov");
        employee1.setId(1);
        employee1.setTransportCompany(dhl);
        employee1.setSalary(BigDecimal.valueOf(2200));
        employee1.setQualificationTypeSet(qualificationTypesEmployee1);     //THESE SET THE MANY TO MANY

        HashSet<QualificationType> qualificationTypesEmployee2 = new HashSet<>();
        qualificationTypesEmployee2.add(QualificationTypeDAO.getQualificationTypeById(4));
        qualificationTypesEmployee2.add(QualificationTypeDAO.getQualificationTypeById(5));

        Employee employee2 = new Employee("Ivan Ivanov");
        employee2.setId(2);
        employee2.setTransportCompany(fedEX);
        employee2.setSalary(BigDecimal.valueOf(6500));
        employee2.setQualificationTypeSet(qualificationTypesEmployee2);

        HashSet<QualificationType> qualificationTypesEmployee3 = new HashSet<>();
        qualificationTypesEmployee3.add(QualificationTypeDAO.getQualificationTypeById(4));
        qualificationTypesEmployee3.add(QualificationTypeDAO.getQualificationTypeById(6));

        Employee employee3 = new Employee("Gosheto Qkiq");
        employee3.setId(3);
        employee3.setTransportCompany(speedy);
        employee3.setSalary(BigDecimal.valueOf(3200));
        employee3.setQualificationTypeSet(qualificationTypesEmployee3);

        HashSet<QualificationType> qualificationTypesEmployee4 = new HashSet<>();
        qualificationTypesEmployee4.add(QualificationTypeDAO.getQualificationTypeById(6));
        qualificationTypesEmployee4.add(QualificationTypeDAO.getQualificationTypeById(7));

        Employee employee4 = new Employee("Smotlio Smotlev");
        employee4.setId(4);
        employee4.setTransportCompany(ups);
        employee4.setSalary(BigDecimal.valueOf(2300));
        employee4.setQualificationTypeSet(qualificationTypesEmployee4);


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

        /////////////////////////////////////// QUALIFICATION TYPE  ///////////////////////////////////

        QualificationType qualificationType1 = new QualificationType("A");  // For a moped with a design speed of not more than 45 Km/h.
        qualificationType1.setId(1);
        QualificationType qualificationType2 = new QualificationType("A1"); //Motorcycles with a cylinder capacity below 125cc, and a power not exceeding 11 kW
        qualificationType2.setId(2);
        QualificationType qualificationType3 = new QualificationType("A2"); //Motorcycles with a power not exceeding 35 kW
        qualificationType3.setId(3);
        QualificationType qualificationType4 = new QualificationType("B");  //For cars, vans and vehicles up to 3.5 tons and up to nine seats.
        qualificationType4.setId(4);
        QualificationType qualificationType5 = new QualificationType("B1"); //For small cars with an engine size above 50cc and speed not exceeding 45 km/h, and a weight of no more than 550 Kg.
        qualificationType5.setId(5);
        QualificationType qualificationType6 = new QualificationType("C");  //Includes vehicles from category B and those weighing more than 3.5 tons and with more than nine seats.
        qualificationType6.setId(6);
        QualificationType qualificationType7 = new QualificationType("C+E");  //For busses or other vehicles with over nine passenger seats
        qualificationType7.setId(7);
        QualificationType qualificationType8 = new QualificationType("D");  //Includes vehicles from category B and those weighing more than 3.5 tons and with more than nine seats.
        qualificationType8.setId(8);

        QualificationTypeDAO.saveOrUpdateQualificationType(qualificationType1);
        QualificationTypeDAO.saveOrUpdateQualificationType(qualificationType2);
        QualificationTypeDAO.saveOrUpdateQualificationType(qualificationType3);
        QualificationTypeDAO.saveOrUpdateQualificationType(qualificationType4);
        QualificationTypeDAO.saveOrUpdateQualificationType(qualificationType5);
        QualificationTypeDAO.saveOrUpdateQualificationType(qualificationType6);
        QualificationTypeDAO.saveOrUpdateQualificationType(qualificationType7);
        QualificationTypeDAO.saveOrUpdateQualificationType(qualificationType8);

        /////////////////////////////////////// 4. TripDetails  //////////////////////////////////////
        LocalDate currentDate = LocalDate.now();

        TripDetails tripDetails1 = new TripDetails("Main Str.", "Park Ave.",
                currentDate, LocalDate.of(2024, 4, 15),
                TransportCompanyDAO.getTransportCompanyById(1));
        tripDetails1.setId(1);
        tripDetails1.setEmployee(employee1);

        TripDetails tripDetails2 = new TripDetails("Elm Str.", "Oak Str.",
                currentDate, LocalDate.of(2024, 5, 20),
                TransportCompanyDAO.getTransportCompanyById(2));
        tripDetails2.setId(2);
        tripDetails2.setEmployee(employee2);


        TripDetails tripDetails3 = new TripDetails("Pine Str.", "Maple Str.",
                currentDate, LocalDate.of(2024, 6, 25),
                TransportCompanyDAO.getTransportCompanyById(3));
        tripDetails3.setId(3);
        tripDetails3.setEmployee(employee2);


        TripDetails tripDetails4 = new TripDetails("Cherry Str.", "Walnut Str.",
                currentDate, LocalDate.of(2024, 7, 30),
                TransportCompanyDAO.getTransportCompanyById(4));
        tripDetails4.setId(4);
        tripDetails4.setEmployee(employee3);


        //adding trips that have arrived

        TripDetails tripDetails5 = new TripDetails("Cherry Str.", "Walnut Str.",
                LocalDate.of(2023, 7, 30), LocalDate.of(2024, 1, 1),
                TransportCompanyDAO.getTransportCompanyById(3));
        tripDetails5.setId(5);
        tripDetails5.setEmployee(employee1);


        TripDetails tripDetails6 = new TripDetails("Cherry Str.", "Walnut Str.",
                LocalDate.of(2023, 7, 30), LocalDate.of(2024, 1, 1),
                TransportCompanyDAO.getTransportCompanyById(3));
        tripDetails6.setId(6);
        tripDetails6.setEmployee(employee1);

        TripDAO.saveOrUpdateTripDetails(tripDetails1);
        TripDAO.saveOrUpdateTripDetails(tripDetails2);
        TripDAO.saveOrUpdateTripDetails(tripDetails3);
        TripDAO.saveOrUpdateTripDetails(tripDetails4);
        TripDAO.saveOrUpdateTripDetails(tripDetails5);
        TripDAO.saveOrUpdateTripDetails(tripDetails6);


        //deletion
//        TripDAO.deleteTrip(TripDAO.getTripDetailsById(3));

        //////////////////////////////////////  ORDER DETAILS   //////////////////////////////////////
        customer1.setBalance(BigDecimal.valueOf(2000));
        CustomerDAO.saveOrUpdateCustomer(customer1);

        OrderDetails orderDetails1 = new OrderDetails(BigDecimal.valueOf(20), true, tripDetails1);
        orderDetails1.setId(1);

        OrderDetailsDAO.updateOrderDetails(orderDetails1);

        OrderDetailsDAO.saveOrUpdateCreateOrder(orderDetails1, customer1);  //TODO: makes new record, therefore its not setting  the id's properly

        ////////////////////////////////////////////////    9. REFERENCES   ///////////////////////////

        System.out.println("ARRIVED TRIPS: " +   TripDAO.getCompletedTripsDTO());        //9.

        System.out.println("NUMBER OF ARRIVED TRIPS: " + TripDAO.getCompletedTripsCountDTO()); //9. a ot zadanieto

        System.out.println("Total Price of Completed Trips: " + TripDAO.getTotalPriceOfCompletedTrips());   //TODO:check if it works okay

//        System.out.println("list of employees and the count of trips each employee has completed:" + EmployeeDAO.getDriversAndCompletedTripsCount());

        System.out.println();
        EmployeeDAO.printDriversAndCompletedTripsCount();       //9.
        System.out.println();

        //TODO: START FROM 106 LINE
        //TODO: REMOVE CUSTOMER OBLIGATIONS TABLE
    }
}