package io.resttestgen.database.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "coverage_stat")  // Indica che questa classe è un documento MongoDB
public class CoverageStat {

    @Id  // Identificativo del documento
    private Long id;

    @DBRef  // Riferimento al documento Job
    private Job job;

    private String coverageType;  // Tipo di copertura
    private int documented;  // Numero di elementi documentati
    private int documentedTested;  // Numero di elementi documentati e testati
    private int notDocumentedTested;  // Numero di elementi non documentati e testati
    private double rate;  // Percentuale di copertura

    // Costruttore vuoto
    public CoverageStat() {
    }

    // Costruttore completo
    public CoverageStat(Long id, Job job, String coverageType, int documented, int documentedTested, int notDocumentedTested, double rate) {
        this.id = id;
        this.job = job;
        this.coverageType = coverageType;
        this.documented = documented;
        this.documentedTested = documentedTested;
        this.notDocumentedTested = notDocumentedTested;
        this.rate = rate;
    }

    // Getter e Setter per ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter per Job
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    // Getter e Setter per CoverageType
    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    // Getter e Setter per Documented
    public int getDocumented() {
        return documented;
    }

    public void setDocumented(int documented) {
        this.documented = documented;
    }

    // Getter e Setter per DocumentedTested
    public int getDocumentedTested() {
        return documentedTested;
    }

    public void setDocumentedTested(int documentedTested) {
        this.documentedTested = documentedTested;
    }

    // Getter e Setter per NotDocumentedTested
    public int getNotDocumentedTested() {
        return notDocumentedTested;
    }

    public void setNotDocumentedTested(int notDocumentedTested) {
        this.notDocumentedTested = notDocumentedTested;
    }

    // Getter e Setter per Rate
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    // Metodo toString per la rappresentazione della classe
    @Override
    public String toString() {
        return "CoverageStat{" +
                "id=" + id +
                ", job=" + job +
                ", coverageType='" + coverageType + '\'' +
                ", documented=" + documented +
                ", documentedTested=" + documentedTested +
                ", notDocumentedTested=" + notDocumentedTested +
                ", rate=" + rate +
                '}';
    }
}
