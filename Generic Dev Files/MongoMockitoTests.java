/*
@ExtendWith(MockitoExtension.class)
public class MongoMockitoTests {

  @Mock
  MongoCollection<Object> mongoCollection;

  @Mock
  private MongoClient client;

  private MongoDBConGeneric dbCon;

  @Mock
  MongoDatabase mongoDatabase;

  @Mock
  private CustomerProfile customerProfile;

  CustomerProfile testCustomerProfile = new CustomerProfile(new Customer("Thelma", "Baudone"), new Address("LE14", "857", "Forster", "Twyford"),
      new Car("49288-0944", "Honda", "Insight", "1"));


  @BeforeEach
  void setUp() {
    dbCon = new MongoDBConGeneric(mongoDatabase);
  }

  @Test
  void insertOneTest() {
    when(mongoDatabase.getCollection(eq("test"), any(Class.class))).thenReturn(mongoCollection);

    dbCon.insert(customerProfile, "test");
    verify(mongoCollection).insertOne(customerProfile);
  }
}
*/