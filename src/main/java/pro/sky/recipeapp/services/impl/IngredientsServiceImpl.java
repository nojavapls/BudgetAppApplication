package pro.sky.recipeapp.services.impl;

import pro.sky.recipeapp.model.Ingredient;
import pro.sky.recipeapp.services.IngredientsService;
import pro.sky.recipeapp.services.exceptions.IncorectArgumentException;
import pro.sky.recipeapp.services.exceptions.IncorrectIdException;

import java.util.HashMap;

@org.springframework.stereotype.Service
public class IngredientsServiceImpl implements IngredientsService {
    public HashMap<Integer, Ingredient> ingredientsMap = new HashMap<>();
    private Integer id = 0;

    @Override
    public void putIngredients(Ingredient ingredient) throws IncorectArgumentException {
        if (ingredient != null) {
            ingredientsMap.put(id++, ingredient);
        } else {
            throw new IncorectArgumentException("Fill all fields for ingredient");
        }
    }

    @Override
    public Ingredient getIngredientByID(Integer id) throws IncorrectIdException {
        if (ingredientsMap.containsKey(id))
            return ingredientsMap.get(id);
        else
            throw new IncorrectIdException("Not found: recipe " + id);
    }

}
