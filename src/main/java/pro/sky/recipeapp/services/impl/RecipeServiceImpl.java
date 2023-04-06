package pro.sky.recipeapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.RecipeService;
import pro.sky.recipeapp.services.exceptions.IncorectArgumentException;
import pro.sky.recipeapp.services.exceptions.IncorrectIdException;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

@Service
@Primary
public class RecipeServiceImpl implements RecipeService {
    private final HashMap<Integer, Recipe> recipeMap = new HashMap<>();
    private Integer id = 0;

    private RecipeFilesServiceImpl fileService;

    String ANNOTATION = "Recipe with id ";
    String EDIT = " has been successfully updated.";
    String DELETE = " has been successfully deleted";
    String NOTFOUND = "No recipe with requested id.";


    public RecipeServiceImpl(RecipeFilesServiceImpl fileService) {
        this.fileService = fileService;
    }
    @PostConstruct
    private void init(){
        fileService.readFromFile();
    }
    @Override
    public void putRecipe(Recipe recipe) throws IncorectArgumentException, JsonProcessingException {
        if (!Objects.isNull(recipe)) {
            recipeMap.put(id++, recipe);
            String json = new ObjectMapper().writeValueAsString(recipe);
            fileService.saveToFile(json);
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
    public String editRecipe(int id, Recipe recipe) throws JsonProcessingException {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            String json = new ObjectMapper().writeValueAsString(recipe);
            fileService.saveToFile(json);
            return ANNOTATION + id + EDIT;
        }
        return NOTFOUND + id;
    }

    @Override
    public String deleteRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            fileService.cleanDataFile();
            return ANNOTATION + id + DELETE;
        }
        return NOTFOUND + id;
    }

}
