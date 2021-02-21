package view;

import model.Log;

public class TextUI {
    /*
     * Variables used for TextUI
     * Log data - Model Object referenced by this TextUI
     */
    Log data;

    /*
     * Creates a new TextUI
     */
    public TextUI(Log log){
        data = log;
    }

    /*
     * Displays the menu with program options
     */
    public void displayMenu() {
        System.out.println("=======================================================");
        data.displayCurrentDate();
        System.out.println("Select Date (d)");
        System.out.println("Modify Weight (w)");
        System.out.println("Modify Calorie Limit (c)");
        System.out.println("Add New Food To Collection (f)");
        System.out.println("Add Food To Today's Log (a)");
        System.out.println("Remove Food From Today's Log (r)");
        System.out.println("View Today's Log (v)");
        System.out.println("Save (s)");
        System.out.println("Exit (e)");
        System.out.println("=======================================================");
        System.out.println("Please select a menu option");
    }

    /*
     * Displays a request for the user to enter a new date
     */
    public void requestDate(){
        System.out.println("Please enter a date to log activities for (YYYY/MM/DD)");
    }

    /*
     * Displays a request for the user to enter a new weight
     */
    public void requestWeight(){
        System.out.println("Please enter the new weight");
    }

    /*
     * Displays a request for the user to enter a new calorie limit
     */
    public void requestCalorieLimit(){
        System.out.println("Please enter the new calorie limit");
    }

    /*
     * Displays a request for the user to enter a food name
     */
    public void requestFoodName() {
        System.out.println("Please enter the name of the food");
    }

    /*
     * Displays a request for the user to enter a number of servings
     */
    public void requestFoodServings() {
        System.out.println("Please enter the number of servings");
    }

    public void checkForRecipe() {
        System.out.println("Does this food consist of two or more ingredients? (y/n)");
    }

    public void displayFoodExists() {
        System.out.println("Food with that name already exists in the collection, use a different name!");
    }

    /*
     * Displays list of foods consumed followed by a request for the user to enter the number
     * of the food item in the list to be removed
     */
    public void requestFoodIndex() {
        data.displayFoodsCurrentDay();
        System.out.println("Please select the number corresponding to the food to be removed");
    }

    /*
     * Displays the breakdown for the selected day
     */
    public void displayBreakdown(){
        data.displayDayBreakdown();
    }

    /*
     * Displays a message stating the collections are being saved
     */
    public void saving() {
        System.out.println("Saving to file...");
    }

    /*
     * Displays a message stating the collections were saved successfully
     */
    public void saved() {
        System.out.println("Saved successfully!");
    }

    /*
     * Displays an error regarding user input on menu item selection
     */
    public void inputError() {
        System.out.println("Input Error! Please enter 1 character corrsponding to your menu selection!");
    }
}
