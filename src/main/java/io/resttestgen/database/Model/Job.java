package io.resttestgen.database.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jobs")  // Indica che questa classe è un documento MongoDB
public class Job {

    @Id  // Identificativo del documento
    private Long id;
    private String jobName;

    // Costruttore vuoto
    public Job() {
    }

    // Costruttore completo
    public Job(Long id, String jobName) {
        this.id = id;
        this.jobName = jobName;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                '}';
    }
}
