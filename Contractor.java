public class Contractor extends Employee {
    private String contractDuration;

    public Contractor(String name, String id, double salary, String contractDuration) {
        super(name, id, "Contractor", salary);
        this.contractDuration = contractDuration;
    }

    public String getContractDuration() { return contractDuration; }
    public void setContractDuration(String contractDuration) { 
        this.contractDuration = contractDuration; 
    }

    @Override
    public Employee clone() {
        Contractor employee = (Contractor) super.clone();
        employee.contractDuration = this.contractDuration;
        return employee;
    }
}
