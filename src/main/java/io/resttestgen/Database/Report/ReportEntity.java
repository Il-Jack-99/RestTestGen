package io.resttestgen.Database.Report;

import io.resttestgen.core.testing.TestSequence;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public String readOnlyParameter;
    private String generator;
    private String name;
    private Timestamp generatedAt;

    @OneToMany(mappedBy = "reportEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InteractionEntity> interactions;





    private Long jobId;
}
