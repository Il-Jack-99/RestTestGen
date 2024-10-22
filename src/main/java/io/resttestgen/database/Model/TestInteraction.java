package io.resttestgen.database.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.sql.Timestamp;

@Document(collection = "test_interaction")  // Indica che questa classe è un documento MongoDB
public class TestInteraction {

    @Id  // Identificativo del documento
    private Long id;

    private Timestamp executionTime;
    private String requestBody;
    private String requestHeader;
    private String requestMethod;
    private Timestamp requestSentAt;
    private String requestUrl;
    private String responseBody;
    private String responseHeaders;
    private String responseProtocol;
    private Timestamp responseReceivedAt;
    private String responseStatusCode;
    private String testStatus;

    @DBRef  // Riferimento alla sequenza di test associata
    private TestSequence sequence;

    // Costruttori, getter e setter

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

    @Override
    public String toString() {
        return "TestInteraction{" +
                "id=" + id +
                ", executionTime=" + executionTime +
                ", requestBody='" + requestBody + '\'' +
                ", requestHeader='" + requestHeader + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestSentAt=" + requestSentAt +
                ", requestUrl='" + requestUrl + '\'' +
                ", responseBody='" + responseBody + '\'' +
                ", responseHeaders='" + responseHeaders + '\'' +
                ", responseProtocol='" + responseProtocol + '\'' +
                ", responseReceivedAt=" + responseReceivedAt +
                ", responseStatusCode='" + responseStatusCode + '\'' +
                ", testStatus='" + testStatus + '\'' +
                ", sequence=" + sequence +
                '}';
    }
}

