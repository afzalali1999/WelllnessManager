package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;

public class Log extends Observable {
    /*
     * String FOOD_CSV - File to create food collection from
     * String LOG_CSV - File to create daily log from
     */
    private static final String FOOD_CSV = "foods.csv";
    private static final String LOG_CSV = "log.csv";
    private static final String EXERCISE_CSV = "exercise.csv";
    /*
     * Variables used for Log
     * FoodCollection foodCollection - Food collection built from csv
     * DailyLog dailyLog - Daily log built from csv
     * Day currentDay - Maintains the current date being accessed by the log
     */
    private FoodCollection foodCollection;
    private DailyLog dailyLog;
    private Day currentDay;
    private ExerciseCollection exerciseCollection;

    /*
     * Create a new log
     */
    public Log(){
        foodCollection = new FoodCollection(FOOD_CSV);
        exerciseCollection = new ExerciseCollection(EXERCISE_CSV);
        dailyLog = new DailyLog(LOG_CSV, foodCollection, exerciseCollection);
        changeDate(LocalDate.now());
    }

    /*
     * Changes the current day to the passed in value
     */
    public void changeDate(LocalDate targetDate) {
        //Create a reference to a day object with the target date for matching
        Day target = new Day(targetDate);
        //Search for a day in the daily log for the given date
        int index = dailyLog.getIndex(target);
        //If a day is found
        if(index != -1) {
            //point current day to the day in the daily log
            currentDay = dailyLog.get(index);
        //If a day is not found
        } else {
            //Set the current day equal to the target and add it to the daily log
            currentDay = target;
            dailyLog.add(currentDay);
        }
    }

    /*
     * Changes the weight for the current day to the passed in value
     */
    public void changeWeight(double newWeight) {
        currentDay.setWeight(newWeight);
    }

    /*
     * Changes the calorie limit for the current day to the passed in value
     */
    public void changeCalorieLimit(double newLimit) {
        currentDay.setCalorieLimit(newLimit);
    }

    /*
     * Adds a new food to the food collection
     */
    public void addFoodToCollection(Food food) {
        foodCollection.add(food);
    }

    public Food checkFoodCollection(String foodName) {
        Food target = foodCollection.get(foodName);
        return target;
    }

    public boolean checkFoodList(int index) {
        if(index > currentDay.getFoodListSize() - 1 || index < 0) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Adds a food to the food list of the current day
     */
    public void addFoodToCurrentDay(String foodName, double servings) {
        Food f = foodCollection.get(foodName);
        currentDay.addFood(f, servings);
        setChanged();
        notifyObservers();
    }

    /*
     * Removes a food from the food list of the current day
     */
    public void removeFoodFromCurrentDay(int index) {
        currentDay.removeFood(index);
        setChanged();
        notifyObservers();
    }

    /*
     * Displays all the information about the current day
     */
    public String displayDayBreakdown() {
        return currentDay.displayDay();
    }

    /*
     * Displays all the foods consumed on the current day
     */
    public void displayFoodsCurrentDay() {
        currentDay.displayFoodLog();
    }

    /*
     * Saves the contents of the food collection and the daily log to their respective files
     */
    public void save() {

        foodCollection.save();

        dailyLog.save();

        exerciseCollection.save();
    }

    /*
     * Displays the current date (YYYY-MM-DD)
     */
    public String displayCurrentDate() {
       return currentDay.toString();
    }

    public void addExerciseToCollection(Exercise exercise) {
        exerciseCollection.add(exercise);
    }
    /*
     * Adds an exercise to the exercise list of the current day
     */
    public void addExerciseToCurrentDay(String exerciseName, double minutes) {
        Exercise e = exerciseCollection.get(exerciseName);
        e.setMinutesOfExercise(minutes);
        currentDay.addExercise(e, minutes);
    }

    public Exercise checkExerciseCollection(String exercise) {
        Exercise target = exerciseCollection.get(exercise);
        return target;
    }


    public Day getCurrentDay(){return currentDay;}

    public boolean checkExerciseList(int index) {
        if(index > currentDay.getExerciseListSize() - 1 || index < 0) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Removes a Exercise from the exercise list of the current day
     */
    public void removeExerciseFromCurrentDay(int index) {
        currentDay.removeExercise(index);
    }

    public ArrayList getFood(){
        return currentDay.getFoodList();
    }
    public ArrayList getExercise(){
        return currentDay.getExerciseList();
    }


}
