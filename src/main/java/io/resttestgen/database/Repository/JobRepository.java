package io.resttestgen.database.Repository;

import io.resttestgen.database.Model.Job;
import org.yaml.snakeyaml.Yaml;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

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

    public Job findFromFileById(){
        Long jobId = retriveJobId();
        if(jobId != null){
            return find(jobId);
        }else{
            return null;
        }

    }

    public Long retriveJobId(){
        Long jobId = null;

        try{
            Yaml yaml = new Yaml();
            FileInputStream inputStream = new FileInputStream("rtg-config.yml");

            Map<String, Object> yamlData = yaml.load(inputStream);

            if(yamlData.containsKey("jobId")){
                Object idObj = yamlData.get("jobId");
                if(idObj instanceof Number){
                    jobId = ((Number) idObj).longValue();
                } else {
                    System.out.println("jobId not valid");
                }
            }else{
                System.out.println("no jobId found");
            }

            inputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jobId;
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
