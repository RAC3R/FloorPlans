package gr.teicm.methodologia.floorplans.view;

import static gr.teicm.methodologia.floorplans.FloorPlans.FloorPlans.undoManager;
import gr.teicm.methodologia.floorplans.view.MyFrame;
import gr.teicm.methodologia.floorplans.view.MyPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.undo.UndoableEditSupport;

/**
 *
 * @author Tony Driver
 */
public class Undo implements UndoableEditListener {

    UndoableEditEvent ev;
    //UndoManager undoManager = new UndoManager();
    //UndoableEditEvent undoableEditEvent = new UndoableEditEvent(this, (UndoableEdit) ev);
    UndoableEditSupport undoableEditSupport;
    private MyFrame myFrame;
    //UndoAction undoAction = new UndoAction();
    //RedoAction redoAction = new RedoAction();

    public Undo() {
        UndoManager undoManager = new UndoManager();
        UndoableEditEvent undoableEditEvent;
        undoableEditEvent = new UndoableEditEvent(this, (UndoableEdit) ev);
        UndoableEditSupport undoSupport = new UndoableEditSupport();
        ActionListener undoAction = new UndoAction();
        ActionListener redoAction = new RedoAction();
        undoSupport.addUndoableEditListener(new UndoAdapter());
    }

    public void Start(UndoableEditListener ev) {
        undoableEditSupport.addUndoableEditListener(ev);

        undoManager.addEdit((UndoableEdit) ev);
    }

    public void getEvent(UndoableEditEvent evt) {
        //undoableEditSupport.postEdit((UndoableEdit) undoable);
        //undoManager.undoOrRedo();
        //undoManager.addEdit((UndoableEdit) evt);
        //ev.getEdit();
        //undoManager.undoableEditHappened(ev);
        UndoableEdit edit = evt.getEdit();
        undoManager.addEdit(edit);
        //System.out.println(painter.undo());
    }

    public void undo() {
        try {
            undoManager.undo();
        } catch (CannotUndoException ex) {
            System.out.println("Unable to undo: " + ex);
            ex.printStackTrace();
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotUndoException ex) {
            System.out.println("Unable to undo: " + ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent ev) {
        //painter.getComponent(0);
        Arrays.toString(myFrame.getPainter().getComponents());
        System.out.println(Arrays.toString(myFrame.getPainter().getComponents()));
        //ev.getEdit().toString();
        //System.out.println(ev.getEdit().toString());
        /*undoManager.addEdit(ev.getEdit());
        System.out.println(ev);
        System.out.println(undoManager.addEdit(ev.getEdit()));
        IntStream e = ev.getEdit().toString().codePoints();
        undoManager.undoableEditHappened((UndoableEditEvent) e);*/
        //refreshUndoRedo();
        //undoAction.update();
        //redoAction.update();
    }
}

class UndoAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent evt) {
        undoManager.undo();
    }
}

class UndoAdapter implements UndoableEditListener {

    @Override
    public void undoableEditHappened(UndoableEditEvent evt) {
        UndoableEdit edit = evt.getEdit();
        undoManager.addEdit(edit);
    }
}

class RedoAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent evt) {
        undoManager.redo();
    }
}

/*class AddAction extends AbstractAction {

     @Override
     public void actionPerformed(ActionEvent evt) {
         // always add to the end of the JList
         Dimension NumOfElements = painter.getSize();
         // however, give the element its ID number 
         //Object element = new String("Foo " + _lastElementID);
         
         // record the effect
         UndoableEdit edit;
         edit = new AddEdit(painter,painter.getComponent(painter.shapeIndex), painter.shapeIndex);
         // perform the operation
         painter.addElement(painter.shapeIndex);
         
         // notify the listeners
         undoSupport_.postEdit(edit);
          
         // increment the ID
          _lastElementID ++ ;

     }

 }*/