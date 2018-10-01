package milestonetwo.database;


/*
public class MongoDBConGeneric implements DBCon {

  private MongoDatabase database;
  private MongoCollection<Document> mongoCollection;
  private MongoCollection<Object> mongoObjectCollection;
  private FindIterable results;

  final Block<Document> printBlock = new Block<Document>() {
    @Override
    public void apply(final Document document) {
      System.out.println(document.toJson());
    }
  };

  final Block<Document> returnBlock = new Block<Document>() {
    @Override
    public void apply(final Document document) {
    }
  };

  public MongoDBConGeneric(final MongoDatabase mongoDatabase) {
    this.database = mongoDatabase;
  }

  public MongoDatabase getDatabase() {
    return database;
  }

  public void setDatabase(final MongoDatabase database) {
    this.database = database;
  }


  public MongoCollection<Document> getMongoCollection() {
    return mongoCollection;
  }

  public void setMongoCollection(final String collectionName) {
    this.mongoCollection = getDatabase().getCollection(collectionName);
  }

  public MongoCollection<Object> getMongoObjectCollection() {
    return mongoObjectCollection;
  }

  public void setMongoObjectCollection(final MongoCollection<Object> mongoObjectCollection) {
    this.mongoObjectCollection = mongoObjectCollection;
  }

  public FindIterable getResults() {
    return results;
  }

  public void setResults(final FindIterable results) {
    this.results = results;
  }

  public void printResults() {
    getResults().forEach(printBlock);
  }

  public void storeAddressesSeparate(final MongoCollection collection) {
    final FindIterable<Address> addresses = collection.find().projection(fields(include("address")));
    final MongoCollection coll = getDatabase().getCollection("addresses", Address.class);
    addresses.forEach((Block) profile -> coll.insertOne(profile));
  }

  @Override
  public void read(final String tableName, final int skipNumber) {
    setMongoCollection(tableName);
    System.out.println("#####--COLLECTION: " + tableName + "--####");
    setResults(getMongoCollection().find().skip(skipNumber));
    printResults();
  }

  @Override
  public void filterRead(final String tableName, final String filterField, final String filterValue, final int skipNumber) {
    System.out.println("#####--FILTERED COLLECTION--####");
    setMongoCollection(tableName);
    setResults(getMongoCollection().find(regex(filterField, "^(?i)" + Pattern.quote(filterValue))).skip(skipNumber));
    printResults();
  }

  @Override
  public void insert(final Object object, final String tableName) {
    final Class clazz = object.getClass();
    final MongoCollection<Object> collection = database.getCollection(tableName, clazz);
    collection.insertOne(object);
  }

  @Override
  public void insertArrayOfData(final ArrayList arrayList, final String tableName) {
    final Class clazz = arrayList.getClass();
    final MongoCollection<Object> collection = getDatabase().getCollection(tableName, clazz);
    collection.insertMany(arrayList);
  }

  @Override
  public void update() {

  }

  @Override
  public void deleteWhere(final String tableName, final String where, final String equals) {
    setMongoCollection(tableName);
    getMongoCollection().deleteMany(eq(where, equals));
  }

  @Override
  public void deleteAll(final String tableName) {
    setMongoCollection(tableName);
    getMongoCollection().drop();
  }
}
*/
