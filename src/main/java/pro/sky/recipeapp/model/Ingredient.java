package pro.sky.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Ingredient {
    private final String name;
    private final int quantity;
    private final String unit;
}
