package io.resttestgen.Database.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "\"TestResult\"")
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "oracle")
    private String oracle;

    @Lob
    @Column(name = "result")
    private String result;

    @Lob
    @Column(name = "message")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"sequenceId\"")
    private TestSequence sequence;

}