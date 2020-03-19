package com.company.player.Item;

public abstract class Item {

    Item(String name, int lvl_to_equipasds){
        item_name = name;
        lvl_to_equip = lvl_to_equipasds;
    }

    protected String item_name;
    protected int lvl_to_equip;
    protected int HitPoint;
    protected int Armour;
    protected int damage;

    public int get_armour(){
        return Armour;
    }
    public int get_damage(){
        return damage;
    }
    public int get_hp(){
        return HitPoint;
    }


}
