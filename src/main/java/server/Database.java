package main.java.server;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Binary;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import main.java.MyGame;
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

    public void createLevel(String name, ArrayList<ArrayList<String>> level) {
        collection = database.getCollection("Levels");

        ArrayList<Document> levels = new ArrayList<>();

        int levelNum = collection.find().into(levels).size();

        UpdateOptions options = new UpdateOptions().upsert(true);

        Document query = new Document().append("name", name);

        Document doc = collection.find(query).first();

        Bson updates;

        if (doc != null)
            updates = Updates.combine(Updates.set("name", name),
                    Updates.set("data", level));

        else
            updates = Updates.combine(Updates.set("name", name),
                    Updates.set("number", levelNum),
                    Updates.set("data", level));

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
                .append("level", 0)
                .append("gravity", 1);

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
                player.setGravity((byte) ((int) doc.getInteger("gravity")));
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
                Updates.set("level", Player.main.getLevel()),
                Updates.set("gravity", Player.main.getGravity()));

        Document query = new Document().append("name", Player.main.getName());

        collection.updateOne(query, updates);
    }

    public void registerAccount(String name, String password) {
        collection = database.getCollection("Accounts");

        Document query = new Document().append("username", name.getBytes());

        if (collection.find(query).first() != null) {
            MyGame.status.addMessage("Account with username '" + name + "' already exists.", 5000);
            return;
        }

        Document user = new Document()
                .append("username", name.getBytes())
                .append("password", password.getBytes())
                .append("level", 0);

        try {
            collection.insertOne(user);
            Account.name = name;
            Account.level = 0;
            MyGame.status.addMessage("Account created successfully!");
        } catch (MongoException e) {
            MyGame.status.addMessage("Account cannot be created at this time.", 5000);
        }
    }

    public void login(String name, String password) {
        collection = database.getCollection("Accounts");

        Document query = new Document()
                .append("username", name.getBytes())
                .append("password", password.getBytes());

        try {
            Document user = collection.find(query).first();

            if (user == null) {
                MyGame.status.addMessage("Incorrect password.", 5000);
                return;
            }

            Binary binary = (Binary) user.get("username");
            String username = new String(binary.getData());

            Account.name = username;
            Account.level = user.getInteger("level");
            MyGame.status.addMessage("Logged in Successfully!");
        } catch (MongoException e) {
            MyGame.status.addMessage("Could not log in at this time.", 5000);
        }
    }

    public void updateUserLevel() {
        collection = database.getCollection("Accounts");

        Document query = new Document()
                .append("username", Account.name.getBytes());

        Document user = collection.find(query).first();

        if (user != null) {
            Bson updates = Updates.combine(Updates.set("level", Account.level));

            collection.updateOne(query, updates);
        }
    }

    public boolean userExists(String name) {
        collection = database.getCollection("Accounts");

        Document query = new Document().append("username", name.getBytes());

        if (collection.find(query).first() == null)
            return false;

        return true;
    }
}
