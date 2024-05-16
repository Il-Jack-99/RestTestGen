package io.resttestgen.database.Repository;

import io.resttestgen.database.Model.RestAssured;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class RestAssuredRepository {


    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public RestAssuredRepository() {
        this.emf = Persistence.createEntityManagerFactory("rtg_pu");
        this.entityManager = this.emf.createEntityManager();

    }

    public RestAssured add(RestAssured restAssured){
        entityManager.getTransaction().begin();
        entityManager.persist(restAssured);
        entityManager.getTransaction().commit();
        return restAssured;
    }


}
