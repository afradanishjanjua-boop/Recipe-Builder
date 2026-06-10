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
import java.util.ArrayList;

public class ResultScreen extends JFrame {

    public ResultScreen(ArrayList<String> selected, String resultText) {

        setTitle("Recipe Result");
        setSize(800, 600);
        setLayout(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                new WELCOME();
                dispose();
            }
        });

        //background image
        JLabel imagelabel = new JLabel(
            new ImageIcon(getClass().getResource("/com/mycompany/project/SignIN.jpg"))
        );
        imagelabel.setBounds(0, 0, 800, 600);
        imagelabel.setLayout(null); // IMPORTANT
        add(imagelabel);

        // title
        JLabel title = new JLabel("Your Recipe Result");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(250, 10, 300, 30);
        imagelabel.add(title);

        // label
        JLabel selectedLabel = new JLabel("Selected Ingredients:");
        selectedLabel.setFont(new Font("Arial", Font.BOLD, 14));
        selectedLabel.setBounds(30, 60, 200, 25);
        imagelabel.add(selectedLabel);

        JTextArea selectedArea = new JTextArea();
        selectedArea.setBounds(30, 90, 200, 120);
        selectedArea.setEditable(false);

        String selectedText = "";
        for (String s : selected) {
            selectedText += "- " + s + "\n";
        }
        selectedArea.setText(selectedText);

        imagelabel.add(selectedArea);

        //result label
        JLabel resultLabel = new JLabel("Recipe Result:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setBounds(30, 230, 200, 25);
        imagelabel.add(resultLabel);

        JTextArea resultArea = new JTextArea(resultText);
        resultArea.setBounds(30, 260, 500, 200);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        imagelabel.add(resultArea);

        // buttn
        JButton tryAgain = new JButton("Try Again");
        tryAgain.setBounds(120, 480, 150, 40);
        imagelabel.add(tryAgain);

        tryAgain.addActionListener(e -> {
            new RecipieBuilder();
            dispose();
        });

        JButton home = new JButton("Home");
        home.setBounds(320, 480, 150, 40);
        imagelabel.add(home);

        home.addActionListener(e -> {
            new WELCOME();
            dispose();
        });

        setVisible(true);
    }
}