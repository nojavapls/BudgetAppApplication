package pro.sky.recipeapp.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.recipeapp.services.FilesService;
import pro.sky.recipeapp.services.impl.IngredientsFilesService;
import pro.sky.recipeapp.services.impl.RecipeFilesServiceImpl;

import java.io.*;

@RestController
@RequestMapping("/files")
    public class FilesController {

//        @Value("${path.to.data.file}")
//        private String filesPath;
//        @Value("${name.of.ingredients.file}")
//        private String ingredientFileName;
//        @Value("${name.of.recipe.file}")
//        private String recipesFileName;
        private final RecipeFilesServiceImpl filesService;
        private final IngredientsFilesService ingredientsFilesService;



    public FilesController(RecipeFilesServiceImpl filesService, IngredientsFilesService ingredientsFilesService) {
            this.filesService = filesService;
            this.ingredientsFilesService = ingredientsFilesService;
    }

        @GetMapping("/downloadRecipes")
        @Operation(
                summary = "выгрузка базы рецептов"
        )
        @ApiResponses(
                value = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "File downloaded successfully.",
                                content = {
                                        @Content(
                                                mediaType = "application/json",
                                                array = @ArraySchema(schema = @Schema(implementation = File.class))
                                        )
                                }
                        ),
                        @ApiResponse(
                                responseCode = "404",
                                description = "NOT FOUND"
                        ),
                        @ApiResponse(
                                responseCode = "400",
                                description = "Invalid request."
                        )
                }

        )
        public ResponseEntity<InputStreamResource> downloadRecipes() throws FileNotFoundException {
            File file = filesService.getDataFile();
            if (file.exists()) {
                InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipes.json\"")
                        .contentLength(file.length())
                        .body(isr);
            } else {
                return ResponseEntity.noContent().build();
            }
        }

    @PostMapping(value = "/updateRecipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "накатываем новый файл с рецептами")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Recipes updated successfully."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND"
            )
    })
    public ResponseEntity<Void> updateRecipes(@RequestParam MultipartFile file) {
        filesService.cleanDataFile();
        File recipesFile = filesService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(recipesFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @PostMapping(value = "/updateIngredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "накатываем базейку ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ingredients updated successfully."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND"
            )
    })
    public ResponseEntity<Void> updateIngredientsFile(@RequestParam MultipartFile file){
        ingredientsFilesService.cleanDataFile();
        File ingredientsFile = ingredientsFilesService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(ingredientsFile)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

