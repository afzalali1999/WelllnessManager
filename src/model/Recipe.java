package model;

import java.util.HashMap;
import java.util.Set;

public class Recipe extends Food {
   /*
    * Variables used for Recipe
    * HashMap<Food, Integer> ingredients - List of all ingredients contained in this recipe.
    */
   private HashMap<Food, Double> ingredients = new HashMap<Food, Double>();
   
   /*
    * Create a new Recipe with name initialized to empty string.
    */
   public Recipe() {
      name = "";
   }
   
   /*
    * Create a new Recipe with name initialized to the passed in value
    */
   public Recipe(String foodName) {
      name = foodName;
   }
   
   /*
    * Add an ingredient to the list of ingredients
    */
   public void addIngredient(Food ingredient, double servings) {
      ingredients.put(ingredient, servings);
   }
   
   /*
    * Remove an ingredient from the list of ingredients
    */
   public void removeIngredient(Food ingredient) {
      ingredients.remove(ingredient);
   }

   /*
    * Retrieve the name of an ingredient
    */
   public String getIngredientName(Food ingredient) {
      return ingredient.getFoodName();
   }

   /*
    * Retrieve the number of servings of an ingredient
    */
   public double getIngredientServings(Food ingredient) {
      return ingredients.get(ingredient);
   }

   /*
    * Retrieve the ingredient list
    */
   public Set<Food> getIngredientList() {
      return ingredients.keySet();
   }
   
   /*
    * Add an ingredient to the list of ingredients and calculate nutritional values
    *    for the recipe.
    */
   public void addIngredientAndCalc(Food ingredient, double servings) {
      ingredients.put(ingredient, servings);
      calculateNutrition();
   }
   
   /*
    * Remove an ingredient from the list of ingredients and calculate the nutritional
    *    values for the recipe.
    */
   public void removeIngredientAndCalc(Food ingredient) {
      ingredients.remove(ingredient);
      calculateNutrition();
   }

   /*
    * Check if this basic food is equal to another basic food
    * Since foods have a unique name, we only need to compare the name
    */
   @Override
   public boolean equals(Object obj) {
      if(obj == this){
         return true;
      }

      if(!(obj instanceof Recipe)) {
         return false;
      }

      Recipe r = (Recipe) obj;

      return name.equals(r.name);
   }

   /*
    * Set this food equal to another food
    */
   public void setEquals(Recipe target) {
      name = target.name;
      calories = target.calories;
      fat = target.fat;
      carbs = target.carbs;
      protein = target.protein;
      ingredients = target.ingredients;
   }

   /*
    * Calculate the nutritional values for this recipe from the list of ingredients
    */
   @Override
   public void calculateNutrition() {
      calories = 0.0;
      fat = 0.0;
      carbs = 0.0;
      protein = 0.0;

      for(Food i : ingredients.keySet()) {
         calories += (i.getCaloriesPerServing() * ingredients.get(i));
         fat += (i.getGramsOfFat() * ingredients.get(i));
         carbs += (i.getGramsOfCarbs() * ingredients.get(i));
         protein += (i.getGramsOfProtein() * ingredients.get(i));
      }
   }
}