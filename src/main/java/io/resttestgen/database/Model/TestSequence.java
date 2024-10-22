package io.resttestgen.database.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

@Document(collection = "test_sequence")  // Indica che questa classe è un documento MongoDB
public class TestSequence {

    @Id  // Identificativo del documento
    private Long id;

    @DBRef  // Riferimento al documento Job
    private Job job;

    private Timestamp generatedAt;
    private String name;
    private String readOnlyParameter;
    private String generator;

    @DBRef  // Riferimento alle interazioni di test
    private Set<TestInteraction> testInteractions = new LinkedHashSet<>();

    @DBRef  // Riferimento ai risultati dei test
    private Set<TestResult> testResults = new LinkedHashSet<>();

    // Costruttori, getter, e setter

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
