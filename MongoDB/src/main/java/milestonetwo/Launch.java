package milestonetwo;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import milestonetwo.database.DBCon;
import milestonetwo.database.DBFunctions;
import milestonetwo.database.MongoDBAddress;
import milestonetwo.database.MongoDBCon;
import milestonetwo.database.MongoDBProfile;
import milestonetwo.fileIO.ClassValidator;
import milestonetwo.fileIO.CsvFileInput;
import milestonetwo.fileIO.FilePath;
import milestonetwo.models.Address;
import milestonetwo.models.CustomerProfile;
import milestonetwo.printers.PrintIterableLists;
import milestonetwo.printers.Printer;

public class Launch {

  public FilePath filePath = new FilePath();

//  private MongoDBCon mongoDBCon = new MongoDBCon();
//
//  private MongoDBProfile mongoDBProfile;
//

  private DBCon dbCon;
  private Printer printer;
  private DBFunctions profileDatabaseFunctions;
  private DBFunctions addressDatabaseFunctions;

  public Launch(final Printer printer, final DBCon dbCon, final DBFunctions profileDatabaseFunctions, final DBFunctions addressDatabaseFunctions) {
    this.printer = printer;
    this.dbCon = dbCon;
    this.profileDatabaseFunctions = profileDatabaseFunctions;
    this.addressDatabaseFunctions = addressDatabaseFunctions;
  }


  public static String getCsvFilePath() {
    return FilePath.getInputFilePath("mock_data", "csv");
  }

  public void readAllProfiles() {
    profileDatabaseFunctions.read();
    printer.printResults(profileDatabaseFunctions.returnResults());
  }

  public void writeCsvToDB() {
    final CsvFileInput csvFileInput = new CsvFileInput(new ArrayList<CustomerProfile>(), new CsvMapper(), new ClassValidator());
    profileDatabaseFunctions.deleteAll();
    profileDatabaseFunctions.insertArrayOfData(csvFileInput.fileToArrayListOfProfiles(FilePath.getInputFilePath("mock_data", "csv")));
  }
//
//  public void readAll() {
//    mongoDBProfile.read();
//    mongoDBProfile.printResults();
//  }
//

  public void readLast10Profiles() {
    profileDatabaseFunctions.limitedRead(10);
    printer.printResults(profileDatabaseFunctions.returnResults());
  }

  public void readBMWs() {
    profileDatabaseFunctions.filterRead("car.make", "BMW");
    printer.printResults(profileDatabaseFunctions.returnResults());
  }

  public void readPostcodeSK11() {
    profileDatabaseFunctions.filterRead("address.postcode", "SK11");
    printer.printResults(profileDatabaseFunctions.returnResults());
  }

  public void storeAddressSeparate() {
    final ArrayList<Address> arrayList = new ArrayList<>();
    profileDatabaseFunctions.read();
    profileDatabaseFunctions.returnResults().forEach((Consumer<? super CustomerProfile>) customerProfile -> arrayList.add(customerProfile.getAddress()));
    System.out.println("************");
    addressDatabaseFunctions.insertArrayOfData(arrayList);
    addressDatabaseFunctions.read();
    printer.printResults(addressDatabaseFunctions.returnResults());
  }

  public void deleteCollections() {
    profileDatabaseFunctions.deleteAll();
    addressDatabaseFunctions.deleteAll();
  }


  public static void main(final String[] args) {
    final MongoDBCon mongoDBCon = new MongoDBCon("test");
    final MongoDBProfile mongoDBProfile = new MongoDBProfile(mongoDBCon.connectToCollection("people", CustomerProfile.class));
    final MongoDBAddress mongoDBAddress = new MongoDBAddress(mongoDBCon.connectToCollection("addresses", Address.class));
    final Launch l = new Launch(new PrintIterableLists(), mongoDBCon, mongoDBProfile, mongoDBAddress);
    //l.readAllProfiles();
    //p.printResults(mongoDBProfile.returnResults());
    //l.writeCsvToDB();
    //l.readAll();
    // l.readLast10Profiles();
    //l.readBMWs();
    // l.readPostcodeSK11();
    l.storeAddressSeparate();
    //l.deleteCollections();
  }

}
