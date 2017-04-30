package gr.teicm.methodologia.floorplans.view;

import gr.teicm.methodologia.floorplans.model.Rectangle;
import gr.teicm.methodologia.floorplans.model.Shape;
import java.awt.Color;
import java.awt.Point;

public class FormRectangle implements IMyPanelCheck {
    
    private final MyPanel myPanel;

    @Override
    public boolean check() {
        return(myPanel.getcShape().type == Shape.ShapesType.Rectangle);
    }

    @Override
    public boolean action(Point new_point) {
        
        myPanel.setImage(myPanel.cloneImage(myPanel.getOrginal_image()));
        int width = new_point.x - myPanel.getcShape().start_point.x;
        int height = new_point.y - myPanel.getcShape().start_point.y;
        Rectangle x = (Rectangle)myPanel.getcShape();
        x.width = width;
        x.height = height;
        x.color = Color.BLUE;
        x.draw(myPanel.getImage().createGraphics());
        myPanel.repaint();
        
        return true;
    }

    public FormRectangle(MyPanel myPanel)
    {
        this.myPanel = myPanel;
    }
    
    
}
