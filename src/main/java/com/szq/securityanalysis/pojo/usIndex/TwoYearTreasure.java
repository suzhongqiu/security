package com.szq.securityanalysis.pojo.usIndex;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.szq.securityanalysis.common.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/9/7 22:40
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwoYearTreasure {
    @JsonFormat(pattern = Constant.NORMAL_DATE_FORMAT, timezone = "UTC+8")
    private Date date;

    private Double percent;
}
