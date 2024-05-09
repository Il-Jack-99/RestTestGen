package io.resttestgen.database.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "test_interaction")
public class TestInteraction {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Timestamp executionTime) {
        this.executionTime = executionTime;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Timestamp getRequestSentAt() {
        return requestSentAt;
    }

    public void setRequestSentAt(Timestamp requestSentAt) {
        this.requestSentAt = requestSentAt;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(String responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public String getResponseProtocol() {
        return responseProtocol;
    }

    public void setResponseProtocol(String responseProtocol) {
        this.responseProtocol = responseProtocol;
    }

    public Timestamp getResponseReceivedAt() {
        return responseReceivedAt;
    }

    public void setResponseReceivedAt(Timestamp responseReceivedAt) {
        this.responseReceivedAt = responseReceivedAt;
    }

    public String getResponseStatusCode() {
        return responseStatusCode;
    }

    public void setResponseStatusCode(String responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public TestSequence getSequence() {
        return sequence;
    }

    public void setSequence(TestSequence sequence) {
        this.sequence = sequence;
    }

    public TestInteraction(){

    }

    @Override
    public String toString() {
        return "TestInteraction{" +
                "id=" + id +
                ", executionTime='" + executionTime + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", requestHeader='" + requestHeader + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestSentAt=" + requestSentAt +
                ", requesturl='" + requestUrl + '\'' +
                ", responseBody='" + responseBody + '\'' +
                ", responseHeaders='" + responseHeaders + '\'' +
                ", responseProtocol='" + responseProtocol + '\'' +
                ", responseReceivedAt=" + responseReceivedAt +
                ", responseStatusCode='" + responseStatusCode + '\'' +
                ", testStatus='" + testStatus + '\'' +
                ", sequence=" + sequence +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "execution_time")
    private Timestamp executionTime;

    @Column(name = "request_body", columnDefinition = "TEXT")
    private String requestBody;

    @Column(name = "request_header", columnDefinition = "TEXT")
    private String requestHeader;


    @Column(name = "request_method")
    private String requestMethod;

    @Column(name = "request_sent_at")
    private Timestamp requestSentAt;


    @Column(name = "request_url")
    private String requestUrl;


    @Column(name = "response_body", columnDefinition = "TEXT")
    private String responseBody;

    @Column(name = "response_header", columnDefinition = "TEXT")
    private String responseHeaders;


    @Column(name = "response_protocol")
    private String responseProtocol;

    @Column(name = "response_received_at")
    private Timestamp responseReceivedAt;


    @Column(name = "response_status_code")
    private String responseStatusCode;


    @Column(name = "test_status")
    private String testStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sequence_id")
    private TestSequence sequence;

}