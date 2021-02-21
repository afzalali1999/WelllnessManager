package controller;

import model.*;
import view.SwingUI;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class ControlGUI {
    /*
     * Variables used for FoodCollection
     * Log data - Model Object referenced by this Control
     * SwingUI sui - View Object referenced by this Control
     * Scanner sc - Retrieves user input
     * boolean execute - Controls operation of this Control
     */
    private Log data;
    private SwingUI sui;
    private Scanner sc;
    private boolean execute;
    private Recipe r;
    private BasicFood basicFood;
    private boolean addIngredient = false;
    private boolean requestMoreIngredient = false;
    private boolean addFoodNutrition = false;

    /*
     * Creates a new Control from the passed in Log and SwingUI
     */
    public ControlGUI(Log log, SwingUI view) {
        data = log;
        sui = view;
        sc = new Scanner(System.in);
        execute = false;
    }

    public void initController() {
        sui.getExit().addActionListener(e -> {
            System.exit(0);
        });

        sui.getMenuItems().addActionListener(e -> {
            readCommand((String) sui.getMenuItems().getSelectedItem(), null);
        });

        sui.getGo().addActionListener(e -> {
            if (addIngredient) {
                readCommand("Add Ingredient", sui.getTextfield().getText());
            } else if (requestMoreIngredient) {
                readCommand("Request More Ingredient", sui.getTextfield().getText());
            } else if (addFoodNutrition) {
                readCommand("Add Food Nutrition", sui.getTextfield().getText());
            } else {
                execute = true;
                readCommand((String) sui.getMenuItems().getSelectedItem(), sui.getTextfield().getText());
            }
        });
    }

    /*
     * Reads and interprets commands from the user
     */
    public void readCommand(String command, String input) {


        switch (command) {

            case "Select Date":
                if (execute) {
                    System.out.println("executing select date");
                    changeDate(input);
                    execute = false;
                } else {
                    sui.requestDate();
                }
                break;
            case "Modify Weight":
                if (execute) {
                    changeWeight(input);
                    execute = false;
                } else {
                    sui.requestWeight();
                }
                break;
            case "Modify Calorie Limit":
                if (execute) {
                    changeCalorieLimit(input);
                    execute = false;
                } else {
                    sui.requestCalorieLimit();
                }
                break;
            case "Add New Food To Collection":
                if (execute) {
                    addFoodToCollection(input);
                    execute = false;
                } else {
                    sui.requestFoodName();
                }
                break;
            case "Add Ingredient":
                addIngredient(input);
                break;
            case "Request More Ingredient":
                addMoreIngredient(input);
                break;
            case "Add Food Nutrition":
                addFoodNutrition(input);
                break;
            case "Add Food To Today's Log":
                if (execute) {
                    addFoodToDay(input);
                    execute = false;
                } else {
                    sui.addFoodDay();
                }
                break;
            case "Remove Food From Today's Log":
                if (execute) {
                    removeFoodFromDay(input);
                    execute = false;
                } else {
                    sui.requestFoodIndex(data.getCurrentDay().getFoodList());
                }
                break;
            case "Add New Exercise to Collection":
                if (execute) {
                    addExerciseToCollection(input);
                    execute = false;
                } else {
                    sui.requestNewExercise();
                }
                break;
            case "Add Exercise to Today's Log":
                if (execute) {
                    addExerciseToDay(input);
                    execute = false;
                } else {
                     sui.requestExerciseDay();
                }
                break;
            case "Remove Exercise From Today's Log":
                if (execute) {
                    removeExerciseFromDay(input);
                    execute = false;
                } else {
                    sui.requestExerciseIndex(data.getExercise());
                }
                break;
            case "View Today's Log":
                if (execute) {
                    sui.displayLog(data.displayDayBreakdown());
                    execute = false;
                } else {
                    sui.requestLog();
                }

                break;
            case "Save":
                if (execute) {
                    save();
                    execute = false;
                } else {
                    sui.requestSave();

                }

                break;
            default:
                //sui.inputError();
                break;

        }
    }

    /*
     * Controls the changing of the current date
     */
    public void changeDate(String input) {
        //Loop control
        String spl[] = input.split("/");
        String Syear = spl[0];
        String Smonth = spl[1];
        String Sday = spl[2];

        boolean control = true;
        //Year, month, day
        int year = 0;
        int month = 0;
        int day = 0;
        //Ask for the 4 digit year

        //WHile control is true
        while (control) {
            //Get the next line

            //If the input is a number and has 4 digits
            if (Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", Syear) && Syear.length() == 4) {
                //Set year and exit the loop
                year = Integer.valueOf(Syear);
                control = false;
                //otherwise
            } else {
                //display error and loop again for new input
                sui.error("Invalid year. Please enter a valid year");
                break;
            }
        }
        //Loop control
        control = true;
        //Ask for the 2 digit day

        //While control is true
        while (control) {
            //Get the next line

            //If the input is a number and has 2 digits
            if (Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", Smonth) && Smonth.length() == 2) {
                //Set month
                month = Integer.valueOf(Smonth);
                //If month is less than 1 or greater than 13
                if (month < 1 || month > 13) {
                    //Display error and loop for new input
                    sui.error("Invalid month. Please enter a valid month");
                    break;
                    //otherwise
                } else {
                    sui.error("Date has been set!");
                    control = false;
                }
                //Otherwise
            } else {
                //Display error and loop for new input
                sui.error("Invalid month. Please enter a valid month");
                break;
            }
        }
        //Loop control
        control = true;
        //Ask for 2 digit day

        //While control is true
        while (control) {
            //Get the next line

            //If the input is a number and has 2 digits
            if (Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", Sday) && Sday.length() == 2) {
                //Set day
                day = Integer.valueOf(Sday);
                //If the month has 30 days
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    //And the day is outside that range
                    if (day < 1 || day > 30) {
                        //Display error and loop for new input
                        sui.error("Invalid day. Please enter a valid day");
                        break;
                        //Otherwise
                    } else {
                        //Exit the loop
                        control = false;
                    }
                    //If the month has 28 days
                } else if (month == 2) {
                    //And the day is outside that range
                    if (day < 1 || day > 28) {
                        //Display error and loop for new input
                        sui.error("Invalid day. Please enter a valid day");
                        break;
                        //otherwise
                    } else {
                        //Exit the loop
                        control = false;
                    }
                    //If the month is any remaining month (which all have 31 days)
                } else if (month > 0 || month < 13) {
                    //And the day is outside that range
                    if (day < 1 || day > 31) {
                        //Display error and loop for new input
                        sui.error("Invalid day. Please enter a valid day");
                        break;
                        //Otherwise
                    } else {
                        //Exit the loop
                        control = false;
                    }
                    //Otherwise
                } else {
                    //Display error and loop for new input
                    sui.error("Invalid day. Please enter a valid day");
                    break;
                }
                //Otherwise
            } else {
                //Display error and loop for new input
                sui.error("Invalid day. Please enter a valid day");
                break;
            }
        }
        //Create a new LocalDate from year/month/day and call logs changeDate()
        LocalDate newDate = LocalDate.of(year, month, day);
        data.changeDate(newDate);
    }

    /*
     * Controls the changing of the weight
     */
    public void changeWeight(String input) {
        //Loop control
        boolean control = true;
        //Ask for weight
        //sui.requestWeight();
        //While control is true
        while (control) {
            //Get the next line

            //If the input is a number
            if (Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input)) {
                //Set weight
                double weight = Double.valueOf(input);
                //If weight is less than 0 or over 1000 (who weighs more than 1000 pounds?)
                if (weight < 0 || weight > 1000) {
                    //Display error and loop for new input
                    sui.error("Invalid weight. Please enter a valid weight");
                    break;
                    //Otherwise
                } else {
                    //Call logs changeWeight() and exit the loop
                    data.changeWeight(weight);
                    sui.error("Weight Modified!");
                    control = false;
                }
                //Otherwise
            } else {
                //Display error and loop for new input
                sui.error("Invalid weight. Please enter a valid weight");
                break;
            }
        }
    }

    /*
     * Controls the changing of the calorie limit
     */
    public void changeCalorieLimit(String input) {
        //Loop control
        boolean control = true;
        //Ask for calorie limit
        //sui.requestCalorieLimit();
        //While control is true
        while (control) {

            //If the input is a number
            if (Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input)) {
                //Set calorie limit
                double limit = Double.valueOf(input);
                //If limit is less than 0 or over 10000 (Worlds strongest men eat like whales)
                if (limit < 0 || limit > 10000) {
                    //Display error and loop for new input
                    sui.error("Invalid calorie limit. Please enter a valid calorie limit");
                    break;
                    //Otherwise
                } else {
                    //Call logs changeCalorieLimit() and exit the loop
                    data.changeCalorieLimit(limit);
                    sui.error("Calorie Limit Modified!");
                    control = false;
                }
                //Otherwise
            } else {
                //Display error and loop for new input
                sui.error("Invalid calorie limit. Please enter a valid calorie limit");
                break;
            }
        }
    }

    public void addIngredient(String input) {
        String[] spl = input.split(",");
        String ingredientName = spl[0];
        String servings = spl[1];

        Food ingredient = data.checkFoodCollection(ingredientName);

        if (ingredient == null) {
            sui.error("Ingredient does not exist!");
        } else {
            r.addIngredient(ingredient, Double.parseDouble(servings));

            sui.requestMoreIngredient();
            addIngredient = false;
            requestMoreIngredient = true;
        }
    }

    public void addMoreIngredient(String input) {
        if(input.equals("y")) {
            sui.requestAddIngredient();
            addIngredient = true;
            requestMoreIngredient = false;
        } else {
            data.addFoodToCollection(r);
            sui.error("Food added to collection!");

            requestMoreIngredient = false;
            r = null;
        }
    }

    public void addFoodNutrition(String input) {
        String[] spl = input.split(",");
        String calories = spl[0];
        String fat = spl[1];
        String carbs = spl[2];
        String protein = spl[3];

        basicFood.setCaloriesPerServing(Double.parseDouble(calories));
        basicFood.setGramsOfFat(Double.parseDouble(fat));
        basicFood.setGramsOfCarbs(Double.parseDouble(carbs));
        basicFood.setGramsOfProtein(Double.parseDouble(protein));

        data.addFoodToCollection(basicFood);
        sui.error("Food added to collection!");

        addFoodNutrition = false;
    }

    /*
     * Controls adding a new food to the collection
     */
    public void addFoodToCollection(String input) {
        String[] spl = input.split(",");
        String foodName = spl[0];
        String hasIngredients = spl[1];

        //Create a reference for the food target
        Food target;

        //Search the food collection for the target and reference it
        target = data.checkFoodCollection(input);

        // if no food in collection
        if (target == null) {

            // if user has ingredients
            if (hasIngredients.equals("y")) {
                //Declare a new recipe
                r = new Recipe(foodName);

                sui.requestAddIngredient();
                addIngredient = true;
            } else {
                // decalare a new basic food
                basicFood = new BasicFood(foodName);

                sui.requestAddFoodNutrition();
                addFoodNutrition = true;
            }
        }
        else {
            sui.error("Food already in collection! Please enter a different food.");
        }
    }

    /*
     * Controls adding a new food to the foods consumed on the given day
     */
    public void addFoodToDay(String input) {
        //Loop control

        String spl[] = input.split(",");
        String name = spl[0];
        String Sservings = spl[1];
        boolean control = true;
        Food target = null;
        //Food name

        //Ask for food name
        //sui.requestFoodName();
        //While control is true
        while (control) {
            //Get the next line

            //Check if the food exists in the food collection
            target = data.checkFoodCollection(name);
            //If the food does not exist in the collection
            if (target == null) {
                //Display error and loop for new input
                sui.error("Food does not exist in the collection. Please enter food name again");
                break;
                //Otherwise
            } else {
                //Exit the loop
                control = false;
            }
        }

            control = true;



        while (control && target != null) {
            //Get the next line

            //If the input is a number
            if (Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", Sservings)) {
                //Set servings
                double servings = Double.valueOf(Sservings);
                //If the servings are less than 0 or greater than 250 (Put the spoon down Shamu)
                if (servings < 0 || servings > 250) {
                    //Display error and loop for new input
                    sui.error("Invalid number of servings. Please enter a valid number of servings");
                    break;
                    //Otherwise
                } else {
                    //call logs addFoodToCurrentDay and exit the loop
                    data.addFoodToCurrentDay(name, servings);
                    sui.error("Food added!");
                    control = false;
                }
                //Otherwise
            } else {
                //Display error and loop for new input
                sui.error("Invalid number of servings. Please enter a valid number of servings");
                break;
            }
        }
        //Loop Control

    }

    /*
     * Controls removing a food from the foods consumed on the given day
     */
    public void removeFoodFromDay(String input) {
        //Loop control
        boolean control = true;
        //Index of food to be removed
        int index = 0;
        //Ask for food index
        //sui.requestFoodIndex();
        //While control is true
        while (control) {

            //If the input is a number
            if (Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", input)) {
                //Set index
                index = Integer.valueOf(input);
                index--;
                //if the index exists in the food list
                if (data.checkFoodList(index)) {
                    //Remove the food item and exit the loop
                    data.removeFoodFromCurrentDay(index);
                    sui.error("Food has been removed!");
                    sui.requestFoodIndex(data.getCurrentDay().getFoodList());
                    control = false;
                    //Otherwise
                } else {
                    //Display error and loop for new input
                    sui.error("Invalid index. Please enter a valid index");
                }
                //Otherwise
            } else {
                //Display error and loop for new input
                sui.error("Invalid index. Please enter a valid index");
            }
        }
    }

    /*
     * Controls showing the daily breakdown
     */
    public void showDay() {
        //sui.displayBreakdown();
    }

    /*
     * Controls the saving of the collections to file
     */
    public void save() {
        //sui.saving();
        data.save();
        sui.error("Files have been saved");
        //sui.saved();
    }

    /*
     * Controls adding a new exercise to the exerciseCollection
     */
    public void addExerciseToCollection(String input) {

        String spl[] = input.split(",");
        String name = spl[0];
        String calories = spl[1];

        //Create a reference for the exercise target
        Exercise target;

        //Search the exercise collection for the target and reference it
        target = data.checkExerciseCollection(name);

        //If the exercise does not already exist in the exercise collection
        if (target == null) {
            Exercise e = new Exercise(name);
            e.setExerciseName(name);
            //Ask for calories per hour

            //If the input is a number
            if (Pattern.matches("(-?[0-9]+(?:[.][0-9]+)?)", calories)) {
                //Set calories per serving
                e.setCaloriesExpended(Double.valueOf(calories));
                data.addExerciseToCollection(e);
                sui.error(name+" has been added to exercises!");
            } else {
                //Display error and loop for new input
                sui.error("Invalid input. Please enter a valid number");
            }
        }
        else{
            sui.error("Exercise already exists in the collection");
        }
    }

    public void addExerciseToDay(String input) {
        String spl[] = input.split(",");
        String exerciseName = spl[0];
        String minutes = spl[1];

        //Check if the food exists in the food collection
        Exercise target = data.checkExerciseCollection(exerciseName);

        //If the exercise does not exist in the collection
        if (target == null) {
            //Display error and loop for new input
            sui.error("Exercise does not exist in the collection. Please enter exercise name again");
        } else {
            //If the input is a number
            if (minutes.matches("(-?[0-9]+(?:[.][0-9]+)?)")) {
                double min = Double.parseDouble(minutes);

                if (min < 0.0 || min > 10000) {
                    sui.error("Please input a valid number for minutes that is greater than 0 and less than 10,000");
                } else {
                    //call logs addFoodToCurrentDay and exit the loop
                    data.addExerciseToCurrentDay(exerciseName, min);
                    sui.error("Exercise added!");
                }
            } else {
                sui.error("Minutes is not a number! Please input a valid number for minutes.");
            }
        }
    }

    public void removeExerciseFromDay(String input) {
        int index =0;

        //If the input is a number
        if (input.matches("(-?[0-9]+(?:[.][0-9]+)?)")) {
            //Set index
            index = Integer.valueOf(input);
            index--;

            //if the index exists in the food list
            if (data.checkExerciseList(index)) {
                //Remove the food item and exit the loop
                data.removeExerciseFromCurrentDay(index);
                sui.error("Exercise has been removed!");
                sui.requestFoodIndex(data.getCurrentDay().getExerciseList());
            } else {
                //Display error and loop for new input
                sui.error("Invalid index. Please enter a valid index");
            }
        } else {
            //Display error and loop for new input
            sui.error("Invalid index. Please enter a valid index that is a number");
        }
    }
}
