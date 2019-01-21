package nl.hanze.projectOOP.mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class BarGraph extends JPanel
{
    private Map<Color, Integer> bars = new LinkedHashMap<Color, Integer>();

    public void addBar(Color color, int value)
    {
        bars.put(color, value);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        // determine longest bar

        int max = Integer.MIN_VALUE;
        for (Integer value : bars.values())
        {
            max = Math.max(max, value);
        }

        // paint bars

        int width = (getWidth() / bars.size()) - 2;
        int x = 1;
        for (Color color : bars.keySet())
        {
            int value = bars.get(color);
            int height = (int)
                    ((getHeight()-5) * ((double)value / max));
            g.setColor(color);
            g.fillRect(x, getHeight() - height, width, height);
            g.setColor(null);
            g.drawRect(x, getHeight() - height, width, height);
            x += (width + 2);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(bars.size() * 10 + 2, 50);
    }

    public void run() {
        JFrame frame = new JFrame("Bar Graph");
        BarGraph chart = new BarGraph();
        chart.addBar(Color.white, 100);
        chart.addBar(Color.red, 20);
        frame.setBackground(Color.white);
        frame.getContentPane().add(chart);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
