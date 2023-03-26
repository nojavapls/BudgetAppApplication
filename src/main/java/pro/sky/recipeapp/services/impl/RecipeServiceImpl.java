package pro.sky.recipeapp.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.RecipeService;
import pro.sky.recipeapp.services.exceptions.IncorectArgumentException;
import pro.sky.recipeapp.services.exceptions.IncorrectIdException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final HashMap<Integer, Recipe> recipeMap = new HashMap<>();
    private Integer id = 0;

    String ANNOTATION = "Recipe with id ";
    String EDIT = " has been successfully updated.";
    String DELETE = " has been successfully deleted";
    String NOTFOUND = "No recipe with requested id.";
    @Override
    public void putRecipe(Recipe recipe) throws IncorectArgumentException {
        if (!Objects.isNull(recipe)) {
            recipeMap.put(id++, recipe);
        } else {
            throw new IncorectArgumentException("Fill all fields for recipe");
        }
    }

    @Override
    public Recipe getRecipeByID (Integer id) throws IncorrectIdException {
//        try{
//            recipeMap.containsKey(id);
//        } catch (Exception e) {
//            throw new IncorrectIdException("Not found: recipe " + id);
//        }


        if (recipeMap.containsKey(id))
            return recipeMap.get(id);
        else
            throw new IncorrectIdException(NOTFOUND + id);
    }

    @Override
    public String editRecipe(int id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            return ANNOTATION + id + EDIT;
        }
        return NOTFOUND + id;
    }

    @Override
    public String deleteRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            return ANNOTATION + id + DELETE;
        }
        return NOTFOUND + id;
    }
}
