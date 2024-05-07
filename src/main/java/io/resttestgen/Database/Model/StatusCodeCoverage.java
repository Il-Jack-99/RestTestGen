package io.resttestgen.Database.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "\"StatusCodeCoverage\"")
public class StatusCodeCoverage {
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
    @Column(name = "\"statusCode\"")
    private String statusCode;

    @Lob
    @Column(name = "category")
    private String category;

    @Column(name = "\"jobId\"")
    private Long jobId;

}