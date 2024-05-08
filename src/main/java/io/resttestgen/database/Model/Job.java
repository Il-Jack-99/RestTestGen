package io.resttestgen.database.Model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "auth_token")
    private String authToken;

    @Column(name = "description")
    private String description;

    @Column(name = "job_state", nullable = false, length = 50)
    private String jobState;

    @Column(name = "strategy_name")
    private String strategyName;

    @Column(name = "timestamp_end")
    private Timestamp timestampEnd;

    @Column(name = "timestamp_start")
    private Timestamp timestampStart;

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", authToken='" + authToken + '\'' +
                ", description='" + description + '\'' +
                ", jobState='" + jobState + '\'' +
                ", strategyName='" + strategyName + '\'' +
                ", timestampEnd=" + timestampEnd +
                ", timestampStart=" + timestampStart +
                '}';
    }

}