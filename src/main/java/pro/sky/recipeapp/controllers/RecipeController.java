package pro.sky.recipeapp.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.RecipeService;
import pro.sky.recipeapp.services.exceptions.IncorectArgumentException;
import pro.sky.recipeapp.services.exceptions.IncorrectIdException;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController (RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    public void createRecipe (@RequestBody Recipe recipe) throws IncorectArgumentException {
        recipeService.putRecipe(recipe);
    }

    @GetMapping("/get")
    public Recipe getRecipe (@RequestParam int id) throws IncorrectIdException {
        return recipeService.getRecipeByID(id);
    }

}
