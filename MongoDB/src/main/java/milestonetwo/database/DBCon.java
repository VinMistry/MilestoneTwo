package milestonetwo.database;

public interface DBCon<T> {

  T connectToCollection(String dbName, Class clazz);

}
