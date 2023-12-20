package org.example.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "transport_company")
public class TransportCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long idTransportCompany;
    @Column(name = "name")
    private String name;
    @Column(name = "income")
    private BigDecimal income;
    @OneToMany(mappedBy = "transportCompany") //stranata, kqoto uprawlqwa wryzkata e w drugiq klas
    private Set<Employee> employeeSet = new HashSet<>();                //1:n - transportCompany:Employees
    @OneToMany(mappedBy = "transportCompany") //1:n - transportCompany:Vehicles
    private List<Vehicle> vehicleListToVehicle = new ArrayList<>();
    @OneToMany(mappedBy = "transportCompany")  //1:n - transportCompany:Obligations
    private Set<Obligations> obligationsSet = new HashSet<>();
    @OneToMany(mappedBy = "transportCompany")  //1:n - transportCompany:Trip - attribute - vehicle to trip
    private List<TripDetails> tripDetailsList = new ArrayList<>();
    @OneToMany(mappedBy = "transportCompany")   //1:n - transportCompany:Trip - attribute - vehicle to trip
    private List<Vehicle> vehicleListToTrip = new ArrayList<>();

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

    public Set<Obligations> getObligationsSet() {
        return obligationsSet;
    }

    public void setObligationsSet(Set<Obligations> obligationsSet) {
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
}
