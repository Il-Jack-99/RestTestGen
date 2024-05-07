package io.resttestgen.Database.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "\"ParameterValueCoverage\"")
public class ParameterValueCoverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "method")
    private String method;

    @Lob
    @Column(name = "endpoint")
    private String endpoint;

    @Lob
    @Column(name = "parameter")
    private String parameter;

    @Lob
    @Column(name = "location")
    private String location;

    @Lob
    @Column(name = "value")
    private String value;

    @Lob
    @Column(name = "category")
    private String category;

    @Column(name = "\"jobId\"")
    private Long jobId;

}