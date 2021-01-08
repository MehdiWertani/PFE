package io.autotest.autotest.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author houssem.bouali
 * @Date 1/8/2021
 */
@Data
public class ExecutionResponse implements Serializable {

    private Long smsNumber;
    private String campaignId;
}
