package io.resttestgen.Database.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "\"TestSequence\"")
public class TestSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "\"readOnlyParameter\"")
    private String readOnlyParameter;

    @Lob
    @Column(name = "generator")
    private String generator;

    @Lob
    @Column(name = "name")
    private String name;

    @Column(name = "\"generatedAt\"")
    private OffsetDateTime generatedAt;

    @NotNull
    @Column(name = "\"jobId\"", nullable = false)
    private Long jobId;

}