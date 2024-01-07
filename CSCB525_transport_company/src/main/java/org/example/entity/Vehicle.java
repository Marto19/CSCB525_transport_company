package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "transport_company_id")  //n:1 - Vehicles:TransportCompany in TC
    private TransportCompany transportCompany;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "vehicle_type_id") // Column referencing GoodsType
    private VehicleType vehicleType1;
//TODO: make the connection one to many - done


    public Vehicle(TransportCompany transportCompany, VehicleType vehicleType1) {
        this.transportCompany = transportCompany;
        this.vehicleType1 = vehicleType1;
    }
    public Vehicle(VehicleType vehicleType1) {
        this.vehicleType1 = vehicleType1;
    }


    public Vehicle(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public VehicleType getVehicleType1() {
        return vehicleType1;
    }

    public void setVehicleType1(VehicleType vehicleType1) {
        this.vehicleType1 = vehicleType1;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", transportCompany=" + transportCompany +
                ", vehicleType1=" + vehicleType1 +
                '}';
    }
}
