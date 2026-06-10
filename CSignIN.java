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
 

 // Login screen for both Users and Admins.
 //Admin login (username: "admin", password: "admin123") = opens AdminPanel
 //Demonstrates: try-catch-finally, custom exception handling
 
public class CSignIN extends JFrame {
 
    JLabel imagelabel, heading;
    JPanel rightpanel;
    JButton signInButton;
    JTextField usernameField;
    JPasswordField passwordField;
 
    public CSignIN() {
        setTitle("Recipe Builder ,Sign In");
        setSize(800, 600);
        setLayout(null);
 
        //go back to Welcome
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
 
        // Rightside panel
        rightpanel = new JPanel();
        rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.Y_AXIS));
        rightpanel.setOpaque(false);
        rightpanel.setBounds(400, 100, 320, 320);
 
        // Heading
        heading = new JLabel("Welcome Back");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setForeground(Color.BLACK);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightpanel.add(heading);
        rightpanel.add(Box.createRigidArea(new Dimension(0, 25)));
 
        // Input fields
        usernameField = new JTextField();
        passwordField = new JPasswordField();
 
        rightpanel.add(createField("Username:", usernameField));
        rightpanel.add(createPasswordField("Password:", passwordField));
        rightpanel.add(Box.createRigidArea(new Dimension(0, 20)));
 
        // Sign In button
        signInButton = new JButton("Sign In");
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightpanel.add(signInButton);
 
        imagelabel.add(rightpanel);
 
        // Sign In action with try-catch-finally
        signInButton.addActionListener(e -> {
            try {
                String enteredUser = usernameField.getText().trim();
                String enteredPass = new String(passwordField.getPassword());
 
                // Throw exception if fields are empty
                if (enteredUser.isEmpty() || enteredPass.isEmpty()) {
                    throw new InvalidLoginException("Please enter username and password!");
                }
 
                // Check if this is the Admin logging in
                
                if (enteredUser.equals("admin") && enteredPass.equals("admin123")) {
                    JOptionPane.showMessageDialog(null, "Admin login successful!");
                    new AdminPanel();
                    dispose();
                    return; // stop,do not run the next lines
                }
 
                // Check normal user login from database
                User foundUser = UserDatabase.findUser(enteredUser);
 
                if (foundUser == null) {
                    throw new InvalidLoginException("Username not found. Please sign up first.");
                }
 
                if (!foundUser.getPassword().equals(enteredPass)) {
                    throw new InvalidLoginException("Incorrect password. Please try again.");
                }
 
                // Successful user login
                JOptionPane.showMessageDialog(null, "Welcome, " + foundUser.getUsername() + "!");
                new RecipieBuilder();
                dispose();
 
            } catch (InvalidLoginException ex) {
                // Show the error
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
 
            } finally {
                //clear the password field after every attempt
                passwordField.setText("");
            }
        });
 
        setVisible(true);
    }
 
    // Helper to create a labeled text field row
    private JPanel createField(String labelText, JTextField field) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.setOpaque(false);
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(100, 25));
        field.setPreferredSize(new Dimension(200, 30));
        p.add(label);
        p.add(field);
        return p;
    }
 
   
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
