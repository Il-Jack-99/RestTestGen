package io.resttestgen.database.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_interaction")
public class TestInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "execution_time")
    private String executionTime;

    @Column(name = "request_body", columnDefinition = "TEXT")
    private String requestBody;

    @Column(name = "request_header", columnDefinition = "TEXT")
    private String requestHeader;


    @Column(name = "request_method")
    private String requestMethod;

    @Column(name = "request_sent_at")
    private Instant requestSentAt;


    @Column(name = "requesturl")
    private String requesturl;


    @Column(name = "response_body", columnDefinition = "TEXT")
    private String responseBody;

    @Column(name = "response_header", columnDefinition = "TEXT")
    private String responseHeaders;


    @Column(name = "response_protocol")
    private String responseProtocol;

    @Column(name = "response_received_at")
    private Instant responseReceivedAt;


    @Column(name = "response_status_code")
    private String responseStatusCode;


    @Column(name = "test_status")
    private String testStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sequence_id")
    private TestSequence sequence;

}