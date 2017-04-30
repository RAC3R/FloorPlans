package gr.teicm.methodologia.floorplans.view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame{
    
    private JButton circleButton = new JButton("Cir");
    private JButton rectangleButton = new JButton("Rec");
    private JButton clearButton = new JButton("Clr");
    private JButton penButton = new JButton("Pen");
    private JButton undoButton = new JButton("Undo");
    private JButton redoButton = new JButton("Redo");
    private JButton lineButton = new JButton("Line");
    private JButton stairsButton = new JButton("Stairs");
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");
    private JButton ovalButton = new JButton("Oval");
    private JButton doorButton = new JButton("Door");
    private JButton windowButton = new JButton("W/dow");
    static MyPanel painter = new MyPanel();
    
    public MyFrame(String title)
    {
        super(title);
        JPanel floorPlanPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        circleButton.setSize(70, 70);
        circleButton.setLocation(10, 10);

        rectangleButton.setSize(70, 70);
        rectangleButton.setLocation(90, 10);

        clearButton.setSize(70, 70);
        clearButton.setLocation(170, 10);

        penButton.setSize(70, 70);
        penButton.setLocation(250, 10);
        
        undoButton.setSize(70, 70);
        undoButton.setLocation(330, 10);
        
        redoButton.setSize(70, 70);
        redoButton.setLocation(410, 10);
        
        lineButton.setSize(70, 70);
        lineButton.setLocation(490, 10);
        
        stairsButton.setSize(70, 70);
        stairsButton.setLocation(570, 10);
        
        saveButton.setSize(70, 70);
        saveButton.setLocation(650, 10);
        
        ovalButton.setSize(70, 70);
        ovalButton.setLocation(730, 10);
        
        doorButton.setSize(70, 70);
        doorButton.setLocation(810, 10);
        
        windowButton.setSize(70, 70);
        windowButton.setLocation(890, 10);
        
        loadButton.setSize(70, 70);
        loadButton.setLocation(970, 10);
        
        painter.setSize(800, 800);
        painter.setLocation(10, 100);
        
        floorPlanPanel.setLayout(null);
        
        floorPlanPanel.add(circleButton);
        floorPlanPanel.add(rectangleButton);
        floorPlanPanel.add(clearButton);
        floorPlanPanel.add(penButton);
        floorPlanPanel.add(undoButton);
        floorPlanPanel.add(redoButton);
        floorPlanPanel.add(lineButton);
        floorPlanPanel.add(stairsButton);
        floorPlanPanel.add(saveButton);
        floorPlanPanel.add(ovalButton);
        floorPlanPanel.add(doorButton);
        floorPlanPanel.add(windowButton);
        floorPlanPanel.add(loadButton);        
        
        this.add(floorPlanPanel);
        
        floorPlanPanel.add(painter);
        
    }
    
    public void addShapeButtonListener(ActionListener listenerForShapeButtons)
    {
        circleButton.addActionListener(listenerForShapeButtons);
        rectangleButton.addActionListener(listenerForShapeButtons);
        penButton.addActionListener(listenerForShapeButtons);
        lineButton.addActionListener(listenerForShapeButtons);
        stairsButton.addActionListener(listenerForShapeButtons);
        ovalButton.addActionListener(listenerForShapeButtons);
        doorButton.addActionListener(listenerForShapeButtons);
        windowButton.addActionListener(listenerForShapeButtons);
    }
    
    public void addEditorButtonListener(ActionListener listenerForEditorButtons)
    {
        clearButton.addActionListener(listenerForEditorButtons);
        undoButton.addActionListener(listenerForEditorButtons);
        redoButton.addActionListener(listenerForEditorButtons);
        saveButton.addActionListener(listenerForEditorButtons);
        loadButton.addActionListener(listenerForEditorButtons);
    }

    public static MyPanel getPainter() {
        return painter;
    }
    
}