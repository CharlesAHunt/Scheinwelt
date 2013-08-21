package utils;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import net.vz.mongodb.jackson.JacksonDBCollection;

import java.net.UnknownHostException;

public class DatabaseUtil {

    private static MongoClient mongoClient;

    public static DB getDB() {

        try {
            mongoClient = new MongoClient( );

            return mongoClient.getDB("database");

        } catch (UnknownHostException e) {
            return null;
        }
    }

    public static JacksonDBCollection getCollection(String collection, Class clazz) {

        try {

            return JacksonDBCollection.wrap(getDB().getCollection(collection), clazz);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Object getEntityById(String collection, Class clazz, String id) {

        try {
            JacksonDBCollection jacksonDBCollection = JacksonDBCollection.wrap(getDB().getCollection(collection), clazz);
            return jacksonDBCollection.findOneById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}

