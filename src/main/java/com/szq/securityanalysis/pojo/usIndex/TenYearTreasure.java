package com.szq.securityanalysis.pojo.usIndex;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.szq.securityanalysis.common.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/9/4 10:39
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "TenYearTreasure")
public class TenYearTreasure {

    @JsonFormat(pattern = Constant.NORMAL_DATE_FORMAT, timezone = "UTC+8")
    private Date date;

    private Double percent;

}
