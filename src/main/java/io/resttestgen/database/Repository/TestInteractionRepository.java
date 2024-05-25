package io.resttestgen.database.Repository;

import io.resttestgen.database.Model.TestInteraction;
import io.resttestgen.database.Model.TestSequence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestInteractionRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public TestInteractionRepository() {
        this.emf = Persistence.createEntityManagerFactory("rtg_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public TestInteraction add(TestInteraction testInteraction) {
        entityManager.getTransaction().begin();
        entityManager.persist(testInteraction);
        entityManager.getTransaction().commit();
        return testInteraction;
    }

    public TestInteraction findById(Long id){
        return entityManager.find(TestInteraction.class, id);
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
