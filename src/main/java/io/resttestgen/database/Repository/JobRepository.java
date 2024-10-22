package io.resttestgen.database.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import io.resttestgen.database.Model.Job;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JobRepository {

    @Autowired
    private MongoDatabase mongoDatabase;  // MongoDatabase iniettato

    // Nome della collection dove i job saranno salvati
    private static final String COLLECTION_NAME = "jobs";

    // Trova un Job per ID
    public Job find(Long id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);
        Document query = new Document("id", id);
        Document jobDoc = collection.find(query).first();

        if (jobDoc != null) {
            // Converte il documento MongoDB in un oggetto Job (mappatura manuale)
            Job job = new Job();
            job.setId(jobDoc.getLong("id"));
            job.setJobName(jobDoc.getString("jobName"));
            return job;
        }
        return null;
    }

    // Salva un Job nel database
    public void add(Job job) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);

        Document jobDoc = new Document("id", job.getId())
                .append("jobName", job.getJobName());

        collection.insertOne(jobDoc);
    }

    // Recupera il jobId da un file di configurazione YAML (resta invariato)
    public Long retriveJobId() {
        // La logica per recuperare il jobId dal file YAML rimane invariata
        // Questo dipende dall'implementazione del file di configurazione
        return null;  // Implementazione della logica di parsing YAML
    }

    // Trova un Job a partire dall'ID nel file YAML
    public Job findFromFileById() {
        Long jobId = retriveJobId();
        if (jobId != null) {
            return find(jobId);
        } else {
            return null;
        }
    }
}
