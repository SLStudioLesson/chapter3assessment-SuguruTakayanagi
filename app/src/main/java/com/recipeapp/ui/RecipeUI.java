package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        this.displayRecipes();
                        break;
                    case "2":
                        this.addNewRecipe();
                        break;
                    case "3":

                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    public void displayRecipes() {
        ArrayList<Recipe> dataList = new ArrayList<>();
        try {
            dataList = this.dataHandler.readData();
            if(!(dataList.isEmpty())) {
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");
                for (Recipe rcp : dataList) {
                    System.out.println("Recipe Name: " + rcp.getName());
                    for (int i = 0; i < rcp.getIngredients().size(); i++) {
                        System.out.println("Main Ingredients: " + rcp.getIngredients().get(i));
                    }
                    System.out.println("-----------------------------------");
                }
            } else {
                System.out.println("No recipes available.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void addNewRecipe() {
        try {
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String newName = reader.readLine();
            System.out.println("Enter ingredients (type 'done' when finished):");
            ArrayList<Ingredient> ingLists = new ArrayList<>();
            String newIng = "";
            while (!(newIng.equals("done"))) {
                System.out.print("Ingredient: ");
                newIng = reader.readLine();
                Ingredient i1 = new Ingredient(newIng);
                ingLists.add(i1);
            }
            Recipe newRecipe = new Recipe(newName, ingLists);
            this.dataHandler.writeData(newRecipe);
            System.out.println("Recipe added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}
