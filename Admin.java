/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

/**
 *
 * @author afrad
 */
//Demonstrates: Inheritance (extends User), Method Overriding (toString)




import java.util.ArrayList;

public class Admin extends User {

    private String adminCode;

    public Admin(String adminUser, String adminPass) {
        super(adminUser, adminPass);
        packageType = "Admin";
        adminCode = "ADMIN-001";
    }

    
    public String getAdminInfo() {
        return "ADMIN: " + username + " | Code: " + adminCode;
    }

    public void displayAdmin() {
        System.out.println(getAdminInfo());
    }

    // CREATE
    public boolean addUser(String uname, String upass, String upackage) {
        User newUser = new User(uname, upass);
        newUser.setPackageType(upackage);
        return UserDatabase.addUser(newUser);
    }

    // DELETE
    public boolean deleteUser(String uname) {
        if (uname.equalsIgnoreCase(username)) {
            return false;
        }
        return UserDatabase.deleteUser(uname);
    }

    // UPDATE
    public boolean updateUserPackage(String uname, String newPackage) {
        return UserDatabase.updatePackage(uname, newPackage);
    }

    // READ ALL
    public ArrayList<User> getAllUsers() {
        return UserDatabase.getAllUsers();
    }

    // COUNT
    public int getTotalUsers() {
        return UserDatabase.getUserCount();
    }

    public String getAdminCode() {
        return adminCode;
    }
}