package io.resttestgen.database.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "parameter_value_coverage")
public class ParameterValueCoverage {

    @Id
    private Long id;

    @DBRef
    private Job job;

    private String category;
    private String method;
    private String endpoint;
    private String parameter;
    private String value;

    // Costruttori, getter e setter
    public ParameterValueCoverage() {
    }

    public ParameterValueCoverage(Long id, Job job, String category, String method, String endpoint, String parameter, String value) {
        this.id = id;
        this.job = job;
        this.category = category;
        this.method = method;
        this.endpoint = endpoint;
        this.parameter = parameter;
        this.value = value;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
