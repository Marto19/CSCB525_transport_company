package org.example.DTO;

import java.time.LocalDate;

public class TripDetailsDTO {
    private long id;
    private String startingPoint;
    private String endPoint;
    private LocalDate departureDate;
    private LocalDate arrivalDate;

    public TripDetailsDTO(long id, String startingPoint, String endPoint, LocalDate departureDate, LocalDate arrivalDate) {
        this.id = id;
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public long getId() {
        return id;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public String toString() {
        return "TripDetailsDTO{" +
                "id=" + id +
                ", startingPoint='" + startingPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
