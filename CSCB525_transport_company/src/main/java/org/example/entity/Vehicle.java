package org.example.entity;

import org.example.VehicleType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "vehicle")
    @Column(name = "transport_company")
    private List<TransportCompany> transportCompanies;

    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    public Vehicle(List<TransportCompany> transportCompanies, VehicleType vehicleType) {
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
