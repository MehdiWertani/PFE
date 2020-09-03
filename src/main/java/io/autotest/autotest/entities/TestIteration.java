package io.autotest.autotest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name="Test_Iterations")
@NoArgsConstructor
public class TestIteration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long deliveryversion;
    private String expecteddeliverydate ;
    private String deliveryobjectif;
    private String state;
            private Long testduration;
//    @OneToMany(mappedBy = "TestIterations",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private Set<Test> pages;
    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="id",nullable = false)
    private CampagneMarketing campagneMarketing;*/

    public TestIteration(Long deliveryversion , String expecteddeliverydate, String deliveryobjectif
            , Long testduration,String state) { this.deliveryversion=deliveryversion;
            this.expecteddeliverydate=expecteddeliverydate;
            this.testduration=testduration;
            this.state=state;
            this.deliveryobjectif=deliveryobjectif;
    }
}
