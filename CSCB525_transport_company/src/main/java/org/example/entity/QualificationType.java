package org.example.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "qualification_type")
public class QualificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)    private long id;

    @Column(name = "qualification_type_name",  nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "qualificationTypeSet")
    private Set<Employee> employeeSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<VehicleType> vehicleTypeSet;

    public QualificationType(String name) {
        this.name = name;
    }

    public QualificationType(){}

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

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public Set<VehicleType> getVehicleTypeSet() {
        return vehicleTypeSet;
    }

    public void setVehicleTypeSet(Set<VehicleType> vehicleTypeSet) {
        this.vehicleTypeSet = vehicleTypeSet;
    }

    @Override
    public String toString() {
        return "QualificationType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualificationType that = (QualificationType) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
