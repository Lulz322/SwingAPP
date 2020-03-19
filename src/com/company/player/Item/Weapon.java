package com.company.player.Item;

public abstract class Weapon extends Item {

    protected boolean is_range; //0 - melee 1 - range;
    protected int damage;

    public Weapon(String name, int lvl_to_equipasds) {
        super(name, lvl_to_equipasds);
    }
}
