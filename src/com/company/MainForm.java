package com.company;

import com.company.Map.Map;
import com.company.player.Bullet;
import com.company.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.util.Random;

import static java.lang.System.exit;


public class MainForm extends JFrame implements KeyListener, ActionListener {
    private JPanel MainPanel;
    private JLabel nameLabel;
    private JLabel classLabel;
    private JLabel hpLabel;
    private JLabel expLabel;
    private JLabel attack_label;
    private JLabel leave_label;
    private JLabel enemy_hp;
    private JLabel enemy_stats;
    private BufferedImage image;
    private BufferedImage enemy_image;
    private BufferedImage brick;
    private BufferedImage bullet_image;



    {
        try {
            URL url = new URL("https://purepng.com/public/uploads/large/purepng.com-american-footballamerican-footballamericanfootballgridiron-footballgridironsportoval-1701528085619owqm7.png");
            image = ImageIO.read(url);
            url = new URL("https://image.flaticon.com/icons/png/512/1477/1477281.png");
            enemy_image = ImageIO.read(url);
            url = new URL("https://c7.hotpng.com/preview/665/693/257/brick-picture-frames-wall-stock-photography-bricks-frame-png.jpg");
            brick = ImageIO.read(url);
            url = new URL("https://www.freepnglogos.com/uploads/bullet-png/golden-bullets-images-0.png");
            bullet_image = ImageIO.read(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int level = 7;

    private Map _map;



    private void set_new_size(JFrame frame){
        frame.setSize(650,650); frame.setResizable(false);
    }
    MainForm(String name, Map map){
        super(name);

        addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(MainPanel);
        set_new_size(this);
        setVisible(true);

        _map = map;
        nameLabel.setText(_map.player.get_name());
        classLabel.setText(_map.player.get_class());
        attack_label.setText("1. For Attack");
        leave_label.setText("2. For leave (50% to leave)");
    }


    private void set_new_text(){
        classLabel.setText(_map.player.get_class() + " lvl." + _map.player.get_lvl());
        hpLabel.setText("HP : " + _map.player.get_hp());
        expLabel.setText("Exp: " + _map.player.get_exp() + "/" + _map.player.get_need_exp());

        if (!_map.combat_list.isEmpty()){
            enemy_hp.setText("HP : " + _map.combat_list.get(0).get_hp() + " lvl." + _map.combat_list.get(0).get_lvl());
            enemy_stats.setText("Attack : " + _map.combat_list.get(0).get_attack() + " Def: " + _map.combat_list.get(0).get_def());


            enemy_stats.repaint();
            attack_label.repaint();
            leave_label.repaint();

        }

        enemy_hp.repaint();
        nameLabel.repaint();
        classLabel.repaint();
        hpLabel.repaint();
        expLabel.repaint();


    }
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 5000, 5000);

        //g.drawImage(brick, 20, 40 ,500, 500, null);
        g.setColor(Color.black);
        g.drawRect(20, 40, 513, 513);


        g.drawImage(image, (_map.player.get_x_coord() - 1) * ((513 / _map.map_size)) + 17 ,
                (_map.player.get_y_coord() - 1) * ((513 / _map.map_size )) + 37, 25, 25, null);

        for (NPC it : _map.enemies){
            g.drawImage(enemy_image, (it.get_x_coord() - 1) * ((513 / _map.map_size)) + 17 ,
                     (it.get_y_coord() - 1) * ((513 / _map.map_size )) + 37, 25, 25, null);
        }
        for (Bullet it : _map.bullet_list){
            it.bullet_move();
            g.drawImage(bullet_image, (it.get_x_coord() - 1) * ((513 / _map.map_size)) + 17 ,
                    (it.get_y_coord() - 1) * ((513 / _map.map_size )) + 37, 25, 25, null);
        }
        set_new_text();
        g.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (_map.combat_list.isEmpty())
        {
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                _map.bullet_list.add(new Bullet(_map.player, 'a'));
                this.repaint();
                return;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                _map.bullet_list.add(new Bullet(_map.player, 'd'));
                this.repaint();
                return;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP){
                _map.bullet_list.add(new Bullet(_map.player, 'w'));
                this.repaint();
                return;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                _map.bullet_list.add(new Bullet(_map.player, 's'));
                this.repaint();
                return;
            }

            if (e.getKeyCode() == KeyEvent.VK_S){
                _map.remove_and_set(_map.player.get_y_coord(), _map.player.get_x_coord(),
                        _map.player.get_y_coord() + 1, _map.player.get_x_coord());
                _map.player.set_player(_map.player.get_y_coord() + 1, _map.player.get_x_coord());
            }
            if (e.getKeyCode() == KeyEvent.VK_W){
                _map.remove_and_set(_map.player.get_y_coord(), _map.player.get_x_coord(),
                        _map.player.get_y_coord() - 1, _map.player.get_x_coord());
                _map.player.set_player(_map.player.get_y_coord() - 1, _map.player.get_x_coord());
            }
            if (e.getKeyCode() == KeyEvent.VK_D){
                _map.remove_and_set(_map.player.get_y_coord(), _map.player.get_x_coord(),
                        _map.player.get_y_coord(), _map.player.get_x_coord() + 1);
                _map.player.set_player(_map.player.get_y_coord(), _map.player.get_x_coord() + 1);
            }
            if (e.getKeyCode() == KeyEvent.VK_A){
                _map.remove_and_set(_map.player.get_y_coord(), _map.player.get_x_coord(),
                        _map.player.get_y_coord(), _map.player.get_x_coord() - 1);
                _map.player.set_player(_map.player.get_y_coord(), _map.player.get_x_coord() - 1);
            }
            System.out.printf("player : Y: %d | X: %d\n", _map.player.get_y_coord(), _map.player.get_x_coord());

            if (_map.player.get_y_coord() > _map.map_size || _map.player.get_y_coord() == 0 ||
                    _map.player.get_x_coord() > _map.map_size || _map.player.get_x_coord() == 0){
                _map.lelel_up();
                repaint();
                return; }

            for (NPC it : _map.enemies){
                it.move(_map.field, _map.player.get_y_coord(), _map.player.get_x_coord());
                _map.remove_and_set(it.old_coord.y, it.old_coord.x, it.get_y_coord(), it.get_x_coord());
            }
            if (!_map.combat_list.isEmpty())
                set_new_text();
        }
        else{
            if (e.getKeyCode() == KeyEvent.VK_1){
                _map.combat_list.get(0).take_dmg(_map.player.get_attack());
                if (_map.combat_list.get(0).get_hp() <= 0){
                    NPC tmp = _map.combat_list.get(0);
                    _map.player.take_exp(tmp.get_exp());
                    _map.combat_list.remove(tmp);
                    _map.enemies.remove(tmp);
                    _map.field[tmp.get_y_coord()][tmp.get_x_coord()] = 0;
                    tmp = null;
                }
                else{
                    _map.player.take_dmg(_map.combat_list.get(0).get_attack());
                    if (_map.player.get_hp() <= 0){
                        System.out.println("U loose");
                        exit(0);
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_2){
                Random random = new Random();
                int r = random.nextInt(2);
                if (r == 1) {
                    enemy_hp.setText("You left from battle");
                    _map.combat_list.get(0).sleep = 1;
                    _map.combat_list.clear();
                }
                else {
                    _map.player.take_dmg(_map.combat_list.get(0).get_attack());
                }
            }
            set_new_text();
        }

        this.repaint();
        _map.print_field();



    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {


    }
}
