package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "qualification_type")
public class QualificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "qualification_type_name")
    private String name;

    public QualificationType(long id, String name) {
        this.id = id;
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

    @Override
    public String toString() {
        return "QualificationType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    //TODO: make the connections
}
