package org.example.DTO;

import org.example.entity.QualificationType;

import java.util.Set;

public class EmployeeQualificationSetDTO {
    private Long id;
    private Set<QualificationType> qualificationTypeSet;

    // Constructor
    public EmployeeQualificationSetDTO(Long id, Set<QualificationType> qualificationTypeSet) {
        this.id = id;
        this.qualificationTypeSet = qualificationTypeSet;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<QualificationType> getQualificationTypeSet() {
        return qualificationTypeSet;
    }

    public void setQualificationTypeSet(Set<QualificationType> qualificationTypeSet) {
        this.qualificationTypeSet = qualificationTypeSet;
    }
}
