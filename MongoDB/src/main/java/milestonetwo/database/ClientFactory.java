package milestonetwo.database;

import static com.mongodb.MongoClient.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class ClientFactory {

  private MongoClientSettings getRegistrySettings() {
    final CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(),
        fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    final MongoClientSettings settings = MongoClientSettings.builder()
        .codecRegistry(pojoCodecRegistry)
        .build();
    return settings;
  }

  public MongoClient clientFactory(final ClientType type) {
    if (type.equals(ClientType.POJO_SETTINGS)) {
      return MongoClients.create(getRegistrySettings());
    } else {
      return MongoClients.create();
    }
  }


}
