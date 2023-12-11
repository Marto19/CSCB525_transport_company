package org.example.entity;

import org.example.VehicleType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "vehicle")
    private List<TransportCompany> transportCompanies;

    private VehicleType vehicleType;

    public Vehicle(long id, List<TransportCompany> transportCompanies, VehicleType vehicleType) {
        this.id = id;
        this.transportCompanies = transportCompanies;
        this.vehicleType = vehicleType;
    }

    public Vehicle() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
