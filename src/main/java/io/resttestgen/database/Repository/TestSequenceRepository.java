package io.resttestgen.database.Repository;


import io.resttestgen.database.Model.TestSequence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestSequenceRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public TestSequenceRepository() {
        this.emf = Persistence.createEntityManagerFactory("rtg_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public TestSequence add(TestSequence testSequence) {
        entityManager.getTransaction().begin();
        entityManager.persist(testSequence);
        entityManager.getTransaction().commit();
        return testSequence;
    }



    public TestSequence findById(Long id){
        return entityManager.find(TestSequence.class, id);
    }



    public void close() {
        emf.close();
    }
}
