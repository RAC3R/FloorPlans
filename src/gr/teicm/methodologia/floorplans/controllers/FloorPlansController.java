package gr.teicm.methodologia.floorplans.controllers;

import gr.teicm.methodologia.floorplans.model.Shape;
import gr.teicm.methodologia.floorplans.view.MyFrame;
import gr.teicm.methodologia.floorplans.view.MyPanel;
import gr.teicm.methodologia.floorplans.view.Save;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

public class FloorPlansController {

    private MyFrame myFrame;
    private Shape theModel;
    Save save = new Save();

    public FloorPlansController(MyFrame myFrame, Shape theModel) {
        this.myFrame = myFrame;
        this.theModel = theModel;

        this.myFrame.addShapeButtonListener(new ShapeButtonListener());
        this.myFrame.addEditorButtonListener(new EditorButtonListener());
    }

    public class ShapeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String shape = ((JButton) ae.getSource()).getText();
            if (null != shape) {
                switch (shape) {
                    case "Pen":
                        myFrame.getPainter().setShape(Shape.ShapesType.Pen);
                        break;
                    case "Cir":
                        myFrame.getPainter().setShape(Shape.ShapesType.Circle);
                        break;
                    case "Rec":
                        myFrame.getPainter().setShape(Shape.ShapesType.Rectangle);
                        break;
                    case "Stairs":
                        myFrame.getPainter().setShape(Shape.ShapesType.Stairs);
                        break;
                    case "Oval":
                        myFrame.getPainter().setShape(Shape.ShapesType.Oval);
                        break;
                    case "Door":
                        myFrame.getPainter().setShape(Shape.ShapesType.Door);
                        break;
                    case "W/dow":
                        myFrame.getPainter().setShape(Shape.ShapesType.Window);
                        break;
                    case "Line":
                        myFrame.getPainter().setShape(Shape.ShapesType.Line);
                        break;
                    default:
                        break;
                }
            }
        }

    }

    public class EditorButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String edit = ((JButton) ae.getSource()).getText();
            if (null != edit) switch (edit) {
                case "Clr":
                    myFrame.getPainter().clear();
                    break;
            //                else if(edit == "Redo")
                case "Undo":
                    myFrame.getPainter().undo();
                    break;
                case "Save":
                    try {
                        save.save();
                    } catch (IOException ex) {
                        Logger.getLogger(FloorPlansController.class.getName()).log(Level.SEVERE, null, ex);
                    }   break;
                /*case "Load":
                    /*try {
                        Save.load();
                    } catch (IOException ex) {
                        Logger.getLogger(FloorPlansController.class.getName()).log(Level.SEVERE, null, ex);
                    }   break;*/
                default:
                    break;
            }
        }

    }
}
