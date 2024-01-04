package org.example.entity;

import javax.persistence.*;

//Creating table instead of an Enum so that we can be more flexible and datasecute
//adding it to the session factory util
@Entity
@Table(name = "goods_type")
public class GoodsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_of_goods_type")
    private long idOfGoodsType;

    @Column(name = "goods_type")
    private String goodsType;

    @OneToOne(mappedBy = "goodsType", fetch = FetchType.LAZY)
    private Goods goods;        //bidirectional connection goodsType-Goods


    public GoodsType(long idOfGoodsType, String goodsType) {
        this.idOfGoodsType = idOfGoodsType;
        this.goodsType = goodsType;
    }
    public GoodsType(){}

    public long getIdOfPositionType() {
        return idOfGoodsType;
    }

    public void setIdOfPositionType(long idOfPositionType) {
        this.idOfGoodsType = idOfPositionType;
    }

    public String getPositionType() {
        return goodsType;
    }

    public void setPositionType(String positionType) {
        this.goodsType = positionType;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "idOfPositionType=" + idOfGoodsType +
                ", positionType='" + goodsType + '\'' +
                '}';
    }
}
