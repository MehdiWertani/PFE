package io.autotest.autotest.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Tests")
@Data
public class Test implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name ;
    private  String description;
    private Long duration ;
    private Long priority ;
    private String type ;
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name="id",nullable = false)
//    private TestIteration testIteration;
//    @OneToOne(mappedBy = "Tests")
//    private TestResult testResult;
}
