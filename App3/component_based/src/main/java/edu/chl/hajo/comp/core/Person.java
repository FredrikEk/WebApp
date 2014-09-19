package edu.chl.hajo.comp.core;

/**
 * Model object
 *
 * @author hajo
 */
public class Person {

    private Long id;
    private String fname;
    private int age;

    public Person(Long id, String fname, int age) {
        this.id = id;
        this.fname = fname;
        this.age = age;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // This is special style of equals
    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Person)) { // Will handle null
            return false;
        }
        final Person other = (Person) obj;
        // Id is Long reference, use equal
        return other.id.equals(this.id);
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", fname=" + fname + ", age=" + age + '}';
    }

}
