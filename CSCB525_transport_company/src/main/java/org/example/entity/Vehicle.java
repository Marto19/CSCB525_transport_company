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

    @OneToMany
    @Column(name = "transport_company_id")
    private List<TransportCompany> transportCompany;

    private VehicleType vehicleType;

    public Vehicle(long id, TransportCompany transportCompany, VehicleType vehicleType) {
        this.id = id;
        this.transportCompany = transportCompany;
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
