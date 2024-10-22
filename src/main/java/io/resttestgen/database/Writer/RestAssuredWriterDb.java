package io.resttestgen.database.Writer;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.resttestgen.database.Model.Job;
import io.resttestgen.database.Model.RestAssured;
import org.bson.Document;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Repository
public class RestAssuredWriterDb {

    @Autowired
    private MongoDatabase mongoDatabase;  // Usa MongoDB per salvare i dati

    private Job job;
    private RestAssured restAssured = new RestAssured();
    private Map<String, String> nominalMap;
    private Map<String, String> errorMap;

    public RestAssuredWriterDb() {
        // Inizializza le mappe per nominalMap e errorMap
        this.nominalMap = new HashMap<>();
        this.errorMap = new HashMap<>();

        // Inizializza il job (puoi personalizzare come il job viene recuperato)
        this.job = new Job();  // Assumi che `Job` sia creato o recuperato altrove
        restAssured.setJob(job);
    }

    // Metodo per chiudere i repository (non più necessario con MongoDB)
    public void closeAllRepository() {
        // Non necessario con MongoDB
    }

    public void getErrorMap() {
        errorMap.forEach((key, value) -> {
            System.out.println("Key: " + key + ", Value: " + value);
        });
    }

    public void addToNominalMap(String fileName, String testAssured) {
        nominalMap.put(fileName, testAssured);
    }

    public void addErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public void addToErrorMap(String fileName, String testAssured) {
        errorMap.put(fileName, testAssured);
    }

    // Metodo per scrivere i dati nominali in MongoDB
    public void writeNominal() {
        byte[] nominalZipBytes = writeZipToBytes(nominalMap);

        // Salva i dati nominali come documento in MongoDB
        MongoCollection<Document> collection = mongoDatabase.getCollection("restAssuredFiles");

        Document nominalDoc = new Document("jobId", job.getId())
                .append("nominalFileName", "NominalFiles.zip")
                .append("nominalFile", new Binary(nominalZipBytes));

        collection.insertOne(nominalDoc);
    }

    // Metodo per scrivere i dati di errore in MongoDB
    public void writeError() {
        byte[] errorZipBytes = writeZipToBytes(errorMap);

        // Salva i dati di errore come documento in MongoDB
        MongoCollection<Document> collection = mongoDatabase.getCollection("restAssuredFiles");

        Document errorDoc = new Document("jobId", job.getId())
                .append("errorFileName", "ErrorFiles.zip")
                .append("errorFile", new Binary(errorZipBytes));

        collection.insertOne(errorDoc);
    }

    // Metodo per scrivere il contenuto dello zip in un array di byte
    private byte[] writeZipToBytes(Map<String, String> contentMap) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream zos = new ZipOutputStream(baos)) {

            for (Map.Entry<String, String> entry : contentMap.entrySet()) {
                zos.putNextEntry(new ZipEntry(entry.getKey()));

                zos.write(entry.getValue().getBytes());

                zos.closeEntry();
            }

            zos.finish();
            return baos.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo per salvare il documento RestAssured in MongoDB (se necessario)
    public void write() {
        MongoCollection<Document> collection = mongoDatabase.getCollection("restAssured");
        Document restAssuredDoc = new Document("jobId", job.getId())
                .append("nominalMap", nominalMap)
                .append("errorMap", errorMap);

        collection.insertOne(restAssuredDoc);
    }
}
