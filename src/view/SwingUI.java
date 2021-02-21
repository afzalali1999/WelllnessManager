package view;
import model.Day;
import model.Exercise;
import model.Food;
import model.Log;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;


public class SwingUI extends JFrame{

    Log data;

    private final Map<String, JLabel> wellness = new HashMap<String, JLabel>() ;
    private static int POINT_SIZE = 18 ;
    private static Font labelFont = new Font(Font.SERIF, Font.PLAIN, POINT_SIZE) ;
    private String d = "Select Date";
    private String w = "Modify Weight";
    private String c = "Modify Calorie Limit";
    private String f = "Add New Food To Collection";
    private String a = "Add Food To Today's Log";
    private String r = "Remove Food From Today's Log";
    private String v = "View Today's Log";
    private String s = "Save";
    private String e = "Exit";
    private String ex ="Add New Exercise to Collection";
    private String el ="Add Exercise to Today's Log";
    private String er = "Remove Exercise From Today's Log";
    private JButton exit;
    private JComboBox<String> menuItems;
    private JTextField textfield;
    private JLabel message;
    private JPanel menu;
    private JButton go;
    private JPanel graph;

    /*
     * Creates a new SwingUI
     */
    public SwingUI(Log log){
        super("Wellness Manager") ;
        this.data = log;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        JPanel contentPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        contentPanel.setBorder(padding);
        this.setContentPane(contentPanel);
        this.setLayout(new GridLayout(7,2)) ;

        this.add( createPanel("Wellness Manager") ) ;

        menuItems = new JComboBox<String>();

        // add items to the combo box
        menuItems.addItem("Select Menu Items");
        menuItems.addItem(d);
        menuItems.addItem(w);
        menuItems.addItem(c);
        menuItems.addItem(f);
        menuItems.addItem(ex);
        menuItems.addItem(a);
        menuItems.addItem(el);
        menuItems.addItem(er);
        menuItems.addItem(r);
        menuItems.addItem(v);
        menuItems.addItem(s);

         menu = new JPanel(new GridLayout(2,1)) ;
        this.add(menu.add(menuItems));





        exit=new JButton(e);
        exit.setBounds(50,100,95,30);
        this.add(exit);


        message = new JLabel("Hello");

        this.add(message);
        message.setVisible(false);

        textfield=new JTextField();
        textfield.setBounds(50,200,95,30);
        this.add(textfield);
        textfield.setVisible(false);

        graph = new JPanel();
        graph.add(new BarGraph(log));
        this.add(graph);
        graph.setVisible(false);

        go=new JButton("Go");
        this.add(go);
        go.setVisible(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        this.pack() ;
        this.setLocationRelativeTo(null);
        this.setSize(500, 500);
        this.setVisible(true) ;

    }

    /*
     * Displays the menu with program options
     */

    /*
     * Create a Panel
     */
    private JPanel createPanel(String name)  {
        JPanel panel = new JPanel(new GridLayout(2,1)) ;
        JLabel label ;
        label = createLabel(" " + name + " ") ;
        panel.add(label) ;

        label = createLabel("") ;
        panel.add(label) ;
        wellness.put(name, label) ;

        return panel ;
    }

    /*
     * Create a Label
     */
    private JLabel createLabel(String title) {
        JLabel label = new JLabel(title) ;

        label.setHorizontalAlignment(JLabel.CENTER) ;
        label.setVerticalAlignment(JLabel.TOP) ;
        label.setFont(labelFont) ;

        return label ;
    }

    /*
     * Displays a request for the user to enter a new date
     */
    public void requestDate(){
        graph.setVisible(false);
        message.setText("<html>Please enter a date to log activities for (YYYY/MM/DD)</html>");
        message.setVisible(true);
        textfield.setVisible(true);
        textfield.setText("");
        go.setVisible(true);
    }

    /*
     * Displays a request for the user to enter a new weight
     */
    public void requestWeight(){
        graph.setVisible(false);
        message.setText("Please enter the new weight");
        message.setVisible(true);
        textfield.setVisible(true);
        textfield.setText("");
        go.setVisible(true);
    }

    public void requestNewExercise(){
        graph.setVisible(false);
        message.setText("Please enter Exercise Name and Calories burned per hour 'name,calories'");
        message.setText("<html>Please enter Exercise Name and Calories burned per hour 'name,calories'<html>");
        message.setVisible(true);
        textfield.setVisible(true);
        textfield.setText("");
        go.setVisible(true);
    }

    public void requestExerciseDay(){
        graph.setVisible(false);
        message.setText("<html>Please enter Exercise Name and Length of exercise in minutes 'name,minutes'</html>");
        message.setVisible(true);
        textfield.setVisible(true);
        textfield.setText("");
        go.setVisible(true);
    }

    public void requestExerciseIndex(ArrayList exercises) {
        graph.setVisible(false);
        String text="<html>";
        for (int i = 0; i < exercises.size();i++)
        {
            Map.Entry<Exercise, Double> map = (Map.Entry)exercises.get(i);
            text+=(i+1 + ". " +  map.getKey().getExerciseName() + " servings: " +map.getValue()+"<br>");
        }
        text+="Select the corresponding number of the exercise you would like to remove</html>";
        message.setText(text);
        message.setVisible(true);
        textfield.setVisible(true);
        textfield.setText("");
        go.setVisible(true);
    }

    /*
     * Displays a request for the user to enter a new calorie limit
     */
    public void requestCalorieLimit(){
        graph.setVisible(false);
        message.setText("Please enter the new calorie limit");
        message.setVisible(true);
        textfield.setVisible(true);
        textfield.setText("");
        go.setVisible(true);
    }

    public void requestSave(){
        graph.setVisible(false);
        message.setText("Press 'Go' to save!");
        message.setVisible(true);
        textfield.setVisible(false);
        textfield.setText("");
        go.setVisible(true);
    }

    /*
     * Displays a request for the user to enter a food name
     */
    public void requestFoodName() {
        graph.setVisible(false);
        message.setText("Please enter the name of the food and whether it has ingredients 'name,y' or 'name,n'");
        message.setVisible(true);
        textfield.setVisible(true);
        textfield.setText("");
        go.setVisible(true);
    }

    public void addFoodDay() {
        graph.setVisible(false);
        message.setText("<html>Please enter the name of food and number of servings 'food,servings'</html>");
        message.setVisible(true);
        textfield.setVisible(true);
        textfield.setText("");
        go.setVisible(true);
    }

    public void requestAddIngredient() {
        graph.setVisible(false);
        message.setText("Please enter ingredients 'name,servings'");
        message.setVisible(true);
        textfield.setVisible(true);
        textfield.setText("");
        go.setVisible(true);
    }

    public void requestMoreIngredient() {
        graph.setVisible(false);
        message.setText("Do you wish to add more ingredients 'y' or 'n'?");
        message.setVisible(true);
        textfield.setVisible(true);
        textfield.setText("");
        go.setVisible(true);
    }

    public void requestAddFoodNutrition() {
        graph.setVisible(false);
        message.setText("Please add calories per serving, grams of fat, \ngrams of carbs, and grams of protein 'calories,fat,carbs,protein'");
        message.setVisible(true);
        textfield.setVisible(true);
        textfield.setText("");
        go.setVisible(true);
    }

    /*
     * Displays a request for the user to enter a number of servings
     */
    public void requestLog() {
        graph.setVisible(false);
        message.setText("Press 'Go' to view Log");
        message.setVisible(true);
        textfield.setVisible(false);
        textfield.setText("");
        go.setVisible(true);
    }

    public void requestFoodIndex(ArrayList foods) {
        graph.setVisible(false);
        String text="<html>";
        for (int i = 0; i < foods.size();i++)
        {
            Map.Entry<Food, Double> map = (Map.Entry)foods.get(i);
            text+=(i+1 + ". " +  map.getKey().getFoodName() + " servings: " +map.getValue()+"<br>");
        }
        text+="Select the corresponding number of the food you would like to remove</html>";
        message.setText(text);
        message.setVisible(true);
        textfield.setVisible(true);
        textfield.setText("");
        go.setVisible(true);
    }

    public void displayLog(String output){
        Object[] objects = new Object[2];
        objects[0] = output;
        objects[1] = graph;
        graph.setVisible(true);
        JOptionPane.showMessageDialog(null, objects);
    }

    public void error(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public void checkForRecipe() {

        //Will ask if food contains more than two recipes
    }

    public void displayFoodExists() {

        //Will display to user that food already exists in the collection

    }

    public JButton getExit(){
        return exit;
    }

    public JComboBox getMenuItems(){
        return menuItems;
    }

    public JButton getGo(){
        return go;
    }

    public JTextField getTextfield(){
        return textfield;
    }

}
