package org.example.entity;

import jakarta.validation.constraints.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;


@Entity
@Table(name = "transport_company")
public class TransportCompany implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long idTransportCompany;

    @NotBlank(message = "Company name cannot be blank!")
    @Size(max = 20, message = "Company name has to be with up to 20 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Company name has to start with capital letter!")
    @Column(name = "name")
    private String name;

    @Column(name = "income")
    @PositiveOrZero
    private BigDecimal income;

    @Column(name = "goods_trip_price_per_kg")
    @Positive
    private BigDecimal goodsTripPrice;

    @Column(name = "passenger_trip_price")
    @Positive
    private BigDecimal passengerTripPrice;


    @OneToMany(mappedBy = "transportCompany", fetch = FetchType.LAZY) //stranata, kqoto uprawlqwa wryzkata e w drugiq klas
    private Set<Employee> employeeSet = new HashSet<>();                //1:n - transportCompany:Employees

    @OneToMany(mappedBy = "transportCompany", fetch = FetchType.LAZY) //1:n - transportCompany:Vehicles
    private List<Vehicle> vehicleListToVehicle = new ArrayList<>();

    @OneToMany(mappedBy = "transportCompany", fetch = FetchType.LAZY)  //1:n - transportCompany:Obligations
    private Set<Salary> obligationsSet = new HashSet<>();

    @OneToMany(mappedBy = "transportCompany", fetch = FetchType.LAZY)  //1:n - transportCompany:Trip - attribute - vehicle to trip
    private List<TripDetails> tripDetailsList = new ArrayList<>();

    @OneToMany(mappedBy = "transportCompany", fetch = FetchType.LAZY)   //1:n - transportCompany:Trip - attribute - vehicle to trip
    private List<Vehicle> vehicleListToTrip = new ArrayList<>();


    public TransportCompany(String name, BigDecimal income, BigDecimal goodsTripPrice, BigDecimal passengerTripPrice) {
        this.name = name;
        this.income = income;
        this.goodsTripPrice = goodsTripPrice;
        this.passengerTripPrice = passengerTripPrice;
    }

    public TransportCompany(String name, BigDecimal income) {
        this.name = name;
        this.income = income;
    }
    public TransportCompany(String name) {
        this.name = name;
    }

    public TransportCompany() {

    }

    public long getIdTransportCompany() {
        return idTransportCompany;
    }

    public void setIdTransportCompany(long idTransportCompany) {
        this.idTransportCompany = idTransportCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public List<Vehicle> getVehicleSet() {
        return vehicleListToVehicle;
    }

    public void setVehicleSet(List<Vehicle> vehicleSet) {
        this.vehicleListToVehicle = vehicleSet;
    }

    public List<Vehicle> getVehicleListToVehicle() {
        return vehicleListToVehicle;
    }

    public void setVehicleListToVehicle(List<Vehicle> vehicleListToVehicle) {
        this.vehicleListToVehicle = vehicleListToVehicle;
    }

    public Set<Salary> getObligationsSet() {
        return obligationsSet;
    }

    public void setObligationsSet(Set<Salary> obligationsSet) {
        this.obligationsSet = obligationsSet;
    }

    public List<TripDetails> getTripList() {
        return tripDetailsList;
    }

    public void setTripList(List<TripDetails> tripDetailsList) {
        this.tripDetailsList = tripDetailsList;
    }

    public List<Vehicle> getVehicleListToTrip() {
        return vehicleListToTrip;
    }

    public void setVehicleListToTrip(List<Vehicle> vehicleListToTrip) {
        this.vehicleListToTrip = vehicleListToTrip;
    }

    public BigDecimal getGoodsTripPrice() {
        return goodsTripPrice;
    }

    public void setGoodsTripPrice(BigDecimal goodsTripPrice) {
        this.goodsTripPrice = goodsTripPrice;
    }

    public BigDecimal getPassengerTripPrice() {
        return passengerTripPrice;
    }

    public void setPassengerTripPrice(BigDecimal passengerTripPrice) {
        this.passengerTripPrice = passengerTripPrice;
    }

    public List<TripDetails> getTripDetailsList() {
        return tripDetailsList;
    }

    public void setTripDetailsList(List<TripDetails> tripDetailsList) {
        this.tripDetailsList = tripDetailsList;
    }


    @Override
    public String toString() {
        return "TransportCompany{" +
                "idTransportCompany=" + idTransportCompany +
                ", name='" + name + '\'' +
                ", income=" + income +
                ", goodsTripPrice=" + goodsTripPrice +
                ", passengerTripPrice=" + passengerTripPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportCompany that = (TransportCompany) o;
        return idTransportCompany == that.idTransportCompany && Objects.equals(name, that.name) && Objects.equals(income, that.income) && Objects.equals(goodsTripPrice, that.goodsTripPrice) && Objects.equals(passengerTripPrice, that.passengerTripPrice) && Objects.equals(employeeSet, that.employeeSet) && Objects.equals(vehicleListToVehicle, that.vehicleListToVehicle) && Objects.equals(obligationsSet, that.obligationsSet) && Objects.equals(tripDetailsList, that.tripDetailsList) && Objects.equals(vehicleListToTrip, that.vehicleListToTrip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransportCompany, name, income, goodsTripPrice, passengerTripPrice, employeeSet, vehicleListToVehicle, obligationsSet, tripDetailsList, vehicleListToTrip);
    }
}
