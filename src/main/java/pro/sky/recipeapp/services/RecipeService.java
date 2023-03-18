package pro.sky.recipeapp.services;

import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.exceptions.IncorectArgumentException;
import pro.sky.recipeapp.services.exceptions.IncorrectIdException;

//
//    int getDailyBudget();
//
//    int getBalance();
//
//    int getVacationBonus(int daysCount);
//
//    int getSalaryWithVacation(int vacationDaysCount, int vacationWorkingDaysCount, int workingDaysInMonth);
    public interface RecipeService {
         void putRecipe(Recipe recipe) throws IncorectArgumentException;

         Recipe getRecipeByID (Integer id) throws IncorrectIdException;
    }

