package org.example.entity;

import org.example.VehicleType;

//@Entity
//@Table(name = "vehicle")
public class Vehicle {
    //@Id
    private long id;
    //@ManyToOne
    //@JoinColumn(name = "transport_company_id", nullable = false)
    private TransportCompany transportCompany;
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
