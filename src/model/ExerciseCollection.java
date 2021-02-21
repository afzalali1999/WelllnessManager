package model;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ExerciseCollection<E> implements SimpleCollection<E> {
    private LinkedHashMap<String, Exercise> exerciseCollection = new LinkedHashMap<String, Exercise>();
    private File file;

    public ExerciseCollection(String fileName) {
        file = new File(fileName);
        readAndCreate();
    }

    @Override // read exercise.csv, convert each line into Exercise object, and store them into a collection
    public void readAndCreate() {
        try {
            Scanner scanner = new Scanner(file);

            //loop over every line in exercise.csv file
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");

                if (line[0].equals("e")) {
                    // create Exercise instance
                    Exercise exercise = new Exercise();
                    exercise.setExerciseName(line[1]);
                    exercise.setCaloriesExpended(Double.parseDouble(line[2]));

                    // store Exercise instance into exerciseCollection
                    exerciseCollection.put(exercise.getExerciseName(), exercise);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override // add new exercise to collection
    public void add(Object o) {
        Exercise e = (Exercise) o;
        exerciseCollection.put(e.getExerciseName(), e);
    }

    @Override // save all items from exerciseCollection to exercise.csv file
    public void save() {
        try {
            FileWriter fr = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(fr);

            // loop over every exercise object in exerciseCollection, and write each object into exercise.csv file
            for(Map.Entry<String, Exercise> entry : exerciseCollection.entrySet()) {
                Exercise e = entry.getValue();

                String output = "e," + e.getExerciseName() + "," + e.getCaloriesExpended();

                br.write(output);
                br.newLine();
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // remove exercise from exerciseCollection
    public void remove(Object o) {
        Exercise e = (Exercise) o;
        exerciseCollection.remove(e.getExerciseName());
    }

    /*
     * Retrieves an exercise from the exercise collection
     */
    public Exercise get(String key) {
        return exerciseCollection.get(key) ;
    }

    @Override // display all exercise items in exerciseCollection
    public void showCollection() {
        for(Map.Entry<String, Exercise> entry : exerciseCollection.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            System.out.println("VALUES: \n" + entry.getValue());
        }
    }
}
