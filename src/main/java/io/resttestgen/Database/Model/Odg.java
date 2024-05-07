package io.resttestgen.Database.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "\"Odg\"")
public class Odg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "odg")
    @Type(type = "org.hibernate.type.TextType")
    private String odg;

    @Column(name = "\"jobId\"")
    private Long jobId;

}