package com.company.player.Item.item_list;

import com.company.player.Item.Weapon;

public class default_range_magical_weapon extends Weapon {
    public default_range_magical_weapon(String name, int lvl_to_equipasds) {
        super(name, lvl_to_equipasds);
        is_range = true;
        damage = 30;
    }
}
