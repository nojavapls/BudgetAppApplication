package pro.sky.recipeapp.services;

import org.springframework.stereotype.Service;
import pro.sky.recipeapp.model.Ingredient;
import pro.sky.recipeapp.services.exceptions.IncorectArgumentException;
import pro.sky.recipeapp.services.exceptions.IncorrectIdException;

@Service
public interface IngredientsService {
        void putIngredients(Ingredient ingredient) throws IncorectArgumentException;

        Ingredient getIngredientByID (Integer id) throws IncorrectIdException;
}
