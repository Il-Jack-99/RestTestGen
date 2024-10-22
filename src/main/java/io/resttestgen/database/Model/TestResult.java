package io.resttestgen.database.Model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "test_result")  // Indica che questa classe è un documento MongoDB
public class TestResult {

    @Id  // Identificativo del documento
    private Long id;

    @DBRef  // Riferimento alla sequenza di test associata
    private TestSequence sequence;

    private String oracle;
    private String result;
    private String message;

    // Getter e Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestSequence getSequence() {
        return sequence;
    }

    public void setSequence(TestSequence sequence) {
        this.sequence = sequence;
    }

    public String getOracle() {
        return oracle;
    }

    public void setOracle(String oracle) {
        this.oracle = oracle;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "id=" + id +
                ", sequence=" + sequence +
                ", oracle='" + oracle + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
