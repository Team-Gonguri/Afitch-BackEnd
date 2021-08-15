package com.project.common.factory;

import com.project.auth.model.entity.User;
import com.project.diet.model.entity.Food;
import com.project.diet.model.entity.Ingredient;
import com.project.diet.model.entity.Meal;
import com.project.diet.model.entity.enums.MealType;

import java.util.Date;
import java.util.List;

public class DietFactory {

    public List<Meal> getMealList(User user) {
        Meal breakFast = new Meal(null,
                MealType.BREAKFAST,
                user,
                new Date(),
                new Ingredient());
        Meal lunch = new Meal(null,
                MealType.LUNCH,
                user,
                new Date(),
                new Ingredient());
        Meal dinner = new Meal(null,
                MealType.DINNER,
                user,
                new Date(),
                new Ingredient());
        Meal snack = new Meal(null,
                MealType.SNACK,
                user,
                new Date(),
                new Ingredient());

        return List.of(breakFast, lunch, dinner, snack);
    }

    public Meal getMeal(User user, MealType mealType) {
        return new Meal(null, mealType, user, new Date(), null);
    }

    public Food getFood() {
        return new Food(
                1L,
                "testFood",
                "testCategory",
                200,
                "g",
                new Ingredient()
        );
    }
}
