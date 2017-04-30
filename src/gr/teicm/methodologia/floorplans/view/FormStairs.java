package gr.teicm.methodologia.floorplans.view;

import gr.teicm.methodologia.floorplans.model.Shape;
import gr.teicm.methodologia.floorplans.model.Stairs;
import java.awt.Color;
import java.awt.Point;

public class FormStairs implements IMyPanelCheck {
    
    private final MyPanel myPanel;

    @Override
    public boolean check() {
        return(myPanel.getcShape().type == Shape.ShapesType.Stairs);
    }

    @Override
    public boolean action(Point new_point) {
        
        myPanel.setImage(myPanel.cloneImage(myPanel.getOrginal_image()));
        int width = new_point.x - myPanel.getcShape().start_point.x;
        int height = new_point.y - myPanel.getcShape().start_point.y;
        Stairs x = (Stairs)myPanel.getcShape();
        x.width = width;
        x.height = height;
        x.color = Color.BLACK;
        x.draw(myPanel.getImage().createGraphics());
        myPanel.repaint();
        
        return true;
        
    }

    public FormStairs(MyPanel myPanel) {
        this.myPanel = myPanel;
    }
    
}
