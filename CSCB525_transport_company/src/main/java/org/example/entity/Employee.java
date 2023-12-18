package org.example.entity;

import org.example.enums.QualificationType;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "qualification_type")
    private QualificationType qualificationType;
    @Column(name = "name")
    private String name;
    @ManyToOne                                  //n:1 - Employee:TransportCompany
    private TransportCompany transportCompany;

    public Employee(QualificationType qualificationType, String name, TransportCompany transportCompany) {
        this.qualificationType = qualificationType;
        this.name = name;
        this.transportCompany = transportCompany;
    }

    public Employee(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public QualificationType getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(QualificationType qualificationType) {
        this.qualificationType = qualificationType;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", qualificationType=" + qualificationType +
                ", name='" + name + '\'' +
                ", transportCompany=" + transportCompany +
                '}';
    }
}
