package milestonetwo.printers;

public class PrintIterableLists implements Printer<Object> {

  @Override
  public void printResults(final Iterable<Object> results) {
    System.out.println("#####--RESULTS--####");
    results.forEach(object -> System.out.println(object));
  }
}
