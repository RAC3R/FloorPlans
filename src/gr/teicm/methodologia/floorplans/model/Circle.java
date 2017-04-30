package gr.teicm.methodologia.floorplans.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle extends Shape{
    
    public int width,height;
    
    public Circle(int x, int y, int width, int height)
    {
        this.start_point = new Point(x, y);
        this.width = width;
        this.height = height;
        this.color = Color.BLACK;
        this.type = ShapesType.Circle;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        //g.drawOval(this.start_point.x, this.start_point.y, this.width, this.height);
        g.drawOval(this.start_point.x ,this.start_point.y , this.height, this.height);
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
