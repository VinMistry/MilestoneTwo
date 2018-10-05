package milestonetwo.database;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.mongodb.client.MongoClient;

class ClientFactoryTest {

  private ClientFactory mongoClientFactory = new ClientFactory();

  @Mock
  private MongoClient mongoClient;

  @Test
  void clientFactoryReturnsClientWithPojoSettings() {
    final MongoClient actual = mongoClientFactory.clientFactory(ClientType.POJO_SETTINGS);
    assertThat(actual).isInstanceOf(MongoClient.class);
  }
}