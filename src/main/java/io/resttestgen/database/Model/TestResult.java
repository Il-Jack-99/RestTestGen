package io.resttestgen.database.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "test_result")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "message")
    private String message;


    @Column(name = "oracle")
    private String oracle;


    @Column(name = "result")
    private String result;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sequence_id")
    private TestSequence sequence;

    public TestResult(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public TestSequence getSequence() {
        return sequence;
    }

    public void setSequence(TestSequence sequence) {
        this.sequence = sequence;
    }


}