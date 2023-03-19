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
            throw new IncorrectIdException("Not found: recipe " + id);
    }
}
