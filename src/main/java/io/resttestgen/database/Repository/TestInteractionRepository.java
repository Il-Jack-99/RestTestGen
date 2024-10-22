package io.resttestgen.database.Repository;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.resttestgen.database.Model.TestInteraction;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestInteractionRepository {

    @Autowired
    private MongoDatabase mongoDatabase;  // MongoDatabase iniettato tramite Spring

    private static final String COLLECTION_NAME = "testInteractions";

    // Salva una TestInteraction nel database MongoDB
    public void add(TestInteraction testInteraction) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);

        Document testInteractionDoc = new Document("id", testInteraction.getId())
                .append("requestBody", testInteraction.getRequestBody())
                .append("responseProtocol", testInteraction.getResponseProtocol())
                .append("sequenceId", testInteraction.getSequence().getId());

        collection.insertOne(testInteractionDoc);
    }

    // Trova una TestInteraction per ID
    public TestInteraction findById(Long id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);
        Document query = new Document("id", id);
        Document testInteractionDoc = collection.find(query).first();

        if (testInteractionDoc != null) {
            TestInteraction testInteraction = new TestInteraction();
            testInteraction.setId(testInteractionDoc.getLong("id"));
            testInteraction.setRequestBody(testInteractionDoc.getString("requestBody"));
            testInteraction.setResponseProtocol(testInteractionDoc.getString("responseProtocol"));
            // Puoi aggiungere il mapping per TestSequence qui, se necessario
            return testInteraction;
        }
        return null;
    }

    // Altri metodi CRUD, se necessari
}
