package com.company.player;

import com.company.player.Item.Helm;
import com.company.player.Item.item_list.default_armour;
import com.company.player.Item.item_list.default_helm;
import com.company.player.Item.item_list.default_melee_weapon;

public class Warrior extends Player {

    public Warrior(String name) {
        super(name);

        _class = "Warrior";
        HitPoints = 100;
        melee_attack = 20;
        range_attack = 0;
        def = 30;
        helm = new default_helm("Default helm", 1);
        armour = new default_armour("Default Armour", 1);
        Weapon = new default_melee_weapon("Default Melee Weapon", 1);
    }

    public int get_attack(){
        return melee_attack + Weapon.get_damage();
    }
}
