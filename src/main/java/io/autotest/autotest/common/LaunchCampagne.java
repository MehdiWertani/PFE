package io.autotest.autotest.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class LaunchCampagne implements Serializable {

    private String iterationName;
    private Long campagneId;
    private String campagneName;
    private String startDate;
    private String endDate;
    private boolean isDownload;
}
