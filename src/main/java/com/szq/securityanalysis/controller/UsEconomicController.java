package com.szq.securityanalysis.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szq.securityanalysis.common.constant.Constant;
import com.szq.securityanalysis.common.response.RequestResult;
import com.szq.securityanalysis.common.response.ResponseCode;
import com.szq.securityanalysis.pojo.usIndex.TenYearTreasureMinus2Year;
import com.szq.securityanalysis.service.crawData.UsCrawDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/8/18 22:05
 * @Version 1.0.0
 */
@RestController
@Api(tags = "美国经济数据")
@Slf4j
@RequestMapping("/usEconomic")
public class UsEconomicController {

     @Autowired
    UsCrawDataService usCrawDataService;

    @GetMapping("/save10Minus2YearRates")
    @ApiOperation("爬取十年期和两年期国债收益率差值")
    public RequestResult save10Minus2YearRates(){
        try {
            String url = Constant.TEN_YEAR_MINUS_TWO_RATES_URL;
            String paraStr = Constant.TEN_YEAR_MINUS_TWO_RATES_PARAMS;
            Connection connection = Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true)
                    .header("Content-Type", "application/json; charset=UTF-8");
            connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36");
            connection.requestBody(paraStr);
            Document document = connection.post();
            Element body = document.body();
            String bodyString = body.toString();
            String usefulString = bodyString.replace("<body>","").replace("</body>", "").replace("\n","");
            JSONObject usefulJson = JSONObject.parseObject(usefulString);
            JSONArray realData = usefulJson.getJSONArray("observations");
            Map<Date, Double> dataMap = new LinkedHashMap<>(realData.size());
            for (Object realDatum : realData) {
                JSONArray jsonData = (JSONArray) realDatum;
                for (int i=0; i< jsonData.size(); i++) {
                    JSONArray oneData = jsonData.getJSONArray(i);
                    Long timeStamp = oneData.getLong(0);
                    BigDecimal percent = oneData.getBigDecimal(1);
                    // String dateStr = DateUtils.date2String(new Date(timeStamp), Constant.NORMAL_DATE_FORMAT);
                    Date time = new Date(timeStamp);
                    if(percent == null) {
                        dataMap.put(time,  null);
                    } else {
                        dataMap.put(time,  percent.doubleValue());
                    }
                }
            }
            // System.out.println(usefulJson.toString());
            usCrawDataService.saveTenYearTreasureMinus2(dataMap);
            return new RequestResult(ResponseCode.OK, null, "success");
        } catch (IOException e) {
            e.printStackTrace();
            return new RequestResult(ResponseCode.INTERNAL_ERROR, null, e.getMessage());
        }
    }



    @GetMapping("/get10Minus2YearRates")
    @ApiOperation("获得十年期和两年期国债收益率差值")
    public RequestResult get10Minus2YearRates(){
        try {
            List<TenYearTreasureMinus2Year> tenYearTreasureMinus2Years = usCrawDataService.queryAllTenYearTreasureMinus2();
            return new RequestResult(ResponseCode.OK, tenYearTreasureMinus2Years, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new RequestResult(ResponseCode.INTERNAL_ERROR, null, e.getMessage());
        }
    }


    @GetMapping("/save10YearTreasure")
    @ApiOperation("爬取美国十年期国债收益率")
    public RequestResult save10YearTreasure(){
        try {
            String url = Constant.TEN_YEAR_TREASURE_URL;
            String paraStr = Constant.TEN_YEAR_TREASURE_PARAM;
            Connection connection = Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true)
                    .header("Content-Type", "application/json; charset=UTF-8");
            connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36");
            connection.requestBody(paraStr);
            Document document = connection.post();
            Element body = document.body();
            String bodyString = body.toString();
            String usefulString = bodyString.replace("<body>","").replace("</body>", "").replace("\n","");
            JSONObject usefulJson = JSONObject.parseObject(usefulString);
            JSONArray realData = usefulJson.getJSONArray("observations");
            Map<Date, Double> dataMap = new LinkedHashMap<>(realData.size());
            for (Object realDatum : realData) {
                JSONArray jsonData = (JSONArray) realDatum;
                for (int i=0; i< jsonData.size(); i++) {
                    JSONArray oneData = jsonData.getJSONArray(i);
                    Long timeStamp = oneData.getLong(0);
                    BigDecimal percent = oneData.getBigDecimal(1);
                    // String dateStr = DateUtils.date2String(new Date(timeStamp), Constant.NORMAL_DATE_FORMAT);
                    Date time = new Date(timeStamp);
                    if(percent == null) {
                        dataMap.put(time,  null);
                    } else {
                        dataMap.put(time,  percent.doubleValue());
                    }
                }
            }
            //System.out.println(usefulJson.toString());
            usCrawDataService.saveTenYearTreasure(dataMap);
            return new RequestResult(ResponseCode.OK, null, "success");
        } catch (IOException e) {
            e.printStackTrace();
            return new RequestResult(ResponseCode.INTERNAL_ERROR, null, e.getMessage());
        }
    }
}
