/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;
 
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminPanel extends JFrame {

    private Admin admin = new Admin("admin", "admin123");
    private JScrollPane scrollPane;
    private JPanel centerPanel;

    public AdminPanel() {

        setTitle("Admin Panel - Manage Users");
        setSize(700, 500);
        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                new WELCOME();
                dispose();
            }
        });

        // TOP PANEL
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(50, 50, 50));

        JLabel adminLabel = new JLabel("Logged in as: " + admin.getAdminInfo());
        adminLabel.setForeground(Color.WHITE);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 14));

        topPanel.add(adminLabel);
        add(topPanel, BorderLayout.NORTH);

        // CENTER PANEL
        centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        loadTable();

        // BOTTOM BUTTON PANEL
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton refreshBtn = new JButton("Refresh");
        JButton addBtn = new JButton("Add User");
        JButton deleteBtn = new JButton("Delete User");
        JButton toBasicBtn = new JButton("Set Basic");
        JButton toPremiumBtn = new JButton("Set Premium");
        JButton logoutBtn = new JButton("Logout");

        deleteBtn.setForeground(Color.RED);
        addBtn.setForeground(new Color(0, 130, 0));

        btnPanel.add(refreshBtn);
        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(toBasicBtn);
        btnPanel.add(toPremiumBtn);
        btnPanel.add(logoutBtn);

        add(btnPanel, BorderLayout.SOUTH);

        // ACTIONS
        refreshBtn.addActionListener(e -> loadTable());

        addBtn.addActionListener(e -> {

            JTextField nameField = new JTextField();
            JTextField passField = new JTextField();

            String[] packages = {"Basic", "Premium"};
            JComboBox<String> packageBox = new JComboBox<>(packages);

            JPanel dialogPanel = new JPanel(new GridLayout(3, 2, 8, 8));
            dialogPanel.add(new JLabel("Username:"));
            dialogPanel.add(nameField);
            dialogPanel.add(new JLabel("Password:"));
            dialogPanel.add(passField);
            dialogPanel.add(new JLabel("Package:"));
            dialogPanel.add(packageBox);

            int result = JOptionPane.showConfirmDialog(
                    null, dialogPanel, "Add New User", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {

                String uname = nameField.getText().trim();
                String upass = passField.getText().trim();
                String upackage = (String) packageBox.getSelectedItem();

                if (uname.isEmpty() || upass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fields cannot be empty!");
                    return;
                }

                boolean added = admin.addUser(uname, upass, upackage);

                if (added) {
                    JOptionPane.showMessageDialog(null, "User added!");
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Username already exists!");
                }
            }
        });

        deleteBtn.addActionListener(e -> {

            JTable t = (JTable) scrollPane.getViewport().getView();
            int row = t.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a user first.");
                return;
            }

            String uname = (String) t.getValueAt(row, 0);//get the user name to del

            if (admin.deleteUser(uname)) {
                JOptionPane.showMessageDialog(null, "User deleted.");
                loadTable();
            }
        });

        toBasicBtn.addActionListener(e -> {

            JTable t = (JTable) scrollPane.getViewport().getView();
            int row = t.getSelectedRow();

            if (row == -1) return;

            String uname = (String) t.getValueAt(row, 0);

            admin.updateUserPackage(uname, "Basic");
            loadTable();
        });

        toPremiumBtn.addActionListener(e -> {

            JTable t = (JTable) scrollPane.getViewport().getView();
            int row = t.getSelectedRow();

            if (row == -1) return;

            String uname = (String) t.getValueAt(row, 0);

            admin.updateUserPackage(uname, "Premium");
            loadTable();
        });

        logoutBtn.addActionListener(e -> {
            new WELCOME();
            dispose();
        });

        setVisible(true);
    }

    // TABLE LOAD METHOD
    private void loadTable() {

        ArrayList<User> allUsers = UserDatabase.getAllUsers();

        String[] cols = {"Username", "Package Type"};
        Object[][] data;

        if (allUsers.isEmpty()) {
            data = new Object[][]{{"No users", ""}};
        } else {
            data = new Object[allUsers.size()][2];

            for (int i = 0; i < allUsers.size(); i++) {
                data[i][0] = allUsers.get(i).getUsername();
                data[i][1] = allUsers.get(i).getPackageType();
            }
        }

        JTable table = new JTable(data, cols);
        table.setRowHeight(25);

        scrollPane = new JScrollPane(table);

        centerPanel.removeAll();
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }
}