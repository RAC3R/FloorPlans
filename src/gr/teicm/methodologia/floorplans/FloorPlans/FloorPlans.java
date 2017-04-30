package gr.teicm.methodologia.floorplans.FloorPlans;

//import gr.teicm.methodologia.floorplans.view.Save;
import gr.teicm.methodologia.floorplans.view.Undo;
import gr.teicm.methodologia.floorplans.controllers.FloorPlansController;
import gr.teicm.methodologia.floorplans.model.Shape;
import gr.teicm.methodologia.floorplans.view.MyFrame;
import javax.swing.undo.UndoManager;

public class FloorPlans {
    
    public static UndoManager undoManager = new UndoManager();
    static Undo und = new Undo();
    //static Save save = new Save();
    
    
    public static void main(String[] args) {
        
        UndoManager manager = new UndoManager();
        MyFrame myFrame = new MyFrame("MyFloorPlan");
        
        FloorPlansController theController = new FloorPlansController(myFrame, null);
        
        myFrame.setVisible(true);
    }

}
