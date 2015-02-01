/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs.pkg493.project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sherman
 */
public class Coords {
    public int x, y;
    public boolean sleep;
    public Coords ref;
    
    public Coords(GameWindow window) {
        ref = this;
        window.addKeyListener(new Listen(ref));
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int point) {
        this.x = point;
    }
    
    public void setY(int point) {
        this.y = point;
    }
    
    public class Listen implements KeyListener {
        protected Coords ref;
        public Listen(Coords coords) {
            ref = coords;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            switch (e.getKeyChar()) {
                case 'a': 
                    ref.x -= 1;  
                    break;
                case 'A':
                    ref.x -= 1;
                    break;
                case 'w':
                    ref.y += 1;
                    break;
                case 'W':
                    ref.y += 1;
                    break;
                case 's':
                    ref.y -= 1;
                    break;
                case 'S':
                    ref.y -= 1;
                    break;
                case 'd':
                    ref.x += 1;
                    break;
                case 'D':
                    ref.x += 1;
                default:                  
            }
            
            
        }

        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
        
    }
}
