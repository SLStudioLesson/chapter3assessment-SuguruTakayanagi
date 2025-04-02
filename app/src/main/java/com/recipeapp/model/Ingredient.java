package com.recipeapp.model;

public class Ingredient {
    
    //フィールド
    private String name;

    //コンストラクタ
    public Ingredient(String name) {
        this.name = name;
    }

    //メソッド
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
