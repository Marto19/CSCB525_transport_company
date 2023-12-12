package org.example.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transport_company")
public class TransportCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTransportCompany;
    private String name;
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

    @Override
    public String toString() {
        return "TransportCompany{" +
                "idTransportCompany=" + idTransportCompany +
                ", name='" + name + '\'' +
                '}';
    }
}
