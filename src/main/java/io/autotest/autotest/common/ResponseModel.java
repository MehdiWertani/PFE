package io.autotest.autotest.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseModel implements Serializable {

    private String body;

    public ResponseModel(String body) {
        this.body = body;
    }
}
