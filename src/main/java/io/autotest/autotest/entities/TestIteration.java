package io.autotest.autotest.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "Test_Iterations")
@NoArgsConstructor
public class TestIteration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iterationName;
    private Long deliveryversion;
    private String startdeliverydate;
    private String expecteddeliverydate;
    private String deliveryobjectif;
    private String state;
    private Long testduration;
    private String campagneid;
//    @OneToMany(mappedBy = "TestIterations",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private Set<Test> pages;
    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="id",nullable = false)
    private CampagneMarketing campagneMarketing;*/

    public TestIteration(Long deliveryversion, String expecteddeliverydate, String deliveryobjectif
            , Long testduration, String state) {
        this.deliveryversion = deliveryversion;
        this.expecteddeliverydate = expecteddeliverydate;
        this.testduration = testduration;
        this.state = state;
        this.deliveryobjectif = deliveryobjectif;
    }
}
