/*
public class MongoTests {

  CsvFileInput fileInput;
  ArrayList<CustomerProfile> arrayList;
  MongoDBConGeneric mongoDBCon;
  CustomerProfile testCustomerProfile = new CustomerProfile(new Customer("Thelma", "Baudone"), new Address("LE14", "857", "Forster", "Twyford"),
      new Car("49288-0944", "Honda", "Insight", "1"));

  @BeforeEach
  void setUp() {
//    mongoDBCon = new MongoDBConGeneric(MongoClients.create(Launch.getRegistrySettings()), "test");
    //mongoDBCon.setMongoObjectCollection(mongoDBCon.getDatabase().getCollection("test", CustomerProfile.class));
    arrayList = new ArrayList<>();
    fileInput = new CsvFileInput(arrayList, new CsvMapper(), new ClassValidator());
  }

  @AfterEach
  void tearDown() {
    mongoDBCon.deleteAll("test");
    arrayList = null;
    mongoDBCon = null;
  }

  @Test
  void writeCsvToDBTest() {
    final JsonFileInput jsonFileInput = new JsonFileInput(new ArrayList<CustomerProfile>(), new ObjectMapper());
    mongoDBCon.insertArrayOfData(fileInput.fileToArrayListOfProfiles(Paths.get("").toAbsolutePath().toString() + "/src/test/resources/mock_data.csv"), "test");
    mongoDBCon.filterRead("test", "customer.firstName", arrayList.get(29).getCustomer().getFirstName(), 0);
    CustomerProfile customerProfile2 = new CustomerProfile();
    for (final Object object : mongoDBCon.getResults()) {
      final Document doc = (Document) object;
      System.out.println(doc.toJson());
      customerProfile2 = (CustomerProfile) jsonFileInput.jsonStringToObject(new CustomerProfile(), doc.toJson());
    }
    Assertions.assertTrue(testCustomerProfile.equals(customerProfile2));
  }

  @Test
  void readCollectionTest() {
    mongoDBCon.insertArrayOfData(fileInput.fileToArrayListOfProfiles(Paths.get("").toAbsolutePath().toString() + "/src/test/resources/mock_data.csv"), "test");
    mongoDBCon.read("test", 0);
    final JsonFileInput jsonFileInput = new JsonFileInput(new ArrayList<CustomerProfile>(), new ObjectMapper());
  }

  @Test
  void readLastTenProfilesTest() {

  }

  @Test
  void findBMWTest() {

  }

  @Test
  void findPostcodeTest() {

  }

  @Test
  void storeAddressesSeparate() {

  }

}
*/