package org.example.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    @NotNull
    private double weight;


    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "goods_type_id") // Column referencing GoodsType
    private GoodsType goodsType;


    @ManyToMany(fetch =  FetchType.LAZY)
    private Set<TripDetails> tripDetails = new HashSet<>();

    public Goods(String name,@NotNull double weight, @NotNull GoodsType goodsType) {
        this.name = name;
        this.weight = weight;
        this.goodsType = goodsType;
    }

    public Goods(String name, @NotNull double weight, GoodsType goodsType, Set<TripDetails> tripDetails) {
        this.name = name;
        this.weight = weight;
        this.goodsType = goodsType;
        this.tripDetails = tripDetails;
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

//    public GoodsType getGoodsType() {
//        return goodsType;
//    }
//
//    public void setGoodsType(GoodsType goodsType) {
//        this.goodsType = goodsType;
//    }

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
