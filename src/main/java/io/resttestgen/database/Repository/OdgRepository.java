package io.resttestgen.database.Repository;

import io.resttestgen.database.Model.Odg;
import io.resttestgen.database.Model.RestAssured;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OdgRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public OdgRepository() {
        this.emf = Persistence.createEntityManagerFactory("rtg_pu");
        this.entityManager = this.emf.createEntityManager();

    }

    public Odg add(Odg odg){
        entityManager.getTransaction().begin();
        entityManager.persist(odg);
        entityManager.getTransaction().commit();
        return odg;
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
