package org.example.entity;

import jakarta.validation.constraints.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "name")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters long!")
    @Pattern(regexp = "^([A-Z].*)\s([a-zA-Z].*)", message = "First name should start with a capital letter and there should be at least a first and last name!")
    private String name;

//    @Column(name = "id_transport_company")
//    private long transportCompanyId;
//    @OneToOne(fetch =  FetchType.LAZY)                           //connection between obligations and employee - 1:1
    @Positive
    @Column(name="salary")
    @Digits(integer = 4, fraction = 2, message = "Salaries should start from 1000.00 and have 2 digits after the decimal point!")
    private BigDecimal salary;
//    private Obligations obligations;    //this is their salary

    @ManyToOne(fetch =  FetchType.LAZY)                                  //n:1 - Employee:TransportCompany
    @JoinColumn(name = "transport_company_id") // Define the name of the foreign key column
    private TransportCompany transportCompany;

    @ManyToMany
    private Set<org.example.entity.QualificationType> qualificationTypeSet = new HashSet<>(); //employee will create employee_qualification_type table

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private Set<TripDetails> tripDetailsSet;

    public Employee(@NotNull String name, BigDecimal salary, TransportCompany transportCompany) {
        this.name = name;
//        this.qualificationTypeSet = qualificationTypeSet;
        this.salary = salary;
        this.transportCompany = transportCompany;
//        this.transportCompanyId = transportCompany.getIdTransportCompany();
    }
    public Employee(@NotNull String name, BigDecimal salary) {
        this.name = name;
//        this.qualificationTypeSet = qualificationTypeSet;
        this.salary = salary;
//        this.transportCompanyId = transportCompany.getIdTransportCompany();
    }


    public Employee(@NotNull String name) {     //TODO: qualificationTypeSet doesn let Employee record to be recorder into the table of Employee
        this.name = name;

    }

    public Employee() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

//    public long getTransportCompanyId() {
//        return transportCompanyId;
//    }
//
//    public void setTransportCompanyId(long transportCompanyId) {
//        this.transportCompanyId = transportCompanyId;
//    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Set<QualificationType> getQualificationTypeSet() {
        return qualificationTypeSet;
    }

    public void setQualificationTypeSet(Set<QualificationType> qualificationTypeSet) {
        this.qualificationTypeSet = qualificationTypeSet;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
//enumns in entities should be ints/long ints since we can reference them to the actual position, they will be keys