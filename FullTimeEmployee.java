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