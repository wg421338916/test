package com.wanggang.mogodbstudy.demo;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-24 09:42
 **/

public interface CityRepository //extends MongoRepository
{
   // GeoResults<City2> findByPosition(Location point, Distance distance);
}
