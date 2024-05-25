package io.resttestgen.database.Repository;

import io.resttestgen.database.Model.StatusCodeCoverage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StatusCodeCoverageRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public StatusCodeCoverageRepository(){
        this.emf = Persistence.createEntityManagerFactory("rtg_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public StatusCodeCoverage add(StatusCodeCoverage statusCodeCoverage){
        entityManager.getTransaction().begin();
        entityManager.persist(statusCodeCoverage);
        entityManager.getTransaction().commit();
        return statusCodeCoverage;
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }

}
