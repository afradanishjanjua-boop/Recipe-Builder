/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

/**
 *
 * @author afrad
 */
import java.awt.*;
import javax.swing.*;


public class WELCOME extends JFrame{
    JLabel imagelabel,greetings; 
    JButton SignIN;
    JButton SignUP; 
    public WELCOME(){
        
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        //Jlabel
        imagelabel = new JLabel(
        new ImageIcon(
            getClass().getResource("/com/mycompany/project/WELCOMEPAGE.jpg")
        )
    );
        //JLabel imagelabel = new JLabel(new ImageIcon(
          //  getClass().getResource("/com/mycompany/WELCOME PAGE INSPO-3 (SCREEN 01).jpg")
        //));
        imagelabel.setBounds(0,0, 800,600);
        imagelabel.setLayout(null); 
        add(imagelabel);
        
        //label for greetings
        greetings = new JLabel("WELCOME\n");
        greetings.setFont(new Font("BellMT",Font.BOLD ,20));
        greetings.setBounds(20, 20, 200, 50);
        greetings.setForeground(Color.WHITE);
        
        imagelabel.add(greetings);
        
        
       //button
         SignIN = new JButton("SignIN");
         SignUP = new JButton("SignUP");
         
         SignIN.setFont(new Font("Arial", Font.BOLD, 14));
         SignIN.setBounds(250,450,120, 35);
         
         SignUP.setFont(new Font("Arial", Font.BOLD, 14));
         SignUP.setBounds(430,450,120, 35);
         
         imagelabel.add(SignIN);
         imagelabel.add(SignUP);
         
     //ActionListener  
     
      
         SignIN.addActionListener(e -> {
    new CSignIN();    // open sign in page
    dispose();      // close welcome page
});
     
         SignUP.addActionListener(e -> {
    new SignUP();   // open sign up page
    dispose();      //close
});
         
              
         
        setVisible(true);

        
       
    }
}
