package cs.pkg493.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author sherman
 */
public class WindowPane extends JComponent {
    CSGame game;
    
    public WindowPane (CSGame params) {
        this.game = params;
        try {
            game.map = ImageIO.read(new File("map3.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(WindowPane.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public void paint (Graphics g) {
        
        Enemy e = game.enemies.get(0);

        g.drawImage(game.map, 0, 0, this);
        g.drawImage(game.plane.pic, game.plane.x, game.plane.y, this);
        g.drawImage(game.enemyMap.getSubimage(e.picx1, e.picy1, e.picx2 - e.picx1, e.picy2 - e.picy1), e.x, e.y, null);
        if (game.plane.fire) {
            g.setColor(Color.YELLOW);
            g.drawLine(game.plane.x, game.plane.y, game.plane.x, 0);
        }
    }
    
    public BufferedImage o (BufferedImage one, BufferedImage two, Enemy e) {
        Graphics2D graphic = one.createGraphics();
        
        graphic.dispose();
        return one;        
    }
         
  /* public void paintComponent (Graphics g, Enemy e) {          
        Graphics2D b = (Graphics2D) game.map.createGraphics();
        b.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        b.drawImage(game.map, 0, 0, this);
        
        b.drawImage(game.enemyMap.getSubimage(e.picx1, e.picy1, e.picx2 - e.picx1, e.picy2 - e.picy1), 0, 0, null);
        //g.drawImage(game.enemyMap, e.picx1, e.picy1, (e.picx2 - e.picx1), (e.picy2 - e.picy1), this);
    } */
}
