package io.resttestgen.database.Repository;



import io.resttestgen.database.Model.CoverageStat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CoverageStatRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public CoverageStatRepository(){
        this.emf = Persistence.createEntityManagerFactory("rtg_pu");
        this.entityManager = this.emf.createEntityManager();
    }


    public CoverageStat add(CoverageStat coverageStat){
        entityManager.getTransaction().begin();
        entityManager.persist(coverageStat);
        entityManager.getTransaction().commit();
        return coverageStat;
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }

}
