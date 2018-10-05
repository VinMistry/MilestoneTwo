package milestonetwo.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBCon implements DBCon<MongoCollection> {

  private MongoDatabase database;

  public MongoDBCon(final MongoClient mongoClient, final String dbName) {
    database = mongoClient.getDatabase(dbName);
  }

  @Override
  public MongoCollection connectToCollection(final String collectionName, final Class clazz) {
    return database.getCollection(collectionName, clazz);
  }

}
