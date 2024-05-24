package io.resttestgen.database.Repository;


import io.resttestgen.database.Model.OperationCoverage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OperationCoverageRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public OperationCoverageRepository() {
        this.emf = Persistence.createEntityManagerFactory("rtg_pu");
        this.entityManager = this.emf.createEntityManager();

    }

    public OperationCoverage add(OperationCoverage operationCoverage){
        entityManager.getTransaction().begin();
        entityManager.persist(operationCoverage);
        entityManager.getTransaction().commit();
        return operationCoverage;
    }
}
