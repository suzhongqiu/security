package com.szq.securityanalysis.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/5/5 22:58
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResult {

    private Integer responseCode;

    private Object result;

    private String message;

}
