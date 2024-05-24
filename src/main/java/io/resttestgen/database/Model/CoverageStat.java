package io.resttestgen.database.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "coverage_stats")
public class CoverageStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "coverage_type")
    private String coverageType;

    @Column(name = "documented")
    private Integer documented;

    @Column(name = "documented_tested")
    private Integer documentedTested;

    @Column(name = "not_documented_tested")
    private Integer notDocumentedTested;


    @Column(name = "rate")
    private Float rate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    public CoverageStat(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public Integer getDocumented() {
        return documented;
    }

    public void setDocumented(Integer documented) {
        this.documented = documented;
    }

    public Integer getDocumentedTested() {
        return documentedTested;
    }

    public void setDocumentedTested(Integer documentedTested) {
        this.documentedTested = documentedTested;
    }

    public Integer getNotDocumentedTested() {
        return notDocumentedTested;
    }

    public void setNotDocumentedTested(Integer notDocumentedTested) {
        this.notDocumentedTested = notDocumentedTested;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

}