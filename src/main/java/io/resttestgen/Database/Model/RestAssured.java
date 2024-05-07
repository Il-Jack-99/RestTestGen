package io.resttestgen.Database.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "rest_assured")
public class RestAssured {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "nominal_file_name")
    private String nominalFileName;

    @Lob
    @Column(name = "error_file_name")
    private String errorFileName;

    @Lob
    @Column(name = "nominal_file", columnDefinition = "bytea")
    private byte[] nominalFile;

    @Lob
    @Column(name = "error_file", columnDefinition = "bytea")
    private byte[] errorFile;

    @Column(name = "job_id")
    private Long jobId;
}
