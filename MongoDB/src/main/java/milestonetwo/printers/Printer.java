package milestonetwo.printers;

public interface Printer<T> {

  void printResults(Iterable<T> t);

}
