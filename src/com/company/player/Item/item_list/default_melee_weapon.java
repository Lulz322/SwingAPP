package com.company.player.Item.item_list;

import com.company.player.Item.Weapon;

public class default_melee_weapon extends Weapon {
    public default_melee_weapon(String name, int lvl_to_equipasds) {
        super(name, lvl_to_equipasds);
        is_range = false;
        damage = 20;
    }
}
