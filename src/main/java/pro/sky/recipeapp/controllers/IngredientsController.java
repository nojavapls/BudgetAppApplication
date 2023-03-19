package pro.sky.recipeapp.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.recipeapp.model.Ingredient;
import pro.sky.recipeapp.services.IngredientsService;
import pro.sky.recipeapp.services.exceptions.IncorectArgumentException;
import pro.sky.recipeapp.services.exceptions.IncorrectIdException;
@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

        private final IngredientsService ingredientsService;

        public IngredientsController (IngredientsService ingredientsService) {
            this.ingredientsService = ingredientsService;
        }

        @PostMapping("/add")
        public void addIngredients (@RequestBody Ingredient ingredient) throws IncorectArgumentException {
            ingredientsService.putIngredients(ingredient);
        }

        @GetMapping("/get")
        public Ingredient getIngredient (@RequestParam int id) throws IncorrectIdException {
            return ingredientsService.getIngredientByID(id);
        }
}

