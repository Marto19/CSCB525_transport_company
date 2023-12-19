package org.example.DTO;

import org.example.enums.GoodsType;

public class GoodsDTO {
    private long id;
    private double weight;
    private GoodsType goodsType;

    public GoodsDTO(long id, double weight, GoodsType goodsType) {
        this.id = id;
        this.weight = weight;
        this.goodsType = goodsType;
    }

    public long getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    @Override
    public String toString() {
        return "GoodsDTO{" +
                "id=" + id +
                ", weight=" + weight +
                ", goodsType=" + goodsType +
                '}';
    }
}
