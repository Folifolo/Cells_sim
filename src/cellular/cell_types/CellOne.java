package cellular.cell_types;

import cellular.utils;
import cellular.utils.Item;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Random;

public final class CellOne extends Cell {

    public CellOne(int x, int y, int energy) {
        super(x, y, energy);
    }

    @Override
    protected utils.Action MakeDecision(Item vision) {

        Random ran = new Random();
        utils.Action turn = utils.Action.stay;
        if (energy > 0) {
            switch (vision)
            {
                case space:
                    turn = utils.Action.move;
                    break;
                case food:
                    turn = utils.Action.eat;
                    break;
                case wall:
                    int rotate = ran.nextInt(2);
                    if (rotate == 0) {
                        direction = (direction + 1) % 4;
                        turn = utils.Action.cw;
                    } else if (rotate == 1) {
                        direction = (direction + 3) % 4;
                        turn = utils.Action.ccw;
                    }
                    break;
                case cell:
                    turn = utils.Action.attack;
                    break;
                case deadCell:
                    turn = utils.Action.eat;
                    break;
            }
            energy -= 1;
        }
        return turn;
    }

    @Override
    public utils.Action MakeTurn(Item vision) {

        return MakeDecision(vision);
    }
}
