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

public class RecipieBuilder extends JFrame {

    // Menu bar and menus
    JMenuBar menuBar = new JMenuBar();
    JMenu MEAT, VEGETABLE, RICE, EXTRAS;
    JLabel imagelabel;

    //Ingredient arrays 
    String[] meat    = {"Chicken", "Beef","Mutton"};
    String[] veg     = {"Carrot", "Potato", "Onion", "Tomato"}; 
    String[] rice    = {"Rice", "Flour"};
    String[] extras  = {"Oil", "Egg", "Garlic"};               

    // ArrayList to hold all checkboxes
    ArrayList<JCheckBox> checkboxList = new ArrayList<>();

    public RecipieBuilder() {
        setTitle("Recipe Builder - Select Ingredients");
        setSize(800, 600);
        setLayout(null);

        // Close → back to Welcome
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

        //Build menu bar
        MEAT      = new JMenu("MEAT");
        VEGETABLE = new JMenu("VEGETABLES");
        RICE      = new JMenu("RICE/GRAIN");
        EXTRAS    = new JMenu("EXTRAS");

        Font menuFont = new Font("Arial", Font.BOLD, 14);
        MEAT.setFont(menuFont);
        VEGETABLE.setFont(menuFont);
        RICE.setFont(menuFont);
        EXTRAS.setFont(menuFont);

        menuBar.add(MEAT);
        menuBar.add(VEGETABLE);
        menuBar.add(RICE);
        menuBar.add(EXTRAS);
        setJMenuBar(menuBar);

        //Checkbox panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);     
        panel.setBounds(50, 60, 300, 460); 

        // MEAT checkboxes + menu items
        JLabel meatLabel = new JLabel(" MEAT ");
        meatLabel.setForeground(Color.BLACK);
        meatLabel.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(meatLabel);

        for (int i = 0; i < meat.length; i++) {  
            JCheckBox cb = new JCheckBox(meat[i]);
            cb.setOpaque(false);
            cb.setForeground(Color.BLACK);
            checkboxList.add(cb);
            panel.add(cb);

            //menu item checks/unchecks the matching checkbox
            JMenuItem item = new JMenuItem(meat[i]);
            final JCheckBox ref = cb;
            item.addActionListener(e -> ref.setSelected(!ref.isSelected()));
            MEAT.add(item);
        }

        //VEGETABLE checkboxes
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        JLabel vegLabel = new JLabel("== VEGETABLES ==");
        vegLabel.setForeground(Color.BLACK);
        vegLabel.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(vegLabel);

        for (int i = 0; i < veg.length; i++) {          
            JCheckBox cb = new JCheckBox(veg[i]);
            cb.setOpaque(false);
            cb.setForeground(Color.BLACK);
            checkboxList.add(cb);
            panel.add(cb);

            JMenuItem item = new JMenuItem(veg[i]);
            final JCheckBox ref = cb;
            item.addActionListener(e -> ref.setSelected(!ref.isSelected()));
            VEGETABLE.add(item);
        }

        // ---- RICE/GRAIN checkboxes + menu items ----
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        JLabel riceLabel = new JLabel("== RICE / GRAIN ==");
        riceLabel.setForeground(Color.BLACK);
        riceLabel.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(riceLabel);

        for (int i = 0; i < rice.length; i++) {        
            JCheckBox cb = new JCheckBox(rice[i]);
            cb.setOpaque(false);
            cb.setForeground(Color.BLACK);
            checkboxList.add(cb);
            panel.add(cb);

            JMenuItem item = new JMenuItem(rice[i]);
            final JCheckBox ref = cb;
            item.addActionListener(e -> ref.setSelected(!ref.isSelected()));
            RICE.add(item);
        }

        //EXTRAS checkboxes
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        JLabel extrasLabel = new JLabel("== EXTRAS ==");
        extrasLabel.setForeground(Color.BLACK);
        extrasLabel.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(extrasLabel);

        for (int i = 0; i < extras.length; i++) {      
            JCheckBox cb = new JCheckBox(extras[i]);
            cb.setOpaque(false);
            cb.setForeground(Color.BLACK);
            checkboxList.add(cb);
            panel.add(cb);

            JMenuItem item = new JMenuItem(extras[i]);
            final JCheckBox ref = cb;
            item.addActionListener(e -> ref.setSelected(!ref.isSelected()));
            EXTRAS.add(item);
        }

        imagelabel.add(panel);

        //Find Recipe button
        JButton findBtn = new JButton("FIND RECIPE");
        findBtn.setFont(new Font("Arial", Font.BOLD, 14));
        findBtn.setBounds(500, 450, 200, 45);
        imagelabel.add(findBtn);

        //Button action
        RecipeEngine engine = new RecipeEngine();

        findBtn.addActionListener(e -> {
            // Collect all selected ingredient names
            ArrayList<String> selected = new ArrayList<>();
            for (JCheckBox cb : checkboxList) {
                if (cb.isSelected()) {
                    selected.add(cb.getText());
                }
            }

            // Must select at least one ingredient
            if (selected.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Please select at least one ingredient!",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get recipe result from engine
            String result = engine.findBestRecipe(selected);

           //Result screen
            new ResultScreen(selected, result);
        });

        setVisible(true);
    }
}