package io.resttestgen.database.Repository;

import io.resttestgen.database.Model.Job;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JobRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public JobRepository() {
        this.emf = Persistence.createEntityManagerFactory("rtg_pu");
        this.entityManager = this.emf.createEntityManager();
    }


    public Job find(Long id){
        return entityManager.find(Job.class, id);
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
