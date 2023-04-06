package pro.sky.recipeapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp.model.Ingredient;
import pro.sky.recipeapp.services.exceptions.IncorectArgumentException;
import pro.sky.recipeapp.services.exceptions.IncorrectIdException;

@Service
public interface IngredientsService {
        void putIngredients(Ingredient ingredient) throws IncorectArgumentException, JsonProcessingException;

        Ingredient getIngredientByID (Integer id) throws IncorrectIdException;
        String deleteIngredient(Integer id);

        String editIngredient(Integer id, Ingredient ingredient) throws JsonProcessingException;
}
