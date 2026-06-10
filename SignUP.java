/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

/**
 *
 * @author afrad
 */
import javax.swing.*;
import java.awt.*;

public class SignUP extends JFrame {

    JLabel imagelabel, heading;
    JPanel rightpanel;
    JButton signUpButton;
    JTextField firstNameField, lastNameField;
    JPasswordField passwordField, confirmPasswordField;
 
    public SignUP() {
        setTitle("Recipe Builder - Sign Up");
        setSize(800, 600);
        setLayout(null);
 
        // go back to Welcome
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                new WELCOME();
                dispose();
            }
        });
 
        // Background image
        imagelabel = new JLabel(
            new ImageIcon(getClass().getResource("/com/mycompany/project/SignIN.jpg"))
        );
        imagelabel.setBounds(0, 0, 800, 600);
        imagelabel.setLayout(null);
        add(imagelabel);
 
        // Right-side panel for form
        rightpanel = new JPanel();
        rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.Y_AXIS));
        rightpanel.setOpaque(false);
        rightpanel.setBounds(400, 80, 320, 420);
 
        // Heading
        heading = new JLabel("Create Account");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setForeground(Color.BLACK);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightpanel.add(heading);
        rightpanel.add(Box.createRigidArea(new Dimension(0, 20)));
 
        // Input fields
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
 
        rightpanel.add(createField("First Name:", firstNameField));
        rightpanel.add(createField("Last Name:", lastNameField));
        rightpanel.add(createPasswordField("Password:", passwordField));
        rightpanel.add(createPasswordField("Confirm:", confirmPasswordField));
        rightpanel.add(Box.createRigidArea(new Dimension(0, 20)));
 
        // Sign Up button
        signUpButton = new JButton("Sign Up");
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightpanel.add(signUpButton);
 
        imagelabel.add(rightpanel);
 
        //Sign Up action with try-catch-finally
        signUpButton.addActionListener(e -> {
            // try-catch-finally demonstrates exception handling requirement
            try {
                String first = firstNameField.getText().trim();
                String last = lastNameField.getText().trim();
                String pass = new String(passwordField.getPassword());
                String confirm = new String(confirmPasswordField.getPassword());
 
                //custom exception if fields are empty
                if (first.isEmpty() || last.isEmpty() || pass.isEmpty()) {
                    throw new InvalidLoginException("Please fill in all fields!");
                }
 
                //custom exception if passwords don't match
                if (!pass.equals(confirm)) {
                    throw new InvalidLoginException("Passwords do not match!");
                }
 
                // Password too short check
                if (pass.length() < 4) {
                    throw new InvalidLoginException("Password must be at least 4 characters!");
                }
 
                // CRUD  make a new User and add to database
                String username = first + " " + last;
                User newUser = new User(username, pass);
                boolean added = UserDatabase.addUser(newUser);
 
                if (!added) {
                    throw new InvalidLoginException("Username already exists! Try a different name.");
                }
 
                JOptionPane.showMessageDialog(null, "Account created successfully! Please sign in.");
                new RecipieBuilder();
                dispose();
 
            } catch (InvalidLoginException ex) {
                //Show the error message
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Sign Up Error", JOptionPane.ERROR_MESSAGE);
 
            } finally {
                //clears password fields for security
                passwordField.setText("");
                confirmPasswordField.setText("");
            }
        });
 
        setVisible(true);
    }
 
    // Helper to create a labeled text field row
    private JPanel createField(String labelText, JTextField field) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.setOpaque(false);
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(100, 25));
        field.setPreferredSize(new Dimension(200, 30));
        p.add(label);
        p.add(field);
        return p;
    }
 
    // Helper to create a labeled password field row
    private JPanel createPasswordField(String labelText, JPasswordField field) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.setOpaque(false);
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(100, 25));
        field.setPreferredSize(new Dimension(200, 30));
        p.add(label);
        p.add(field);
        return p;
    }
}