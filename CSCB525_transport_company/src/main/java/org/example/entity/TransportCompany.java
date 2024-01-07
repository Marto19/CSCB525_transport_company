package org.example.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import javax.persistence.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;


@Entity
@Table(name = "transport_company")
public class TransportCompany {
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


    public TransportCompany(String name, BigDecimal income) {
        this.name = name;
        this.income = income;
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

    @Override
    public String toString() {
        return "TransportCompany{" +
                "idTransportCompany=" + idTransportCompany +
                ", name='" + name + '\'' +
                ", income=" + income +
//                ", employeeSet=" + employeeSet +
//                ", vehicleListToVehicle=" + vehicleListToVehicle +
//                ", obligationsSet=" + obligationsSet +
//                ", tripList=" + tripDetailsList +
//                ", vehicleListToTrip=" + vehicleListToTrip +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportCompany that = (TransportCompany) o;
        return idTransportCompany == that.idTransportCompany && Objects.equals(name, that.name) && Objects.equals(income, that.income) && Objects.equals(employeeSet, that.employeeSet) && Objects.equals(vehicleListToVehicle, that.vehicleListToVehicle) && Objects.equals(obligationsSet, that.obligationsSet) && Objects.equals(tripDetailsList, that.tripDetailsList) && Objects.equals(vehicleListToTrip, that.vehicleListToTrip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransportCompany, name, income, employeeSet, vehicleListToVehicle, obligationsSet, tripDetailsList, vehicleListToTrip);
    }
}
