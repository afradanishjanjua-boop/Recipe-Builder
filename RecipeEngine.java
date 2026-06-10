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
import java.util.Arrays;

public class RecipeEngine {

    
    static class Recipe {
        String name;
        ArrayList<String> ingredients;
        String instructions;

        
        Recipe(String name, String instructions, String[] ingredients) {
            this.name = name;
            this.instructions = instructions;
            this.ingredients = new ArrayList<>(Arrays.asList(ingredients));
        }
    }

    // Recipe database
    ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipeEngine() {

        recipes.add(new Recipe(
            "Chicken Rice",
            "1. Boil rice until fluffy.\n2. Cook chicken with salt and spices.\n3. Mix together and serve hot.",
            new String[]{"Chicken", "Rice"}
        ));

        recipes.add(new Recipe(
            "Veg Curry",
            "1. Chop all vegetables.\n2. Fry onion until golden.\n3. Add tomato and cook 5 min.\n4. Add potato and carrot, simmer 20 min.",
            new String[]{"Potato", "Carrot", "Onion", "Tomato"}
        ));

        recipes.add(new Recipe(
            "Fried Rice",
            "1. Cook rice and let it cool.\n2. Fry onion and carrot in oil.\n3. Add rice, soy sauce, mix well.",
            new String[]{"Rice", "Carrot", "Onion", "Oil"}
        ));

        recipes.add(new Recipe(
            "Omelette",
            "1. Beat eggs with salt.\n2. Fry onion and tomato in oil.\n3. Pour egg mixture and cook until set.",
            new String[]{"Egg", "Onion", "Tomato", "Oil"}
        ));

        recipes.add(new Recipe(
            "Pasta",
            "1. Boil pasta until tender.\n2. Fry garlic in oil.\n3. Add tomato and simmer 10 min.\n4. Mix pasta into sauce.",
            new String[]{"Flour", "Tomato", "Garlic", "Oil"}
        ));

        recipes.add(new Recipe(
            "Beef Stew",
            "1. Brown beef in oil.\n2. Add potato, carrot, onion.\n3. Pour water and simmer 40 min.",
            new String[]{"Beef", "Potato", "Carrot", "Onion", "Oil"}
        ));

        recipes.add(new Recipe(
            "Chicken Soup",
            "1. Boil chicken in water.\n2. Add carrot and potato.\n3. Season and simmer 30 min.",
            new String[]{"Chicken", "Carrot", "Potato"}
        ));
    }

    // VERSION 1: ArrayList input
    public String findBestRecipe(ArrayList<String> userItems) {

        Recipe bestRecipe = null;
        int bestMatch = 0;
        ArrayList<String> missingIngredients = new ArrayList<>();//missing item

        for (Recipe r : recipes) {

            int matchCount = 0;//reset
            ArrayList<String> tempMissing = new ArrayList<>();

            for (String ingredient : r.ingredients) {

                if (userItems.contains(ingredient)) {
                    matchCount++;
                } else {
                    tempMissing.add(ingredient);
                }
            }

            if (matchCount > bestMatch && matchCount >= 1) {
                bestMatch = matchCount;
                bestRecipe = r;
                missingIngredients = tempMissing;
            }
        }

        if (bestRecipe == null) {
            return "No recipe found. Try selecting more ingredients!";
        }

        String result = "Recipe: " + bestRecipe.name
                + "\n\nInstructions:\n" + bestRecipe.instructions;

        if (missingIngredients.isEmpty()) {
            result += "\n\nYou have ALL the ingredients! Great!";
        } else {
            result += "\n\nMissing Ingredients: " + missingIngredients;
        }

        return result;
    }

    }