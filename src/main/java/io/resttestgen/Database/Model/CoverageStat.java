package io.resttestgen.Database.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "\"CoverageStats\"")
public class CoverageStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "\"coverageType\"")
    private String coverageType;

    @Column(name = "documented")
    private Integer documented;

    @Column(name = "\"documentedTested\"")
    private Integer documentedTested;

    @Column(name = "\"notDocumentedTested\"")
    private Integer notDocumentedTested;

    @Column(name = "rate")
    private float rate;

    @Column(name = "\"jobId\"")
    private Long jobId;

}