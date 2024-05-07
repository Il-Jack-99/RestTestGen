package io.resttestgen.Database.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "\"PathCoverage\"")
public class PathCoverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "path")
    private String path;

    @Lob
    @Column(name = "category")
    private String category;

    @Column(name = "\"jobId\"")
    private Long jobId;

}