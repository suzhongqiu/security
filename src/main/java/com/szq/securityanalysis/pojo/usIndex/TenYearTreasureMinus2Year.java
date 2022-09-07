package com.szq.securityanalysis.pojo.usIndex;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.szq.securityanalysis.common.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/** 十年期国债收益率减2年期国债收益率
 * @Author szq
 * @Description TODO
 * @Date 2022/8/22 23:00
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="TenYearTreasureMinus2Year")
public class TenYearTreasureMinus2Year {

    @Id
    private String id;

    @JsonFormat(pattern = Constant.NORMAL_DATE_FORMAT,timezone = "utc+8")
    private Date date;

    private Double percent;
}
