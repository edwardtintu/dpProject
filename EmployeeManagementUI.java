// EmployeeManagementUI.java
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class EmployeeManagementUI extends JFrame {
    private Map<String, Employee> employees = new HashMap<>();
    private JTextField nameField, idField, salaryField, benefitsField;
    private JComboBox<String> employeeTypeCombo;
    private JTextArea displayArea;
    private JComboBox<String> employeeListCombo;

    public EmployeeManagementUI() {
        setTitle("Employee Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Input Panel
        JPanel inputPanel = createInputPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(inputPanel, gbc);

        // Button Panel
        JPanel buttonPanel = createButtonPanel();
        gbc.gridy = 1;
        mainPanel.add(buttonPanel, gbc);

        // Display Area
        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        mainPanel.add(scrollPane, gbc);

        add(mainPanel);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Employee Type
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Employee Type:"), gbc);
        
        gbc.gridx = 1;
        employeeTypeCombo = new JComboBox<>(new String[]{"Full-Time Employee", "Contractor"});
        panel.add(employeeTypeCombo, gbc);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Name:"), gbc);
        
        gbc.gridx = 1;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);

        // ID
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("ID:"), gbc);
        
        gbc.gridx = 1;
        idField = new JTextField(20);
        panel.add(idField, gbc);

        // Salary
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Salary:"), gbc);
        
        gbc.gridx = 1;
        salaryField = new JTextField(20);
        panel.add(salaryField, gbc);

        // Benefits/Contract Duration
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Benefits/Duration:"), gbc);
        
        gbc.gridx = 1;
        benefitsField = new JTextField(20);
        panel.add(benefitsField, gbc);

        panel.setBorder(BorderFactory.createTitledBorder("Employee Details"));
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(e -> addEmployee());

        JButton viewButton = new JButton("View All");
        viewButton.addActionListener(e -> viewAllEmployees());

        employeeListCombo = new JComboBox<>();
        
        JButton cloneButton = new JButton("Clone Selected");
        cloneButton.addActionListener(e -> cloneEmployee());

        panel.add(addButton);
        panel.add(viewButton);
        panel.add(employeeListCombo);
        panel.add(cloneButton);

        return panel;
    }

    private void addEmployee() {
        try {
            String name = nameField.getText();
            String id = idField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            String benefitsOrDuration = benefitsField.getText();

            if (name.isEmpty() || id.isEmpty() || benefitsOrDuration.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            Employee employee;
            if (employeeTypeCombo.getSelectedItem().equals("Full-Time Employee")) {
                employee = new FullTimeEmployee(name, id, salary, benefitsOrDuration);
            } else {
                employee = new Contractor(name, id, salary, benefitsOrDuration);
            }

            employees.put(id, employee);
            employeeListCombo.addItem(id);
            clearFields();
            displayArea.append("Employee added successfully!\n");
            viewAllEmployees();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid salary!");
        }
    }

    private void cloneEmployee() {
        String selectedId = (String) employeeListCombo.getSelectedItem();
        if (selectedId == null) {
            JOptionPane.showMessageDialog(this, "Please select an employee to clone!");
            return;
        }

        Employee sourceEmployee = employees.get(selectedId);
        if (sourceEmployee != null) {
            String newName = JOptionPane.showInputDialog("Enter name for the cloned employee:");
            String newId = JOptionPane.showInputDialog("Enter ID for the cloned employee:");

            if (newName != null && newId != null && !newName.isEmpty() && !newId.isEmpty()) {
                Employee clonedEmployee = sourceEmployee.clone();
                clonedEmployee.setName(newName);
                clonedEmployee.setId(newId);
                employees.put(newId, clonedEmployee);
                employeeListCombo.addItem(newId);
                displayArea.append("Employee cloned successfully!\n");
                viewAllEmployees();
            }
        }
    }

    private void viewAllEmployees() {
        displayArea.setText(""); // Clear previous content
        if (employees.isEmpty()) {
            displayArea.append("No employees found!\n");
            return;
        }

        displayArea.append("All Employees:\n\n");
        for (Employee employee : employees.values()) {
            displayArea.append(employee.toString() + "\n");
        }
    }

    private void clearFields() {
        nameField.setText("");
        idField.setText("");
        salaryField.setText("");
        benefitsField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmployeeManagementUI().setVisible(true);
        });
    }
}