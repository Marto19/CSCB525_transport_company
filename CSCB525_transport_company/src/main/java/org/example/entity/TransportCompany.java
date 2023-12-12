package org.example.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transport_company")
public class TransportCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long idTransportCompany;
    @Column(name = "name")
    private String name;
    @Column(name = "income")
    private BigDecimal income;
    @OneToMany(mappedBy = "transportCompany", fetch = FetchType.EAGER) //stranata, kqoto uprawlqwa wryzkata e w drugiq klas
    private Set<Employee> employeeSet = new HashSet<>();

    @OneToMany(mappedBy = "transportCompany", fetch = FetchType.EAGER)
    private Set<Vehicle> vehicleSet = new HashSet<>();

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

    public Set<Vehicle> getVehicleSet() {
        return vehicleSet;
    }

    public void setVehicleSet(Set<Vehicle> vehicleSet) {
        this.vehicleSet = vehicleSet;
    }

    @Override
    public String toString() {
        return "TransportCompany{" +
                "idTransportCompany=" + idTransportCompany +
                ", name='" + name + '\'' +
                ", income=" + income +
                '}';
    }
}
