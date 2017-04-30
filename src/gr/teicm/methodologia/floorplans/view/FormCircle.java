package gr.teicm.methodologia.floorplans.view;

import gr.teicm.methodologia.floorplans.model.Circle;
import gr.teicm.methodologia.floorplans.model.Shape;
import java.awt.Color;
import java.awt.Point;

public class FormCircle implements IMyPanelCheck {
    
    private final MyPanel myPanel;

    @Override
    public boolean check() {
        return(myPanel.getcShape().type == Shape.ShapesType.Circle);
    }

    
    @Override
    public boolean action(Point new_point) {
        
        myPanel.setImage(myPanel.cloneImage(myPanel.getOrginal_image()));
        int width = new_point.x - myPanel.getcShape().start_point.x;
        int height = new_point.y - myPanel.getcShape().start_point.y;
        Circle x = (Circle)myPanel.getcShape();
        x.width = width;
        x.height = height;
        x.draw(myPanel.getImage().createGraphics(),Color.RED);
        myPanel.repaint();
        
        return true;
        
    }

    FormCircle(MyPanel myPanel)
    {
        this.myPanel = myPanel;
    }
    
}
