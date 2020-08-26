package cellular.cell_types;

import cellular.utils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static cellular.utils.IntArrayToString;

public abstract class Cell {
    protected int GENOME_LENGTH = 64;

    protected int[] genome;
    protected int pointer;
    protected int energy;

    protected int x;
    protected int y;

    protected int direction;


    @Contract(pure = true)
    public Cell(int x, int y, int energy) {
        this.x = x;
        this.y = y;
        this.energy = energy;
        this.direction = 0;
        this.genome = new int[this.GENOME_LENGTH];
        this.pointer = 0;
    }

    public int GetEnergy() {
        return energy;
    }

    public void AddEnergy(int eng) {
        this.energy += eng;
    }

    @NotNull
    @Contract(pure = true)
    public int[] GetPosition() {
        int[] res = new int[2];
        res[0] = x;
        res[1] = y;
        return res;
    }

    @NotNull
    public int[] ViewPosition() {
        int[] res = new int[2];
        res[0] = x + utils.directionMap.get(direction)[0];
        res[1] = y + utils.directionMap.get(direction)[1];
        return res;
    }

    public void Move() {
        int[] view = ViewPosition();
        this.x = view[0];
        this.y =  view[1];
    }

    protected abstract utils.Action MakeDecision(utils.Item vision);

    public abstract utils.Action MakeTurn(utils.Item vision);

    public void SaveGenome(String path) {

        FileWriter writer;
        try {
            writer = new FileWriter("genomes\\"+ path + ".txt", false);
            // запись всей строки
            writer.write(IntArrayToString(genome));
            writer.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void LoadGenome(String path) {
        FileReader reader;
        try
        {
            reader = new FileReader("genomes\\"+ path + ".txt");
            // читаем посимвольно
            int c;
            int i = 0;
            while((c=reader.read())!=-1){
                this.genome[i++] = Character.getNumericValue(c);
            }
        }
        catch(IOException e){

            System.out.println(e.getMessage());
        }
        System.out.println(IntArrayToString(genome));
    }
}

