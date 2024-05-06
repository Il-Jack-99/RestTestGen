package io.resttestgen.Database.Report;

import io.resttestgen.core.datatype.HttpMethod;
import io.resttestgen.core.datatype.HttpStatusCode;
import io.resttestgen.core.openapi.Operation;
import io.resttestgen.core.testing.TestStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class InteractionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Request fields
    //private final transient Operation referenceOperation; // Operation from the specification (typically, read only)
    //private transient Operation fuzzedOperation; // Operation populated by the fuzzer
    private HttpMethod requestMethod;
    private String requestURL;
    private String requestHeaders;
    private String requestBody;
    private Timestamp requestSentAt;

    // Response fields
    private String responseProtocol;
    private String responseStatusCode;
    private String responseHeaders;
    private String responseBody;
    private Timestamp responseReceivedAt;

    // Other fields
    private Timestamp executionTime;
    private String testStatus;

    @ManyToOne
    @JoinColumn(name = "report_entity_id")
    private ReportEntity reportEntity;
}
