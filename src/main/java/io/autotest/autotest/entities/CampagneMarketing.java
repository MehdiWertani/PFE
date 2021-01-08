package io.autotest.autotest.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "Campagne_Marketings")
public class CampagneMarketing implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String date_start;
    private String date_end;
    private String canal_type;
    private String execution_type;
    private Long smsNumber;
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @PrePersist
    public void initCreationDate() {
        this.creationDate = new Date();
    }

//    @OneToMany(mappedBy = "CampagneMarketings",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private Set<TestIteration> testIterations;
}
