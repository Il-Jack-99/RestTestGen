package io.resttestgen.database.Model;


import javax.persistence.*;


@Entity
@Table(name = "rest_assured")
public class RestAssured {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "error_file")
    private byte[] errorFile;

    @Column(name = "nominal_file")
    private byte[] nominalFile;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;


    @Column(name = "error_file_name")
    private String errorFileName;

    @Column(name = "nominal_file_name")
    private String nominalFileName;

    public RestAssured(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getErrorFile() {
        return errorFile;
    }

    public void setErrorFile(byte[] errorFile) {
        this.errorFile = errorFile;
    }

    public byte[] getNominalFile() {
        return nominalFile;
    }

    public void setNominalFile(byte[] nominalFile) {
        this.nominalFile = nominalFile;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getErrorFileName() {
        return errorFileName;
    }

    public void setErrorFileName(String errorFileName) {
        this.errorFileName = errorFileName;
    }

    public String getNominalFileName() {
        return nominalFileName;
    }

    public void setNominalFileName(String nominalFileName) {
        this.nominalFileName = nominalFileName;
    }


}