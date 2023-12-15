package org.example.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long id;
    @Column(name = "starting_point")
    @NotNull
    private String startingPoint;
    @Column(name = "end_point")
    @NotNull
    private String endPoint;
    @Column(name = "departure_date")
    private LocalDate departureDate;
    @Column(name = "arrival_date")
    private LocalDate arrivalDate;
    //Adding the foreign keys below
    @ManyToOne
    @JoinColumn(name = "transport_company_id")
    private TransportCompany transportCompany;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    //TODO: create goods table, because you'll need its foreign key here
    //creating the table
    @OneToMany(mappedBy = "trip")
    private List<Goods> goodsList = new ArrayList<>();

    public Trip(){}

    public Trip(@NotNull String startingPoint, @NotNull String endPoint, LocalDate departureDate, LocalDate arrivalDate) {
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", startingPoint='" + startingPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
