package milestonetwo.database;

import java.util.ArrayList;

public interface DBCon<T> {

  void read();

  void filterRead(String filterField, String filterValue);

  void insert(T t);

  void insertArrayOfData(ArrayList arrayList);

  void update();

  void deleteWhere(String where, String equals);

  void deleteAll();

}

