package io.resttestgen.database.Writer;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.resttestgen.core.testing.Coverage;
import io.resttestgen.core.testing.coverage.CoverageManager;
import io.resttestgen.database.Model.Job;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoverageReportWriterDb {

    private Job job;

    @Autowired
    private MongoDatabase mongoDatabase;  // MongoDB database iniettato

    private final CoverageManager coverageManager;

    public CoverageReportWriterDb(CoverageManager coverageManager) {
        // Recupera il job attuale (questa logica può essere modificata in base a come viene gestito `Job`)
        this.coverageManager = coverageManager;
    }

    // Metodo per chiudere le repository (non più necessario con MongoDB)
    public void closeAllRepository() {
        // Non necessario con MongoDB
    }

    // Metodo per scrivere singole coperture (coverage) su MongoDB
    public void writeSingleCoverage() {
        MongoCollection<Document> coverageCollection = mongoDatabase.getCollection("coverageResults");

        for (Coverage coverage : coverageManager.getCoverages()) {
            Document coverageDoc = new Document("jobId", job.getId())
                    .append("coverageType", coverage.getClass().getSimpleName())
                    .append("documented", coverage.getToTest())
                    .append("documentedTested", coverage.getNumOfTestedDocumented())
                    .append("notDocumentedTested", coverage.getNumOfTestedNotDocumented())
                    .append("rate", coverage.getCoverage());

            coverageCollection.insertOne(coverageDoc);
        }
    }

    // Metodo per scrivere le statistiche di copertura su MongoDB
    public void writeStats() {
        MongoCollection<Document> coverageStatCollection = mongoDatabase.getCollection("coverageStats");

        for (Coverage coverage : coverageManager.getCoverages()) {
            Document coverageStatDoc = new Document("jobId", job.getId())
                    .append("coverageType", coverage.getClass().getSimpleName())
                    .append("documented", coverage.getToTest())
                    .append("documentedTested", coverage.getNumOfTestedDocumented())
                    .append("notDocumentedTested", coverage.getNumOfTestedNotDocumented())
                    .append("rate", coverage.getCoverage());

            coverageStatCollection.insertOne(coverageStatDoc);
        }
    }

    // Scrivi i singoli dati di copertura per le operazioni
    private void writeSingleOperationCoverage(Document reportAsJsonObject) {
        MongoCollection<Document> operationCoverageCollection = mongoDatabase.getCollection("operationCoverage");

        // Salvataggio delle coperture (documented, documentedTested, notDocumentedTested, notTested)
        operationCoverageCollection.insertOne(reportAsJsonObject);
    }

    // Scrivi i dati di copertura per il percorso (path coverage)
    private void writeSinglePathCoverage(Document reportAsJsonObject) {
        MongoCollection<Document> pathCoverageCollection = mongoDatabase.getCollection("pathCoverage");

        // Salvataggio delle coperture del percorso
        pathCoverageCollection.insertOne(reportAsJsonObject);
    }

    // Salvataggio dei dati di copertura di codice di stato
    private void writeSingleStatusCodeCoverage(Document reportAsJsonObject) {
        MongoCollection<Document> statusCodeCoverageCollection = mongoDatabase.getCollection("statusCodeCoverage");

        // Salvataggio delle coperture dei codici di stato
        statusCodeCoverageCollection.insertOne(reportAsJsonObject);
    }

    // Scrivi i dati di copertura per i parametri
    private void writeSingleParameterCoverage(Document reportAsJsonObject) {
        MongoCollection<Document> parameterCoverageCollection = mongoDatabase.getCollection("parameterCoverage");

        // Salvataggio delle coperture dei parametri
        parameterCoverageCollection.insertOne(reportAsJsonObject);
    }

    // Scrivi i dati di copertura per i valori dei parametri
    private void writeSingleParameterValueCoverage(Document reportAsJsonObject) {
        MongoCollection<Document> parameterValueCoverageCollection = mongoDatabase.getCollection("parameterValueCoverage");

        // Salvataggio delle coperture dei valori dei parametri
        parameterValueCoverageCollection.insertOne(reportAsJsonObject);
    }
}
