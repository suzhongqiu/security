package com.szq.securityanalysis.service.crawData.impl;

import com.mongodb.bulk.BulkWriteResult;
import com.szq.securityanalysis.pojo.usIndex.TenYearTreasure;
import com.szq.securityanalysis.pojo.usIndex.TenYearTreasureMinus2Year;
import com.szq.securityanalysis.service.crawData.UsCrawDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/8/24 0:00
 * @Version 1.0.0
 */
@Service
public class UsCrawDataServiceImpl implements UsCrawDataService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveTenYearTreasureMinus2(Map<Date, Double> rawDataMap) {
        List<TenYearTreasureMinus2Year> insertList = new ArrayList<>();
        for (Map.Entry<Date, Double> entry: rawDataMap.entrySet()) {
            TenYearTreasureMinus2Year temp = new TenYearTreasureMinus2Year();
            temp.setId(UUID.randomUUID().toString());
            temp.setDate(entry.getKey());
            temp.setPercent(entry.getValue());
            insertList.add(temp);
        }

        // Collection<TenYearTreasureMinus2Year> tenYearTreasureMinus2Years = mongoTemplate.insertAll(insertList);
        // if(!insertList.isEmpty()) {
        //     insertList.forEach(index -> {
        //         Query query = new Query(Criteria.where("date").is(index.getDate()));
        //         // TenYearTreasureMinus2Year old = mongoTemplate.findOne(query, TenYearTreasureMinus2Year.class, "percent");
        //
        //     });
        // }

        List<Pair<Query, Update>> insertOrUpdateList = new ArrayList<>(insertList.size());
        insertList.forEach(index->{
            Query query = new Query(Criteria.where("date").is(index.getDate()));
            Update update = new Update();

            update.setOnInsert("_id", index.getId());
            update.set("date", index.getDate());
            update.set("percent", index.getPercent());

            Pair<Query, Update> updatePair = Pair.of(query, update);
            insertOrUpdateList.add(updatePair);
        });
        BulkOperations operations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, "TenYearTreasureMinus2Year").upsert(insertOrUpdateList);
        BulkWriteResult re = operations.execute();
    }

    // private void merge(List insertData, List<String> params, String collectionName) {
    //     List<Pair<Query, Update>> insertOrUpdateList = new ArrayList<>(insertData.size());
    //     insertData.forEach(index->{
    //         Query query = new Query(Criteria.where("date").is(index.getDate()));
    //         Update update = new Update();
    //
    //         update.setOnInsert("_id", index.getId());
    //     });
    // }

    @Override
    public List<TenYearTreasureMinus2Year> queryAllTenYearTreasureMinus2(){
        List<TenYearTreasureMinus2Year> allIndex = mongoTemplate.findAll(TenYearTreasureMinus2Year.class);
        return allIndex;
    }

    @Override
    public void saveTenYearTreasure(Map<Date, Double> rawDataMap) {
        List<TenYearTreasure> insertList = new ArrayList<>();
        for (Map.Entry<Date, Double> entry: rawDataMap.entrySet()) {
            TenYearTreasure temp = new TenYearTreasure();
            temp.setDate(entry.getKey());
            temp.setPercent(entry.getValue());
            insertList.add(temp);
        }

        // Collection<TenYearTreasureMinus2Year> tenYearTreasureMinus2Years = mongoTemplate.insertAll(insertList);
        // if(!insertList.isEmpty()) {
        //     insertList.forEach(index -> {
        //         Query query = new Query(Criteria.where("date").is(index.getDate()));
        //         // TenYearTreasureMinus2Year old = mongoTemplate.findOne(query, TenYearTreasureMinus2Year.class, "percent");
        //
        //     });
        // }

        List<Pair<Query, Update>> insertOrUpdateList = new ArrayList<>(insertList.size());
        insertList.forEach(index->{
            Query query = new Query(Criteria.where("date").is(index.getDate()));
            Update update = new Update();
            update.set("date", index.getDate());
            update.set("percent", index.getPercent());

            Pair<Query, Update> updatePair = Pair.of(query, update);
            insertOrUpdateList.add(updatePair);
        });
        BulkOperations operations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, "TenYearTreasure").upsert(insertOrUpdateList);
        BulkWriteResult re = operations.execute();
    }
}
