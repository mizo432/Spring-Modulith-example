package undecided.demo.relationship.model.employee;

public record EmployeeCode(String value) {

  public static final EmployeeCode EMPTY = new EmployeeCode(null);

  public static EmployeeCode of(String value) {
    return new EmployeeCode(value);

  }
}
