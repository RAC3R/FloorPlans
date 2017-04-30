package gr.teicm.methodologia.floorplans.view;

import gr.teicm.methodologia.floorplans.model.Pen;
import gr.teicm.methodologia.floorplans.model.Shape;
import java.awt.Color;
import java.awt.Point;

public class FormPen implements IMyPanelCheck {

    private final MyPanel myPanel;
    
    @Override
    public boolean check() {
        return(myPanel.getcShape().type == Shape.ShapesType.Pen);
    }
    
    @Override
    public boolean action(Point new_point) {
        
        Pen x = (Pen)myPanel.getcShape();
        x.addPoint(new_point);
        x.draw(myPanel.getImage().createGraphics(),Color.GREEN);
        myPanel.repaint();
        
        return true;
        
    }

    
    FormPen(MyPanel myPanel)
    {
        this.myPanel = myPanel;
    }
    
}
