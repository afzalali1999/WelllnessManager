package model;

import java.time.LocalDate;
import java.util.*;

public class Day<LinkedHashmap> {
    /*
     * Variables used for Day
     * LocalDate date - The year, month, and day (yyyy, mm, dd)
     * double weight - The user's weight on this day
     * double limit - The user's calorie limit for this day
     * ArrayList<Map.Entry<Food, Double>> consumed - Array List containing map entries
     * this allows for key/value pairs to be stored with possibly duplicate keys and
     * allows for unambiguous access to the food entries for this day
     */
    private LocalDate date;
    private double weight;
    private double limit;
    private double totalCalories;
    private double totalFat;
    private double totalCarbs;
    private double totalProtein;
    private ArrayList<Map.Entry<Food, Double>> consumed = new ArrayList<Map.Entry<Food, Double>>();
    private ArrayList<Map.Entry<Exercise, Double>> exercises = new ArrayList<Map.Entry<Exercise, Double>>();

    /*
     * Create a new day with date initialized to today and weight/limit at default value
     */
    public Day() {
        date = LocalDate.now();
        weight = 150.0;
        limit = 2000.0;
        totalCalories = 0.0;
        totalFat = 0.0;
        totalCarbs = 0.0;
        totalProtein = 0.0;
    }

    /*
     * Create a new day with date initialized to passed in values and weight/limit at default value
     */
    public Day(LocalDate targetDate) {
        //Add try catch block for DateTimeException
        date = targetDate;
        weight = 150.0;
        limit = 2000.0;
        totalCalories = 0.0;
        totalFat = 0.0;
        totalCarbs = 0.0;
        totalProtein = 0.0;
    }

    /*
     * Create a new day with date initialized to passed in values and weight/limit at default value
     */
    public Day(int year, int month, int day) {
        //Add try catch block for DateTimeException
        date = LocalDate.of(year, month, day);
        weight = 150.0;
        limit = 2000.0;
        totalCalories = 0.0;
        totalFat = 0.0;
        totalCarbs = 0.0;
        totalProtein = 0.0;
    }

    /*
     * Create a new day with date, weight, and limit set to the passed in values
     */
    public Day(int year, int month, int day, double weightToday, double calorieLimit) {
        //Add try catch block for DateTimeException
        date = LocalDate.of(year, month, day);
        weight = weightToday;
        limit = calorieLimit;
        totalCalories = 0.0;
        totalFat = 0.0;
        totalCarbs = 0.0;
        totalProtein = 0.0;
    }

    /*
     * Set the date to the passed in values
     */
    public void setDate(int year, int month, int day) {
        //Add try catch block for DateTimeException
        date = LocalDate.of(year, month, day);
    }

    /*
     * Set the year to the passed in value
     */
    public void setYear(int year) {
        //Add try catch block for DateTimeException
        date = date.withYear(year);
    }

    /*
     * Set the month to the passed in value
     */
    public void setMonth(int month) {
        //Add try catch block for DateTimeException
        date = date.withMonth(month);
    }

    /*
     * Set the day to the passed in value
     */
    public void setDay(int day) {
        //Add try catch block for DateTimeException
        date = date.withDayOfMonth(day);
    }

    /*
     * Retrieve the date
     */
    public LocalDate getDate() {
        return date;
    }

    /*
     * Retrieve the year
     */
    public int getYear() {
        return date.getYear();
    }

    /*
     * Retrieve the month
     */
    public int getMonth() {
        return date.getMonthValue();
    }

    /*
     * Retrieve the day
     */
    public int getDay() {
        return date.getDayOfMonth();
    }

    /*
     * Set the user's weight for this day to the passed in value
     */
    public void setWeight(double weightToday) {
        weight = weightToday;
    }

    /*
     * Retrieve the user's weight for this day
     */
    public double getWeight() {
        return weight;
    }

    /*
     * Set the user's calorie limit for this day to the passed in value
     */
    public void setCalorieLimit(double calorieLimit) {
        limit = calorieLimit;
    }

    /*
     * Retrieve the user's calorie limit for this day
     */
    public double getCalorieLimit() {
        return limit;
    }

    /*
     * Add a food to the list of foods consumed on this day
     */
    public void addFood(Food food, double servings) {
        consumed.add(new AbstractMap.SimpleEntry<Food, Double>(food, servings));
        totalCalories += food.getCaloriesPerServing() * servings;
        totalFat += food.getGramsOfFat() * servings;
        totalCarbs += food.getGramsOfCarbs() * servings;
        totalProtein += food.getGramsOfProtein() * servings;
    }

    /*
     * Remove a food from the list of foods consumed on this day
     */
    public void removeFood(int index) {
        Food f = getFood(index);
        double servings = getServings(index);
        totalCalories -= f.getCaloriesPerServing() * servings;
        totalFat -= f.getGramsOfFat() * servings;
        totalCarbs -= f.getGramsOfCarbs() * servings;
        totalProtein -= f.getGramsOfProtein() * servings;
        consumed.remove(index);
    }

    /*
     * Retrieve a food from the list of foods consumed on this day
     */
    public Food getFood(int index) {
        Map.Entry<Food, Double> target = consumed.get(index);
        return target.getKey();
    }

    /*
     * Retrieve the servings for a food fromt he list of foods consumed on this day
     */
    public double getServings(int index) {
        Map.Entry<Food, Double> target = consumed.get(index);
        return target.getValue();
    }

    /*
     * Retrieve the food list for this day
     */
    public ArrayList getFoodList() {
        return consumed;
    }

    /*
     * Retrieve the number of entries in the food list
     */
    public int getFoodListSize() {
        return consumed.size();
    }

    /*
     * Check if this food is equal to another food
     * Since foods have a unique name, we only need to compare the name
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }

        if(!(obj instanceof Day)) {
            return false;
        }

        Day day = (Day) obj;

        return date.equals(day.getDate());
    }

    /*
     * Set this day equal to another day
     */
    public void setEqual(Day target) {
        date = target.date;
        weight = target.weight;
        limit = target.limit;
        totalCalories = target.totalCalories;
        totalFat = target.totalFat;
        totalCarbs = target.totalCarbs;
        totalProtein = target.totalProtein;
        consumed = target.consumed;
    }

    /*
     * String representation of a day (minus the food collection)
     */
    public String toString() {
        return "Date: " + date.toString() + " Weight: " + weight + " Calorie Limit: " + limit +
                "\nTotal Calories: " + totalCalories + "\nTotal Fat: " + totalFat +
                "\nTotal Carbs: " + totalCarbs + "\nTotal Protein: " + totalProtein;
    }

    /*
     * Displays the daily breakdown for the day. Calls various helper functions.
     */
    public String displayDay() {
        long[] percents = calcPercents();
        return displayBreakdown(percents[0], percents[1], percents[2]);
    }

    /*
     * Helper function for displayDay(). Calculates the percentages of fats, carbs, and proteins
     * while ensuring that the rounded totals are equal to 100%
     */
    public long[] calcPercents() {
        long fat, carbs, protein;
        long prev = 0;
        double grams = totalFat + totalCarbs + totalProtein;
        double cValue = (totalFat/grams) * 100;
        long cRound = Math.round(cValue);
        fat = cRound;
        prev = cRound;
        cValue += (totalCarbs/grams) * 100;
        cRound = Math.round(cValue);
        carbs = cRound - prev;
        prev = cRound;
        cValue += (totalProtein/grams) * 100;
        cRound = Math.round(cValue);
        protein = cRound - prev;

        long[] percents = new long[3];
        percents[0] = fat;
        percents[1] = carbs;
        percents[2] = protein;
        return percents;
    }





    /*
     * Helper function for displayDay() called by calcPercents(). This displays the current
     * date, weight, calorie limit, total calories consumed, the nutritional breakdown, and
     * a list of foods consumed with their corresponding servings and calorie counts
     */
    private String displayBreakdown(long fat, long carbs, long protein) {
        String output="<html>";
        output+=("Summary for " + date+"<br>");
        output+=("=======================================================<br>");
        output+=("Weight: " + weight+"<br>");
        output+=("Calorie Limit: " + limit +"<br>");
        output+=("Calories Consumed: " + totalCalories+"<br>");
        output+=("Calories Expended: " + getCaloriesExpended()+"<br>");
        output+=("Net Calories: " + getNetCalories()+"<br>");
        output+=("=======================================================<br>");
        output+=("Nutritional Breakdown<br>");
        output+=("=======================================================<br>");
        output+=("Fat: " + fat + "%"+"<br>");
        output+=("Carbs: " + carbs + "%"+"<br>");
        output+=("Protein: " + protein + "%"+"<br>");
        output+=("=======================================================<br>");
        output+=("Foods consumed<br>");
        output+=("=======================================================<br>");
        return output+displayFoodLog()+displayExerciseLog();
    }

    /*
     * Doubles as a helper function for displayDay() but can be called by itself. Shows the list of
     * foods consumed with their corresponding servings and calorie counts
     */
    public String displayFoodLog() {
        //If no foods have been entered today
        String output ="";
        if (consumed.size() < 1) {
            //Display that no foods have been entered
            output+=("No foods entered today<br>");
        //otherwise
        } else {
            //For every entry in the food list
            for (int i = 0; i < consumed.size(); i++) {
                //Create a reference to the food object stored in the map key
                Food foodEntry = consumed.get(i).getKey();
                //Retrieve the number of servings store din the map value
                double servings = consumed.get(i).getValue();
                //Calculate and store the number of calories
                double calories = foodEntry.getCaloriesPerServing() * servings;
                //Output a list number, the food name, number of servings, and amount of calories for this food item
                output+=((i + 1) + ". Food: " + foodEntry.getFoodName() + " Servings: " + servings +
                        " Calories: " + calories +"<br>");
            }
        }
        return output;
    }

    public String displayExerciseLog() {
        //If no foods have been entered today
        String output ="";
        if (exercises.size() < 1) {
            //Display that no foods have been entered
            output+=("No exercises entered today");
            //otherwise
        } else {
            //For every entry in the food list
            for (int i = 0; i < exercises.size(); i++) {
                //Create a reference to the food object stored in the map key
                Exercise exerEntry = exercises.get(i).getKey();
                //Retrieve the number of servings store din the map value
                double time = exercises.get(i).getValue();
                //Calculate and store the number of calories
                double calories = exerEntry.getCaloriesExpended() * (weight/100.0) * (exercises.get(i).getValue()/60.0);
                //Output a list number, the food name, number of servings, and amount of calories for this food item
                output+=((i + 1) + ". Exercise: " + exerEntry.getExerciseName() + " Minutes: " + time +" " +
                        "Calories Expended:" + (calories+"<br>"));
            }
        }
        return output;
    }

    /*
     * Retrieve the exercise list for this day
     */
    public ArrayList getExerciseList() {
        return exercises;
    }

    /*
     * Retrieve the number of entries in the exercises list
     */
    public int getExerciseListSize() {
        return exercises.size();
    }

    /*
     * Add an exercise to the list of exercises performed on this day
     */
    public void addExercise(Exercise exercise, double minutes) {
        exercises.add(new AbstractMap.SimpleEntry<Exercise, Double>(exercise, minutes));
    }

    /*
     * Retrieve an exercise from the list of exercise performed on this day
     */
    public Exercise getExercise(int index) {
        Map.Entry<Exercise, Double> target = exercises.get(index);
        return target.getKey();
    }

    /*
     * Retrieve minutes from the list of exercise performed on this day
     */
    public double getExerciseMinutes(int index) {
        Map.Entry<Exercise, Double> target = exercises.get(index);
        return target.getValue();
    }

    public double getCaloriesExpended() {
        double caloriesExpended = 0.0;

        for(int i=0; i<exercises.size(); i++) {
            caloriesExpended += exercises.get(i).getKey().getCaloriesExpended() * (weight/100.0) * (exercises.get(i).getKey().getMinutesOfExercise()/60.0);
        }

        return caloriesExpended;
    }

    public double getNetCalories() {
        return totalCalories - getCaloriesExpended();
    }

    /*
     * Remove an exercise from the list of exercise performed on this day
     */
    public void removeExercise(int index) {
        exercises.remove(index);
    }
    public double getTotalCalories(){return totalCalories;}

}