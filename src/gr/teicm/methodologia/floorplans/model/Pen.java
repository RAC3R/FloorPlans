package gr.teicm.methodologia.floorplans.model;

import gr.teicm.methodologia.floorplans.model.Shape;
import java.awt.*;

public class Pen extends Shape{
    
    public Point[] points = new Point[1000];
    private int index = 0;
    
    public Pen(int x, int y)
    {
        this.start_point = new Point(x, y);
        this.color = Color.BLACK;
        this.type = ShapesType.Pen;
    }

    public boolean addPoint(Point p)
    {
        if(index < points.length)
        {
            points[index++] = new Point(p.x, p.y);
            return true;
        }
        return false;
    }
            
    public void drawAll(Graphics2D g)
    {
        g.setColor(color);
        g.drawLine(start_point.x, start_point.y, points[0].x, points[0].y);
        for(int i=1;i<index;i++)
        {
            g.drawLine(points[i-1].x, points[i-1].y, points[i].x, points[i].y);
        }
    }

    public void draw(Graphics2D g)
    {
        g.setColor(color);
        if(index == 1)
            g.drawLine(start_point.x, start_point.y, points[0].x, points[0].y);
        else
            g.drawLine(points[index-2].x, points[index-2].y, points[index-1].x, points[index-1].y);
    }
    
    public boolean isIn(Point p) {
        for(int i=1;i<index;i++)
        {
            if(p.x == points[i].x && p.y == points[i].y)
            return true;
        }
        return false;
    }
}
