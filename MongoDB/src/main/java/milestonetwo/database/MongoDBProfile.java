package milestonetwo.database;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import lombok.Getter;
import lombok.Setter;
import milestonetwo.models.CustomerProfile;

public class MongoDBProfile implements DBCon<CustomerProfile> {

  @Getter
  @Setter
  MongoCollection<CustomerProfile> mongoCollection;
  @Getter
  @Setter
  FindIterable<CustomerProfile> results;

  final Block<Document> printBlock = new Block<Document>() {
    @Override
    public void apply(final Document document) {
      System.out.println(document.toJson());
    }
  };

  public MongoDBProfile(final MongoCollection mongoCollection) {
    this.mongoCollection = mongoCollection;
  }

  public void printResults() {
    System.out.println("#####--RESULTS--####");
    results.forEach((Block) profile -> System.out.println(profile));
  }

  public FindIterable<CustomerProfile> retrieveProjection(final String fieldName) {
    return mongoCollection.find().projection(fields(include(fieldName)));
  }

  @Override
  public void read() {
    results = mongoCollection.find();
  }

  public void limitedRead(final int skipNumber) {
    final int number = (int) mongoCollection.countDocuments();
    results = mongoCollection.find().skip(number - skipNumber);
  }

  @Override
  public void filterRead(final String filterField, final String filterValue) {
    results = mongoCollection.find(regex(filterField, "^(?i)" + Pattern.quote(filterValue)));
  }

  @Override
  public void insert(final CustomerProfile customerProfile) {
    mongoCollection.insertOne(customerProfile);
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
    mongoCollection.deleteMany(eq(where, equals));
  }

  @Override
  public void deleteAll() {
    mongoCollection.drop();
  }
}
