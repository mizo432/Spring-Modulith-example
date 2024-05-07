package undecided.demo.relationship;

import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public record EmployeeChanged(
    undecided.demo.relationship.internal.model.employee.Employee employee) {

}
