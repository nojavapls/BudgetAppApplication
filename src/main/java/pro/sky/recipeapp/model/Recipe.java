package pro.sky.recipeapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
        private final String name;
        private final int cookingTime;
        private List<Ingredient> ingredients;
        private final List<String> steps;

}
