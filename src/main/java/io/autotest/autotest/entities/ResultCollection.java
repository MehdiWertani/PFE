package io.autotest.autotest.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class ResultCollection implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Long idCampagne;
    private String executionStatus;
    private String errorDescription;
    private Long succeededSmsNbr;
    private Long koSmsNbr;
}
