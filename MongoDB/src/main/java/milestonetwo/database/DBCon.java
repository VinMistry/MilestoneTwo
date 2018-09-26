package milestonetwo.database;

import java.util.ArrayList;

public interface DBCon {

  void read(String tableName, int skipNumber);

  void filterRead(final String tableName, String filterField, String filterValue, final int skipNumber);

  void insert(Object object, String tableName);

  void insertArrayOfData(ArrayList arrayList, String tableName);

  void update();

  void deleteWhere(String tableName, String where, String equals);

  void deleteAll(String tableName);

}

