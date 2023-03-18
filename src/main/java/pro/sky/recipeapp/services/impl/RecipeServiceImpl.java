package pro.sky.recipeapp.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.RecipeService;
import pro.sky.recipeapp.services.exceptions.IncorectArgumentException;
import pro.sky.recipeapp.services.exceptions.IncorrectIdException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

@Service
//public class RecipeServiceImpl implements RecipeService {

//    public static final int SALARY = 120_000;
//    public static final int AVG_SALARY = (59_000 + 59_000 + 59_000 + 120_000 + 120_000 + 120_000 + 120_000 + 120_000 + 120_000 + 120_000 + 120_000 + 120_000)/12;
//    public static final double AVG_DAYS = 29.3;
//    @Override
//    public int getDailyBudget() {
//        return SALARY/31;
//    }
//
//    @Override
//    public int getBalance() {
//
//        return SALARY - LocalDate.now().getDayOfMonth() * getDailyBudget();
//    }
//
//    @Override
//    public int getVacationBonus(int daysCount){
//        double avgDaySalary = AVG_SALARY / AVG_DAYS;
//        return (int)(daysCount * avgDaySalary);
//    }
//
//    @Override
//    public int getSalaryWithVacation(int vacationDaysCount, int vacationWorkingDaysCount, int workingDaysInMonth){
//        int salary = SALARY / workingDaysInMonth * (workingDaysInMonth - vacationWorkingDaysCount);
//        return salary + getVacationBonus(vacationDaysCount);
//    }
public class RecipeServiceImpl implements RecipeService {
    private final HashMap<Integer, Recipe> recipeMap = new HashMap<>();
    private Integer id = 0;

    @Override
    public void putRecipe(Recipe recipe) throws IncorectArgumentException {
        if (!Objects.isNull(recipe)) {
            recipeMap.put(id++, recipe);
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
            throw new IncorrectIdException("Not found: recipe " + id);
    }
}
