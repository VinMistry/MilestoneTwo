package milestonetwo.database;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mongodb.client.MongoCollection;

import milestonetwo.models.Address;

@ExtendWith(MockitoExtension.class)
class MongoDBAddressTest {

  @Mock
  MongoCollection<Address> mongoCollection;

  @Mock
  ArrayList<Address> arrayList;

  MongoDBAddress mongoDBAddress;

  @BeforeEach
  void setUp() {
    mongoDBAddress = new MongoDBAddress(mongoCollection);
  }

  @AfterEach
  void tearDown() {
    mongoCollection = null;
    mongoDBAddress = null;
  }

  @Test
  void read() {
    mongoDBAddress.read();
    verify(mongoCollection).find();
  }
  
  @Test
  void insertArrayOfData() {
    mongoDBAddress.insertArrayOfData(arrayList);
    verify(mongoCollection).insertMany(arrayList);
  }

  @Test
  void deleteAll() {
    mongoDBAddress.deleteAll();
    verify(mongoCollection).drop();
  }

}