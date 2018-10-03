package milestonetwo;

import static com.mongodb.MongoClient.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

import milestonetwo.database.MongoDBAddress;
import milestonetwo.database.MongoDBProfile;
import milestonetwo.fileIO.ClassValidator;
import milestonetwo.fileIO.CsvFileInput;
import milestonetwo.fileIO.FilePath;
import milestonetwo.models.Address;
import milestonetwo.models.Car;
import milestonetwo.models.Customer;
import milestonetwo.models.CustomerProfile;

public class Launch {

  public FilePath filePath = new FilePath();

  public static ArrayList<Object> createCustomerProfileArray() {
    //Create Pojo.Customer objects
    final Customer c1 = new Customer("Vinesh", "Mistry");
    final Customer c2 = new Customer("Dan", "Graef");
    final Customer c3 = new Customer("Jon", "Kneller");
    //Create Pojo.Car objects
    final Car vroom1 = new Car("CFM 139W", "BMW", "M3", "1");
    final Car vroom2 = new Car("CSZ 2614", "Hyundai", "i10", "1.2");
    final Car vroom3 = new Car("EHA 642", "Kia", "Sportage", "1.4");
    //Create customer profile objects
    final CustomerProfile cp1 = new CustomerProfile(c1, new Address("BL3 6TS", "31", "Street", "Bolton"), vroom1);
    final CustomerProfile cp2 = new CustomerProfile(c2, new Address("M2 2BT", "1", "Road", "Manchester"), vroom2);
    final CustomerProfile cp3 = new CustomerProfile(c3, new Address("M3 3IS", "90", "Queen", "Manchester"), vroom3);

    final ArrayList<Object> j = new ArrayList<>();
    j.add(cp1);
    j.add(cp2);
    j.add(cp3);

    return j;
  }

  public static ArrayList<CustomerProfile> createCustomerProfileArray2() {
    //Create Pojo.Customer objects
    final Customer c1 = new Customer("Vinesh", "Mistry");
    final Customer c2 = new Customer("Dan", "Graef");
    final Customer c3 = new Customer("Jon", "Kneller");
    //Create Pojo.Car objects
    final Car vroom1 = new Car("CFM 139W", "BMW", "M3", "1");
    final Car vroom2 = new Car("CSZ 2614", "Hyundai", "i10", "1.2");
    final Car vroom3 = new Car("EHA 642", "Kia", "Sportage", "1.4");
    //Create customer profile objects
    final CustomerProfile cp1 = new CustomerProfile(c1, new Address("BL3 6TS", "31", "Street", "Bolton"), vroom1);
    final CustomerProfile cp2 = new CustomerProfile(c2, new Address("M2 2BT", "1", "Road", "Manchester"), vroom2);
    final CustomerProfile cp3 = new CustomerProfile(c3, new Address("M3 3IS", "90", "Queen", "Manchester"), vroom3);

    final ArrayList<CustomerProfile> j = new ArrayList<>();
    j.add(cp1);
    j.add(cp2);
    j.add(cp3);

    return j;
  }


  public static MongoClientSettings getRegistrySettings() {
    final CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(),
        fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    final MongoClientSettings settings = MongoClientSettings.builder()
        .codecRegistry(pojoCodecRegistry)
        .build();
    return settings;
  }

  public static MongoCollection<CustomerProfile> getMongoDB(final String dbName) {
    final MongoClient mongoClient = MongoClients.create(getRegistrySettings());
    return mongoClient.getDatabase(dbName).getCollection("people", CustomerProfile.class);
  }

  static MongoCollection<Address> getMongoAddr(final String dbName) {
    final MongoClient mongoClient = MongoClients.create(getRegistrySettings());
    return mongoClient.getDatabase(dbName).getCollection("addresses", Address.class);
  }


  public static String getCsvFilePath() {
    return FilePath.getInputFilePath("mock_data", "csv");
  }

  public void writeCsvToDB() {
    final ClassValidator classValidator = new ClassValidator();
    final CsvFileInput csvFileInput = new CsvFileInput(new ArrayList<CustomerProfile>(), new CsvMapper(), classValidator);
    final MongoDBProfile mongoDBProfile = new MongoDBProfile(Launch.getMongoDB("test"));
    mongoDBProfile.deleteAll();
    mongoDBProfile.insertArrayOfData(csvFileInput.fileToArrayListOfProfiles(FilePath.getInputFilePath("mock_data", "csv")));
  }

  public void readAll() {
    final MongoDBProfile mongoDBProfile = new MongoDBProfile(Launch.getMongoDB("test"));
    mongoDBProfile.read();
    mongoDBProfile.printResults();
  }


  public void readLast10() {
    final MongoDBProfile mongoDBProfile = new MongoDBProfile(Launch.getMongoDB("test"));
    mongoDBProfile.limitedRead(10);
    mongoDBProfile.printResults();
  }

  public void readBMWs() {
    final MongoDBProfile mongoDBProfile = new MongoDBProfile(Launch.getMongoDB("test"));
    mongoDBProfile.filterRead("car.make", "BMW");
    mongoDBProfile.printResults();
  }

  public void readPostcodeSK11() {
    final MongoDBProfile mongoDBProfile = new MongoDBProfile(Launch.getMongoDB("test"));
    mongoDBProfile.filterRead("address.postcode", "SK11");
    mongoDBProfile.printResults();
  }

  public void storeAddressSeparate() {
    final MongoDBProfile mongoDBProfile = new MongoDBProfile(Launch.getMongoDB("test"));
    mongoDBProfile.retrieveProjection("address");
    final ArrayList<Address> arrayList = new ArrayList<>();
    final MongoDBAddress mongoDBAddress = new MongoDBAddress(getMongoAddr("test"));
    mongoDBProfile.getResults().forEach((Block<? super CustomerProfile>) customerProfile -> arrayList.add(customerProfile.getAddress()));
    System.out.println("************");
    mongoDBAddress.insertArrayOfData(arrayList);
    mongoDBAddress.read();
    mongoDBAddress.printResults();
  }

  public void deleteCollections() {
    final MongoDBProfile mongoDBProfile = new MongoDBProfile(Launch.getMongoDB("test"));
    final MongoDBAddress mongoDBAddress = new MongoDBAddress(getMongoAddr("test"));
    mongoDBAddress.deleteAll();
    mongoDBProfile.deleteAll();
  }

  public static void main(final String[] args) {
    final Block<Document> printBlock = new Block<Document>() {
      @Override
      public void apply(final Document document) {
        System.out.println(document.toJson());
      }
    };
    final Launch l = new Launch();
    l.writeCsvToDB();
    //l.readAll();
    //l.readLast10();
    // l.readBMWs();
    //l.storeAddressSeparate();
    //l.deleteCollections();
  }

}
