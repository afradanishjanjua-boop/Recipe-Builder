/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

/**
 *
 * @author afrad
 */
import java.util.ArrayList;

public class UserDatabase {
    // Static list holds all users across the whole program
    private static ArrayList<User> users = new ArrayList<>();
 
   //CREATE: Add a new user
    public static boolean addUser(User user) {
        // Check if username already exists before adding
        if (findUser(user.getUsername()) != null) {
            return false; // username already taken
        }
        users.add(user);
        return true;
    }
 
    //READ: Find a user by username
    public static User findUser(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null; // not found
    }
 
    //READ: Get all users (used by AdminPanel to show table)
    public static ArrayList<User> getAllUsers() {
        return users;
    }
 
    //UPDATE: Change a user's package type
    public static boolean updatePackage(String username, String newPackage) {
        User u = findUser(username);
        if (u != null) {
            u.setPackageType(newPackage);
            return true;
        }
        return false;
    }
 
    // UPDATE: Change a user's password
    public static boolean updatePassword(String username, String newPassword) {
        User u = findUser(username);
        if (u != null) {
            u.setPassword(newPassword);
            return true;
        }
        return false;
    }
 
    // DELETE: Remove a user by username 
    public static boolean deleteUser(String username) {
        User u = findUser(username);
        if (u != null) {
            users.remove(u);
            return true;
        }
        return false;
    }
 
    //Helper: total number of users
    public static int getUserCount() {
        return users.size();
    }

   
}
