package main.java.server;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import main.java.object.entity.GhostPlayer;
import main.java.object.entity.Player;
import main.java.screen.Screen;

public class Database {
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    public ArrayList<Document> levels;

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

        UpdateOptions options = new UpdateOptions().upsert(true);

        Document query = new Document().append("name", name);

        Document doc = collection.find(query).first();

        Bson updates;

        if (doc != null)
            updates = Updates.combine(Updates.set("name", name),
                    Updates.set("data", Arrays.asList(levelLayout)));

        else
            updates = Updates.combine(Updates.set("name", name),
                    Updates.set("number", levelNum),
                    Updates.set("data", Arrays.asList(levelLayout)));

        collection.updateOne(query, updates, options);
        refreshLevels();
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

    public void addPlayer(String name) {
        collection = database.getCollection("Server");

        Document doc = new Document()
                .append("name", name)
                .append("x", 0)
                .append("y", 0)
                .append("direction", 0)
                .append("level", 0);

        collection.insertOne(doc);
    }

    public void addPlayers() {
        collection = database.getCollection("Server");

        ArrayList<Document> documents = new ArrayList<>();
        ArrayList<Boolean> checks = new ArrayList<>();

        collection.find().into(documents);

        for (int i = 0; i < documents.size(); i++)
            checks.add(false);

        for (int i = 0; i < documents.size(); i++) {
            Document doc = documents.get(i);

            for (int k = 0; k < Player.players.size(); k++) {
                Player player = Player.players.get(k);

                if (player.getName().equals(doc.getString("name")))
                    checks.set(i, true);
            }
        }

        for (int i = 0; i < documents.size(); i++) {
            Document doc = documents.get(i);

            if (checks.get(i).equals(false) && !doc.getString("name").equals(Player.main.getName())) {
                GhostPlayer g = new GhostPlayer(doc.getInteger("x"), doc.getInteger("y"),
                        doc.getString("name"));
                Screen.add(g);
            }
        }
    }

    public void removePlayer(String name) {
        collection = database.getCollection("Server");

        Document query = new Document().append("name", name);

        collection.deleteOne(query);
    }

    public void updatePlayer(Player player) {
        collection = database.getCollection("Server");

        ArrayList<Document> documents = new ArrayList<>();
        ArrayList<Boolean> checks = new ArrayList<>();

        collection.find().into(documents);

        int updatedIndex = -1;

        for (int i = 0; i < documents.size(); i++)
            checks.add(false);

        for (int i = 0; i < documents.size(); i++) {
            Document doc = documents.get(i);

            if (player.getName().equals(doc.getString("name"))) {
                player.x = doc.getInteger("x");
                player.y = doc.getInteger("y");
                player.direction = doc.getInteger("direction");
                player.setLevel(doc.getInteger("level"));
                checks.set(i, true);
                updatedIndex = i;
                break;
            }
        }

        if (updatedIndex == -1) {
            Player.players.remove(player);
            Screen.remove(player);
        }
    }

    public void updateMain() {
        collection = database.getCollection("Server");

        Bson updates = Updates.combine(
                Updates.set("x", Player.main.x),
                Updates.set("y", Player.main.y),
                Updates.set("direction", (int) Player.main.direction),
                Updates.set("level", Player.main.getLevel()));

        Document query = new Document().append("name", Player.main.getName());

        collection.updateOne(query, updates);
    }
}
