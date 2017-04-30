package gr.teicm.methodologia.floorplans.view;

import gr.teicm.methodologia.floorplans.model.Rectangle;
import gr.teicm.methodologia.floorplans.model.Shape;
import java.awt.Point;

public class ShapeFlexibility implements IMyPanelCheck {

    private final MyPanel myPanel;
    
    @Override
    public boolean check()
    {
        return(myPanel.isIsShapePressed() && myPanel.getShapes()[myPanel.getPressedShapeIndex()].type != Shape.ShapesType.Pen);
    }
    
    @Override
    public boolean action(Point new_point)
    {
        myPanel.setImage(myPanel.cloneImage(myPanel.getOrginal_image()));
        int xShift = new_point.x - myPanel.getClickPoint().x;
        int yShift = new_point.y - myPanel.getClickPoint().y;
        myPanel.setClickPoint(new Point(new_point.x, new_point.y));
        if(myPanel.isIsResizeable())
        {
            //resize
            Rectangle r = (Rectangle)myPanel.getShapes()[myPanel.getPressedShapeIndex()];
            r.width += xShift;
            r.height += yShift;
            r.draw(myPanel.getImage().createGraphics());
        }
        else
        {
            //move
            myPanel.getShapes()[myPanel.getPressedShapeIndex()].start_point.x += xShift;
            myPanel.getShapes()[myPanel.getPressedShapeIndex()].start_point.y += yShift;
            myPanel.getShapes()[myPanel.getPressedShapeIndex()].draw(myPanel.getImage().createGraphics());
            //undoableEditListeners = undoableEditSupport.getUndoableEditListeners();
        }
        myPanel.repaint();
        
        return true;
    }
    
    ShapeFlexibility(MyPanel myPanel)
    {
        this.myPanel = myPanel;
    }
}
