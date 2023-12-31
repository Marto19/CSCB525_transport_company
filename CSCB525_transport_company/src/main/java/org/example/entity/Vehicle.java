package org.example.entity;

import org.example.enums.VehicleType;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "vehicle_type")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "transport_company_id")  //n:1 - Vehicles:TransportCompany in TC
    private TransportCompany transportCompany;

//    @OneToOne(fetch =  FetchType.LAZY)
//    @JoinColumn(name = "vehicle_type_id") // Column referencing GoodsType
//    private org.example.entity.VehicleType vehicleType1;
//TODO: make the connection one to many

    public Vehicle(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vehicleType=" + vehicleType +
//                ", transportCompany=" + transportCompany +
                '}';
    }
}
