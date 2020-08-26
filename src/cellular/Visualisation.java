package cellular;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class Visualisation extends JPanel {

    private utils.Item[][] grid;
    private static final Random rnd = new Random();
    private int generationCounter;

    public Visualisation(int height, int width) {
        this.grid = new utils.Item[height][width];
    }

    public void UpdateGrid(utils.Item[] grid) {
        for (int i = 0; i < this.grid.length; i++)
            System.arraycopy(grid, i * this.grid[0].length, this.grid[i], 0, this.grid[0].length);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(grid.length * 4, grid[0].length * 4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        //g.drawString("size" + grid.length + " " + grid[0].length + "Generation: " + generationCounter++, 0, 10);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                switch (grid[i][j]) {
                    case food:
                        g.setColor(Color.green);
                        g.fillRect(j * 4, i * 4, 4, 4);
                        break;
                    case wall:
                        g.setColor(Color.black);
                        g.fillRect(j * 4, i * 4, 4, 4);
                        break;
                    case cell:
                        g.setColor(Color.blue);
                        g.fillRect(j * 4, i * 4, 4, 4);
                        break;
                    case deadCell:
                        g.setColor(Color.red);
                        g.fillRect(j * 4, i * 4, 4, 4);
                        break;
                }
            }
        }

        g.setColor(gColor);
    }


}