package org.example.DTO;

import org.example.entity.TransportCompany;
import org.example.enums.QualificationType;

public class EmployeeDTO {
    private long id;
    private QualificationType qualificationType;
    private String name;
    private TransportCompany transportCompany;

    public EmployeeDTO(long id, QualificationType qualificationType, String name, TransportCompany transportCompany) {
        this.id = id;
        this.qualificationType = qualificationType;
        this.name = name;
        this.transportCompany = transportCompany;
    }

    public long getId() {
        return id;
    }

    public QualificationType getQualificationType() {
        return qualificationType;
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
                ", qualificationType=" + qualificationType +
                ", name='" + name + '\'' +
                ", transportCompany=" + transportCompany +
                '}';
    }
}
