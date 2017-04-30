package gr.teicm.methodologia.floorplans.model;

import java.awt.*;

public abstract class Shape {
    public enum ShapesType {Circle,Rectangle,Pen,Door,Line,Oval,Stairs,Window};
    public Color color;
    public Point start_point;
    public ShapesType type;
    
    public void draw(Graphics2D g,Color c)
    {
        this.color = c;
        draw(g);
    }
    
    public abstract void draw(Graphics2D g);
    
    public abstract boolean isIn(Point p);
}
