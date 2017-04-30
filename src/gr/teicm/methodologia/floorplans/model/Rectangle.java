package gr.teicm.methodologia.floorplans.model;

import gr.teicm.methodologia.floorplans.model.Shape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rectangle extends Shape{
    
    public int width,height;
    
    public Rectangle(int x, int y, int width, int height)
    {
        this.start_point = new Point(x, y);
        this.width = width;
        this.height = height;
        this.color = Color.BLACK;
        this.type = ShapesType.Rectangle;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawRect(this.start_point.x, this.start_point.y, this.width, this.height);
    }

    public boolean isIn(Point p) {
        if(p.x > this.start_point.x &&
                p.y > this.start_point.y &&
                p.x < this.width + this.start_point.x &&
                p.y < this.height + this.start_point.y)
            return true;
        return false;
    }
    
}
