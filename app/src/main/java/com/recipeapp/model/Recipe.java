package com.recipeapp.model;

import java.util.ArrayList;

public class Recipe {
    
    //フィールド
    private String name;
    private ArrayList<Ingredient> ingredients;

    //コンストラクタ
    public Recipe(String name, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    //メソッド
    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
