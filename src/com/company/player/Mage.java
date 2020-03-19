package com.company.player;

public class Mage extends Player {
    public Mage(String name) {
        super(name);

        _class = "Mage";
        HitPoints = 50;
        def = 0;
        range_attack = 30;
        melee_attack = 10;
    }
}
