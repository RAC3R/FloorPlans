/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teicm.methodologia.floorplans.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Tony-Driver
 */
public class Oval extends Shape{
     public int width,height;
    
    public Oval(int x, int y, int width, int height)
    {
        this.start_point = new Point(x, y);
        this.width = width;
        this.height = height;
        this.color = Color.BLACK;
        this.type = ShapesType.Oval;
    }

     @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawOval(this.start_point.x, this.start_point.y, this.width, this.height);
    }

     @Override
    public boolean isIn(Point p) {
        if(p.x > this.start_point.x &&
                p.y > this.start_point.y &&
                p.x < this.width + this.start_point.x &&
                p.y < this.height + this.start_point.y)
            return true;
        return false;
    }
}
