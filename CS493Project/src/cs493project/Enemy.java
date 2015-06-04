package cs493project;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author sherman
 */
public class Enemy implements Runnable, Constants {
    public int type;
    public int health;
    public int picx1, picx2, picy1, picy2, height, width;  
    public BufferedImage image; 
    public boolean oob;
    public CSGame game;
    public int value;
    public Path path;
    private int attack;
        
    public Enemy (CSGame params) {
        this.game = params;
        this.type = (int) (Math.random() *9);
        this.path = new Path(game.d);
        
        try {
            image = ImageIO.read(new File("smallestInvaders.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (this.type) {
            case 0: 
                picx1 = 13;
                picx2 = 70;
                picy1 = 25;
                picy2 = 63;              
                height = picy2 - picy1;
                width = picx2 - picx1;
                health = 1;
                attack = 35;
                value = 100;
                break;
            case 1:
                picx1 = 75;
                picx2 = 125;
                picy1 = 22;
                picy2 = 63;
                height = picy2 - picy1;
                width = picx2 - picx1;
                health = 2;
                attack = 30;
                value = 200;
                break;
            case 2:
                picx1 = 138;
                picx2 = 182;
                picy1 = 25;
                picy2 = 60;
                height = picy2 - picy1;
                width = picx2 - picx1;
                health = 4;
                attack = 25;
                value = 300;
                break;
            case 3:
                picx1 = 19;
                picx2 = 66;
                picy1 = 77;
                picy2 = 120;
                height = picy2 - picy1;
                width = picx2 - picx1;
                health = 8;
                attack = 20;
                value = 400;
                break;
            case 4:
                picx1 = 76;
                picx2 = 126;
                picy1 = 78;
                picy2 = 120;
                height = picy2 - picy1;
                width = picx2 - picx1;
                health = 10;
                attack = 15;
                value = 500;
                break;
            case 5:
                picx1 = 138;
                picx2 = 186;
                picy1 = 85;
                picy2 = 115;
                height = picy2 - picy1;
                width = picx2 - picx1;
                health = 8;
                attack = 20;
                value = 440;
                break;
            case 6:
                picx1 = 19;
                picx2 = 66;
                picy1 = 142;
                picy2 = 175;
                height = picy2 - picy1;
                width = picx2 - picx1;
                health = 4;
                attack = 25;
                value = 330;
                break;
            case 7:
                picx1 = 75;
                picx2 = 130;
                picy1 = 140;
                picy2 = 177;
                height = picy2 - picy1;
                width = picx2 - picx1;
                health = 2;
                attack = 30;
                value = 220;
                break;
            case 8:
                picx1 = 141;
                picx2 = 182;
                picy1 = 140;
                picy2 = 178;
                height = picy2 - picy1;
                width = picx2 - picx1;
                health = 1;
                attack = 35;
                value = 110;
                break;
            default:
                picx1 = 0;
                picx2 = 0;                      
                picy1 = 0;
                picy2 = 0;
                height = picy2 - picy1;
                width = picx2 - picx1;
                break; 
        }
    }  
    
    public boolean update(int x) {        
        Projectiles projectile;
        for (int y = 0; y < game.projectiles.size(); y++) {
            projectile = game.projectiles.get(y);
            if (!projectile.enemyFire) {
                if ((projectile.y >= this.path.yPos) && (projectile.y <= this.path.yPos + this.height))
                    if ((projectile.x >= this.path.xPos) && (projectile.x <= this.path.xPos + this.width)) {
                        health--;
                        game.projectiles.remove(y);
                        y = game.projectiles.size();
                        if (health == 0) {
                            game.score += game.enemies.get(x).value;
                            game.enemies.remove(x);                                                        
                        }
                    }
            }
        }
        
        if ((this.path.yPos +this.height < 0) || (this.path.xPos + this.width < 0) || (this.path.yPos > YSTATES -40) || (this.path.xPos >game.d.width))
                this.oob = true;
              
        path.update();
        
        if (((int) (Math.random()*39)) > attack)
            return true;
        else 
            return false;
    }    
    
    public void run() {
        while (game.lives > 0) {
            switch ((int) (Math.random()*2)) {
                case 0:
                    if (game.enemies.size() < 10) 
                        game.enemies.add(new Enemy(game));
                    break;
                case 1:
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}