package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_type")
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_of_vehicle_type")
    private long idOfVehicleType;

    @Column(name = "vehicle_type")
    private String vehicleType;

//    @OneToOne(mappedBy = "vehicleType1", fetch = FetchType.LAZY)
//    private Vehicle vehicle;
//TODO: make the connection one to many

    public VehicleType(long idOfVehicleType, String vehicleType) {
        this.idOfVehicleType = idOfVehicleType;
        this.vehicleType = vehicleType;
    }

    public long getIdOfVehicleType() {
        return idOfVehicleType;
    }

    public void setIdOfVehicleType(long idOfVehicleType) {
        this.idOfVehicleType = idOfVehicleType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "VehicleType{" +
                "idOfVehicleType=" + idOfVehicleType +
                ", vehicleType='" + vehicleType + '\'' +
                '}';
    }
}
