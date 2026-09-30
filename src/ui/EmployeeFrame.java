package ui;

import dao.EmployeeDAO;
import model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EmployeeFrame extends JFrame {

    // DAO object
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    // Table components
    private DefaultTableModel tableModel;
    private JTable employeeTable;


    public EmployeeFrame() {

        // =========================
        // WINDOW SETTINGS
        // =========================

        setTitle("Employee Management System");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);


        // =========================
        // TITLE
        // =========================

        JLabel title = new JLabel("Employee Management System");

        title.setBounds(220, 20, 300, 30);

        add(title);


        // =========================
        // EMPLOYEE ID
        // =========================

        JLabel idLabel = new JLabel("Employee ID:");

        idLabel.setBounds(50, 80, 100, 30);

        add(idLabel);


        JTextField idField = new JTextField();

        idField.setBounds(160, 80, 200, 30);

        add(idField);


        // =========================
        // NAME
        // =========================

        JLabel nameLabel = new JLabel("Name:");

        nameLabel.setBounds(50, 130, 100, 30);

        add(nameLabel);


        JTextField nameField = new JTextField();

        nameField.setBounds(160, 130, 200, 30);

        add(nameField);


        // =========================
        // DEPARTMENT
        // =========================

        JLabel departmentLabel = new JLabel("Department:");

        departmentLabel.setBounds(50, 180, 100, 30);

        add(departmentLabel);


        JTextField departmentField = new JTextField();

        departmentField.setBounds(160, 180, 200, 30);

        add(departmentField);


        // =========================
        // SALARY
        // =========================

        JLabel salaryLabel = new JLabel("Salary:");

        salaryLabel.setBounds(50, 230, 100, 30);

        add(salaryLabel);


        JTextField salaryField = new JTextField();

        salaryField.setBounds(160, 230, 200, 30);

        add(salaryField);


        // =========================
        // ADD BUTTON
        // =========================

        JButton addButton = new JButton("Add");

        addButton.setBounds(50, 290, 100, 35);

        add(addButton);


        // =========================
        // UPDATE BUTTON
        // =========================

        JButton updateButton = new JButton("Update");

        updateButton.setBounds(160, 290, 100, 35);

        add(updateButton);


        // =========================
        // DELETE BUTTON
        // =========================

        JButton deleteButton = new JButton("Delete");

        deleteButton.setBounds(270, 290, 100, 35);

        add(deleteButton);


        // =========================
        // CLEAR BUTTON
        // =========================

        JButton clearButton = new JButton("Clear");

        clearButton.setBounds(380, 290, 100, 35);

        add(clearButton);


        // =========================
        // TABLE
        // =========================

        String[] columns = {
                "ID",
                "Name",
                "Department",
                "Salary"
        };

        tableModel = new DefaultTableModel(columns, 0);

        employeeTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(employeeTable);

        scrollPane.setBounds(50, 350, 600, 170);

        add(scrollPane);


        // =========================
        // ADD EMPLOYEE
        // =========================

        addButton.addActionListener(e -> {

            if (idField.getText().isEmpty() ||
                    nameField.getText().isEmpty() ||
                    departmentField.getText().isEmpty() ||
                    salaryField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields!"
                );

                return;
            }

            try {

                int id = Integer.parseInt(
                        idField.getText()
                );

                String name = nameField.getText();

                String department =
                        departmentField.getText();

                double salary = Double.parseDouble(
                        salaryField.getText()
                );


                Employee employee = new Employee(
                        id,
                        name,
                        department,
                        salary
                );


                employeeDAO.addEmployee(employee);


                JOptionPane.showMessageDialog(
                        this,
                        "Employee added successfully!"
                );


                // Refresh table
                loadEmployees();

                // Clear fields
                clearFields(
                        idField,
                        nameField,
                        departmentField,
                        salaryField
                );


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "ID and Salary must be valid numbers!"
                );
            }
        });


        // =========================
        // UPDATE EMPLOYEE
        // =========================

        updateButton.addActionListener(e -> {

            if (idField.getText().isEmpty() ||
                    nameField.getText().isEmpty() ||
                    departmentField.getText().isEmpty() ||
                    salaryField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields!"
                );

                return;
            }

            try {

                int id = Integer.parseInt(
                        idField.getText()
                );

                String name = nameField.getText();

                String department =
                        departmentField.getText();

                double salary = Double.parseDouble(
                        salaryField.getText()
                );


                Employee employee = new Employee(
                        id,
                        name,
                        department,
                        salary
                );


                employeeDAO.updateEmployee(employee);


                JOptionPane.showMessageDialog(
                        this,
                        "Employee updated successfully!"
                );


                // Refresh table
                loadEmployees();


                // Clear fields
                clearFields(
                        idField,
                        nameField,
                        departmentField,
                        salaryField
                );


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "ID and Salary must be valid numbers!"
                );
            }
        });


        // =========================
        // DELETE EMPLOYEE
        // =========================

        deleteButton.addActionListener(e -> {

            if (idField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter Employee ID!"
                );

                return;
            }

            try {

                int id = Integer.parseInt(
                        idField.getText()
                );


                employeeDAO.deleteEmployee(id);


                JOptionPane.showMessageDialog(
                        this,
                        "Employee deleted successfully!"
                );


                // Refresh table
                loadEmployees();


                // Clear fields
                clearFields(
                        idField,
                        nameField,
                        departmentField,
                        salaryField
                );


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Employee ID must be a number!"
                );
            }
        });


        // =========================
        // CLEAR BUTTON
        // =========================

        clearButton.addActionListener(e -> {

            clearFields(
                    idField,
                    nameField,
                    departmentField,
                    salaryField
            );
        });


        // =========================
        // LOAD EMPLOYEES
        // =========================

        loadEmployees();


        // =========================
        // SHOW WINDOW
        // =========================

        setVisible(true);
    }


    // =========================================
    // LOAD EMPLOYEES INTO TABLE
    // =========================================

    private void loadEmployees() {

        // Remove old rows
        tableModel.setRowCount(0);


        // Get employees from database
        List<Employee> employees =
                employeeDAO.getAllEmployees();


        // Add each employee to table
        for (Employee employee : employees) {

            Object[] row = {
                    employee.getId(),
                    employee.getName(),
                    employee.getDepartment(),
                    employee.getSalary()
            };

            tableModel.addRow(row);
        }
    }


    // =========================================
    // CLEAR ALL INPUT FIELDS
    // =========================================

    private void clearFields(
            JTextField idField,
            JTextField nameField,
            JTextField departmentField,
            JTextField salaryField) {

        idField.setText("");

        nameField.setText("");

        departmentField.setText("");

        salaryField.setText("");
    }


    // =========================================
    // MAIN METHOD
    // =========================================

    public static void main(String[] args) {

        new EmployeeFrame();
    }
}