package model;

public class BasicFood extends Food {
   
   /*
    * Create a new BasicFood with values initialized to empty/0.0.
    */
   public BasicFood() {
      name = "";
      calories = 0.0;
      fat = 0.0;
      carbs = 0.0;
      protein = 0.0;
   }

   /*
    * Create a new BasicFood with values initialized to empty/0.0.
    */
   public BasicFood(String foodName) {
      name = foodName;
      calories = 0.0;
      fat = 0.0;
      carbs = 0.0;
      protein = 0.0;
   }
   
   /*
    * Create a new BasicFood with all values set.
    */
   public BasicFood(String foodName, double caloriesPerServing, double gramsOfFat, double gramsOfCarbs, double gramsOfProtein) {
      name = foodName;
      calories = caloriesPerServing;
      fat = gramsOfFat;
      carbs = gramsOfCarbs;
      protein = gramsOfProtein;
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

      if(!(obj instanceof BasicFood)) {
         return false;
      }

      BasicFood bf = (BasicFood) obj;

      return name.equals(bf.name);
   }

   /*
    * Set this basic food equal to another food
    */
   public void setEqual(BasicFood target) {
      name = target.name;
      calories = target.calories;
      fat = target.fat;
      carbs = target.carbs;
      protein = target.protein;
   }
}