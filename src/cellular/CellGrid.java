package cellular;

import java.util.Random;

public class CellGrid {
    public int[] grid;
    private int height;
    private int width;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public CellGrid(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new int[this.height * this.width];
        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++)
                if ((i == 0) || (i == this.height - 1) || (j == this.width - 1) || (j == 0))
                    this.grid[j + i * this.width] = utils.itemNumber.get("wall");
    }

    private void AddOneItem(int x, int y, int item) {
        this.grid[x + y * width] = item;
    }

    public void AddItems(int number, int item) {
        Random ran = new Random();
        for (int i = 0; i < number; i++) {
            int x = ran.nextInt(width - 2) + 1;
            int y = ran.nextInt(height - 2) + 1;
            if (this.grid[x + y * width] == utils.itemNumber.get("space"))
                AddOneItem(x, y, item);
        }
    }

    private boolean IsTileOnMap(int x, int y) {
        return ((0 <= x) && (x < width)) && ((0 <= y) && (y < height));
    }

    public int GetTile(int x, int y) {
        if (IsTileOnMap(x, y))
            return this.grid[x + y * width];
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public void SetTile(int x, int y, int tile) {
        if (IsTileOnMap(x, y))
            this.grid[x + y * width] = tile;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public void PlaceCell(int x, int y) throws WrongPositionException {
        if (this.grid[x + y * width] == utils.itemNumber.get("space"))
            SetTile(x, y, utils.itemNumber.get("cell"));
        else
            throw new WrongPositionException("cell not in space");
    }

    public void DeleteCell(int x, int y) throws WrongPositionException {
        if (IsTileOnMap(x, y)) {
            if (this.grid[x + y * width] == utils.itemNumber.get("cell"))
                this.grid[x + y * width] = utils.itemNumber.get("space");
            else
                throw new WrongPositionException("there is no cell");
        }
    }

    public void PrintMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (this.grid[j + i * width] == utils.itemNumber.get("wall"))
                    System.out.print("#");
                else if (this.grid[j + i * width] == utils.itemNumber.get("space"))
                    System.out.print(".");
                else if (this.grid[j + i * width] == utils.itemNumber.get("food"))
                    System.out.print("f");
                else if (this.grid[j + i * width] == utils.itemNumber.get("cell"))
                    System.out.print("@");
                else if (this.grid[j + i * width] == utils.itemNumber.get("dead cell"))
                    System.out.print("X");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class WrongPositionException extends Exception {
    private String details;

    WrongPositionException(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Wrong Position: " + details;
    }
}
