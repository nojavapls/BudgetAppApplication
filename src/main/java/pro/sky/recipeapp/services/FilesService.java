package pro.sky.recipeapp.services;

import java.io.File;
import java.nio.file.Path;

public interface FilesService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();
//    boolean saveIngredients(String json);

    boolean saveRecipes(String json);

    File getRecipesFile();

    File getIngredientsFile();
}
