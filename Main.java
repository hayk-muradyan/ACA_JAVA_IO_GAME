package com.company;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static boolean order = true;
    public static File file = new File("Warior.txt");

    public static void main(String[] args) {
	// write your code here

        Random random = new Random();
        double archerHealth = random.nextInt(200) + 900;
        double swordsmanHealth = random.nextInt(200) + 900;

        double archerArmor = random.nextInt(10) + 10;
        double swordsmanArmor = random.nextInt(10) + 10;

        double archerStrength = random.nextInt(50) + 100;
        double swordsmanStrength = random.nextInt(50) + 100;

        Archer archer = new Archer(archerHealth,archerArmor,archerStrength);
        Swordsman swordsman = new Swordsman(swordsmanHealth,swordsmanArmor,swordsmanStrength);



        if(file.length() != 0) {
            try {
                List<String> allLines = Files.readAllLines(Paths.get(file.getPath()));
                double health, armor,strength;
                health = Double.parseDouble( allLines.get(allLines.size() - 9).substring(allLines.get(allLines.size() - 9).indexOf("=")+1));
                armor = Double.parseDouble( allLines.get(allLines.size() - 8).substring(allLines.get(allLines.size() - 8).indexOf("=")+1));
                strength = Double.parseDouble( allLines.get(allLines.size() - 7).substring(allLines.get(allLines.size() - 7).indexOf("=")+1));

                if(allLines.get(allLines.size() - 10).contains("Archer"))
                    archer.initialize(health,armor,strength);
                else
                    swordsman.initialize(health,armor,strength);

                health = Double.parseDouble( allLines.get(allLines.size() - 4).substring(allLines.get(allLines.size() - 4).indexOf("=")+1));
                armor = Double.parseDouble( allLines.get(allLines.size() - 3).substring(allLines.get(allLines.size() - 3).indexOf("=")+1));
                strength = Double.parseDouble( allLines.get(allLines.size() - 2).substring(allLines.get(allLines.size() - 2).indexOf("=")+1));

                if(allLines.get(allLines.size() - 5).contains("Archer")) {
                    archer.initialize(health, armor, strength);
                }
                else {
                    swordsman.initialize(health, armor, strength);
                    order = false;
                }

            }catch (Exception es){
                System.out.println(es.toString());
            }
        }

        while(true) {
            Scanner scanner = new Scanner(System.in);
            if(scanner.nextLine().equals("")){
                if(file.length() == 0){
                    archer.insertStatisticsInFile(archer);
                }
                if(order) {
                    archer.beat(swordsman);
                    order = false;
                }
                else {
                    swordsman.beat(archer);
                    order = true;
                }
            }
            if(archer.health <= 0) {
                System.out.println("Swordsman has won");
                return;
            }
            else if(swordsman.health <= 0) {
                System.out.println("Archer has won");
                return;
            }
        }


    }
}
