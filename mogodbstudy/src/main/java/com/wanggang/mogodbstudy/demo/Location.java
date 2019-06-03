package com.wanggang.mogodbstudy.demo;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-24 09:29
 **/
public class Location {
    private String type = "Point";
    private double coordinates[];

    public Location(){}

    public Location(double x, double y) {
        this.coordinates = new double[]{x, y};
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
