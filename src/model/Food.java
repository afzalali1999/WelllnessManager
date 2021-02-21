package model;

public abstract class Food {
   /*
    * Variables used by all foods.
    * String name - The name of the food
    * double calories - The amount of calories per serving
    * double fat - The number of grams of fat per serving
    * double carbs - The number of grams of carbs per serving
    * double protein - The number of grams of protein per serving
    */
   protected String name;
   protected double calories;
   protected double fat;
   protected double carbs;
   protected double protein;
   
   /*
    * Set the name of the food.
    */
   public void setFoodName(String foodName) {
      name = foodName;
   }
   
   /*
    * Set the number of calories per serving.
    */
   public void setCaloriesPerServing(double caloriesPerServing) {
      calories = caloriesPerServing;
   }
   
   /*
    * Set the number of grams of fat per serving.
    */
   public void setGramsOfFat(double gramsOfFat) {
      fat = gramsOfFat;
   }
   
   /*
    * Set the number of grams of carbs per serving.
    */
   public void setGramsOfCarbs(double gramsOfCarbs) {
      carbs = gramsOfCarbs;
   }
   
   /*
    * Set the number of grams of protein per serving.
    */
   public void setGramsOfProtein(double gramsOfProtein) {
      protein = gramsOfProtein;
   }
   
   /*
    * Get the name of the food.
    */
   public String getFoodName() {
      return name;
   }
   
   /*
    * Get the number of calories per serving.
    */
   public double getCaloriesPerServing() {
      return calories;
   }
   
   /*
    * Get the number of grams of fat per serving.
    */
   public double getGramsOfFat() {
      return fat;
   }
   
   /*
    * Get the number of grams of carbs per serving.
    */
   public double getGramsOfCarbs() {
      return carbs;
   }
   
   /*
    * Get the number of grams of protein per serving.
    */
   public double getGramsOfProtein() {
      return protein;
   }
   
   /*
    * Override the toString method to show the name of the food and its nutrition info
    */
   public String toString() {
      return "Name: " + name + "\nCalories Per Serving: " + calories + "\nGrams of Fat: " + fat
               + "\nGrams of Carbs: " + carbs + "\nGrams of Protein: " + protein + "\n";
   }

   /*
    * Calculate the nutritional values for this recipe from the list of ingredients
    */
   public void calculateNutrition() {
      //Optional. Only complex foods require extra calculation
   }
}