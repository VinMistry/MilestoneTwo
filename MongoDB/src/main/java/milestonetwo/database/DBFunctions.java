package milestonetwo.database;

import java.util.ArrayList;

public interface DBFunctions<T> {

  void read();

  void limitedRead(int numberOfResultsToReturn);

  void filterRead(String filterField, String filterValue);

  Iterable<T> returnResults();

  void insert(T t);

  void insertArrayOfData(ArrayList arrayList);

  void update();

  void deleteWhere(String where, String equals);

  void deleteAll();

}

