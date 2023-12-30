package org.example.entity;

import javax.persistence.*;

//Creating table instead of an Enum so that we can be more flexible and datasecute
//adding it to the session factory util
@Entity
@Table(name = "goods_type")
public class GoodsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_of_position_type")
    private long idOfPositionType;

    @Column(name = "position_type")
    private String positionType;


    public GoodsType(long idOfPositionType, String positionType) {
        this.idOfPositionType = idOfPositionType;
        this.positionType = positionType;
    }
    public GoodsType(){}

    public long getIdOfPositionType() {
        return idOfPositionType;
    }

    public void setIdOfPositionType(long idOfPositionType) {
        this.idOfPositionType = idOfPositionType;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "idOfPositionType=" + idOfPositionType +
                ", positionType='" + positionType + '\'' +
                '}';
    }
}
