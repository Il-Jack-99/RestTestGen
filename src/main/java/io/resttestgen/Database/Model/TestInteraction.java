package io.resttestgen.Database.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "\"TestInteraction\"")
public class TestInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "\"requestMethod\"")
    private String requestMethod;

    @Lob
    @Column(name = "\"requestURL\"")
    private String requestURL;

    @Column(name = "\"requestHeader\"")
    @Type(type = "org.hibernate.type.TextType")
    private String requestHeader;

    @Column(name = "\"requestBody\"")
    @Type(type = "org.hibernate.type.TextType")
    private String requestBody;

    @Column(name = "\"requestSentAt\"")
    private Timestamp requestSentAt;

    @Lob
    @Column(name = "\"responseProtocol\"")
    private String responseProtocol;

    @Lob
    @Column(name = "\"responseStatusCode\"")
    private String responseStatusCode;

    @Column(name = "\"responseHeaders\"")
    @Type(type = "org.hibernate.type.TextType")
    private String responseHeaders;

    @Column(name = "\"responseBody\"")
    @Type(type = "org.hibernate.type.TextType")
    private String responseBody;

    @Lob
    @Column(name = "\"responseReceivedAt\"")
    private Timestamp responseReceivedAt;

    @Lob
    @Column(name = "\"executionTime\"")
    private String executionTime;

    @Lob
    @Column(name = "\"testStatus\"")
    private String testStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"sequenceId\"")
    private TestSequence sequence;

}