/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.project;

/**
 *
 * @author afrad
 */
import javax.swing.SwingUtilities;

public class PROJECT {

    public static void main(String[] args) {
         SwingUtilities.invokeLater(()-> {
         new WELCOME();
         //new SignUP();
         }); 
    }
}