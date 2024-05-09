package io.resttestgen.database.Repository;

import io.resttestgen.database.Model.TestInteraction;
import io.resttestgen.database.Model.TestResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestResultRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public TestResultRepository() {
        this.emf = Persistence.createEntityManagerFactory("rtg_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public TestResult add(TestResult testResult) {
        entityManager.getTransaction().begin();
        entityManager.persist(testResult);
        entityManager.getTransaction().commit();
        return testResult;
    }
}
