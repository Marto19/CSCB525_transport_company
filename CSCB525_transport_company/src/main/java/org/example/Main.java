package org.example;

import org.example.DAO.*;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.*;
import org.example.exceptions.EmployeeNotQualfiedException;
import org.example.exceptions.NoVehicleSetForThisTrip;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws NoVehicleSetForThisTrip, EmployeeNotQualfiedException {
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

        ///////////////////////////////////////// GOODS TYPE    //////////////////////////////////
        GoodsType goodsType1 = new GoodsType("GOODS");
        goodsType1.setIdOfPositionType(1);
        GoodsType goodsType2 = new GoodsType("PASSENGERS");
        goodsType2.setIdOfPositionType(2);

        GoodsTypeDAO.saveOrUpdateGoodsType(goodsType1);
        GoodsTypeDAO.saveOrUpdateGoodsType(goodsType2);
        //////////////////////////////////////////  GOODS   ///////////////////////////////////////
        Goods goods1 = new Goods("Tomatoes", 0.123, 50, BigDecimal.valueOf(2.99), goodsType1);
        goods1.setId(1);
        Goods goods2 = new Goods("Cubumbers", 0.100, 50,BigDecimal.valueOf(2), goodsType1);
        goods2.setId(2);

        GoodsDAO.saveOrUpdateGoods(goods1);
        GoodsDAO.saveOrUpdateGoods(goods2);



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
        // For each qualification type, create two vehicle types
        VehicleType moped1 = new VehicleType("Moped 1");
        moped1.setId(1);
        VehicleType moped2 = new VehicleType("Moped 2");
        moped2.setId(2);

        VehicleType motorcycle1 = new VehicleType("Motorcycle 1");
        motorcycle1.setId(3);
        VehicleType motorcycle2 = new VehicleType("Motorcycle 2");
        motorcycle2.setId(4);

        VehicleType powerBike1 = new VehicleType("Power Bike 1");
        powerBike1.setId(5);
        VehicleType powerBike2 = new VehicleType("Power Bike 2");
        powerBike2.setId(6);

        VehicleType car1 = new VehicleType("Car 1");
        car1.setId(7);
        VehicleType car2 = new VehicleType("Car 2");
        car2.setId(8);

        VehicleType smallCar1 = new VehicleType("Small Car 1");
        smallCar1.setId(9);
        VehicleType smallCar2 = new VehicleType("Small Car 2");
        smallCar2.setId(10);

        VehicleType bus1 = new VehicleType("Bus 1");
        bus1.setId(11);
        VehicleType bus2 = new VehicleType("Bus 2");
        bus2.setId(12);

        VehicleType bus3 = new VehicleType("Bus 3");
        bus3.setId(13);
        VehicleType bus4 = new VehicleType("Bus 4");
        bus4.setId(14);

        VehicleType truck1 = new VehicleType("Truck 1");
        truck1.setId(15);
        VehicleType truck2 = new VehicleType("Truck 2");
        truck2.setId(16);

// Save or update the vehicle types
        VehicleTypeDAO.saveOrUpdateVehicleType(moped1);
        VehicleTypeDAO.saveOrUpdateVehicleType(moped2);
        VehicleTypeDAO.saveOrUpdateVehicleType(motorcycle1);
        VehicleTypeDAO.saveOrUpdateVehicleType(motorcycle2);
        VehicleTypeDAO.saveOrUpdateVehicleType(powerBike1);
        VehicleTypeDAO.saveOrUpdateVehicleType(powerBike2);
        VehicleTypeDAO.saveOrUpdateVehicleType(car1);
        VehicleTypeDAO.saveOrUpdateVehicleType(car2);
        VehicleTypeDAO.saveOrUpdateVehicleType(smallCar1);
        VehicleTypeDAO.saveOrUpdateVehicleType(smallCar2);
        VehicleTypeDAO.saveOrUpdateVehicleType(bus1);
        VehicleTypeDAO.saveOrUpdateVehicleType(bus2);
        VehicleTypeDAO.saveOrUpdateVehicleType(bus3);
        VehicleTypeDAO.saveOrUpdateVehicleType(bus4);
        VehicleTypeDAO.saveOrUpdateVehicleType(truck1);
        VehicleTypeDAO.saveOrUpdateVehicleType(truck2);


        //list them all
        VehicleTypeDAO.getVehicleTypes().stream().forEach(System.out::println);

        //NOW VEHICLES
        Vehicle vehicle1 = new Vehicle(TransportCompanyDAO.getTransportCompanyById(1), VehicleTypeDAO.getVehicleTypeById(1));
        vehicle1.setId(1);
        Vehicle vehicle2 = new Vehicle(TransportCompanyDAO.getTransportCompanyById(2), VehicleTypeDAO.getVehicleTypeById(2));
        vehicle2.setId(2);
        Vehicle vehicle3 = new Vehicle(fedEX, bus2);
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

        /////////////////////////////////////setting the qualification_type_vehicle_type///////////////
        // For each qualification type, create a set of vehicle types
        Set<VehicleType> qftVehicleTypeSet1 = new HashSet<>();
        qftVehicleTypeSet1.add(moped1);
        qftVehicleTypeSet1.add(moped2);
        qualificationType1.setVehicleTypeSet(qftVehicleTypeSet1);

        Set<VehicleType> qftVehicleTypeSet2 = new HashSet<>();
        qftVehicleTypeSet2.add(motorcycle1);
        qftVehicleTypeSet2.add(motorcycle2);
        qualificationType2.setVehicleTypeSet(qftVehicleTypeSet2);

        Set<VehicleType> qftVehicleTypeSet3 = new HashSet<>();
        qftVehicleTypeSet3.add(powerBike1);
        qftVehicleTypeSet3.add(powerBike2);
        qualificationType3.setVehicleTypeSet(qftVehicleTypeSet3);

        Set<VehicleType> qftVehicleTypeSet4 = new HashSet<>();
        qftVehicleTypeSet4.add(car1);
        qftVehicleTypeSet4.add(car2);
        qualificationType4.setVehicleTypeSet(qftVehicleTypeSet4);

        Set<VehicleType> qftVehicleTypeSet5 = new HashSet<>();
        qftVehicleTypeSet5.add(smallCar1);
        qftVehicleTypeSet5.add(smallCar2);
        qualificationType5.setVehicleTypeSet(qftVehicleTypeSet5);

        Set<VehicleType> qftVehicleTypeSet6 = new HashSet<>();
        qftVehicleTypeSet6.add(bus1);
        qftVehicleTypeSet6.add(bus2);
        qualificationType6.setVehicleTypeSet(qftVehicleTypeSet6);

        Set<VehicleType> qftVehicleTypeSet7 = new HashSet<>();
        qftVehicleTypeSet7.add(bus3);
        qftVehicleTypeSet7.add(bus4);
        qualificationType7.setVehicleTypeSet(qftVehicleTypeSet7);

        Set<VehicleType> qftVehicleTypeSet8 = new HashSet<>();
        qftVehicleTypeSet8.add(truck1);
        qftVehicleTypeSet8.add(truck2);
        qualificationType8.setVehicleTypeSet(qftVehicleTypeSet8);

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
        tripDetails1.setVehicle(vehicle1);
        tripDetails1.setEmployee(TripDAO.getQualificatedEmployee(employee1, tripDetails1.getVehicle().getVehicleType1(), tripDetails1));
        //TODO: it returns dto object and we need employee
        TripDetails tripDetails2 = new TripDetails("Elm Str.", "Oak Str.",
                currentDate, LocalDate.of(2024, 5, 20),
                TransportCompanyDAO.getTransportCompanyById(2));
        tripDetails2.setId(2);
        tripDetails2.setVehicle(vehicle1);
//        tripDetails2.setEmployee(employee2);


        TripDetails tripDetails3 = new TripDetails("Pine Str.", "Maple Str.",
                currentDate, LocalDate.of(2024, 6, 25),
                TransportCompanyDAO.getTransportCompanyById(3));
        tripDetails3.setId(3);
        tripDetails3.setVehicle(vehicle2);
//        tripDetails3.setEmployee(employee2);


        TripDetails tripDetails4 = new TripDetails("Cherry Str.", "Walnut Str.",
                currentDate, LocalDate.of(2024, 7, 30),
                TransportCompanyDAO.getTransportCompanyById(4));
        tripDetails4.setId(4);
        tripDetails4.setVehicle(vehicle2);
//        tripDetails4.setEmployee(employee3);


        //adding trips that have arrived

        TripDetails tripDetails5 = new TripDetails("Cherry Str.", "Walnut Str.",
                LocalDate.of(2023, 7, 30), LocalDate.of(2024, 1, 1),
                TransportCompanyDAO.getTransportCompanyById(3));
        tripDetails5.setId(5);
        tripDetails5.setVehicle(vehicle3);
//        tripDetails5.setEmployee(employee1);


        TripDetails tripDetails6 = new TripDetails("Cherry Str.", "Walnut Str.",
                LocalDate.of(2023, 7, 30), LocalDate.of(2024, 1, 1),
                TransportCompanyDAO.getTransportCompanyById(3));
        tripDetails6.setId(6);
        tripDetails6.setVehicle(vehicle3);
//        tripDetails6.setEmployee(employee1);

        TripDAO.saveOrUpdateTripDetails(tripDetails1);
        TripDAO.saveOrUpdateTripDetails(tripDetails2);
        TripDAO.saveOrUpdateTripDetails(tripDetails3);
        TripDAO.saveOrUpdateTripDetails(tripDetails4);
        TripDAO.saveOrUpdateTripDetails(tripDetails5);
        TripDAO.saveOrUpdateTripDetails(tripDetails6);


        //deletion
//        TripDAO.deleteTrip(TripDAO.getTripDetailsById(3));

        //in trip_details table an order is set first
        //we fetch information about the the type of order
        // depending on the order(goods or passengers )

        //////////////////////////////////////  ORDER DETAILS   //////////////////////////////////////
        customer1.setBalance(BigDecimal.valueOf(2000));
        customer2.setBalance(BigDecimal.valueOf(3000));
        CustomerDAO.saveOrUpdateCustomer(customer1);
        CustomerDAO.saveOrUpdateCustomer(customer2);

        // Create additional OrderDetails instances
                                                        //use dao method for the price here
        OrderDetails orderDetails1 = new OrderDetails(BigDecimal.valueOf(30), false);   //set trip details id after everything in trip details is set properly throught the setter
        orderDetails1.setId(1);
        orderDetails1.setTripDetails(tripDetails1);
        OrderDetailsDAO.updateOrderDetails(orderDetails1);
        OrderDetailsDAO.saveOrUpdateCreateOrder(orderDetails1, customer1);

        OrderDetails orderDetails2 = new OrderDetails(BigDecimal.valueOf(30), false);   //set trip details id after everything in trip details is set properly throught the setter
        orderDetails2.setId(2);
        orderDetails2.setTripDetails(tripDetails2);
        OrderDetailsDAO.updateOrderDetails(orderDetails2);
        OrderDetailsDAO.saveOrUpdateCreateOrder(orderDetails2, customer1);

        OrderDetails orderDetails3 = new OrderDetails(BigDecimal.valueOf(40), true);
        orderDetails3.setId(3);
        orderDetails3.setTripDetails(tripDetails3);
        OrderDetailsDAO.updateOrderDetails(orderDetails3);
        OrderDetailsDAO.saveOrUpdateCreateOrder(orderDetails3, customer2);

        OrderDetails orderDetails4 = new OrderDetails(BigDecimal.valueOf(50), false);
        orderDetails4.setId(4);
        orderDetails4.setTripDetails(tripDetails4);
        OrderDetailsDAO.updateOrderDetails(orderDetails4);
        OrderDetailsDAO.saveOrUpdateCreateOrder(orderDetails4, customer2);




        tripDetails1.setOrderDetails(orderDetails1);
        tripDetails2.setOrderDetails(orderDetails2);
        tripDetails3.setOrderDetails(orderDetails3);
        tripDetails4.setOrderDetails(orderDetails4);
        tripDetails5.setOrderDetails(orderDetails1);
        tripDetails6.setOrderDetails(orderDetails2);

        TripDAO.saveOrUpdateTripDetails(tripDetails1);
        TripDAO.saveOrUpdateTripDetails(tripDetails2);
        TripDAO.saveOrUpdateTripDetails(tripDetails3);
        TripDAO.saveOrUpdateTripDetails(tripDetails4);
        TripDAO.saveOrUpdateTripDetails(tripDetails5);
        TripDAO.saveOrUpdateTripDetails(tripDetails6);

        /////////////////////////////   set goods_order_details ///////////////////////////////////


        //////////////////////////////////////7.  CRITERIA AND SORTING   ///////////////////////////////////////////
        System.out.println("createCompany:");
//        TransportCompanyDAO.createCompany(new TransportCompany(/* Provide transport company details */));

        System.out.println("getTransportCompanyById:");
        System.out.println(TransportCompanyDAO.getTransportCompanyById(1));

        System.out.println("getCompanies:");
        System.out.println(TransportCompanyDAO.getCompanies());

        System.out.println("countCompanies:");
        System.out.println(TransportCompanyDAO.countCompanies());

        System.out.println("getCompaniesDTO:");
        System.out.println(TransportCompanyDAO.getCompaniesDTO());

        System.out.println("getCompanyEmployees:");
        System.out.println(TransportCompanyDAO.getCompanyEmployees(1));

//        System.out.println("companiesFindByBalanceBetween:");
//        System.out.println(TransportCompanyDAO.companiesFindByBalanceBetween(BigDecimal.ZERO, BigDecimal.TEN));

        System.out.println("companiesFindByNameStartingWith:");
        System.out.println(TransportCompanyDAO.companiesFindByNameStartingWith("F"));

        System.out.println("getOrderedCompaniesByName:");
        System.out.println(TransportCompanyDAO.getOrderedCompaniesByName());

        System.out.println("getOrderedCompaniesByIncome:");
        System.out.println(TransportCompanyDAO.getOrderedCompaniesByIncome());



        //B.
        System.out.println();
        System.out.println("getEmployeesByQualificationAndSalary:");
        System.out.println(EmployeeDAO.getEmployeesByQualificationAndSalary(qualificationType4.getName(), BigDecimal.valueOf(2000), BigDecimal.valueOf(7000)));
        System.out.println("getEmployeesWithSalaryAbove:");
        System.out.println(EmployeeDAO.getEmployeesWithSalaryAbove(BigDecimal.valueOf(2000)));
        System.out.println("getEmployeesWithSalaryBelow:");
        System.out.println(EmployeeDAO.getEmployeesWithSalaryBelow(BigDecimal.valueOf(2000)));

        System.out.println("getEmployeesWithSalaryBelowDTO:");
        System.out.println(EmployeeDAO.getEmployeesWithSalaryBelowDTO(BigDecimal.valueOf(3400)));
        System.out.println("getEmployeesWithSalaryAboveDTO:");
        System.out.println(EmployeeDAO.getEmployeesWithSalaryAboveDTO(BigDecimal.valueOf(3400)));

        //C.
        System.out.println("tripDetailsFindByDestination:");
        System.out.println(TripDAO.tripDetailsFindByDestination("Walnut Str."));

        //TODO: B.C
        ////////////////////////////////////////////////    9. REFERENCES   ///////////////////////////

        System.out.println("ARRIVED TRIPS: " +   TripDAO.getCompletedTripsDTO());        //9.

        System.out.println("NUMBER OF ARRIVED TRIPS: " + TripDAO.getCompletedTripsCountDTO()); //9. a ot zadanieto

        System.out.println("Total Price of Completed Trips: " + TripDAO.getTotalPriceOfCompletedTrips());   //TODO:check if it works okay

//        System.out.println("list of employees and the count of trips each employee has completed:" + EmployeeDAO.getDriversAndCompletedTripsCount());

        System.out.println();
        EmployeeDAO.printDriversAndCompletedTripsCount();       //9.
        System.out.println();

        System.out.println("Employee incomes: " + EmployeeDAO.getEmployeeIncomesDTO());

        System.out.println("getTotalPriceOfCompletedTrips:" + TripDAO.getTotalPriceOfCompletedTrips());

        ////////////////////////////////////TRYING DAOS //////////////////////////////////
        System.out.println();
        System.out.println("GET EMPLOYEES DTO:");
        System.out.println(EmployeeDAO.getEmployees());
        System.out.println(" ordered by position in ascending order:");
//        System.out.println(EmployeeDAO.sortEmployeesByQualificationType(EmployeeDAO.getEmployees())); //todo: fix the method

    }
}

//TODO: make many to many qualification type and vehicle type and make check in the dao when creating a trip or smh
//TODO: START FROM 106 LINE
//TODO: REMOVE CUSTOMER OBLIGATIONS TABLE - done
//TODO: makes new record, therefore its not setting  the id's properly - fixed

//TODO: connect qualification table with vehicletype table - DONE many to many
//my idea is that when we connect them with many to many relationship a new table will be created
// a new table will be created, and if somehow we take an id of one table it will correspond to a
//record from other table, therefor not letting the employee drive something that he doesnt have a -
//qualification for

//todo: do the math
//todo: make it so that when a an order is placed

//TODO!!!!!: create a new column in vehicle_type - qualification type - created table - many to many
//extract qualification type from employee_qualification_type
//update Employee constructor so that it includes qualification type not null
//update the employees in Main
//now when creating a trip and assigning an employee - check his qualification
//if not qualified - throw CUSTOM exception
//else - creat the trip record in the table

//when creating trip_details record, vehicle id u must set - not nullable
//then u must set employee, employee qualification(extract from many to many employee_qualification_type)
//must match vehicle type(extract it from vehicle_type)
//if not qualified - throw CUSTOM exception
//else - creat the trip record in the table


///in trip_details there is goods_id which we need to search in goods_order_details to see orderedgoods
//we search which goods with order_id = x, are there
//then we check their price per good
//we miltiply (goodsX x goodsXprice)+(goodsXweight x CompanysChargeX) + (goodsY x goodsYprice) + (goodsYweight x CompanysChargeY) .... N
//we do