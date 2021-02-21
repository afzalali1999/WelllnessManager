package view;

import model.Day;
import model.Log;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.*;

public class BarGraph extends Canvas implements Observer {

    private Day currentDay;
    private Log data;


    public BarGraph(Log data){
        this.data = data;
        currentDay = this.data.getCurrentDay();
        this.setPreferredSize(new Dimension(300, 300));
        this.setMinimumSize(new Dimension(300, 300));
        data.addObserver(this);
        this.update(data, new Object());
    }

    public void update(Observable o, Object arg) {
        System.out.println("bar graph update.");
        currentDay = this.data.getCurrentDay();
        this.repaint();
    }

    public void paint(Graphics graphics){
        System.out.println("painting");
        long[] percents = currentDay.calcPercents();
        long fat = percents[0];
        long carbs = percents[1];
        long protein = percents[2];

        this.setPreferredSize(new Dimension(300, (int)Math.max(fat, Math.max(carbs, protein))));
        this.setMinimumSize(new Dimension(300, 300));

        Rectangle bg = graphics.getClipBounds();
        int width = (int)bg.getWidth();
        int height = (int)bg.getHeight();  // make sure doesn't go off page?
        int barWidth = width / 3;

        graphics.setColor(Color.RED);
        graphics.setPaintMode();

        // fat
        graphics.fillRect(0, height - (int)fat, barWidth, height);
        // carbs
        graphics.setColor(Color.GREEN);
        graphics.fillRect(barWidth, height - (int)carbs, barWidth, height);
        // protein
        graphics.setColor(Color.BLUE);
        graphics.fillRect(2*barWidth, height - (int)protein, barWidth, height);
    }


}
