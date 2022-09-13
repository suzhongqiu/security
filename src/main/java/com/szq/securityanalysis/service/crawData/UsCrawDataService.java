package com.szq.securityanalysis.service.crawData;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.Map;

/** fred网爬取美国经济数据
 * @Author szq
 * @Description TODO
 * @Date 2022/8/23 23:58
 * @Version 1.0.0
 */
public interface UsCrawDataService {

    /**
     * @param rawDataMap 保存十年期国债收益率
     */
    void saveTenYearTreasureMinus2(Map<Date, Double> rawDataMap);

    /**
     * @return 查询十年期与两年期国债收益率的剪刀差
     */
    JSONObject queryAllTenYearTreasureMinus2();

    /**
     * @param rawDataMap 保存十年期国债收益率
     */
    void saveTenYearTreasure(Map<Date, Double> rawDataMap);

    /**
     * @param rawDataMap 保存两年期国债收益率
     */
    void saveTwoYearTreasure(Map<Date, Double> rawDataMap);

    /**
     * @param rawDataMap 保存爬取的数据
     */
    void saveTotalAssets(Map<Date, Integer> rawDataMap);
}
