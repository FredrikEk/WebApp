package jpa_ige.inherit;

import javax.persistence.*;

/**
 * All will end up in table Employee
 * @author hajo
 */
@Entity
public class Employee extends Person {

    private int salary;

    public Employee(String name, int age, int salary ) {
        super(name, age);
        this.salary = salary;
    }
    
    public Employee() {
      super();
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
      
}
