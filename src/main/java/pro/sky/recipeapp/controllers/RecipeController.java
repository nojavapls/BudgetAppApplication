package pro.sky.recipeapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.RecipeService;
import pro.sky.recipeapp.services.exceptions.IncorectArgumentException;
import pro.sky.recipeapp.services.exceptions.IncorrectIdException;
//import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    String NOTFOUND = "No recipe with requested id.";
    public RecipeController (RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new recipe.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                         description = "New recipe added.")
    })
    public void createRecipe (@RequestBody Recipe recipe) throws IncorectArgumentException {
        recipeService.putRecipe(recipe);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a recipe by ID.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Recpie listed.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )

    })
    public Recipe getRecipe (@PathVariable Integer id) throws IncorrectIdException {
        return recipeService.getRecipeByID(id);
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit recipe")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Recipe successfully edited.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    })
    })
    public ResponseEntity<String> editRecipe (@PathVariable int id, @RequestBody Recipe recipe) {
        String responce = recipeService.editRecipe(id, recipe);
        if (responce.equals(NOTFOUND)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responce);
    }

    @DeleteMapping("/delete/{id}")
    @Parameters(value = {
            @Parameter(name = "id")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Recipe removed successfully.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )

    })
    public ResponseEntity<String> deleteRecipe (@PathVariable Integer id) {
        String responce = recipeService.deleteRecipe(id);
        if (responce.equals(NOTFOUND)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responce);
    }

}
