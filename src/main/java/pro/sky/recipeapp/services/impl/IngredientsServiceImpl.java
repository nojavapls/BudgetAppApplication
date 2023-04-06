package pro.sky.recipeapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pro.sky.recipeapp.model.Ingredient;
import pro.sky.recipeapp.services.FilesService;
import pro.sky.recipeapp.services.IngredientsService;
import pro.sky.recipeapp.services.exceptions.IncorectArgumentException;
import pro.sky.recipeapp.services.exceptions.IncorrectIdException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Objects;

@org.springframework.stereotype.Service

public class IngredientsServiceImpl implements IngredientsService {
    public HashMap<Integer, Ingredient> ingredientsMap = new HashMap<>();
    private Integer id = 0;

    private IngredientsFilesService fileService;

    String ANNOTATION = "Ingredient with id ";
    String EDIT = " has been successfully updated.";
    String DELETE = " has been successfully deleted";
    String NOTFOUND = "No Ingredient with id ";

    public IngredientsServiceImpl(IngredientsFilesService fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    private void init(){
        fileService.readFromFile();
    }

    @Override
    public void putIngredients(Ingredient ingredient) throws IncorectArgumentException, JsonProcessingException {
        if (!Objects.isNull(ingredient)) {
            ingredientsMap.put(id++, ingredient);
            String json = new ObjectMapper().writeValueAsString(ingredient);
            fileService.saveToFile(json);
        } else {
            throw new IncorectArgumentException("Fill all fields for ingredient");
        }
    }

    @Override
    public Ingredient getIngredientByID(Integer id) throws IncorrectIdException {
        if (ingredientsMap.containsKey(id))
            return ingredientsMap.get(id);
        else
            throw new IncorrectIdException("Not found: ingredient " + id);
    }

    @Override
    public String editIngredient(Integer id, Ingredient ingredient) throws JsonProcessingException {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.put(id, ingredient);
            String json = new ObjectMapper().writeValueAsString(ingredient);
            fileService.saveToFile(json);
            return ANNOTATION + id + EDIT;
        }
        return NOTFOUND + id;
    }

    @Override
    public String deleteIngredient(Integer id) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.remove(id);
            fileService.cleanDataFile();
            return ANNOTATION + id + DELETE;
        }
        return NOTFOUND + id;
    }

}
