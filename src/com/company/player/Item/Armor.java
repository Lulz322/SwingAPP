package com.company.player.Item;

public abstract class Armor extends Item {


    protected int Armor;

    public Armor(String default_armour, int lvl_to_equipasds) {
        super(default_armour, lvl_to_equipasds);
    }

    public int get_armour(){
        return Armor;
    }
}
