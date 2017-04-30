/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teicm.methodologia.floorplans.model;

/**
 *
 * @author Tony-Driver
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;



public class Window extends Shape {

    public int width, height;

    public Window(int x, int y, int width, int height) {
        this.start_point = new Point(x, y);
        this.width = width;
        this.height = height;
        //this.color = Color.BLACK;
        this.type = Shape.ShapesType.Window;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        //g.fillRect(240, 130, 40, 40);
        g.setColor(Color.black);
        g.drawRect(this.start_point.x, this.start_point.y, 40, 40);
        g.drawLine(this.start_point.x+20, this.start_point.y, this.start_point.x+20, this.start_point.y+40);
        g.drawLine(this.start_point.x, this.start_point.y+20, this.start_point.x+40, this.start_point.y+20);
    }

    @Override
    public boolean isIn(Point p) {
        if (p.x > this.start_point.x
                && p.y > this.start_point.y
                && p.x < this.width + this.start_point.x
                && p.y < this.height + this.start_point.y) {
            return true;
        }
        return false;
    }

}
