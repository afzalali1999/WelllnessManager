package model;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class DailyLog<E> implements SimpleCollection<E> {
   /*
    * Variables used for DailyLog
    * ArrayList<Day> dailyLog - List of all days contained in this collection
    * File file - The file that the collection is built from
    * FoodCollection fc - The food collection foods information is taken from
    */
   private ArrayList<Day> dailyLog = new ArrayList<>();
   private File file;
   private FoodCollection fc;
   private ExerciseCollection ec;

   /*
    * Create a new daily log using the passed in file name
    */
   public DailyLog(String filename, FoodCollection foodlist, ExerciseCollection exerciseList) {
      file = new File(filename);
      fc = foodlist;
      ec = exerciseList;
      readAndCreate();
   }

   /*
    * Reads in log data from a csv file and builds the daily log. Lines must be formatted
    * as "yyyy,mm,dd,w,weight", "yyyy,mm,dd,c,calories", or "yyyy,mm,dd,f,name,count"
    */
   @Override
   public void readAndCreate() {
      try {
         //Declare a scanner to parse the file
         Scanner scanner = new Scanner(file);
         //While there is another line in the file
         while (scanner.hasNextLine()) {
            //Read that line and tokenize it
            String[] line = scanner.nextLine().split(",");
            //Create usable values for year, month, day from tokens
            int year = Integer.valueOf(line[0]);
            int month = Integer.valueOf(line[1]);
            int day = Integer.valueOf(line[2]);
            //Create a reference for the target day
            Day target = new Day(year, month, day);
            //If the day already exists in the daily log
            if(dailyLog.contains(target)) {
               //Point target to the day in the daily log
               target = dailyLog.get(dailyLog.indexOf(target));
            //If the day does not exist in the daily log
            } else {
               //Add the day to the daily log
               dailyLog.add(target);
            }
            //If this entry denotes the weight for the given day
            if(line[3].equals("w")) {
               //Set the weight for this day to the value in the next token
               target.setWeight(Double.valueOf(line[4]));
            //If this entry denotes the calorie limit for the given day
            } else if(line[3].equals("c")) {
               //Set the calorie limit for the given day to the value in the next token
               target.setCalorieLimit(Double.valueOf(line[4]));
            //If this entry denotes a food consumed on the given day
            } else if(line[3].equals("f")) {
               Food f = (Food) fc.get(line[4]);
               double s = Double.valueOf(line[5]);
               //Add the food to the given day
               target.addFood(f, s);
            } else if(line[3].equals("e")) {
               Exercise e = (Exercise) ec.get(line[4]);
               Double minutes = Double.valueOf(line[5]);
               e.setMinutesOfExercise(minutes);
               target.addExercise(e, minutes);
            }
         }
         scanner.close();
      } catch(FileNotFoundException e){
         //We will want to do something here in place of a stack trace.
         //Implement later
         e.printStackTrace();
      }

   }

   /*
    * Parses the daily log and saves it to the csv file
    */
   @Override
   public void save() {
      try {
         //Declare a filewriter and pass in the food csv file
         //then wrap it in a buffered writer
         FileWriter fr = new FileWriter(file);
         BufferedWriter br = new BufferedWriter(fr);
         //For every key in the daily log
         for(int i = 0; i < dailyLog.size(); i++) {
            //Get the day object stored at this key
            Day current = dailyLog.get(i);
            //Format the date portion of the output string for this day (YYYY,MM,DD,)
            String date = String.format("%04d,%02d,%02d,", current.getYear(), current.getMonth(), current.getDay());

            //Format the line for weight (YYYY,MM,DD,w,WEIGHT)
            String output = date + "w," + current.getWeight();
            //Write the line to the file, move to next line
            br.write(output);
            br.newLine();

            //Format the line for calorie limit (YYYY,MM,DD,c,CALORIE LIMIT)
            output = date + "c," + current.getCalorieLimit();
            //Write the line to the file, move to next line
            br.write(output);
            br.newLine();

            //For every food in the food list for this day
            for(int j = 0; j < current.getFoodList().size(); j++) {
               //Format the line for the food (YYYY,MM,DD,f,FOOD NAME, FOOD SERVINGS)
               output = date + "f," + current.getFood(j).getFoodName() +"," +current.getServings(j);
               //Write the line to the file, move to next line
               br.write(output);
               br.newLine();
            }

            for(int j=0; j<current.getExerciseListSize(); j++) {
               //Format the line for calorie limit (YYYY,MM,DD,e, EXERCISENAME, MINUTES)
               output = date + "e," + current.getExercise(j).getExerciseName() + "," + current.getExerciseMinutes(j);
               //Write the line to the file, move to next line
               br.write(output);
               br.newLine();
            }
         }
         //The daily log has been parsed, flush the buffer and ensure all lines have
         //been written to the file then close the buffered reader
         br.close();
      } catch (Exception e){
         //We will want to do something here in place of a stack trace.
         //Implement later
         e.printStackTrace();
      }
   }

   /*
    * Adds a day to the daily log
    */
   @Override
   public void add(Object o) {
      Day d = (Day) o;
      dailyLog.add(d);
      sort();
   }

   /*
    * Removes a day from the daily log
    */
   @Override
   public void remove(Object o) {
      Day d = (Day) o;
      dailyLog.remove(d);
   }

   /*
    * Retrieves a day from the daily log
    */
   public Day get(int index) {
      return dailyLog.get(index);
   }

   /*
    * Retrieves the index of a day from the daily log
    */
   public int getIndex(Day target) {
      return dailyLog.indexOf(target);
   }

   /*
    * Sorts the daily log entries by date
    */
   public void sort() {
      dailyLog.sort(Comparator.comparing(Day::getDate));
   }

   /*
    * Loops through the daily log and displays the contents of each day
    * to the console
    */
   @Override
   public void showCollection() {
      for(int i = 0; i < dailyLog.size(); i++) {
         Day d = dailyLog.get(i);
         d.displayDay();
         System.out.println();
      }
   }
}