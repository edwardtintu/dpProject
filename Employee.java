public abstract class Employee implements Cloneable {
    private String name;
    private String id;
    private String role;
    private double salary;

    public Employee(String name, String id, String role, double salary) {
        this.name = name;
        this.id = id;
        this.role = role;
        this.salary = salary;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public Employee clone() {
        try {
            return (Employee) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("Employee [name=%s, id=%s, role=%s, salary=%.2f]", 
            name, id, role, salary);
    }
}

// FullTimeEmployee.java
public class FullTimeEmployee extends Employee {
    private String benefits;

    public FullTimeEmployee(String name, String id, double salary, String benefits) {
        super(name, id, "Full-Time", salary);
        this.benefits = benefits;
    }

    public String getBenefits() { return benefits; }
    public void setBenefits(String benefits) { this.benefits = benefits; }

    @Override
    public Employee clone() {
        FullTimeEmployee employee = (FullTimeEmployee) super.clone();
        employee.benefits = this.benefits;
        return employee;
    }
}
