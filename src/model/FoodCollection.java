package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class FoodCollection<E> implements SimpleCollection<E> {
    /*
     * Variables used for FoodCollection
     * LinkedHashMap<String, Food> foodCollection - List of all foods contained in this collection
     * File file - The file that the collection is built from
     */
    private LinkedHashMap<String, Food> foodCollection = new LinkedHashMap<String, Food>();
    private File file;

    /*
     * Create a new food collection using the passed in file name
     */
    public FoodCollection(String filename){
        file = new File(filename);
        readAndCreate();
    }

    /*
     * Reads in food data from a csv file and builds the food collection. Lines must be formatted as
     * "b,name,calories,fat,carb,protein" or "r,name,f1name,f1count,f2name, f2count,...,fNname,fNcount"
     * and lines beginning with r can not use name/count pairs that have not been previously defined
     * in the csv file.
     */
    @Override
    public void readAndCreate(){
        try {
            //Declare a scanner to parse the file
            Scanner scanner = new Scanner(file);
            //While there is another line in the file
            while (scanner.hasNextLine()) {
                //Read that line and tokenize it
                String[] line = scanner.nextLine().split(",");
                //If the first token denotes this entry is a basic food
                if (line[0].equals("b")){
                    //Create a new basic food
                    BasicFood bf = new BasicFood();
                    //Set the values of the basic food from the remaining tokens
                    bf.setFoodName(line[1]);
                    bf.setCaloriesPerServing(Double.parseDouble(line[2]));
                    bf.setGramsOfFat(Double.parseDouble(line[3]));
                    bf.setGramsOfCarbs(Double.parseDouble(line[4]));
                    bf.setGramsOfProtein(Double.parseDouble(line[5]));
                    //Then add the basic food to the food collection
                    foodCollection.put(bf.name, bf);
                }
                //If the first token denotes this entry as a recipe
                else if(line[0].equals("r")){
                    //Create a new recipe and set its name from the next token
                    Recipe r = new Recipe();
                    r.setFoodName(line[1]);
                    //For the remaining tokens, read them in pairs of two
                    for (int i = 2; i < line.length; i+=2){
                        //Create a food object from the first token
                        //Add the ingredient to the recipe list using the second token
                        //as the number of servings
                        Food ingredient = foodCollection.get(line[i]);
                        r.addIngredient(ingredient, Double.parseDouble(line[i+1]));
                    }
                    //Finally, calculate the nutrition for this recipe and add it to the food collection
                    r.calculateNutrition();
                    foodCollection.put(r.name, r);
                }
            }
            //The file is parsed and the collection is built, close the scanner
            scanner.close();
        }
        catch(FileNotFoundException e){
            //We will want to do something here in place of a stack trace.
            //Implement later
            e.printStackTrace();
        }
    }

    /*
     * Parses the food collection and saves it to the csv file
     */
    @Override
    public void save(){
        try {
            //Declare a filewriter and pass in the food csv file
            //then wrap it in a buffered writer
            FileWriter fr = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(fr);
            //For every key in the food collection
            for(String s : foodCollection.keySet()) {
                //Declare a food object and store the value of the current key
                Food target = foodCollection.get(s);
                //If the food referenced by this key is an instance of basic food
                if(target instanceof BasicFood) {
                    //Cast the food to a basic food
                    BasicFood bf = (BasicFood) target;
                    //Create the comma delimited string to write to the file
                    String output = "b," + bf.getFoodName() + "," + bf.getCaloriesPerServing() + "," +
                            bf.getGramsOfFat() + "," + bf.getGramsOfCarbs() + "," + bf.getGramsOfProtein();
                    //Write the comma delimited string to the file and move to a new line
                    br.write(output);
                    br.newLine();
                    //If the food referenced by this key is an instance of recipe
                } else if(target instanceof Recipe) {
                    //Cast the food to a recipe
                    Recipe r = (Recipe) target;
                    //Create the beginning of the comma delimited string
                    String output = "r," + r.getFoodName() + ",";
                    //For every food in the ingredient list of this recipe
                    for(Food f : r.getIngredientList()) {
                        //Append the ingredient name and number of servings to the comma delimited string
                        output += r.getIngredientName(f) + "," + r.getIngredientServings(f) + ",";
                    }
                    //Remove the extra comma from the comma delimited string
                    output = output.substring(0, output.length() - 1);
                    //Write the comma delimited string to the file and move to a new line
                    br.write(output);
                    br.newLine();
                }
            }
            //The collection has been parsed, flush the buffer and ensure all lines have
            //been written to the file then close the buffered reader
            br.close();
        }
        catch (Exception e){
            //We will want to do something here in place of a stack trace.
            //Implement later
            e.printStackTrace();
        }
    }

    /*
     * Adds a food to the food collection
     */
    @Override
    public void add(Object o) {
        Food f = (Food) o;
        foodCollection.put(f.getFoodName(), f);
    }

    /*
     * Removes a food from the food collection
     */
    @Override
    public void remove(Object o) {
        Food f = (Food) o;
        foodCollection.remove(f.getFoodName());
    }

    /*
     * Retrieves a food from the food collection
     */
    public Food get(String key) {
        return foodCollection.get(key) ;
    }

    /*
     * Loops through the food collection and displays the key where the food is stored
     * and the nutrition values of the food it references
     */
    @Override
    public void showCollection() {
        for(String i : foodCollection.keySet()) {
            System.out.println("Key: " + i);
            System.out.println("VALUES: \n" + foodCollection.get(i));
        }
    }
}
