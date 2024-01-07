package org.example.entity;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "products_name")
    @Size(min = 1, max = 30, message = "Name must be between 1 and 30 characters long!")
    @Pattern(regexp = "^([A-Z].*)", message = "Product's name should start with a capital letter!")
    private String name;

    @Positive
    @Column(name = "weight")
    @NotNull
    private double weight;


    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "goods_type_id") // Column referencing GoodsType
    @NotNull
    private GoodsType goodsType;


//    @ManyToMany(fetch =  FetchType.LAZY)
//    private Set<TripDetails> tripDetails = new HashSet<>();
    @ManyToMany(fetch =  FetchType.LAZY)
    private Set<OrderDetails> orderDetailsSet;
    public Goods(String name,@NotNull double weight, @NotNull GoodsType goodsType) {
        this.name = name;
        this.weight = weight;
        this.goodsType = goodsType;
    }

//    public Goods(String name, @NotNull double weight, GoodsType goodsType) {
//        this.name = name;
//        this.weight = weight;
//        this.goodsType = goodsType;
////        this.tripDetails = tripDetails;
//    }

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
