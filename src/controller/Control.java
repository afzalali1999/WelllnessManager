package controller;

import model.Log;
import model.BasicFood;
import model.Recipe;
import model.Food;
import view.TextUI;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Control {
    /*
     * Variables used for FoodCollection
     * Log data - Model Object referenced by this Control
     * TextUI tui - View Object referenced by this Control
     * Scanner sc - Retrieves user input
     * boolean execute - Controls operation of this Control
     */
    private Log data;
    private TextUI tui;
    private Scanner sc;
    private boolean execute;

    /*
     * Creates a new Control from the passed in Log and TextUI
     */
    public Control (Log log, TextUI view){
        data=log;
        tui=view;
        sc  = new Scanner(System.in);
        execute = true;
    }

    /*
     * Starts the control loop which modifies the TextUI and Log based on user input
     */
    public void execute() {
        while(execute) {
            readCommand();
        }
    }

    /*
     * Reads and interprets commands from the user
     */
    public void readCommand(){
        tui.displayMenu();
        char c = sc.next().charAt(0);
        sc.nextLine();

        switch (c){

            case 'd':
                changeDate();
                break;
            case 'w':
                changeWeight();
                break;
            case 'c':
                changeCalorieLimit();
                break;
            case 'f':
                addFoodToCollection();
                break;
            case 'a':
                addFoodToDay();
                break;
            case 'r':
                removeFoodFromDay();
                break;
            case 'v':
                showDay();
                break;
            case 's':
                save();
                break;
            case 'e':
                execute = false;
                break;
            default:
                tui.inputError();
                break;

        }
    }

    /*
     * Controls the changing of the current date
     */
    public void changeDate() {
        //Loop control
        boolean control = true;
        //Year, month, day
        int year = 0;
        int month = 0;
        int day = 0;
        //Ask for the 4 digit year
        System.out.println("Please enter the 4 digit year (YYYY)");
        //WHile control is true
        while(control) {
            //Get the next line
            String input = sc.nextLine();
            //If the input is a number and has 4 digits
            if(Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input) && input.length() == 4) {
                //Set year and exit the loop
                year = Integer.valueOf(input);
                control = false;
            //otherwise
            } else {
                //display error and loop again for new input
                System.out.println("Invalid year. Please enter a valid year");
            }
        }
        //Loop control
        control = true;
        //Ask for the 2 digit day
        System.out.println("Please enter the 2 digit month (MM)");
        //While control is true
        while(control) {
            //Get the next line
            String input = sc.nextLine();
            //If the input is a number and has 2 digits
            if(Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input) && input.length() == 2) {
                //Set month
                month = Integer.valueOf(input);
                //If month is less than 1 or greater than 13
                if(month < 1 || month > 13) {
                    //Display error and loop for new input
                    System.out.println("Invalid month. Please enter a valid month");
                //otherwise
                } else {
                    //Exit the loop
                    control = false;
                }
            //Otherwise
            } else {
                //Display error and loop for new input
                System.out.println("Invalid month. Please enter a valid month");
            }
        }
        //Loop control
        control = true;
        //Ask for 2 digit day
        System.out.println("Please enter the 2 digit day (DD)");
        //While control is true
        while(control) {
            //Get the next line
            String input = sc.nextLine();
            //If the input is a number and has 2 digits
            if(Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input) && input.length() == 2) {
                //Set day
                day = Integer.valueOf(input);
                //If the month has 30 days
                if(month == 4 || month == 6 || month == 9 || month == 11) {
                    //And the day is outside that range
                    if(day < 1 || day > 30) {
                        //Display error and loop for new input
                        System.out.println("Invalid day. Please enter a valid day");
                    //Otherwise
                    } else {
                        //Exit the loop
                        control = false;
                    }
                //If the month has 28 days
                } else if (month == 2){
                    //And the day is outside that range
                    if(day < 1 || day > 28) {
                        //Display error and loop for new input
                        System.out.println("Invalid day. Please enter a valid day");
                    //otherwise
                    } else {
                        //Exit the loop
                        control = false;
                    }
                //If the month is any remaining month (which all have 31 days)
                } else if (month > 0 || month < 13) {
                    //And the day is outside that range
                    if(day < 1 || day > 31) {
                        //Display error and loop for new input
                        System.out.println("Invalid day. Please enter a valid day");
                    //Otherwise
                    } else {
                        //Exit the loop
                        control = false;
                    }
                //Otherwise
                } else {
                    //Display error and loop for new input
                    System.out.println("Invalid day. Please enter a valid day");
                }
            //Otherwise
            } else {
                //Display error and loop for new input
                System.out.println("Invalid day. Please enter a valid day");
            }
        }
        //Create a new LocalDate from year/month/day and call logs changeDate()
        LocalDate newDate = LocalDate.of(year, month, day);
        data.changeDate(newDate);
    }

    /*
     * Controls the changing of the weight
     */
    public void changeWeight() {
        //Loop control
        boolean control = true;
        //Ask for weight
        tui.requestWeight();
        //While control is true
        while(control) {
            //Get the next line
            String input =  sc.nextLine();
            //If the input is a number
            if(Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input)) {
                //Set weight
                double weight = Double.valueOf(input);
                //If weight is less than 0 or over 1000 (who weighs more than 1000 pounds?)
                if(weight < 0 || weight > 1000) {
                    //Display error and loop for new input
                    System.out.println("Invalid weight. Please enter a valid weight");
                //Otherwise
                } else {
                    //Call logs changeWeight() and exit the loop
                    data.changeWeight(weight);
                    System.out.println("Weight Modified!");
                    control = false;
                }
            //Otherwise
            } else {
                //Display error and loop for new input
                System.out.println("Invalid weight. Please enter a valid weight");
            }
        }
    }

    /*
     * Controls the changing of the calorie limit
     */
    public void changeCalorieLimit() {
        //Loop control
        boolean control = true;
        //Ask for calorie limit
        tui.requestCalorieLimit();
        //While control is true
        while(control) {
            //Get the next line
            String input =  sc.nextLine();
            //If the input is a number
            if(Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input)) {
                //Set calorie limit
                double limit = Double.valueOf(input);
                //If limit is less than 0 or over 10000 (Worlds strongest men eat like whales)
                if(limit < 0 || limit > 10000) {
                    //Display error and loop for new input
                    System.out.println("Invalid calorie limit. Please enter a valid calorie limit");
                //Otherwise
                } else {
                    //Call logs changeCalorieLimit() and exit the loop
                    data.changeCalorieLimit(limit);
                    System.out.println("Calorie Limit Modified!");
                    control = false;
                }
                //Otherwise
            } else {
                //Display error and loop for new input
                System.out.println("Invalid calorie limit. Please enter a valid calorie limit");
            }
        }
    }

    /*
     * Controls adding a new food to the collection
     */
    public void addFoodToCollection() {
        //Loop control
        boolean control = true;
        //Create a reference for the food target
        Food target;
        //Request and retrieve the food name
        tui.requestFoodName();
        //While control is true
        while(control) {
            //Get the next line
            String input = sc.nextLine();
            //Search the food collection for the target and reference it
            target = data.checkFoodCollection(input);
            //If the food does not already exist in the food collection
            if(target == null) {
                //Ask if the food contains one or more ingredients, get response
                tui.checkForRecipe();
                //While control is true
                while(control) {
                    //Get the next character
                    char c = sc.next().charAt(0);
                    sc.nextLine();
                    switch (c) {
                        //If the food does contain one or more ingredients (y)
                        case 'y':
                            //Declare a new recipe
                            Recipe r = new Recipe(input);
                            //While control is true
                            while(control) {
                                //Get the name of the ingredient and check if it is in the food collection
                                System.out.println("Enter the name of the ingredient");
                                String name = sc.nextLine();
                                target = data.checkFoodCollection(name);
                                //If it is not in the food collection
                                if(target == null) {
                                    //Report it does not exist
                                    System.out.println("Ingredient does not exist in the collection");
                                //If it is in the food collection
                                } else {
                                    //Request and store the number of servings
                                    System.out.println("Please enter the number of servings");
                                    //While control is true
                                    while(control) {
                                        //Get the next line
                                        input = sc.nextLine();
                                        //If the input is a number
                                        if(Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input)) {
                                            //Add the ingredient to the recipe
                                            r.addIngredient(target, Double.valueOf(input));
                                            //Exit the loop
                                            control = false;
                                        } else {
                                            //Display error and loop for new input
                                            System.out.println("Invalid input. Please enter a valid number");
                                        }
                                    }
                                }
                                //Loop control
                                control = true;
                                //Ask if the user is done adding ingredients
                                System.out.println("Are you done adding ingredients? (y to exit)");
                                //If they answer yes
                                c = sc.next().charAt(0);
                                sc.nextLine();
                                if(c == 'y') {
                                    //Calculate the nutrition for the recipe, add it to the food collection,
                                    //and set control to false to break the loops
                                    r.calculateNutrition();
                                    data.addFoodToCollection(r);
                                    control = false;
                                }
                            }
                            break;
                        //If the food does not contain any ingredients (n)
                        case 'n':
                            //Declare a new basic food
                            BasicFood bf = new BasicFood(input);
                            //Ask for calories per serving
                            System.out.println("Please enter the calories per serving");
                            //While control is true
                            while(control) {
                                //Get the next line
                                input = sc.nextLine();
                                //If the input is a number
                                if(Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input)) {
                                    //Set calories per serving
                                    bf.setCaloriesPerServing(Double.valueOf(input));
                                    //Exit the loop
                                    control = false;
                                //Otherwise
                                } else {
                                    //Display error and loop for new input
                                    System.out.println("Invalid input. Please enter a valid number");
                                }
                            }
                            //Loop control
                            control = true;
                            //Ask for grams of fat
                            System.out.println("Please enter the grams of fat");
                            //While control is true
                            while(control) {
                                //Get the next line
                                input = sc.nextLine();
                                //If the input is number
                                if(Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input)) {
                                    //Set grams of fat
                                    bf.setGramsOfFat(Double.valueOf(input));
                                    //Exit the loop
                                    control = false;
                                //Otherwise
                                } else {
                                    //Display error and loop for new input
                                    System.out.println("Invalid input. Please enter a valid number");
                                }
                            }
                            //Loop control
                            control = true;
                            //Ask for grams of carbs
                            System.out.println("Please enter the grams of carbs");
                            //While control is true
                            while(control) {
                                //Get the next line
                                input = sc.nextLine();
                                //If the input is a number
                                if(Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input)) {
                                    //Set grams of carbs
                                    bf.setGramsOfCarbs(Double.valueOf(input));
                                    //Exit the loop
                                    control = false;
                                //Otherwise
                                } else {
                                    //Display error and loop for new input
                                    System.out.println("Invalid input. Please enter a valid number");
                                }
                            }
                            //Loop Control
                            control = true;
                            //Ask for grams of protein
                            System.out.println("Please enter the grams of protein");
                            //While control is true
                            while(control) {
                                //Get the next line
                                input = sc.nextLine();
                                //If the input is a number
                                if (Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input)) {
                                    //Set grams of protein
                                    bf.setGramsOfProtein(Double.valueOf(input));
                                    //Exit the loop
                                    control = false;
                                //Otherwise
                                } else {
                                    //Display error and loop for new input
                                    System.out.println("Invalid input. Please enter a valid number");
                                }
                            }
                            //Add the food to the collection
                            data.addFoodToCollection(bf);
                            //Exit the loop
                            control = false;
                            break;
                        //If the input was not y or n
                        default:
                            //Display error and loop for new input
                            System.out.println("Invalid Selection. Please enter y or n");
                            break;
                    }
                }
            //If the food does already exist
            } else {
                //Show that it exists, ask for a new name, and loop again
                tui.displayFoodExists();
                tui.requestFoodName();
            }
        }
    }

    /*
     * Controls adding a new food to the foods consumed on the given day
     */
    public void addFoodToDay() {
        //Loop control
        boolean control = true;
        //Food name
        String name = "";
        //Ask for food name
        tui.requestFoodName();
        //While control is true
        while(control) {
            //Get the next line
            name = sc.nextLine();
            //Check if the food exists in the food collection
            Food target = data.checkFoodCollection(name);
            //If the food does not exist in the collection
            if(target == null) {
                //Display error and loop for new input
                System.out.println("Food does not exist in the collection. Please enter food name again");
            //Otherwise
            } else {
                //Exit the loop
                control = false;
            }
        }
        //Loop Control
        control = true;
        //Ask for food servings
        tui.requestFoodServings();
        //While control is true
        while(control) {
            //Get the next line
            String input = sc.nextLine();
            //If the input is a number
            if(Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input)) {
                //Set servings
                double servings = Double.valueOf(input);
                //If the servings are less than 0 or greater than 250 (Put the spoon down Shamu)
                if(servings < 0 || servings > 250) {
                    //Display error and loop for new input
                    System.out.println("Invalid number of servings. Please enter a valid number of servings");
                //Otherwise
                } else {
                    //call logs addFoodToCurrentDay and exit the loop
                    data.addFoodToCurrentDay(name, servings);
                    System.out.println("Food added!");
                    control = false;
                }
            //Otherwise
            } else {
                //Display error and loop for new input
                System.out.println("Invalid number of servings. Please enter a valid number of servings");
            }
        }
    }

    /*
     * Controls removing a food from the foods consumed on the given day
     */
    public void removeFoodFromDay() {
        //Loop control
        boolean control = true;
        //Index of food to be removed
        int index = 0;
        //Ask for food index
        tui.requestFoodIndex();
        //While control is true
        while(control) {
            String input = sc.nextLine();
            //If the input is a number
            if(Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input)) {
                //Set index
                index = Integer.valueOf(input);
                index--;
                //if the index exists in the food list
                if(data.checkFoodList(index)) {
                    //Remove the food item and exit the loop
                    data.removeFoodFromCurrentDay(index);
                    control = false;
                //Otherwise
                } else {
                    //Display error and loop for new input
                    System.out.println("Invalid index. Please enter a valid index");
                }
            //Otherwise
            } else {
                //Display error and loop for new input
                System.out.println("Invalid index. Please enter a valid index");
            }
        }
    }

    /*
     * Controls showing the daily breakdown
     */
    public void showDay() {
        tui.displayBreakdown();
    }

    /*
     * Controls the saving of the collections to file
     */
    public void save(){
        tui.saving();
        data.save();
        tui.saved();
    }
}
