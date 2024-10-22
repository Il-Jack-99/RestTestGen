package io.resttestgen.database.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "rest_assured")
public class RestAssured {

    @Id
    private Long id;

    @DBRef
    private Job job;

    private String nominalFileName;
    private byte[] nominalFile;

    private String errorFileName;
    private byte[] errorFile;

    // Costruttori, getter e setter
    public RestAssured() {
    }

    public RestAssured(Long id, Job job, String nominalFileName, byte[] nominalFile, String errorFileName, byte[] errorFile) {
        this.id = id;
        this.job = job;
        this.nominalFileName = nominalFileName;
        this.nominalFile = nominalFile;
        this.errorFileName = errorFileName;
        this.errorFile = errorFile;
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

    public String getNominalFileName() {
        return nominalFileName;
    }

    public void setNominalFileName(String nominalFileName) {
        this.nominalFileName = nominalFileName;
    }

    public byte[] getNominalFile() {
        return nominalFile;
    }

    public void setNominalFile(byte[] nominalFile) {
        this.nominalFile = nominalFile;
    }

    public String getErrorFileName() {
        return errorFileName;
    }

    public void setErrorFileName(String errorFileName) {
        this.errorFileName = errorFileName;
    }

    public byte[] getErrorFile() {
        return errorFile;
    }

    public void setErrorFile(byte[] errorFile) {
        this.errorFile = errorFile;
    }
}
