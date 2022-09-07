package com.szq.securityanalysis.service.crawData;

import com.szq.securityanalysis.pojo.usIndex.TenYearTreasureMinus2Year;

import java.util.Date;
import java.util.List;
import java.util.Map;

/** 爬取美国经济数据
 * @Author szq
 * @Description TODO
 * @Date 2022/8/23 23:58
 * @Version 1.0.0
 */
public interface UsCrawDataService {

    //保存爬取的数据
    void saveTenYearTreasureMinus2(Map<Date, Double> rawDataMap);

    //获取所有数据
    List<TenYearTreasureMinus2Year> queryAllTenYearTreasureMinus2();

    void saveTenYearTreasure(Map<Date, Double> rawDataMap);
}
