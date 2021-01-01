package io.autotest.autotest.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author houssem.bouali
 * @Date 12/31/2020
 */
@Data
public class ResponseModel implements Serializable {

    private String body;

    public ResponseModel(String body) {
        this.body = body;
    }
}
