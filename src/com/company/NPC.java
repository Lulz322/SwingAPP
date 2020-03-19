package com.company;

import com.company.player.Player;

import java.awt.*;
import java.util.*;

public class NPC{

    private static class part{

        part(int dy, int dx, int dsteps, int dsave_pos){
            bfs_coord = new Point();

            bfs_coord.y = dy;
            bfs_coord.x = dx;

            steps = dsteps;
            save_pos = dsave_pos;
        }


        Point bfs_coord;

        int steps;
        int save_pos;
    }

    public NPC(int level){
        _lvl = level;

        coord = new Point();
        old_coord = new Point();
        sleep = 0;
        scale();
    }

    public void set_coords(int y, int x){

        old_coord.x = coord.x;
        old_coord.y = coord.y;

        coord.y = y;
        coord.x = x;
    }

    private void set_way(int i){
        queue = new ArrayList<>();
        Point tmp = new Point();
        while (way.get(i).steps != 0) {
            tmp.y = way.get(i).bfs_coord.y;
            tmp.x = way.get(i).bfs_coord.x;
            queue.add(new Point(tmp.x, tmp.y));
            i = way.get(i).save_pos;
        }
    }

    private void add_queue(part prev, int size){
        part q;
        if ((prev.bfs_coord.y < size && prev.bfs_coord.y > 0)  && (prev.bfs_coord.x < size && prev.bfs_coord.x > 0) &&
                (dist[prev.bfs_coord.y][prev.bfs_coord.x] == 0 || dist[prev.bfs_coord.y][prev.bfs_coord.x] == '#')){
            dist[prev.bfs_coord.y][prev.bfs_coord.x] = 'M';
            q = new part(prev.bfs_coord.y, prev.bfs_coord.x, prev.steps, prev.save_pos);
            way.add(q);
        }
    }

    private void bfs(char [][]grid, int player_y, int player_x) {

        //creating map;
        dist = new char[grid.length][grid.length];
        for (int a = 0; a < grid.length; a++){
            for (int b = 0; b < grid.length;b++){
                dist[a][b] = grid[a][b];
            }
        }
        way = new ArrayList<>();

        part tmp = new part(coord.y, coord.x, 0, -1);
        way.add(tmp);
        int i = -1;

        while(++i < way.size()){
            if (way.get(i).bfs_coord.x == player_x && way.get(i).bfs_coord.y == player_y) {
                set_way(i); return;
            }
            add_queue(new part(way.get(i).bfs_coord.y, way.get(i).bfs_coord.x + 1, way.get(i).steps + 1, i), grid.length);
            add_queue(new part(way.get(i).bfs_coord.y, way.get(i).bfs_coord.x - 1, way.get(i).steps + 1, i), grid.length);
            add_queue(new part(way.get(i).bfs_coord.y + 1, way.get(i).bfs_coord.x, way.get(i).steps + 1, i), grid.length);
            add_queue(new part(way.get(i).bfs_coord.y - 1, way.get(i).bfs_coord.x, way.get(i).steps + 1, i), grid.length);
        }
    }
    public void move(char [][]map, int player_y, int player_x){

        if (sleep > 0)
            sleep--;
        if (sleep == 0){
            bfs(map, player_y , player_x);
            if (queue.size() != 0){
                if (map[queue.get(queue.size() - 1).y][queue.get(queue.size() - 1).x] == 0)
                    set_coords(queue.get(queue.size() - 1).y, queue.get(queue.size() - 1).x);
            }

        }
    }

    public int get_y_coord(){
        return coord.y;
    }
    public int get_x_coord(){
        return coord.x;
    }


    public int get_lvl(){
        return _lvl;
    }

    public int get_exp(){
        return given_exp_in_death;
    }

    public int get_attack(){
        return attack;
    }

    public void take_dmg(int dmg){
        if (dmg - def > 0)
            hp -= (dmg - def);
    }

    public int get_def(){
        return def;
    }

    public int get_hp(){
        return hp;
    }

    private void scale(){
        hp = 100 * _lvl - (75 * _lvl);
        def = 10 * _lvl - 7 * _lvl;
        attack = 20 * _lvl - 15 * _lvl;
        given_exp_in_death = 200 * _lvl - 100 * _lvl;
    }

    private int attack;
    private int def;
    private int hp;
    private int _lvl;
    private int given_exp_in_death;
    private ArrayList<part> way;
    private ArrayList<Point> queue;
    public int sleep;

    private char [][]dist;
    Point coord;
    Point old_coord;
}
