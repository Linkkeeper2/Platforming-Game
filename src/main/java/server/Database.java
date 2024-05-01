package main.java.server;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Database {
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private ArrayList<Document> levels;

    public Database() {
        try {
            byte[] tokens = new byte[] { 109,
                    111,
                    110,
                    103,
                    111,
                    100,
                    98,
                    43,
                    115,
                    114,
                    118,
                    58,
                    47,
                    47,
                    76,
                    105,
                    110,
                    107,
                    107,
                    101,
                    101,
                    112,
                    101,
                    114,
                    50,
                    58,
                    97,
                    100,
                    109,
                    105,
                    110,
                    64,
                    99,
                    108,
                    117,
                    115,
                    116,
                    101,
                    114,
                    48,
                    46,
                    104,
                    97,
                    101,
                    50,
                    103,
                    51,
                    107,
                    46,
                    109,
                    111,
                    110,
                    103,
                    111,
                    100,
                    98,
                    46,
                    110,
                    101,
                    116,
                    47,
                    63,
                    114,
                    101,
                    116,
                    114,
                    121,
                    87,
                    114,
                    105,
                    116,
                    101,
                    115,
                    61,
                    116,
                    114,
                    117,
                    101,
                    38,
                    119,
                    61,
                    109,
                    97,
                    106,
                    111,
                    114,
                    105,
                    116,
                    121,
                    38,
                    97,
                    112,
                    112,
                    78,
                    97,
                    109,
                    101,
                    61,
                    67,
                    108,
                    117,
                    115,
                    116,
                    101,
                    114,
                    48 };
            String uri = new String(tokens, StandardCharsets.UTF_8);

            client = MongoClients.create(uri);

            database = client.getDatabase("Platforming-Game");

            collection = database.getCollection("Levels");

            levels = new ArrayList<>();

            collection.find().into(levels);
        } catch (Exception e) {
        }
    }

    public void createLevel(String name, ArrayList<String> level) {
        collection = database.getCollection("Levels");

        String[] levelLayout = new String[level.size()];

        for (int i = 0; i < level.size(); i++)
            levelLayout[i] = level.get(i);

        ArrayList<Document> levels = new ArrayList<>();

        int levelNum = collection.find().into(levels).size();

        Document doc = new Document()
                .append("name", name)
                .append("number", levelNum)
                .append("data", Arrays.asList(levelLayout));

        collection.insertOne(doc);
    }

    public Document getLevel(int level) {
        for (int i = 0; i < levels.size(); i++) {
            Document doc = levels.get(i);

            if (doc.getInteger("number") == level)
                return doc;
        }

        return null;
    }

    public void refreshLevels() {
        collection = database.getCollection("Levels");

        levels = new ArrayList<>();

        collection.find().into(levels);
    }
}
