package undecided.demo.relationship.model.employee;

public record EmployeeName(String value) {

  public static final EmployeeName EMPTY = new EmployeeName(null);

  public static EmployeeName of(String value) {
    return new EmployeeName(value);
  }
}
