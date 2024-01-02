package org.example.entity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch =  FetchType.LAZY)                                  //n:1 - Employee:TransportCompany
    private TransportCompany transportCompany;
    @Transient
    private long transportCompanyId;
    @OneToOne(fetch =  FetchType.LAZY)                           //connection between obligations and employee - 1:1
    @Positive
    @Digits(integer = 4, fraction = 2, message = "Salaries should start from 1000.00 and have 2 digits after the decimal point!")
    private Obligations obligations;    //this is their salary

    @ManyToMany
    private Set<org.example.entity.QualificationType> qualificationTypeSet = new HashSet<>(); //employee will create employee_qualification_type table

    public Employee(@NotNull String name, @Nullable Set<QualificationType> qualificationTypeSet, @Nullable Obligations obligations, long transportCompanyId) {
        this.name = name;
        this.qualificationTypeSet = qualificationTypeSet;
        this.obligations = obligations;
        this.transportCompanyId = transportCompanyId;
//        this.transportCompany = transportCompany;
    }

    public Employee(@NotNull String name) {     //TODO: qualificationTypeSet doesn let Employee record to be recorder into the table of Employee
        this.name = name;
        this.qualificationTypeSet = qualificationTypeSet;
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

    public long getTransportCompanyId() {
        return transportCompanyId;
    }

    public void setTransportCompanyId(long transportCompanyId) {
        this.transportCompanyId = transportCompanyId;
    }

    public Obligations getObligations() {
        return obligations;
    }

    public void setObligations(Obligations obligations) {
        this.obligations = obligations;
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
