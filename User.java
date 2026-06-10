/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

/**
 *
 * @author afrad
 */
//Demonstrates: Encapsulation (private fields + getters/setters)
 //Inherited by: Admin.java
public class User {
   
    // Private fields(encapsulation) 
    String username;
    private String password;
    protected String packageType; // protected so Admin can access it

    
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.packageType = "Basic"; // default package for new users
    }
 
    //Getters
    public String getUsername() {
        return username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public String getPackageType() {
        return packageType;
    }
 
    // Setters
    public void setUsername(String username) {
        this.username = username;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }
 
     public void displayUser() {
        System.out.println("User: " + username + " | Package: " + packageType);
    }
    
}