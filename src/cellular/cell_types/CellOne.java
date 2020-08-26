package cellular.cell_types;

import cellular.utils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public final class CellOne extends Cell {

    public CellOne(int x, int y, int energy) {
        super(x, y, energy);
    }

    @Override
    protected int MakeDecision(int vision) {

        Random ran = new Random();
        int turn = utils.actionNumber.get("stay");
        if (energy > 0) {
            if (vision == utils.itemNumber.get("food")) {
                turn = utils.actionNumber.get("eat");

            } else if (vision == utils.itemNumber.get("wall")) {
                int rotate = ran.nextInt(2);
                if (rotate == 0) {
                    direction = (direction + 1) % 4;
                    turn = utils.actionNumber.get("cw");
                } else if (rotate == 1) {
                    direction = (direction + 3) % 4;
                    turn = utils.actionNumber.get("ccw");
                }

            } else if (vision == utils.itemNumber.get("space")) {
                turn = utils.actionNumber.get("move");

            } else if (vision == utils.itemNumber.get("cell")) {
                turn = utils.actionNumber.get("attack");

            } else if (vision == utils.itemNumber.get("dead cell")) {
                turn = utils.actionNumber.get("eat");

            } else
                turn = utils.actionNumber.get("stay");
            energy -= 1;
        }
        return turn;
    }

    @Override
    public int MakeTurn(int vision) {

        return MakeDecision(vision);
    }
}
