/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs.pkg493.project;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author sherman
 */
public class GameWindow extends JFrame implements Runnable {
    public CSGame game;
    public int mapHeight = 1000;
    public int mapWidth = 500;
    public WindowPane pane;
    public Image image;
    public Graphics g;

    public GameWindow(String name, CSGame params) {
        super(name);
        this.game = params;
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension d = tools.getScreenSize();
        this.setBounds(5,5, d.width, d.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane = new WindowPane (game);
        g = pane.getGraphics();
        this.addKeyListener(new Listen());
        this.getContentPane().add(pane);
        this.setVisible(true);
        this.setResizable(false);
    } 

    @Override
    public void run() {
        g = this.getGraphics();
        while (true){
        game.enemies.get(0).y += 6;
        game.enemies.get(0).x += 3;
        
        if (game.enemies.get(0).y > 999)
            game.enemies.get(0).y = 0;
        if (game.enemies.get(0).x > 999)
            game.enemies.get(0).x = 0;
        this.paint(g);
            try {
                sleep(70);
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
    }
    
    public class mListen implements MouseMotionListener {
            boolean moved = false;
            int x, y;
            
        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (!moved) {
                moved = true;
                x = e.getX();
                y = e.getY();
            }
            else {
                if (e.getX() - x > 0)
                    game.plane.x ++;
                if (e.getY() - y > 0)
                    game.plane.y++;
                if (e.getX() - x < 0)
                    game.plane.x--;
                if (e.getY() - y < 0)
                    game.plane.y--;
            }
        }        
    }
    
    public class Listen implements KeyListener {
        Navigator dirs;
    public class Navigator implements Runnable {
        boolean a, A; 
        boolean d, D; 
        boolean w, W; 
        boolean s, S;
        char direction;
        CSGame game;
        
        public Navigator (CSGame g) {
            this.a = this.A = false;
            this.d = this.D = false;
            this.w = this.W = false;
            this.s = this.S = false;
            this.game = g;   
        }
        
        public void run() {
            switch (direction) {
                case 'a' |'A':
                    this.A = true;
                    while (this.a) {
                        game.plane.x--;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            System.err.println(ex);
                        }
                    }
                    break;
                case 'd' | 'D':
                    this.D = true;
                    while (this.d) {
                        game.plane.x++;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            System.err.println(ex);
                        }
                    }
                    break;
                case 'w' | 'W':
                    this.W = true;
                    while (this.w) {
                        game.plane.y--;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            System.err.println(ex);
                        }
                    }
                    break;
                case 's' | 'S':
                    this.S = true;
                    while (this.s) {
                        game.plane.y++;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            System.err.println(ex);
                        }
                    }
                    break;
            }
        }
    }
    public Listen() {
        dirs = new Navigator(game);
    }

        @Override
        public void keyTyped(KeyEvent e) { }
            
        @Override
        public void keyPressed(KeyEvent e) {    
           Thread unnecessary = new Thread (dirs);
           switch (e.getKeyChar()) {
                case 'a' | 'A':
                    dirs.direction = 'a';
                    dirs.a = true;
                    if (!dirs.A)
                        unnecessary.start();
                    break;
                case 'd' | 'D':
                    dirs.direction = 'd';
                    dirs.d = true;
                    if (!dirs.D)
                        unnecessary.start();
                    break;
                case 'w' | 'W':
                    dirs.direction = 'w';
                    dirs.w = true;
                    if (!dirs.W)
                        unnecessary.start();
                    break;
                case 's' | 'S':
                    dirs.direction = 's';
                    dirs.s = true;
                    if (!dirs.S)
                        unnecessary.start();
                    break;
                case 32:
                    game.plane.fire = true;
                    break;
           }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyChar()) {
                case 'a' | 'A':
                    dirs.a = dirs.A = false;
                    break;
                case 'd' | 'D':
                    dirs.d = dirs.D = false;
                    break;
                case 'w' | 'W':
                    dirs.w = dirs.W = false;
                    break;
                case 's' | 'S':
                    dirs.s = dirs.S = false;
                    break; 
                case 32:
                    game.plane.fire = false;
                    break;
            }
                                                }
        
    }

    public static void main(String[] args) {
        CSGame game = new CSGame();
        GameWindow b = new GameWindow("Test", game);
        Graphics g = b.getGraphics();
        Thread thread = new Thread(b);
        thread.start();

        //b.g = b.pane.getGraphics();
        //b.pane.o(game.map, game.enemyMap, bob);
        
    }
}
