package io.resttestgen.database.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "status_code_coverage")
public class StatusCodeCoverage {

    @Id
    private Long id;

    @DBRef
    private Job job;

    private String category;
    private String endpoint;
    private String method;
    private String statusCode;

    // Costruttori, getter e setter
    public StatusCodeCoverage() {
    }

    public StatusCodeCoverage(Long id, Job job, String category, String endpoint, String method, String statusCode) {
        this.id = id;
        this.job = job;
        this.category = category;
        this.endpoint = endpoint;
        this.method = method;
        this.statusCode = statusCode;
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

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
