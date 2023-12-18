package org.example.dto;

import org.example.entity.TransportCompany;
import org.example.enums.QualificationType;

public class EmployeeDto {
    private long id;
    private QualificationType qualificationType;
    private String name;
    private TransportCompany transportCompany;

    public EmployeeDto(long id, QualificationType qualificationType, String name, TransportCompany transportCompany) {
        this.id = id;
        this.qualificationType = qualificationType;
        this.name = name;
        this.transportCompany = transportCompany;
    }

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
    //TODO: finish the dto
}
