package com.wanggang.mogodbstudy;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-23 14:36
 **/
@Document(collection = "city")
public class City implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    @Id
    private Long id;
    private Date positionEndTime;
    private Date createTime;
    private Date updateTime;

    private long distance;

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    private double[] position;//位置信息

    public City(){}

    public City(double lon, double lat) {
        double[] p = new double[]{lon, lat};
        this.position = p;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getPositionEndTime() {
        return positionEndTime;
    }

    public void setPositionEndTime(Date positionEndTime) {
        this.positionEndTime = positionEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}