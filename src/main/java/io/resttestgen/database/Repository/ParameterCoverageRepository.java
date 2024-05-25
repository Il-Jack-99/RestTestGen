package io.resttestgen.database.Repository;

import io.resttestgen.database.Model.ParameterCoverage;
import io.resttestgen.database.Model.StatusCodeCoverage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ParameterCoverageRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public ParameterCoverageRepository(){
        this.emf = Persistence.createEntityManagerFactory("rtg_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public ParameterCoverage add(ParameterCoverage parameterCoverage){
        entityManager.getTransaction().begin();
        entityManager.persist(parameterCoverage);
        entityManager.getTransaction().commit();
        return parameterCoverage;
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
