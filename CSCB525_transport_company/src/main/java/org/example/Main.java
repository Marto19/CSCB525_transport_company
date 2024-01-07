package org.example;

import org.example.DAO.*;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SessionFactoryUtil.getSessionFactory().openSession();

          ////////////////////////////////////////////   COMPANY  1.  //////////////////////////////
//        //1.- CREATE company
        TransportCompany wonkaCompany = new TransportCompany("Wonka ltd.", BigDecimal.valueOf(10000));
//        TransportCompanyDAO.createCompany(wonkaCompany);
//
//
//        //1.- UPDATE company - change the value of income from 10000 to 20000
//        wonkaCompany.setIdTransportCompany(1);        //setId must be after createCompany
//        wonkaCompany.setIncome(BigDecimal.valueOf(30000));
//        TransportCompanyDAO.updateCompany(wonkaCompany);
//
//        //1.- DELETE company
//        //lets create another one in order to be deleted and not leave an empty table
//        TransportCompany starkIndustries = new TransportCompany("Stark Industries.", BigDecimal.valueOf(25000));
//        TransportCompanyDAO.createCompany(starkIndustries);
//        starkIndustries.setIdTransportCompany(2);
//
//        starkIndustries.setIncome(BigDecimal.valueOf(223000));
//        TransportCompanyDAO.saveOrUpdateCompany(starkIndustries);
//
////        TransportCompanyDAO.deleteCompany(starkIndustries);
//        //conclusion - in order to delete/update an entity, you have to have the same id in the constructor
//        //still, we need to keep track of the id, because after deleting company we cannot set from the next available in the table, because it will create other two
//
//        ////////////////////////////////////// CUSTOMERS  2.//////////////////////////////////////////////////
//        //2-CREATE customer
        Customer customer1 = new Customer("Gosho", "Smeshkov");
//        CustomerDAO.createCustomer(customer1);
//
//        //2-UPDATE customer
//        customer1.setId(1);
//        customer1.setFirstName("Petko");
//        CustomerDAO.updateCustomer(customer1);
//
//        //2-DELETE customer
////        CustomerDAO.deleteCustomer(customer1);
//
//
//        ///////////////////////////////// VehicleType  ///////////////////////////////////
//        //create vehicle type before creating a vehicle
//        VehicleType vehicleType = new VehicleType("Hooonda");
//        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType);    //DONT FORGET TO REMOVE IT
//        vehicleType.setIdOfVehicleType(1);
//        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType);
//
//        VehicleType vehicleType2 = new VehicleType("SUUUZUUUKIIII");
//        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType2);
//        vehicleType2.setIdOfVehicleType(2);
//        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType2);
//
//        VehicleType vehicleType3 = new VehicleType("CHEVIII");
//        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType3);
//        vehicleType2.setIdOfVehicleType(3);
//        VehicleTypeDAO.saveOrUpdateVehicleType(vehicleType3);

//        VehicleTypeDAO.deleteVehicleType(vehicleType);

//        //////////////////////////////////////////////Vehicles 3 /////////////////////////////////////////
//        Vehicle vehicle = new Vehicle(); //remember, the foreign keys are passed as objects, created in the main class
//        //set the foreign key through the setter of the entity
//        vehicle.setTransportCompany(TransportCompanyDAO.getTransportCompanyById(1));//maybe you can with company.getId too
//        vehicle.setVehicleType1(VehicleTypeDAO.getVehicleTypeById(2));//maybe you can with company.getId too
//        //Vehicle CREATED TODO: UPDATE & DELETE 3. ot zadanieto
//        VehicleDAO.createVehicle(vehicle);
//        vehicle.setId(1);
//        VehicleDAO.updateVehicle(vehicle);


//        Vehicle vehicle2 = new Vehicle();
//        vehicle2.setTransportCompany(TransportCompanyDAO.getTransportCompanyById(2));//maybe you can with company.getId too
//        vehicle2.setVehicleType1(VehicleTypeDAO.getVehicleTypeById(2));
//        VehicleDAO.createVehicle(vehicle2);
//        vehicle2.setId(2);
//        VehicleDAO.updateVehicle(vehicle2);


        Vehicle vehicle3 = new Vehicle();
//        vehicle3.setTransportCompany(TransportCompanyDAO.getTransportCompanyById(2));//maybe you can with company.getId too
//        vehicle3.setVehicleType1(VehicleTypeDAO.getVehicleTypeById(1));
//        VehicleDAO.createVehicle(vehicle3);
//        vehicle3.setId(3);
//        VehicleDAO.updateVehicle(vehicle3);
//
//        VehicleDAO.deleteVehicleById(3);
//
//    //////////////////////////////////////EMPLOYEES 4.    ////////////////////////////////////////////
////        CREATE
//        Employee employee1 = new Employee("Smeshniqt Pesho");
//        EmployeeDAO.createEmployee(employee1);
//        employee1.setId(1); //remember to increment id when creating a new one
//        EmployeeDAO.updateEmployee(employee1);
//        //update
//        employee1.setSalary(BigDecimal.valueOf(3000));
//        employee1.setTransportCompany(TransportCompanyDAO.getTransportCompanyById(1));
//        EmployeeDAO.updateEmployee(employee1);
//        //DELETE
//        EmployeeDAO.deleteEmployeeById(2);
        //UPDATE - WORKS
//        Employee employee2 = new Employee("Gosho Peshov");
//        EmployeeDAO.createEmployee(employee2);
//        employee2.setId(3); //remember to increment id when creating a new one
//          employee2.setSalary(BigDecimal.valueOf(2000));
//          employee2.setTransportCompany(TransportCompanyDAO.getTransportCompanyById(1));
//        EmployeeDAO.updateEmployee(employee2);


        Vehicle vehicle1 = new Vehicle(wonkaCompany, VehicleTypeDAO.getVehicleTypeById(1));




        ///////////////////////////// TRIP 5. /////////////////////////////////////////////////
//        Before creating a trip with 3.trip detail, we need goods, and to have 2.goods, we need things in
//        1.goodsType
        //CREATE
        GoodsType goodsType1 = new GoodsType("STOKA");
//        GoodsTypeDAO.createGoodsType(goodsType1);
//        goodsType1.setIdOfPositionType(1);
//        GoodsTypeDAO.updateGoodsType(goodsType1);

        //create another one
        GoodsType goodsType2 = new GoodsType("PASSENGERS");
//        GoodsTypeDAO.createGoodsType(goodsType2);
//        goodsType2.setPositionType("PASSENGERS");
//        goodsType2.setIdOfPositionType(2);
//        GoodsTypeDAO.updateGoodsType(goodsType2);
//        //TODO:CREATE goods objects
        Goods goods1 = new Goods("Gotini Stoki",2.2, goodsType1); //TODO: Maybe make another constructor, with
//        GoodsDAO.createGoods(goods1);

        TripDetails tripDetails = new TripDetails("Santa Str.", "Baker Str.",
                LocalDate.now(), LocalDate.of(2024, 3, 12),
                TransportCompanyDAO.getTransportCompanyById(1), VehicleDAO.getVehicleById(1));
//        //TODO: validate so that the departure date is now and cannot be beyond arrival and the other way around. mby use validation annotaion
//        TripDAO.createTrip(tripDetails);
//        tripDetails.setId(1);
//        TripDAO.updateVehicle(tripDetails);

        TripDetails tripDetails2 = new TripDetails("Yantra Str.", "Shoko Str.",
                LocalDate.now(), LocalDate.of(2024, 6, 20),
                TransportCompanyDAO.getTransportCompanyById(1), VehicleDAO.getVehicleById(1));
////        //TODO: validate so that the departure date is now and cannot be beyond arrival and the other way around. mby use validation annotaion
//        TripDAO.createTrip(tripDetails2);
//        tripDetails2.setId(3);
//        //UPDATE
//        tripDetails2.setEndPoint("Abbey Road");
//        TripDAO.updateVehicle(tripDetails2);
        //DELETE
//        TripDAO.deleteTripDetailById(4);
        //GET 1 ByDestination
        TripDAO.getOrderedTripDetailsByDestination();
        //GET 2
//        System.out.println("NOW WERE GOING TO TRY TO FIND ABBEY ROAD");
//        TripDAO.tripDetailsFindByDestination("Abbey Road");


        ////////////////////////////////TESTING CRUD IN CUSTOMER_OBLIGATIONS
        CustomerObligation customerObligation = new CustomerObligation(true, CustomerDAO.getCustomerById(1));
//        CustomerObligationDAO.createCustomerObligation(customerObligation); //TODO: FIX THE TransientObjectException
        //UPDATE
//        customerObligation.setId(4);
//        customerObligation.setTripDetails(TripDAO.getTripDetailsById(1));
        //TODO: ADD UPDATE METHOD IN THE DAO
//        CustomerObligationDAO.saveOrUpdateCustomerObligation(customerObligation);

        CustomerObligation customerObligation2 = new CustomerObligation(true, CustomerDAO.getCustomerById(1));
//        CustomerObligationDAO.createCustomerObligation(customerObligation2); //TODO: FIX THE TransientObjectException
        //UPDATE
//        customerObligation2.setId(5);
//        customerObligation2.setTripDetails(TripDAO.getTripDetailsById(1));
        //DELETE
//        CustomerObligationDAO.deleteCustomerObligationById(7);
        //GET ALL
//        CustomerObligationDAO.getAllCustomerObligations();

        ////////////////////////////////////ADDING QUALIFICATION TYPES/////////////////////////////////

        QualificationType qualificationType = new QualificationType("Car");
        QualificationTypeDAO.addQualificationType(qualificationType);

        System.out.println("EMPLOYEEEEEEEEEEEEEEES");
        EmployeeDAO.getEmployeeDTO();
        System.out.println("EMPLOYEEEEE QUALIFICATION");
        //EmployeeDAO.employeesFindByQualificationById(1);

    }
}