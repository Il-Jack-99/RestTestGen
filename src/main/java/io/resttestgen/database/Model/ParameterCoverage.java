package io.resttestgen.database.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "parameter_coverage")  // Indica che questa classe è un documento MongoDB
public class ParameterCoverage {

    @Id  // Identificativo del documento
    private Long id;

    @DBRef  // Riferimento al documento Job
    private Job job;

    private String category;  // Categoria della copertura (es. documented, notTested)
    private String method;  // Metodo HTTP (GET, POST, PUT, ecc.)
    private String endpoint;  // Endpoint relativo all'operazione
    private String parameter;  // Nome del parametro

    // Costruttore vuoto
    public ParameterCoverage() {
    }

    // Costruttore completo
    public ParameterCoverage(Long id, Job job, String category, String method, String endpoint, String parameter) {
        this.id = id;
        this.job = job;
        this.category = category;
        this.method = method;
        this.endpoint = endpoint;
        this.parameter = parameter;
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

    // Getter e Setter per Category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter e Setter per Method
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    // Getter e Setter per Endpoint
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    // Getter e Setter per Parameter
    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    // Metodo toString per la rappresentazione dell'oggetto
    @Override
    public String toString() {
        return "ParameterCoverage{" +
                "id=" + id +
                ", job=" + job +
                ", category='" + category + '\'' +
                ", method='" + method + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", parameter='" + parameter + '\'' +
                '}';
    }
}
