package io.resttestgen.database.Writer;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.resttestgen.core.testing.Oracle;
import io.resttestgen.core.testing.TestResult;
import io.resttestgen.core.testing.TestSequence;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReportWriterDb {

    @Autowired
    private MongoDatabase mongoDatabase;  // Usa il database MongoDB

    public void closeAllRepository() {
        // MongoDB non richiede la chiusura manuale della connessione
    }

    public void write(TestSequence testSequence) {
        // Ottieni la collection "testResults" dove salverai tutti i dati
        MongoCollection<Document> testResultsCollection = mongoDatabase.getCollection("testResults");

        // Crea un documento per il TestSequence con tutte le interazioni e risultati
        Document testSequenceDoc = new Document("name", testSequence.getName())
                .append("generator", testSequence.getGenerator())
                .append("generatedAt", testSequence.getGeneratedAt());

        // Lista di documenti per le interazioni di test
        List<Document> testInteractionsDocs = new ArrayList<>();
        for (io.resttestgen.core.testing.TestInteraction ti : testSequence.getTestInteractions()) {
            Document tiDoc = new Document("requestMethod", ti.getRequestMethod().toString())
                    .append("requestUrl", ti.getRequestURL())
                    .append("requestHeaders", ti.getRequestHeaders())
                    .append("requestBody", ti.getRequestBody())
                    .append("requestSentAt", ti.getRequestSentAt())
                    .append("responseProtocol", ti.getResponseProtocol())
                    .append("responseStatusCode", ti.getResponseStatusCode().toString())
                    .append("responseHeaders", ti.getResponseHeaders())
                    .append("responseBody", ti.getResponseBody())
                    .append("responseReceivedAt", ti.getResponseReceivedAt())
                    .append("executionTime", ti.getExecutionTime())
                    .append("testStatus", ti.getTestStatus().toString());

            testInteractionsDocs.add(tiDoc);
        }

        // Aggiungi le interazioni al documento principale
        testSequenceDoc.append("testInteractions", testInteractionsDocs);

        // Salva i risultati dei test per ogni Oracle
        Map<String, Document> testResultsDocs = new HashMap<>();
        for (Map.Entry<Oracle, TestResult> entry : testSequence.getTestResults().entrySet()) {
            Oracle oracle = entry.getKey();
            TestResult testResult = entry.getValue();

            Document trDoc = new Document("result", testResult.getResult())
                    .append("message", testResult.getMessage());

            testResultsDocs.put(oracle.toString(), trDoc);
        }

        // Aggiungi i risultati al documento principale
        testSequenceDoc.append("testResults", testResultsDocs);

        // Inserisci il documento nella collection "testResults"
        testResultsCollection.insertOne(testSequenceDoc);
    }
}

