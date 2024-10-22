package io.resttestgen.database.Repository;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.resttestgen.database.Model.Odg;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OdgRepository {

    @Autowired
    private MongoDatabase mongoDatabase;  // MongoDatabase iniettato

    private static final String COLLECTION_NAME = "odg";  // Nome della collection MongoDB

    // Metodo per aggiungere un Oggetto 'Odg' nella collection 'odg'
    public Odg add(Odg odg) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);

        Document odgDoc = new Document("id", odg.getId())
                .append("description", odg.getDescription())
                .append("type", odg.getType());

        collection.insertOne(odgDoc);
        return odg;  // Restituisce l'oggetto salvato
    }

    // Metodo per chiudere la connessione (non necessario in MongoDB)
    public void close() {
        // MongoDB gestisce automaticamente la connessione
    }
}
