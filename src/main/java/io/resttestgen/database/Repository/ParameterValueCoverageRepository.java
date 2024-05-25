package io.resttestgen.database.Repository;

import io.resttestgen.database.Model.ParameterCoverage;
import io.resttestgen.database.Model.ParameterValueCoverage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ParameterValueCoverageRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public ParameterValueCoverageRepository(){
        this.emf = Persistence.createEntityManagerFactory("rtg_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public ParameterValueCoverage add(ParameterValueCoverage parameterValueCoverage){
        entityManager.getTransaction().begin();
        entityManager.persist(parameterValueCoverage);
        entityManager.getTransaction().commit();
        return parameterValueCoverage;
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
