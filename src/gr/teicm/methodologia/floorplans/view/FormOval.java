package gr.teicm.methodologia.floorplans.view;

import gr.teicm.methodologia.floorplans.model.Oval;
import gr.teicm.methodologia.floorplans.model.Shape;
import java.awt.Color;
import java.awt.Point;

public class FormOval implements IMyPanelCheck {

    private final MyPanel myPanel;
    
    @Override
    public boolean check() {
        return(myPanel.getcShape().type == Shape.ShapesType.Oval);
    }

    @Override
    public boolean action(Point new_point) {
        
        myPanel.setImage(myPanel.cloneImage(myPanel.getOrginal_image()));
        int width = new_point.x - myPanel.getcShape().start_point.x;
        int height = new_point.y - myPanel.getcShape().start_point.y;
        Oval x = (Oval)myPanel.getcShape();
        x.width = width;
        x.height = height;
        x.draw(myPanel.getImage().createGraphics(),Color.RED);
        myPanel.repaint();
        
        return true;
        
    }

    public FormOval(MyPanel myPanel)
    {
        this.myPanel = myPanel;
    }
    
}
