package cs493project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.CubicCurve2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author sherman
 */
public class WindowPane extends JComponent implements Constants {
    public CSGame game;
    public ArrayList <Projectiles> oob;
    public int neg = -1;
    public boolean flip = true;
    public boolean once = true;
    
    public WindowPane (CSGame params) {
        this.game = params;
        oob = new ArrayList<>();        
    }
    
    public void paintComponent (Graphics g) {
        g.drawImage(game.map, 0, 0, this);

        g.setColor(Color.BLACK);      
        
        g.fillRect(0, YSTATES-40, game.d.width, 10);

        if (game.plane.plane.noPaint) {
            g.setColor(Color.RED);
            if (flip) {
                neg = -1;
                flip = false;                
            } else {
                neg = 0;
                flip = true;
            }
            g.fillOval(game.plane.x, game.plane.y, 30 + (20 *neg ), 30 + (20 *neg));
        } else     
            g.drawImage(game.plane.pic, game.plane.x, game.plane.y, null);
        
        for (int x = 0; x < game.enemies.size(); x++) {
            Enemy e = game.enemies.get(x);
            if (e.oob) {
                game.enemies.remove(x);
                x--;
            }
            else {
                try {
                    game.enemyMap = ImageIO.read(new File("smallestInvaders.jpg"));
                    g.drawImage(game.enemyMap.getSubimage(e.picx1, e.picy1, e.picx2 - e.picx1, e.picy2 - e.picy1), (int) e.path.xPos, (int) e.path.yPos, null);
                } catch (IOException ex) {
                    Logger.getLogger(WindowPane.class.getName()).log(Level.SEVERE, null, ex);
                }            
            }
        }
            
        Font font = new Font ("Sans Serif", Font.PLAIN, 25);
        g.setFont(font);
        g.setColor(Color.ORANGE);
        g.drawString("Lives:", LIFESTRING, YSTATES);
        
        for (int x = 0; x < game.lives; x++) {
            g.fillOval(LIFELOC + (x*25) + 25, YSTATES - 15, 20, 20);
        }
        
        g.drawString("Special:", 300, YSTATES);
        g.drawString("Score:", 800, YSTATES);
        g.drawString(Long.toString(game.score),900, 840);
        g.fillRect(400, 820, game.fillLevel, 20);       
       
        for (int x = 0; x < game.projectiles.size(); x ++) {               
            Projectiles projectile = game.projectiles.get(x);
            if (projectile.update(game.plane.x, game.plane.y, game.plane.pic.getWidth(), game.plane.pic.getHeight())) {
                oob.add(game.projectiles.get(x));
                game.lives--;
                Thread thread = new Thread(game.plane);
                thread.run();
            }
            else {
                if (projectile.enemyFire) {
                    g.setColor(Color.RED);
                    g.fillOval(projectile.x, projectile.y, 7, 7);
                }
                else {
                    g.setColor(Color.YELLOW);
                    g.drawLine(projectile.x, projectile.y, projectile.x, projectile.y-5);
                }
            }            
            if ((game.projectiles.get(x).y <= 0) || (game.projectiles.get(x).x <= 0) || 
                    (game.projectiles.get(x).x >= game.d.width) || (game.projectiles.get(x).y >= game.d.height))
                    oob.add(game.projectiles.get(x));
        }
                
        for (int x = 0; x < oob.size(); x++) {            
            game.projectiles.remove(oob.get(x));
        }
        oob.clear();
    }    
}