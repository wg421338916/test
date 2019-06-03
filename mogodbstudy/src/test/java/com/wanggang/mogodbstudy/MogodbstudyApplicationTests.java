package com.wanggang.mogodbstudy;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.geojson.Geometry;
import com.mongodb.client.model.geojson.Position;
import com.mongodb.client.result.UpdateResult;
import com.wanggang.mogodbstudy.demo.City2;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MogodbstudyApplicationTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void addMore() throws InterruptedException {
        AtomicLong ato = new AtomicLong(20000);
        CountDownLatch latch = new CountDownLatch(20000);

        double y = 28.669;
        double x = 114.032;

        IntStream.range(0, 5).forEach(ii -> {
            new Thread(() -> {
                for (long i = 0; i < 4000; i++) {
                    double x1 = x + Math.random();
                    add(x1, y, ato.incrementAndGet());
                    latch.countDown();
                }

            }).start();
        });

        latch.await();
    }

    private void add(double x, double y, long uid) {
        City2 u = new City2();

        com.mongodb.client.model.geojson.Point point = new com.mongodb.client.model.geojson.Point(new Position(x, y));
        u.setPosition(new GeoJsonPoint(x, y));
//        u.setPosition(npoint));

        u.setId(uid);

        Calendar rightNow = Calendar.getInstance();

        rightNow.add(Calendar.DAY_OF_YEAR, 1);//日期加10天
        Date endDate = rightNow.getTime();

        u.setPositionEndTime(endDate);
        u.setCreateTime(new Date());
        u.setUpdateTime(new Date());

        mongoTemplate.save(u);
    }

    @Test
    public void add2() {
        City2 u = new City2();
        u.setId(2l);
        Calendar rightNow = Calendar.getInstance();

        rightNow.add(Calendar.DAY_OF_YEAR, 1);//日期加10天
        Date endDate = rightNow.getTime();


        u.setPosition(new GeoJsonPoint(105.754484701156, 41.689607057699));
        u.setUpdateTime(endDate);
        u.setCreateTime(new Date());
        u.setUpdateTime(new Date());

        mongoTemplate.save(u);


    }

    @Test
    public void upsert() throws InterruptedException {
        double y = 114.032;
        BigDecimal bigDecimal = new BigDecimal(Double.toString(y));
        Calendar rightNow = Calendar.getInstance();

        rightNow.add(Calendar.DAY_OF_YEAR, 1);//日期加10天
        Date endDate = rightNow.getTime();

        AtomicLong ato = new AtomicLong(0);
        CountDownLatch latch = new CountDownLatch(20000);

        IntStream.range(0, 5).forEach(x -> {
            new Thread(() -> {
                for (long i = 0; i < 4000; i++) {
                    long l = ato.incrementAndGet();
                    Query query = Query.query(Criteria.where("id").is(l));

                    Update update = new Update()
                            .set("id", l)
                            .set("position", Arrays.asList(28.669d, bigDecimal.doubleValue()))
                            .set("positionEndTime", endDate)
                            .set("createTime", new Date())
                            .set("updateTime", new Date());

                    bigDecimal.add(new BigDecimal(0.01));

                    mongoTemplate.upsert(query, update, City.class);

                    latch.countDown();
                }
            }).start();
        });

        System.out.println("ok111");

        latch.await();

        System.out.println("ok");
    }

    @Test
    public void findOne() {
        // Query query = Query.query(Criteria.where("userName").is("test"));

        Query query = Query.query(Criteria.where("_id").is(1L));
        City u = mongoTemplate.findOne(query, City.class);

        System.out.println(u);
    }

    @Test
    public void update() {
        Query query = new Query(Criteria.where("id").is(1L));
        Update update = new Update().set("userName", "test").set("passWord", "22222");

        //更新查询返回结果集的第一条
        UpdateResult result = mongoTemplate.updateFirst(query, update, City.class);
    }

    @Test
    public void delete() {
        Query query = new Query(Criteria.where("id").is(1L));
        mongoTemplate.remove(query, City.class);
    }


    @Test
    public void findCircle() {
        List<BasicDBObject> basicDBObjectList = new ArrayList<>();

        BasicDBObject basicDBObject = new BasicDBObject("$geoNear",
                new BasicDBObject("near",
                        new BasicDBObject("type", "Point").append("coordinates", new double[]{104.705977010726, 41.921549795110}))
                        .append("distanceField", "distance")
                        .append("spherical", true)
                        .append("num", 10)
        );

        basicDBObjectList.add(basicDBObject);

        AggregateIterable<Document> aggregate = mongoTemplate.getCollection("city2").aggregate(basicDBObjectList);

        MongoCursor<Document> iterator = aggregate.iterator();

        iterator.forEachRemaining(document -> {
            System.out.println(document);
        });

    }

    @Test
    public void findCircle2() {

        long l = System.currentTimeMillis();
        NearQuery query = NearQuery.near(114.032, 28.669, Metrics.KILOMETERS);
        //query.maxDistance(1, Metrics.KILOMETERS);
        query.num(10);
        query.spherical(true);



//      Query q = Query.query()


        GeoResults<City2> geoResults = mongoTemplate.geoNear(query, City2.class, "city2");
        geoResults.iterator().forEachRemaining(City2 -> {
            System.out.println(City2);
        });

        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void x(){

        DBObject field=new BasicDBObject();
        field.put("_id",true);

//        Query query=new BasicQuery(new BasicDBObject(),field);
//        query.addCriteria(andCriteria);

        GeoJsonPoint point = new GeoJsonPoint(1,2);


        Geometry s=new com.mongodb.client.model.geojson.Point(new Position(1,1));

        System.out.println(s.toJson());
    }

}
