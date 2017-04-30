/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teicm.methodologia.floorplans.view;

import gr.teicm.methodologia.floorplans.model.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JPanel;

/**
 *
 * @author Tony-Driver
 */
public class Save {

    private MyFrame myFrame;
    private MyPanel myPanel =new MyPanel() ;
    static Object next;

    public Save() {

    }

    public void save() throws IOException {
        try {
            FileOutputStream f = new FileOutputStream("myfile.dat");
            ObjectOutputStream o = new ObjectOutputStream(f);
            for (int i = 0; i < myPanel.getPointVector().size(); i++) {
                next = MyPanel.getPointVector().listIterator().next();
                o.writeObject(next);
            }
            o.flush();
            o.close();
            f.close();
        } catch (IOException ex) {
            System.err.println("Trouble writing display list array list");
        }
    }

    /*public static void lo() throws IOException{
        BufferedImage load;
        load = load();
    }*/
    /*public static void load() throws IOException {
        File file = new File("myfile.dat");
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new FileInputStream(file));
            //in.readObject();
            Object object = (JPanel) in.readObject();
            MyPanel.setcShape((Shape) object);
            in.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        //return myPanel.getImage();
    }*/
}
