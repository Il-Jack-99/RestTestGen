package io.resttestgen.database.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.resttestgen.database.Model.TestSequence;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestSequenceRepository {

    @Autowired
    private MongoDatabase mongoDatabase;  // MongoDatabase iniettato tramite Spring

    private static final String COLLECTION_NAME = "testSequences";

    // Salva una TestSequence nel database MongoDB
    public void add(TestSequence testSequence) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);

        Document testSequenceDoc = new Document("id", testSequence.getId())
                .append("jobId", testSequence.getJob().getId())
                .append("name", testSequence.getName());

        collection.insertOne(testSequenceDoc);
    }

    // Trova una TestSequence per ID
    public TestSequence findById(Long id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);
        Document query = new Document("id", id);
        Document testSequenceDoc = collection.find(query).first();

        if (testSequenceDoc != null) {
            TestSequence testSequence = new TestSequence();
            testSequence.setId(testSequenceDoc.getLong("id"));
            testSequence.setName(testSequenceDoc.getString("name"));
            // Recupera il Job associato alla TestSequence (da un altro repository, se necessario)
            // Puoi gestire il mapping di Job qui
            return testSequence;
        }
        return null;
    }

    // Altri metodi CRUD, se necessari
}
