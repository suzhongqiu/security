package com.szq.securityanalysis.service.crawData.impl;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.bulk.BulkWriteResult;
import com.szq.securityanalysis.pojo.usIndex.TenYearTreasure;
import com.szq.securityanalysis.pojo.usIndex.TenYearTreasureMinus2Year;
import com.szq.securityanalysis.pojo.usIndex.TotalAssets;
import com.szq.securityanalysis.pojo.usIndex.TwoYearTreasure;
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
    public JSONObject queryAllTenYearTreasureMinus2(){
        List<TenYearTreasureMinus2Year> minusIndex = mongoTemplate.findAll(TenYearTreasureMinus2Year.class);
        List<TwoYearTreasure> twoYearIndex = mongoTemplate.findAll(TwoYearTreasure.class);
        List<TenYearTreasure> tenYearIndex = mongoTemplate.findAll(TenYearTreasure.class);
        JSONObject resJs = new JSONObject();
        resJs.put("minusIndex", minusIndex);
        resJs.put("twoYearIndex", twoYearIndex);
        resJs.put("tenYearIndex", tenYearIndex);
        return resJs;
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

    @Override
    public void saveTwoYearTreasure(Map<Date, Double> rawDataMap) {
        List<TwoYearTreasure> insertList = new ArrayList<>();
        for (Map.Entry<Date, Double> entry: rawDataMap.entrySet()) {
            TwoYearTreasure temp = new TwoYearTreasure();
            temp.setDate(entry.getKey());
            temp.setPercent(entry.getValue());
            insertList.add(temp);
        }


        List<Pair<Query, Update>> insertOrUpdateList = new ArrayList<>(insertList.size());
        insertList.forEach(index->{
            Query query = new Query(Criteria.where("date").is(index.getDate()));
            Update update = new Update();
            update.set("date", index.getDate());
            update.set("percent", index.getPercent());

            Pair<Query, Update> updatePair = Pair.of(query, update);
            insertOrUpdateList.add(updatePair);
        });
        BulkOperations operations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, "TwoYearTreasure").upsert(insertOrUpdateList);
        BulkWriteResult re = operations.execute();
    }

    @Override
    public void saveTotalAssets(Map<Date, Integer> rawDataMap) {
        List<TotalAssets> insertList = new ArrayList<>();
        for (Map.Entry<Date, Integer> entry: rawDataMap.entrySet()) {
            TotalAssets temp = new TotalAssets();
            temp.setDate(entry.getKey());
            temp.setValue(entry.getValue());
            insertList.add(temp);
        }


        List<Pair<Query, Update>> insertOrUpdateList = new ArrayList<>(insertList.size());
        insertList.forEach(index->{
            Query query = new Query(Criteria.where("date").is(index.getDate()));
            Update update = new Update();
            update.set("date", index.getDate());
            update.set("value", index.getValue());

            Pair<Query, Update> updatePair = Pair.of(query, update);
            insertOrUpdateList.add(updatePair);
        });
        BulkOperations operations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, "TotalAssets").upsert(insertOrUpdateList);
        BulkWriteResult re = operations.execute();
    }
}
