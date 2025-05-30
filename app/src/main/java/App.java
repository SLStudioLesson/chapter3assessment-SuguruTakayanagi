import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();
            if (choice.equals("2")) {
                JSONDataHandler jsonDataHandler = new JSONDataHandler();
                RecipeUI recipeUI1 = new RecipeUI(jsonDataHandler);
                recipeUI1.displayMenu();
            } else {
                CSVDataHandler csvDataHandler = new CSVDataHandler();
                RecipeUI recipeUI2 = new RecipeUI(csvDataHandler);
                recipeUI2.displayMenu();
            }
            

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}