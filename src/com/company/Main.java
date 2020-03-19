package com.company;

import com.company.Map.Map;
import com.company.player.Hunter;
import com.company.player.Mage;
import com.company.player.Player;
import com.company.player.Warrior;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static Player loading(){
        System.out.println("Hello in game\n1.Create a new Hero\n2.Choose exist Hero");
        Scanner in = new Scanner(System.in);

        String input = in.next();
        Player player = null;

        switch (input){
            case "1":
                player = creating_new_hero();
                break;
            case "2":
                player = load_hero();
                break;
            default:
                System.out.println("Error input");
        }
        return player;
    }
    public static Player creating_new_hero(){
        System.out.println("Creating Hero");
        System.out.println("Enter Hero name");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();


        System.out.println("Choose a Class For ur Hero");
        System.out.println("1.Warrior\n2.Mage\n3.Hunter");
        Player p = null;
        String input = in.next();
        switch (input){
            case "1":
                p = new Warrior(name);
                break;
            case "2":
                p = new Mage(name);
                break;
            case "3":
                p = new Hunter(name);
                break;
            default:
                System.out.println("Error input");
        }
        p.save_hero();
        System.out.println("Hero created");

        return p;
    }
    private static Player load_hero() {
        System.out.println("Write way to ur hero");
        String[] file = new String[8];

        int counter = 0;
        Player p = null;
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        try(BufferedReader reader = new BufferedReader(new FileReader(input))){
            while((file[counter++] = reader.readLine()) != null && counter < 7);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(file[0]);
        switch (file[1]){
            case "Warrior":
                p = new Warrior(file[0]); p.loading_character(file); break;
            case "Mage":
                p = new Mage(file[0]); p.loading_character(file); break;
            case "Hunter":
                p = new Hunter(file[0]); p.loading_character(file); break;
            default:
                System.out.println("Error Input");
        }

        return p;
    }

    public static void main(String[] args) {

        System.out.println(a);

        Player player = loading();
        Map map = new Map(player);

        map.print_field();

        JFrame frame = new MainForm("Game Window", map);
    }
}
