package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle_type")
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_of_vehicle_type")
    private long idOfVehicleType;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @OneToMany(mappedBy = "vehicleType1", fetch = FetchType.LAZY)
    private List<Vehicle> vehicle = new ArrayList<>();
//TODO: make the connection one to many - done

    public VehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleType(String vehicleType, List<Vehicle> vehicle) {
        this.vehicleType = vehicleType;
        this.vehicle = vehicle;
    }


    public VehicleType(){}

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
