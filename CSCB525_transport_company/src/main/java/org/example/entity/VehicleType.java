package org.example.entity;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle_type")
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_of_vehicle_type", nullable = false, unique = true)
    private long idOfVehicleType;

    @Column(name = "vehicle_type")
    @Size(min = 2, max = 30, message = "Type must be between 2 and 30 characters long!")
    @Pattern(regexp = "^([A-Z].*)", message = "Vehicle type should start with a capital letter!")
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
