package model;

public class Exercise {
    private String exerciseName;
    private double caloriesExpended;
    private double minutes; // in minutes

    public Exercise(String name) {
        exerciseName = name;
    }

    public Exercise() {
        exerciseName = "";
        caloriesExpended = 0.0;
        minutes = 0.0;
    }

    public Exercise(String exerciseName, double caloriesExpended, double minutes) {
        this.exerciseName = exerciseName;
        this.caloriesExpended = caloriesExpended;
        this.minutes = minutes;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setCaloriesExpended(double caloriesExpended) {
        this.caloriesExpended = caloriesExpended;
    }

    public void setMinutesOfExercise(double minutes) {
        this.minutes = minutes;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public double getCaloriesExpended() {
        return caloriesExpended;
    }

    public double getMinutesOfExercise() {
        return minutes;
    }
}