package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Warior {
    protected double health;
    protected double armor;
    protected double strength;

    public void initialize(double health, double armor, double strength) {
        this.health = health;
        this.strength = strength;
        this.armor = armor;
    }

    public void insertStatisticsInFile(Warior warior) {
        //File file = new File("Warior.txt");
            try {
                Files.write(Paths.get(Main.file.getPath()), warior.toString().getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
            }
    }
    public void beat(Warior warior){

        warior.health -= this.strength *(100 - warior.armor)/100;

        insertStatisticsInFile(warior);
    }

    @Override
    public String toString() {
        return this.getClass().toString().substring(this.getClass().toString().lastIndexOf(".")+1) + "{\n" +
                "health=" + health + "\n" +
                "armor=" + armor + "\n" +
                "strength=" + strength + "\n" +
                "}\n";
    }
}
