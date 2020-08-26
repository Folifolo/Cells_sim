package cellular;

import cellular.cell_types.Cell;
import cellular.cell_types.CellOne;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game {

    CellGrid gameMap;
    ArrayList<Cell> cells;
    int cellsCount = 0;
    Visualisation visual;

    public Game() {
        int height = 20;
        int width = 20;
        gameMap = new CellGrid(height, width);

        cells = new ArrayList<Cell>();

        visual = initVisualisation(height, width);
    }

    public void Play() throws WrongPositionException, InterruptedException {
        Random ran = new Random();
        for (int i = 0; i < 4; i++)
            placeCell(ran.nextInt(gameMap.getWidth() - 2) + 1, ran.nextInt(gameMap.getHeight() - 2) + 1, 30);


        gameMap.AddItems(30, utils.Item.wall);
        gameMap.AddItems(30, utils.Item.food);

        int turnCounter = 0;
        while (true) {
            turnCounter++;
            for (Cell cell1 : cells) {
                cellAct(cell1);
            }
            System.out.println(turnCounter);
        }
    }

    private void cellAct(@NotNull Cell cell) throws InterruptedException, WrongPositionException {
        int[] cellView = cell.ViewPosition();
        int[] cellPosition = cell.GetPosition();
        utils.Action action = cell.MakeTurn(gameMap.GetTile(cellView[0], cellView[1]));

        visual.UpdateGrid(gameMap.grid);
        visual.repaint();
        TimeUnit.MILLISECONDS.sleep(100);

        if (cell.GetEnergy() <= 0) {
            gameMap.SetTile(cellPosition[0], cellPosition[1], utils.Item.deadCell);
        } else {
            switch (action) {
                case stay:
                case cw:
                case ccw:
                    break;
                case eat:
                    if (gameMap.GetTile(cellView[0], cellView[1]) == utils.Item.food) {
                        gameMap.SetTile(cellView[0], cellView[1], utils.Item.space);
                        cell.AddEnergy(5);
                    }
                    if (gameMap.GetTile(cellView[0], cellView[1]) == utils.Item.deadCell) {
                        Cell cell2 = FindCell(cellView[0], cellView[1]);
                        cells.remove(cell2);
                        gameMap.SetTile(cellView[0], cellView[1], utils.Item.space);
                        cell.AddEnergy(10);
                    }
                    break;
                case move:
                    if (gameMap.GetTile(cellView[0], cellView[1]) == utils.Item.space) {
                        gameMap.DeleteCell(cellPosition[0], cellPosition[1]);
                        cell.Move();
                        gameMap.PlaceCell(cellView[0], cellView[1]);
                    }
                    break;
                case attack:
                    if (gameMap.GetTile(cellView[0], cellView[1]) == utils.Item.cell) {
                        Cell cell2 = FindCell(cellView[0], cellView[1]);
                        cell2.AddEnergy(-5);
                        cell.AddEnergy(5);
                        break;
                    }
            }
        }
    }

    @NotNull
    private Cell FindCell(int x, int y) throws WrongPositionException {

        for (Cell cell1 : cells) {
            if (cell1.GetPosition()[0] == x && cell1.GetPosition()[1] == y)
                return cell1;
        }
        throw new WrongPositionException("there is no cell");
    }

    private void placeCell(int x, int y, int energy) throws WrongPositionException {
        CellOne cell = new CellOne(x, y, energy);
        this.cells.add(cell);
        cellsCount++;
        gameMap.PlaceCell(x, y);
    }

    @NotNull
    private Visualisation initVisualisation(int height, int width) {
        Visualisation c = new Visualisation(height, width);
        JFrame frame = new JFrame();
        frame.getContentPane().add(c);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        return c;
    }
}
