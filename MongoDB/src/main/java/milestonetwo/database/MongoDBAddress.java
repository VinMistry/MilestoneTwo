package milestonetwo.database;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import lombok.Getter;
import lombok.Setter;
import milestonetwo.models.Address;

public class MongoDBAddress implements DBFunctions<Address> {


  @Getter
  @Setter
  private MongoCollection<Address> mongoCollection;

  private FindIterable results;

  final Block<Document> printBlock = new Block<Document>() {
    @Override
    public void apply(final Document document) {
      System.out.println(document.toJson());
    }
  };

  public MongoDBAddress(final MongoCollection<Address> mongoCollection) {
    this.mongoCollection = mongoCollection;
  }

  @Override
  public void read() {
    results = mongoCollection.find();
  }

  @Override
  public void limitedRead(final int numberOfResultsToReturn) {

  }

  @Override
  public void filterRead(final String filterField, final String filterValue) {

  }

  @Override
  public FindIterable<Address> returnResults() {
    return results;
  }

  @Override
  public void insert(final Address address) {

  }

  @Override
  public void insertArrayOfData(final ArrayList arrayList) {
    mongoCollection.insertMany(arrayList);
  }

  @Override
  public void update() {

  }

  @Override
  public void deleteWhere(final String where, final String equals) {

  }

  @Override
  public void deleteAll() {
    mongoCollection.drop();
  }
}
