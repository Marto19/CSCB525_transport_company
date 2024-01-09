package org.example.DTO;

public class EmployeeTripCountDTO {
    private EmployeeDTO employeeDTO;
    private long completedTripsCount;

    public EmployeeTripCountDTO(EmployeeDTO employeeDTO, long completedTripsCount) {
        this.employeeDTO = employeeDTO;
        this.completedTripsCount = completedTripsCount;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public long getCompletedTripsCount() {
        return completedTripsCount;
    }

    public void setCompletedTripsCount(long completedTripsCount) {
        this.completedTripsCount = completedTripsCount;
    }

    // getters and setters

    @Override
    public String toString() {
        return "EmployeeTripCountDTO{" +
                "employeeDTO=" + employeeDTO +
                ", completedTripsCount=" + completedTripsCount +
                '}';
    }

}
