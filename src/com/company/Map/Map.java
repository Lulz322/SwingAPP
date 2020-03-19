package com.company.Map;

import com.company.NPC;
import com.company.player.Bullet;
import com.company.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {

    public Map(Player playeraaa){
        level = 1;

        player = playeraaa;
        enemies = new ArrayList<NPC>();
        combat_list = new ArrayList<>();
        is_combat = false;
        set_map_size();
        set_player_on_center();
        add_enemies_on_map();
        generate_field();
        set_npc();
        bullet_list = new ArrayList<>();
    }


    private void generate_field(){
        field = new char[map_size + 2][map_size + 2];

        for (int i = 0; i < map_size + 2; i++){
            for (int j = 0; j < map_size + 2; j++){
                if (i == 0 || i == map_size + 1)
                    field[i][j] = '_';
                if (j == 0 || j == map_size + 1)
                    field[i][j] = '|';
            }
        }
    }

    public void check_is_combat(){
        int py, px;
        py = player.get_y_coord();
        px = player.get_x_coord();

        for (NPC it : enemies){
            if (it.get_y_coord() - 1 == py && it.get_x_coord() -1 == px ||
                it.get_y_coord() == py && it.get_x_coord() - 1 == px ||
                it.get_y_coord() + 1 == py && it.get_x_coord() - 1 == px ||
                    it.get_y_coord() - 1 == py && it.get_x_coord() == px ||
                    it.get_y_coord() == py && it.get_x_coord()  == px ||
                    it.get_y_coord() + 1 == py && it.get_x_coord() == px ||
                        it.get_y_coord() - 1 == py && it.get_x_coord() + 1 == px ||
                        it.get_y_coord() == py && it.get_x_coord() + 1 == px ||
                        it.get_y_coord() + 1 == py && it.get_x_coord() + 1 == px)
                if (combat_list.lastIndexOf(it) == -1)
                    combat_list.add(it);
        }
    }

    private void set_player_on_center(){
        player.set_player((map_size / 2) + 1, map_size /2  + 1 );
    }
    public void print_field(){
        for (int i = 0; i < map_size + 2; i++){
            System.out.print(i);
            for (int j = 0; j < map_size + 2; j++){
                if (field[i][j] == 0)
                    System.out.print(" ");
                else
                    System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

    void set_map_size(){
        map_size = ((level - 1) * 5 + 10 - (level % 2)) ; number_of_enemies = level * 10 - ((level * 10) / 2);
    }

    int get_map_size(){
        return (map_size);
    }

    public void remove_and_set(int old_y, int old_x, int new_y, int new_x){
        char tmp = field[old_y][old_x];
        field[old_y][old_x] = 0;
        field[new_y][new_x] = tmp;

        check_is_combat();
    }

    public void lelel_up(){
        enemies.clear();
        level++;
        set_map_size();
        set_player_on_center();
        add_enemies_on_map();
        generate_field();
        set_npc();

        combat_list.clear();
    }

    void set_npc(){
        for (NPC it : enemies){
            System.out.printf("NPC Y: %d | X : %d\n",it.get_y_coord(),it.get_x_coord());
            field[it.get_y_coord()][it.get_x_coord()] = '@';
        }
            field[player.get_y_coord()][player.get_x_coord()] = '#';
    }

    boolean check_list(int y, int x){
        for (NPC it : enemies){
            if (it.get_y_coord() == y && it.get_x_coord() == x ||
                (y == player.get_y_coord() && x == player.get_x_coord()) ||
                    y == player.get_y_coord() - 1 && (x == player.get_x_coord() || x == player.get_x_coord() - 1 || x == player.get_x_coord() + 1) ||
                    y == player.get_y_coord() + 1 && (x == player.get_x_coord() || x == player.get_x_coord() - 1 || x == player.get_x_coord() + 1) ||
                    x == player.get_x_coord() + 1 && (y == player.get_y_coord() || y == player.get_y_coord() - 1 || x == player.get_y_coord() + 1) ||
                    x == player.get_x_coord() - 1 && (y == player.get_y_coord() || y == player.get_y_coord() - 1 || x == player.get_y_coord() + 1))
                return false;
        }
        return true;
    }

    void add_enemies_on_map(){
        Random random = new Random();
        int y_tmp = 0;
        int x_tmp = 0;
        boolean check = false;
        for (int i = 0; i < number_of_enemies; ++i)
            enemies.add(new NPC(level));
        for (NPC it : enemies){
            check = false;
            do{
                y_tmp = random.nextInt(map_size) + 1;
                x_tmp = random.nextInt(map_size) + 1;

                check = check_list(y_tmp, x_tmp);
            } while (!check);
            it.set_coords(y_tmp, x_tmp);
        }
    }
    public boolean is_combat;
    private int level;
    public int map_size;
    private int number_of_enemies;
    public ArrayList<NPC> enemies;
    public Player player;
    public ArrayList<NPC> combat_list;
    public ArrayList<Bullet> bullet_list;

    public char [][]field;
}
