package milestonetwo.database;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import milestonetwo.models.CustomerProfile;

@ExtendWith(MockitoExtension.class)
class MongoDBConTest {

  @Mock
  MongoDatabase database;

  @Mock
  MongoClient mongoClient;


  @Mock
  MongoCollection mongoCollection;

  @Captor
  ArgumentCaptor<String> paramCaptor;


  private MongoDBCon dbCon;

  @Mock
  CustomerProfile customerProfile;

  @BeforeEach
  void setUp() {
    when(mongoClient.getDatabase(anyString())).thenReturn(database);
    dbCon = new MongoDBCon(mongoClient, "test");
  }

  @AfterEach
  void tearDown() {
    dbCon = null;
  }

  @Test
  void connectToCollection() {
    dbCon.connectToCollection("test", CustomerProfile.class);
    verify(database).getCollection("test", CustomerProfile.class);
  }
}