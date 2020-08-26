package cellular;

import java.util.Random;

public class CellGrid {
    public utils.Item[] grid;
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
        this.grid = new utils.Item[this.height * this.width];
        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++) {
                this.grid[j + i * this.width] = utils.Item.space;
                if ((i == 0) || (i == this.height - 1) || (j == this.width - 1) || (j == 0))
                    this.grid[j + i * this.width] = utils.Item.wall;
            }
    }

    private void AddOneItem(int x, int y, utils.Item item) {
        this.grid[x + y * width] = item;
    }

    public void AddItems(int number, utils.Item item) {
        Random ran = new Random();
        for (int i = 0; i < number; i++) {
            int x = ran.nextInt(width - 2) + 1;
            int y = ran.nextInt(height - 2) + 1;
            if (this.grid[x + y * width] == utils.Item.space)
                AddOneItem(x, y, item);
        }
    }

    private boolean IsTileOnMap(int x, int y) {
        return ((0 <= x) && (x < width)) && ((0 <= y) && (y < height));
    }

    public utils.Item GetTile(int x, int y) {
        if (IsTileOnMap(x, y))
            return this.grid[x + y * width];
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public void SetTile(int x, int y, utils.Item tile) {
        if (IsTileOnMap(x, y))
            this.grid[x + y * width] = tile;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public void PlaceCell(int x, int y) throws WrongPositionException {
        if (this.grid[x + y * width] == utils.Item.space)
            SetTile(x, y, utils.Item.cell);
        else
            throw new WrongPositionException("cell not in space");
    }

    public void DeleteCell(int x, int y) throws WrongPositionException {
        if (IsTileOnMap(x, y)) {
            if (this.grid[x + y * width] == utils.Item.cell)
                this.grid[x + y * width] = utils.Item.space;
            else
                throw new WrongPositionException("there is no cell");
        }
    }

    public void PrintMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (this.grid[j + i * width]) {

                    case space:
                        System.out.print(".");
                        break;
                    case food:
                        System.out.print("f");
                        break;
                    case wall:
                        System.out.print("#");
                        break;
                    case cell:
                        System.out.print("@");
                        break;
                    case deadCell:
                        System.out.print("X");
                        break;
                }
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
