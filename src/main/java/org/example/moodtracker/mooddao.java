package org.example.moodtracker;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

public class mooddao {
    private final MongoCollection<Document> moodcollection;

    public mooddao() {
        Dotenv dotenv = Dotenv.load();
        String dbName = dotenv.get("DB_NAME");
        String collectionName = "mood_entries";
        this.moodcollection = dbconnection.createConnection(dbName, collectionName);
    }

    public void insertMood(String username, String mood, String note, String[] tags){
        Document moodDocument = new Document()
                .append("Username", username)
                .append("Mood", mood)
                .append("Note", note)
                .append("Tags", Arrays.asList(tags))
                .append("Timestamp", Date.from(Instant.now()));

        moodcollection.insertOne(moodDocument);
        System.out.println("Mood inserted successfully!");
    }


    public void showAllMoods() {
        for (Document doc : moodcollection.find()) {
            System.out.println(doc.toJson());
        }
    }

    public void findMoodByUsername(String username){
        Bson usernamefilter = Filters.eq("Username", username);

        FindIterable<Document> results = moodcollection.find(usernamefilter);
        boolean found = false;
        for (Document doc : results) {
            System.out.println(doc.toJson());
            found = true;
        }

        if (!found) {
            System.out.println("No mood entries found for username: " + username);
        }

    }

    public void updateMood(String id, String newMood, String newNote, String[] newTags) {
        try {
            ObjectId objectId = new ObjectId(id);
            Document filter = new Document("_id", objectId);

            Document updateFields = new Document();

            if (newMood != null && !newMood.isEmpty()) {
                updateFields.append("Mood", newMood);
            }
            if (newNote != null && !newNote.isEmpty()) {
                updateFields.append("Note", newNote);
            }
            if (newTags != null && newTags.length > 0 && !newTags[0].isEmpty()) {
                updateFields.append("Tags", Arrays.asList(newTags));
            }

            if (updateFields.isEmpty()) {
                System.out.println("No fields to update.");
                return;
            }

            Document updateOperation = new Document("$set", updateFields);
            UpdateResult result = moodcollection.updateOne(filter, updateOperation);

            if (result.getMatchedCount() > 0) {
                System.out.println("Mood updated successfully.");
            } else {
                System.out.println("No mood found with that ID.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ID format.");
        } catch (Exception e) {
            System.out.println("Error updating mood: " + e.getMessage());
        }
    }


    public void deleteMoodById(String idString){
       try {
           Object id = new ObjectId(idString);
           Bson idfilter = Filters.eq("_id", id);
           DeleteResult result = moodcollection.deleteOne(idfilter);

           if (result.getDeletedCount() > 0) {
               System.out.println("Mood deleted successfully!");
           } else {
               System.out.println("No mood found with that ID.");
           }
       } catch (IllegalArgumentException e) {
           System.out.println("Invalid ID format.");
       }

    }
}
