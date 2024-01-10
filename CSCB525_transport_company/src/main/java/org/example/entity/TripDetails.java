package org.example.entity;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "trip_details")
public class TripDetails implements Serializable {          ////8. ot zadanieto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Column(name = "starting_point")
    @NotNull
    @Size(min = 4, max = 100, message = "Starting point must be between 4 and 100 characters")
    private String startingPoint;
    @Column(name = "end_point")
    @NotNull
    @Size(min = 7, max = 100, message = "End point must be between 4 and 100 characters long!")
    private String endPoint;
    @Column(name = "departure_date")
    private LocalDate departureDate;
    @Column(name = "arrival_date")
    private LocalDate arrivalDate;
    //Adding the foreign keys below
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_company_id")
    private TransportCompany transportCompany;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    @NotNull
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;

    //TODO: add cost column?

    //TODO: create goods table, because you'll need its foreign key here
    //creating the table
//    @ManyToMany(mappedBy = "tripDetails", fetch = FetchType.LAZY)
//    private Set<Goods> goodsList = new HashSet<>();


//    @OneToOne(mappedBy = "tripDetails", fetch = FetchType.LAZY) //relationship trip:customerObligations - 1:1
//    private CustomerObligation customerObligation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;


    @AssertTrue(message = "Departure date should be before the arrival date!")
    private boolean isDepartureBeforeArrival() {
        return departureDate == null || arrivalDate == null || !departureDate.isAfter(arrivalDate);
    }

    @AssertTrue(message = "Destination must be different from the starting point!")
    private boolean isDestinationDifferentFromStartingPoint() {
        return startingPoint == null || endPoint == null || !startingPoint.equals(endPoint);
    }

    public TripDetails(@NotNull String startingPoint, @NotNull String endPoint,
                       LocalDate departureDate, LocalDate arrivalDate,
                       TransportCompany transportCompany, Vehicle vehicle){
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.transportCompany = transportCompany;
        this.vehicle = vehicle;

    }

    public TripDetails(@NotNull String startingPoint, @NotNull String endPoint,
                       LocalDate departureDate, LocalDate arrivalDate,
                       TransportCompany transportCompany){
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.transportCompany = transportCompany;

    }

    public TripDetails(){}


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

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public OrderDetails getPayments() {
        return orderDetails;
    }

    public void setPayments(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    //    public Set<Goods> getGoodsList() {
//        return goodsList;
//    }
//
//    public void setGoodsList(Set<Goods> goodsList) {
//        this.goodsList = goodsList;
//    }




    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", startingPoint='" + startingPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
//                ", transportCompany=" + transportCompany +
//                ", vehicle=" + vehicle +
//                ", goodsList=" + goodsList +
//                ", payments=" + orderDetails +
                '}';
    }
}
