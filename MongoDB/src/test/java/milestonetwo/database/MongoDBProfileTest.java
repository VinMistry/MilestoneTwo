package milestonetwo.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.bson.conversions.Bson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import milestonetwo.models.CustomerProfile;

@ExtendWith(MockitoExtension.class)
class MongoDBProfileTest {

  @Mock
  MongoCollection<CustomerProfile> mongoCollection;

  @Mock
  CustomerProfile customerProfile;

  @Mock
  ArrayList<CustomerProfile> arrayList;

  @Captor
  ArgumentCaptor<Bson> bsonCaptor;

  @Mock
  FindIterable<CustomerProfile> results;


  MongoDBProfile mongoDBProfile;

  @Mock
  private FindIterable<CustomerProfile> customerProfiles;

  @BeforeEach
  void setUp() {
    mongoDBProfile = new MongoDBProfile(mongoCollection);
  }

  @AfterEach
  void tearDown() {
    mongoCollection = null;
    mongoDBProfile = null;
    bsonCaptor = null;

  }

  @Test
  void retrieveProjection() {
    when(mongoCollection.find()).thenReturn(customerProfiles);
    mongoDBProfile.retrieveProjection("address");
    verify(customerProfiles).projection(bsonCaptor.capture());
    final Bson value = bsonCaptor.getValue();
    assertEquals(value.toString(), "Projections{projections=[{ \"address\" : 1 }]}");
  }

  @Test
  void read() {
    mongoDBProfile.read();
    verify(mongoCollection).find();
  }

  @Test
  void limitedRead() {
    when(mongoCollection.find()).thenReturn(customerProfiles);
    mongoDBProfile.limitedRead(0);
    verify(customerProfiles).skip(0);
  }

  @Test
  void filterRead() {
    mongoDBProfile.filterRead("a", "a");
    verify(mongoCollection).find(bsonCaptor.capture());
    final Bson value = bsonCaptor.getValue();
    assertEquals(value.toString(), "Operator Filter{fieldName='a', operator='$eq', value=BsonRegularExpression{pattern='^(?i)\\Qa\\E', options=''}}");

  }

  @Test
  void insert() {
    mongoDBProfile.insert(customerProfile);
    verify(mongoCollection).insertOne(customerProfile);
  }

  @Test
  void insertArrayOfData() {
    mongoDBProfile.insertArrayOfData(arrayList);
    verify(mongoCollection).insertMany(arrayList);
  }

  @Test
  void update() {
  }

  @Test
  void deleteWhere() {
    mongoDBProfile.deleteWhere("a", "a");

    verify(mongoCollection).deleteMany(bsonCaptor.capture());

    final Bson value = bsonCaptor.getValue();
    assertEquals(value.toString(), "Filter{fieldName='a', value=a}");
  }

  @Test
  void deleteAll() {
    mongoDBProfile.deleteAll();
    verify(mongoCollection).drop();
  }
}