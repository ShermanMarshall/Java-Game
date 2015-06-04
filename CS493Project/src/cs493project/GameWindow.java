package cs493project;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Circle;
import javax.swing.JFrame;

/**
 *
 * @author sherman
 */
public class GameWindow extends JFrame implements Runnable, Constants {
    public CSGame game;
    public WindowPane pane;
    public Graphics g;
    public String score;
    public Rectangle special;
    public Circle lives;
    

    public GameWindow(CSGame params) {
        super(params.title);
        this.game = params;
        Toolkit tools = Toolkit.getDefaultToolkit();       
        this.game.d  = tools.getScreenSize();               
        this.setBounds(0,0, game.d.width, game.d.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane = new WindowPane (game);
        this.addKeyListener(new Listen());
        this.getContentPane().add(pane);
        this.setVisible(true);
        this.setResizable(false);
    } 

    @Override
    public void run() {
        g = this.getGraphics();
        while (game.lives > 0){
            if (game.fillLevel < game.fillMax)
                game.fillLevel += 5;            
        for (int x = 0, y = game.enemies.size(); x < game.enemies.size(); x++) {
            if (game.enemies.get(x).update(x)) {
                if (y == game.enemies.size())
                    game.projectiles.add(new Projectiles(game.plane.x, game.plane.y, 
                            (int) game.enemies.get(x).path.xPos + game.enemies.get(x).width / 2, 
                                (int) game.enemies.get(x).path.yPos + game.enemies.get(x).height, 0, 0, true));
                else  {
                    x--;
                    y = game.enemies.size();
                }
            }                
        }               
        pane.repaint();
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
    }  
    
    public class Listen implements KeyListener, Runnable {
        
        boolean a, A; 
        boolean d, D; 
        boolean w, W; 
        boolean s, S;
        char direction;
        
        public Listen() {
            a = d = w = s = false;
        }
public void run() {
            switch (direction) {
                case 97:
                    this.a = true;
                    while (this.a) {
                        if (game.plane.x > 0)
                            game.plane.x--;
                        try {     
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            System.err.println(ex);
                        }
                    }
                    break;
                case 100:
                    this.d = true;
                    while (this.d) {
                        if (game.plane.x + game.plane.pic.getWidth()  < game.d.width)
                            game.plane.x++;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            System.err.println(ex);
                        }
                    }
                    break;
                case 119:
                    this.w = true;
                    while (this.w) {
                        if (game.plane.y > 0)
                            game.plane.y--;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            System.err.println(ex);
                        }
                    }
                    break;
                case 115:
                    this.s = true;                    
                    while (this.s) {
                        if (game.plane.y + game.plane.pic.getHeight() < 800)
                            game.plane.y++;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            System.err.println(ex);
                        }
                    }
                    break;
                case 32:
                    game.plane.fire = true;
                    while (game.plane.fire) {                        
                        try {
                            switch (game.plane.strength) {
                                case MIN:
                                    game.projectiles.add(new Projectiles(game.plane.x+30, game.plane.y, 0, 0, game.plane.strength, game.plane.weaponType, false));
                                    break;
                                case MID:
                                    game.projectiles.add(new Projectiles(game.plane.x+20, game.plane.y, 0, 0, game.plane.strength, game.plane.weaponType, false));
                                    game.projectiles.add(new Projectiles(game.plane.x+40, game.plane.y, 0, 0, game.plane.strength, game.plane.weaponType, false));
                                    break;
                                case MAX:
                                    game.projectiles.add(new Projectiles(game.plane.x+25, game.plane.y, 0, 0, game.plane.strength, game.plane.weaponType, false));
                                    game.projectiles.add(new Projectiles(game.plane.x+35, game.plane.y, 0, 0, game.plane.strength, game.plane.weaponType, false));
                                    game.projectiles.add(new Projectiles(game.plane.x, game.plane.y, 0, 0, game.plane.strength, game.plane.weaponType, false));
                                    game.projectiles.add(new Projectiles(game.plane.x+game.plane.width, game.plane.y, 0, 0, game.plane.strength, game.plane.weaponType, false));
                                    break;
                                default:
                                    System.out.println("figure this out");
                                    break;                                   
                            }
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            System.err.println(ex);
                        }
                    }
                    break;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) { }
            
        @Override
        public void keyPressed(KeyEvent e) {    
           Thread unnecessary = new Thread (this);
           switch (e.getKeyChar()) {
                case 'a' | 'A':
                    direction = 'a';
                    if (!a)
                        unnecessary.start();
                    break;
                case 'd' | 'D':
                    direction = 'd';
                    if (!d)
                        unnecessary.start();
                    break;
                case 'w'  | 'W':
                    direction = 'w';                 
                    if (!w)
                        unnecessary.start();
                    break;
                case 's' | 'S':
                    direction = 's';
                    if (!s)
                        unnecessary.start();
                    break;
                case 32:                    
                    direction = 32;
                    if (!game.plane.fire)
                        unnecessary.start();
                    break;
                }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyChar()) {
                case 'a' | 'A':
                    a = false;
                    break;
                case 'd' | 'D':
                    d= false;
                    break;
                case 'w' | 'W':
                    w = false;
                    break;
                case 's' | 'S':
                    s= false;
                    break; 
                case 32:
                    game.plane.fire = false;
                    break;
            }
        }        
    }
}