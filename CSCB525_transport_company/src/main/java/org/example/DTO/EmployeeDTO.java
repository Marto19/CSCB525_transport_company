package org.example.DTO;

import org.example.entity.QualificationType;
import org.example.entity.TransportCompany;

import java.util.Set;

public class EmployeeDTO {
    private long id;
    private Set<QualificationType> qualificationTypeSet;
    private String name;
    private TransportCompany transportCompany;

    public EmployeeDTO(long id, Set<QualificationType> qualificationTypeSet, String name, TransportCompany transportCompany) {
        this.id = id;
        this.qualificationTypeSet = qualificationTypeSet;
        this.name = name;
        this.transportCompany = transportCompany;
    }

    public long getId() {
        return id;
    }

    public Set<QualificationType> getQualificationTypeSet() {
        return qualificationTypeSet;
    }

    public String getName() {
        return name;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", qualificationTypeSet=" + qualificationTypeSet +
                ", name='" + name + '\'' +
                ", transportCompany=" + transportCompany +
                '}';
    }
}
