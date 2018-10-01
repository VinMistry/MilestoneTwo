package milestonetwo.database;

import java.util.ArrayList;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import lombok.Getter;
import lombok.Setter;
import milestonetwo.models.Address;

public class MongoDBAddress implements DBCon<Address> {


  @Getter
  @Setter
  MongoCollection<Address> mongoCollection;
  @Getter
  @Setter
  FindIterable results;

  public MongoDBAddress(final MongoCollection<Address> mongoCollection) {
    this.mongoCollection = mongoCollection;
  }

  @Override
  public void read() {

  }

  @Override
  public void filterRead(final String filterField, final String filterValue) {

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