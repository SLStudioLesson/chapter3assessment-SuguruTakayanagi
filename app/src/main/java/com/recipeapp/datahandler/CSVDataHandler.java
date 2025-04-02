package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    // フィールド
    private String filePath;

    // コンストラクタ2つ
    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    // インターフェースのメソッド4つ
    @Override
    public String getMode() {
        return "CSV";
    }

    public ArrayList<Recipe> readData() throws IOException {
        // 最後にリストをreturnするためにlistsを定義しておく。
        ArrayList<Recipe> lists = new ArrayList<>();
        // recipe.csvを代入しているfilePathを読み込む。
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            // csvを1行ごとに読み込んでlineに保存、そしてline内で(",")で区切ったものを配列に格納。
            // このメソッドの最初で定義したlistsはRecipeオブジェクトを格納するリストなので、ここから逆算で考え記述していく。
            // Recipeオブジェクトを作るにあたってまずはrecipeクラスをインスタンス化する必要がある。
            // しかし引数にIngredient型のリストが必要なので、これも作成する。
            // そのために、Ingredientクラスをインスタンス化して引数に配列s1の料理名を除く他の要素を渡す。
            // このIngredientオブジェクトをingredient型のリストに格納する。
            // Ingredient型リストをRecipeインスタンスの引数に渡す。
            // あとはRecipe型リストにRecipeオブジェクトを格納してreturnする。
            while ((line = reader.readLine()) != null) {
                String[] s1 = line.split(",", 2);
                Ingredient ingredient = new Ingredient(s1[1]);
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                ingredients.add(ingredient);
                Recipe recipe = new Recipe(s1[0], ingredients);
                lists.add(recipe);
            }
        } catch (IOException e) {
            throw new IOException();
        }
        return lists;
    }

    public void writeData(Recipe recipe) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, true))) {
            writer.newLine();
            String str = "";
            for (int i = 0; i < recipe.getIngredients().size() - 1; i++) {
                if (i < recipe.getIngredients().size() - 2) {
                    str += recipe.getIngredients().get(i) + ",";
                } else {
                    str += recipe.getIngredients().get(i);
                }
            }
            String writeString = recipe.getName() + "," + str;
            writer.write(writeString);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }

}
