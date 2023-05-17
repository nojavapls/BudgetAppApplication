package pro.sky.recipeapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipeapp.model.Ingredient;
import pro.sky.recipeapp.services.IngredientsService;
import pro.sky.recipeapp.services.exceptions.IncorectArgumentException;
import pro.sky.recipeapp.services.exceptions.IncorrectIdException;
@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

        private final IngredientsService ingredientsService;
        String NOTFOUND = "No ingredient with requested id.";
        public IngredientsController (IngredientsService ingredientsService) {
            this.ingredientsService = ingredientsService;
        }

        @PostMapping("/add")
        public void addIngredients (@RequestBody Ingredient ingredient) throws IncorectArgumentException, JsonProcessingException {
            ingredientsService.putIngredients(ingredient);
        }

        @GetMapping("/get")
        public Ingredient getIngredient (@RequestParam int id) throws IncorrectIdException {
            return ingredientsService.getIngredientByID(id);
        }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit ingredient by ID.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ingredient edited successfully.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public ResponseEntity<String> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) throws JsonProcessingException {
        String responce = ingredientsService.editIngredient(id, ingredient);
        if (responce.equals(NOTFOUND)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responce);
    }


    @DeleteMapping ("/delete/{id}")
    @Operation(summary = "Remove ingredient from list.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Indregient removed successfully."
            )
    })
    public ResponseEntity<String> deleteIngredientById(@PathVariable int id) {
        String reponse = ingredientsService.deleteIngredient(id);
        if (reponse.equals(NOTFOUND)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reponse);
    }
}

