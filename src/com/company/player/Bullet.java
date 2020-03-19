package com.company.player;

import java.awt.*;

public class Bullet {
    public Bullet(Player player, char dir){

        owner = player;
        direction = dir;
        coord = new Point(player.get_x_coord(), player.get_y_coord());
        bullet_move();
    }

    public void bullet_move(){
        if (direction == 'a')
            coord.x--;
        if (direction == 'd')
            coord.x++;
        if (direction == 's')
            coord.y++;
        if (direction == 'w')
            coord.y--;
    }


    Point coord;
    Player owner;
    char direction;

    public int get_x_coord() {
        return coord.x;
    }

    public int get_y_coord() {
        return coord.y;
    }
}
