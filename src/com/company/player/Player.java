package com.company.player;

import com.company.player.Item.Armor;
import com.company.player.Item.Item;

import java.awt.*;
import java.io.*;
import java.util.Formatter;
import java.util.Scanner;


public abstract class Player {

    public Player(String name){
        _name = name;

        _lvl = 1;
        _exp = 0;
        set_need_exp();
        coord = new Point();
    }

    private void level_up(){
        ++_lvl;
        HitPoints = (HitPoints * _lvl) - ((HitPoints * _lvl)/2);
        def = (def * _lvl) - ((def * _lvl)/2);
        attack = (attack * _lvl) - ((attack * _lvl)/2);
        set_need_exp();
    }

    private void set_need_exp(){
        _need_exp = _lvl * 1000 + ((_lvl - 1) * (_lvl - 1)) * 450;
    }

    void get_dmg(int dmg){
        int tmp = dmg - def - armour.get_armour();

        if (tmp > 0)
            HitPoints -= dmg - def - armour.get_armour();
    }

    public void loading_character(String []data){
        _name = data[0];
        _class = data[1];
        _lvl = Integer.parseInt(data[2]);
        _exp = Integer.parseInt(data[3]);
        attack = Integer.parseInt(data[4]);
        def = Integer.parseInt(data[5]);
        HitPoints = Integer.parseInt(data[6]);
    }

    public void set_player(int y_a, int x_a){
        coord.y = y_a;
        coord.x = x_a;
    }

    public int get_y_coord(){
        return coord.y;
    }
    public int get_x_coord(){
        return coord.x;
    }

    public void save_hero() {
        try{
            BufferedWriter bw = null;
            FileWriter writer = new FileWriter(_name + ".hero", false);
            bw = new BufferedWriter(writer);
            bw.write(_name + "\n" + _class + "\n" + _lvl + "\n" + _exp + "\n" +attack + "\n"+ def + "\n" + HitPoints+ "\n");
            bw.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public int get_lvl(){
        return _lvl;
    }

    public int get_exp(){
        return _exp;
    }

    public int get_need_exp(){
        return _need_exp;
    }

    public int get_attack(){
        return attack + Weapon.get_damage();
    }
    public void take_dmg(int dmg){
        if (dmg - def - helm.get_hp() - armour.get_armour() > 0)
            HitPoints -= (dmg - def - helm.get_hp() - armour.get_armour());
    }

    public void take_exp(int exp){
        if (exp + _exp >= _need_exp){
            int tmp = exp + _exp - _need_exp;
            level_up();
            exp = tmp;
        }
        else
            _exp += exp;
    }

    public int get_def(){
        return def;
    }

    public int get_hp(){
        return HitPoints;
    }

    public String get_name(){
        return _name;
    }

    public String get_class(){
        return _class;
    }


    public Point coord;
    protected String _name;
    protected String _class;
    protected int _lvl;
    protected int _exp;
    protected int _need_exp;
    protected int attack;
    protected int melee_attack;
    protected int range_attack;
    protected int def;
    protected int HitPoints;


    Item helm;
    Item armour;
    Item Weapon;
}
