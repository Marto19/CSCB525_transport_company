package org.example.DTO;

import org.example.entity.VehicleType;

public class VehicleDTO {
    private long id;
    private VehicleType vehicleType;

    public VehicleDTO(long id, VehicleType vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
    }

    public long getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "id=" + id +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
