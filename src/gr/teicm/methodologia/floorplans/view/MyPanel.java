package gr.teicm.methodologia.floorplans.view;

import gr.teicm.methodologia.floorplans.model.Pen;
import gr.teicm.methodologia.floorplans.model.Rectangle;
import gr.teicm.methodologia.floorplans.model.Circle;
import gr.teicm.methodologia.floorplans.model.Line;
import gr.teicm.methodologia.floorplans.model.Oval;
import gr.teicm.methodologia.floorplans.model.Door;
import gr.teicm.methodologia.floorplans.model.Window;
import gr.teicm.methodologia.floorplans.model.Stairs;
import gr.teicm.methodologia.floorplans.model.Shape;
import gr.teicm.methodologia.floorplans.model.Shape.ShapesType;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Vector;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.List;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.undo.UndoableEditSupport;

public class MyPanel extends JPanel{
    
    private  static  Shape.ShapesType shape;
    private BufferedImage image,orginal_image;
    private Shape cShape;
    private Shape[] shapes = new Shape[1000];
    private int shapeIndex = 0;
    private Point clickPoint;
    private boolean isShapePressed = false;
    private boolean isResizeable = false;
    private int pressedShapeIndex;
    protected static Vector pointVector = new Vector();
    private List ListShapes =null;
    Point new_point;
    UndoManager undo = new UndoManager();
    UndoableEditSupport undoableEditSupport;
    UndoableEditListener undoable;
    UndoableEditEvent evt;
    UndoableEdit und;
    UndoableEditListener[] undoableEditListeners;
    private List<IMyPanelCheck> myPanelCheckers;
    
    
    MouseMotionListener mml = new MouseMotionListener() {
        
        @Override
        public void mouseDragged(MouseEvent me)
        {
//            undoableEditSupport.addUndoableEditListener(undo);
            Point new_point = new Point(me.getX(), me.getY());
            for(IMyPanelCheck myPanelChecker : myPanelCheckers)
            {
                if(myPanelChecker.check())
                {
                    myPanelChecker.action(new_point);
                    //pointVector.add(sh);
                    //myPanelCheckers.add(shapeIndex, myPanelChecker);
                }
            }
            
            try {
                for (int i = 0; i < shapeIndex; i++)
                {
                    //myPanelCheckers.add(e);
                    undoableEditSupport.postEdit(undo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }   

        @Override
        public void mouseMoved(MouseEvent me) {
            boolean isIn = false;
            int index=-1;
            for(int i=0;i<shapeIndex;i++)
            {
                if(shapes[i].isIn(new Point(me.getX(), me.getY())))
                    isIn = true;
                    index = i;
                    break;
            }
            if(isIn)
            {
                if(shapes[index].type == ShapesType.Rectangle)
                {
                    Rectangle z = (Rectangle)shapes[index];                        
                    Point end_point = new Point(z.start_point.x + z.width, z.start_point.y + z.height);
                    if(me.getX() > end_point.x-10 && me.getY() > end_point.y-10)
                        setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                    else
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
                else
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    
    };
    MouseListener ml = new MouseListener() {
        
        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            boolean isIn = false;
            for(int i=0;i<shapeIndex;i++)
            {
                if(shapes[i].isIn(new Point(me.getX(), me.getY())))
                {    
                    isIn = true;
                    isShapePressed = true;
                    pressedShapeIndex = i;
                    break;
                }
            }
            if(isIn)
            {
                isResizeable = false;
                clickPoint = new Point(me.getX(), me.getY());
                setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                orginal_image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
                for(int i=0;i<shapeIndex;i++)
                {
                    if(i!=pressedShapeIndex)
                    {
                        if(shapes[i].type == ShapesType.Pen)
                        {
                            Pen x = (Pen)shapes[i];
                            x.drawAll(orginal_image.createGraphics());
                        }
                        else
                            shapes[i].draw(orginal_image.createGraphics());
                    }
                }
                
                if(shapes[pressedShapeIndex].type == ShapesType.Rectangle)
                {
                    //Resize or Move (Rectangle)
                    Rectangle z = (Rectangle)shapes[pressedShapeIndex];
                    Point end_point = new Point(z.start_point.x + z.width, z.start_point.y + z.height);
                    if(me.getX() > end_point.x-10 && me.getY() > end_point.y-10)
                    {
                        setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                        isResizeable = true;
                    }
                }
                
            }
            else
            { 
                isShapePressed = false;
                pressedShapeIndex = -1;
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                orginal_image = cloneImage(image);

                if(null != shape)
                    switch (shape) {
                        case Pen:
                            cShape = new Pen(me.getX(), me.getY());
                            break;
                        case Circle:
                            cShape = new Circle(me.getX(), me.getY(), 0, 0);
                            break;
                        case Rectangle:
                            cShape = new Rectangle(me.getX(), me.getY(), 0, 0);
                            break;
                        case Line:
                            cShape = new Line(me.getX(), me.getY(), 0, 0);
                            break;
                        case Stairs:
                            cShape = new Stairs(me.getX(), me.getY(), 0, 0);
                            break;
                        case Oval:
                            cShape = new Oval(me.getX(), me.getY(), 0, 0);
                            break;
                        case Door:
                            cShape = new Door(me.getX(), me.getY(), 0, 0);
                            break;
                        case Window:
                            cShape = new Window(me.getX(), me.getY(), 0, 0);
                            break;
                        default:
                            break;
                    }
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if(!isShapePressed)
                shapes[shapeIndex++] = cShape;
            else
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            
        }

        @Override
        public void mouseExited(MouseEvent me) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }  
    };
        
    public MyPanel()
    {
        super();
        this.setBackground(Color.WHITE);
        this.addMouseListener(ml);
        this.addMouseMotionListener(mml);
        UndoManager undo = new UndoManager();
        undo = new UndoManager();
        undoableEditSupport = new UndoableEditSupport();
        undoableEditSupport.addUndoableEditListener(new UndoAdapter());
        
        myPanelCheckers = new ArrayList<>();
        
        myPanelCheckers.add(new ShapeFlexibility(this));
        myPanelCheckers.add(new FormPen(this));
        myPanelCheckers.add(new FormCircle(this));
        myPanelCheckers.add(new FormDoor(this));
        myPanelCheckers.add(new FormRectangle(this));
        myPanelCheckers.add(new FormOval(this));
        myPanelCheckers.add(new FormLine(this));
        myPanelCheckers.add(new FormStairs(this));
        myPanelCheckers.add(new FormWindow(this));
    }
    
    public void setShape(ShapesType shape)
    {
        this.shape = shape;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(image == null)
            image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
        g.drawRect(1, 1, this.getWidth()-2, this.getHeight()-2);
        g.drawImage(image, 0, 0, null);
    }
    
    public void clear()
    {
        image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
        shapeIndex = 0;
        repaint();
    }
    
    public void undo()
    {
        undo.addEdit(undo);
        undo.getUndoPresentationName();
        undo.undoableEditHappened(evt);
        undo.undo();
    }

    public void redo()
    {
        undo.getRedoPresentationName();
        undo.redo();
    }
    
    public BufferedImage cloneImage(BufferedImage _image)
    {
        if(_image == null)
            return null;
        ColorModel cm = _image.getColorModel();
        boolean isAlpha = cm.isAlphaPremultiplied();
        WritableRaster raster = _image.copyData(null);
        
        return new BufferedImage(cm,raster,isAlpha,null);
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public void setShapes(Shape[] shapes) {
        this.shapes = shapes;
    }

    public boolean isIsShapePressed() {
        return isShapePressed;
    }

    public void setIsShapePressed(boolean isShapePressed) {
        this.isShapePressed = isShapePressed;
    }

    public int getPressedShapeIndex() {
        return pressedShapeIndex;
    }

    public void setPressedShapeIndex(int pressedShapeIndex) {
        this.pressedShapeIndex = pressedShapeIndex;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getOrginal_image() {
        return orginal_image;
    }

    public void setOrginal_image(BufferedImage orginal_image) {
        this.orginal_image = orginal_image;
    }

    public Point getClickPoint() {
        return clickPoint;
    }

    public void setClickPoint(Point clickPoint) {
        this.clickPoint = clickPoint;
    }

    public boolean isIsResizeable() {
        return isResizeable;
    }

    public void setIsResizeable(boolean isResizeable) {
        this.isResizeable = isResizeable;
    }

    public Shape getcShape() {
        return cShape;
    }

    public  void setcShape(Shape  cShape) {
        this.cShape = cShape;
    }

    public int getShapeIndex() {
        return shapeIndex;
    }

    public static Vector getPointVector() {
        return pointVector;
    }
    
    
}
