package org.example.moodtracker;

import com.mongodb.client.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

public class dbconnection {
    public static MongoCollection<Document> createConnection(String dbname, String collectionname) {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("MONGO_URI");

        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase(dbname);
        return database.getCollection(collectionname);
    }
}
