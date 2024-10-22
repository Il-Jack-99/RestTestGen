package io.resttestgen.database.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "odg")  // Indica che questa classe è un documento MongoDB
public class Odg {

    @Id  // Identificativo del documento
    private Long id;
    private String description;
    private String type;

    // Costruttore vuoto
    public Odg() {
    }

    // Costruttore completo
    public Odg(Long id, String description, String type) {
        this.id = id;
        this.description = description;
        this.type = type;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Odg{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
