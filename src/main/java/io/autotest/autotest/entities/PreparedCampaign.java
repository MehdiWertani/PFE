package io.autotest.autotest.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;

@Entity
@Data
public class PreparedCampaign implements Serializable {

    @Id
    private String id;
    @Lob
    private String content;
}
