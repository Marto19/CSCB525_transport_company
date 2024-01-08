package org.example.DTO;

import java.time.LocalDate;

public class CarriedOutTripsReferenceDTO {
    private LocalDate arrivalDate;

    public CarriedOutTripsReferenceDTO(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "CarriedOutTripsReferenceDTO{" +
                "arrivalDate=" + arrivalDate +
                '}';
    }
}
