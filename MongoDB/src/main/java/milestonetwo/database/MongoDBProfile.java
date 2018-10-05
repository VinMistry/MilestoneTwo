package milestonetwo.database;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import lombok.Getter;
import lombok.Setter;
import milestonetwo.models.CustomerProfile;

public class MongoDBProfile implements DBFunctions<CustomerProfile> {

  @Getter
  @Setter
  private MongoCollection<CustomerProfile> mongoCollection;

  private FindIterable<CustomerProfile> results;

  public MongoDBProfile(final MongoCollection mongoCollection) {
    this.mongoCollection = mongoCollection;
  }

  @Override
  public void read() {
    results = mongoCollection.find();
  }

  @Override
  public FindIterable<CustomerProfile> returnResults() {
    return results;
  }


  @Override
  public void limitedRead(final int numberOfResultsToReturn) {
    final int number = (int) mongoCollection.countDocuments();
    results = mongoCollection.find().skip(number - numberOfResultsToReturn);
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
