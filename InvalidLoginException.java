/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

/**
 *
 * @author afrad
 */
//exception thrown when login fails

public class InvalidLoginException extends Exception  {
    
    // Constructor takes a message describing what went wrong
    public InvalidLoginException(String message) {
        super(message); // passes message to Exception parent class
    }
    
}
