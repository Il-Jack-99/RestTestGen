package io.resttestgen.database.Model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "path_coverage")
public class PathCoverage {

    @Id
    private Long id;

    @DBRef
    private Job job;

    private String category;
    private String path;

    // Costruttori, getter e setter
    public PathCoverage() {
    }

    public PathCoverage(Long id, Job job, String category, String path) {
        this.id = id;
        this.job = job;
        this.category = category;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
