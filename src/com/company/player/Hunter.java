package com.company.player;

public class Hunter extends Player {
    public Hunter(String name) {
        super(name);

        _class = "Hunter";

        HitPoints = 80;
        melee_attack = 20;
        range_attack = 30;
        def = 20;
    }
}
