package org.example.DTO;

public class DriverTripsDTO {
    private String driverName;
    private Long completedTripsCount;

    public DriverTripsDTO(String driverName, Long completedTripsCount) {
        this.driverName = driverName;
        this.completedTripsCount = completedTripsCount;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Long getCompletedTripsCount() {
        return completedTripsCount;
    }

    public void setCompletedTripsCount(Long completedTripsCount) {
        this.completedTripsCount = completedTripsCount;
    }

    @Override
    public String toString() {
        return "DriverTripsDTO{" +
                "driverName='" + driverName + '\'' +
                ", completedTripsCount=" + completedTripsCount +
                '}';
    }
}
