/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs493project;

import java.awt.Dimension;

/**
 *
 * @author sherman
 */
public class Path implements Constants {
    public double delta, xPos, yPos, redirect, 
                theta, deltaX, deltaY, arcAngle;
    public Dimension dims;
    public Enemy enemy;
    public boolean curve, negative;
      
    public Path (Dimension d) {
        redirect = 3;
        dims = d;
        arcAngle = 0;
        
        switch ((int)(Math.random()*2)) {
            case 0:
                delta = 15;
                xPos = (int) (Math.random() *dims.width );
                yPos = (int) (Math.random() * (100));
                break;
            case 1:
                delta = 15;
                yPos = (int) (Math.random() * (dims.height - 300));
                xPos = (int) (Math.random() * (dims.width));
                break;            
            default:
                delta = 15;
                xPos = (int) (Math.random() * dims.width );
                yPos = 100;
                break;
        }        
        
        System.out.println(Integer.toString((int)xPos ) + ", " + Integer.toString((int) yPos));

        switch ((int) (Math.random()*2)) {
            case  0:
                curve = false;
                break;
            case 1:
                curve = true;
                break;
            default:
                curve = false;
                break;                
        }                 
        
        switch ((int) (Math.random()*2)) {
            case 0:
                negative = false;
                break;
            case 1:
                negative = true;
                break;
            default:
                negative = false;
                break;                
        }
        
        if (!curve) {
            if (yPos < 10) {
              if (xPos < dims.width/2)
                  theta = ((int) (Math.random()*90) + 240);
              else 
                  theta = ((int) (Math.random()*90) + 210);
              
              deltaX = EPROSPEED * (Math.sin(Math.toRadians(theta)));
              deltaY = EPROSPEED * (Math.cos(Math.toRadians(theta)));
              
              if (negative) {
                  switch ((int) Math.random()*3) {
                      case 0:
                          deltaX *= -1;
                          break;
                      case 1:
                          deltaY *= -1;
                          break;
                      case 2:
                          deltaX *= -1;
                          deltaY *= -1;
                          break;
                      default:
                          break;
                  }
              }
                  
            } else {
                if (yPos > (dims.height - 300)/ 2)
                    theta = ((int) (Math.random()*60) - 90);
                else
                    theta = ((int) (Math.random()*30) - 90);
                
                deltaX = EPROSPEED * (Math.cos(Math.toRadians(theta)));
                deltaY = EPROSPEED * (Math.sin(Math.toRadians(theta)));
                
                 if (negative) {
                  switch ((int) Math.random()*3) {
                      case 0:
                          deltaX *= -1;
                          break;
                      case 1:
                          deltaY *= -1;
                          break;
                      case 2:
                          deltaX *= -1;
                          deltaY *= -1;
                          break;
                      default:
                          break;
                  }
              }
                 
            }
        } else {
            if (yPos == 0) {
                if (xPos < dims.width/2)
                    theta = ((int) (Math.random()*90) + 220);
                else 
                    theta = ((int) (Math.random()*75) -30);
                
                deltaX = EPROSPEED * (Math.sin(Math.toRadians(theta)));
                deltaY = EPROSPEED * (Math.cos(Math.toRadians(theta)));
            } else {
                if (yPos > (dims.height - 300) /2) 
                    theta = ((int) (Math.random()*60) - 90);
                else
                    theta = ((int) (Math.random()*30) - 90);
                
                deltaX = EPROSPEED * (Math.cos(Math.toRadians(theta)));
                deltaY = EPROSPEED * (Math.sin(Math.toRadians(theta)));
            }
        }
    /*    System.out.println("Xint: " + xPos);
        System.out.println("Yint: " + yPos);
        System.out.println("Theta: " + theta);
        System.out.println("Curved: " + curve);
        System.out.println("DeltaX: " + deltaX);
        System.out.println("DeltaY: " + deltaY);
*/
    }    
    
public Path (Path former) {
    enemy = former.enemy;
    xPos = former.xPos;
    yPos = former.yPos;
    dims = former.dims;
    arcAngle = 0;
    redirect--;
    
    switch ((int) (Math.random()*2)) {
            case  0:
                curve = false;
                break;
            case 1:
                curve = true;
                break;
            default:
                curve = false;
                break;                
        }        
        
        if (!curve) {
            if (yPos == 0) {
              if (xPos < dims.width/2)
                  theta = ((int) (Math.random()*90) + 240);
              else 
                  theta = ((int) (Math.random()*90) + 210);
           
              deltaX = EPROSPEED *(Math.sin(Math.toRadians(theta)));
              deltaY = EPROSPEED * (Math.cos(Math.toRadians(theta)));
            } else {
                if (yPos > (dims.height - 300)/ 2)
                    theta = ((int) (Math.random()*60) - 90);
                else
                    theta = ((int) (Math.random()*30) - 90);
                
                deltaX  = EPROSPEED * (Math.cos(Math.toRadians(theta)));
                deltaY = EPROSPEED * (Math.sin(Math.toRadians(theta)));
            }
        } else {
            if (yPos == 0) {
                if (xPos < dims.width/2)
                    theta = ((int) (Math.random()*90) + 220);
                else 
                    theta = ((int) (Math.random()*75) -30);
                
                deltaX = EPROSPEED * (Math.sin(Math.toRadians(theta)));
                deltaY = EPROSPEED * (Math.cos(Math.toRadians(theta)));
            } else {
                if (yPos > (dims.height - 300) /2) 
                    theta = ((int) (Math.random()*60) - 90);
                else
                    theta = ((int) (Math.random()*30) - 90);
                
                deltaX = EPROSPEED * (Math.cos(Math.toRadians(theta)));
                deltaY = EPROSPEED *  (Math.sin(Math.toRadians(theta)));
            }
        }
}

public void update() {
    if (curve) {
        arcAngle += 5;
        if ((theta > 45) && (theta < 135)) {
            xPos += (EPROSPEED/5) * (Math.sin(Math.toRadians(arcAngle)));
            yPos += (EPROSPEED/5) * (Math.cos(Math.toRadians(arcAngle)));
        } else {
            xPos += (EPROSPEED/5) *(Math.cos(Math.toRadians(arcAngle)));
            yPos += (EPROSPEED/5) *(Math.sin(Math.toRadians(arcAngle)));
        }
    } else {
            xPos += deltaX;
            yPos += deltaY;
    }
}
}