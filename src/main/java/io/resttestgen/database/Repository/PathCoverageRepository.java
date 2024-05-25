package io.resttestgen.database.Repository;

import io.resttestgen.database.Model.PathCoverage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PathCoverageRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public PathCoverageRepository() {
        this.emf = Persistence.createEntityManagerFactory("rtg_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public PathCoverage add(PathCoverage pathCoverage){
        entityManager.getTransaction().begin();
        entityManager.persist(pathCoverage);
        entityManager.getTransaction().commit();
        return pathCoverage;
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
