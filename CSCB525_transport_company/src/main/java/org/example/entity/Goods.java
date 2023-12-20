package org.example.entity;

import org.example.enums.GoodsType;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long id;
    @Column(name = "weight")
    @NotNull
    private double weight;
    @Column(name = "goods_type")
    @Enumerated
    @NotNull
    private GoodsType goodsType;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private TripDetails tripDetails;

    public Goods(@NotNull double weight, @NotNull GoodsType goodsType) {
        this.weight = weight;
        this.goodsType = goodsType;
    }

    public Goods(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", weight=" + weight +
                //", goodsType=" + goodsType +
                '}';
    }

    //TODO: maybe remove all the setters for the id in all classes
    //TODO: think about the relations between goods and trip, does it have to be reversed?
}
