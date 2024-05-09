package io.resttestgen.database.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "test_sequence")
public class TestSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Column(name = "generated_at")
    private Timestamp generatedAt;


    @Column(name = "name")
    private String name;


    @Column(name = "read_only_parameter")
    private String readOnlyParameter;


    @Column(name = "generator")
    private String generator;

    @OneToMany(mappedBy = "sequence")
    private Set<TestInteraction> testInteractions = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Timestamp getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(Timestamp generatedAt) {
        this.generatedAt = generatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReadOnlyParameter() {
        return readOnlyParameter;
    }

    public void setReadOnlyParameter(String readOnlyParameter) {
        this.readOnlyParameter = readOnlyParameter;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public Set<TestInteraction> getTestInteractions() {
        return testInteractions;
    }

    public void setTestInteractions(Set<TestInteraction> testInteractions) {
        this.testInteractions = testInteractions;
    }

    public Set<TestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(Set<TestResult> testResults) {
        this.testResults = testResults;
    }

    @OneToMany(mappedBy = "sequence")
    private Set<TestResult> testResults = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "TestSequence{" +
                "id=" + id +
                ", job=" + job +
                ", generatedAt=" + generatedAt +
                ", name='" + name + '\'' +
                ", readOnlyParameter='" + readOnlyParameter + '\'' +
                ", generator='" + generator + '\'' +
                '}';
    }
}