import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private Map<String, Employee> employees = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Contractor");
            System.out.println("3. Clone Employee");
            System.out.println("4. View All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addFullTimeEmployee();
                    break;
                case 2:
                    addContractor();
                    break;
                case 3:
                    cloneEmployee();
                    break;
                case 4:
                    viewAllEmployees();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addFullTimeEmployee() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter benefits: ");
        String benefits = scanner.nextLine();

        FullTimeEmployee employee = new FullTimeEmployee(name, id, salary, benefits);
        employees.put(id, employee);
        System.out.println("Full-Time Employee added successfully!");
    }

    private void addContractor() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter contract duration: ");
        String duration = scanner.nextLine();

        Contractor contractor = new Contractor(name, id, salary, duration);
        employees.put(id, contractor);
        System.out.println("Contractor added successfully!");
    }

    private void cloneEmployee() {
        System.out.print("Enter ID of employee to clone: ");
        String sourceId = scanner.nextLine();
        
        Employee sourceEmployee = employees.get(sourceId);
        if (sourceEmployee == null) {
            System.out.println("Employee not found!");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new ID: ");
        String newId = scanner.nextLine();

        Employee clonedEmployee = sourceEmployee.clone();
        clonedEmployee.setName(newName);
        clonedEmployee.setId(newId);
        
        employees.put(newId, clonedEmployee);
        System.out.println("Employee cloned successfully!");
    }

    private void viewAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        System.out.println("\nAll Employees:");
        for (Employee employee : employees.values()) {
            System.out.println(employee);
        }
    }

    public static void main(String[] args) {
        EmployeeManagementSystem system = new EmployeeManagementSystem();
        system.run();
    }
}