/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Yad Sarwat Mustafa
 */
public class Pan extends Canvas implements Runnable, KeyListener {

    private boolean CTRL, S, Z;
    private BufferStrategy bs;
    private static BufferedImage img;
    private Graphics g;
    public static int pixels[][][];
    public static File imgFile;
    private boolean left, right, up, down;
    int x = 0, y = 0, val = 4;
    public static int width,height;

    public Pan(int width, int height){
        this.setSize(width, height);
        Pan.width=width;
        Pan.height=height;
    }
    
    @Override
    public void run() {
        try {
            while (true) {

                UPDATE();
                DRAW();

                Thread.sleep(5);
            }
        } catch (RuntimeException | InterruptedException re) {
            JOptionPane.showMessageDialog(null, re, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void DRAW() {

        if (right) {
            x += val;
        }
        if (left) {
            x += -val;
        }
        if (up) {
            y += -val;
        }
        if (down) {
            y += val;
        }

        bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        if (img != null) {
            g.drawImage(img, 0 + x, 0 + y, null);
        }

        g.dispose();
        bs.show();
    }

    private void UPDATE() {
        if (CTRL && S) {
            try {
                JFileChooser ch = new JFileChooser();
                ch.setMultiSelectionEnabled(false);
                ch.showOpenDialog(null);
                imgFile = ch.getSelectedFile();
                String tpath=imgFile.getAbsolutePath();
                if(!tpath.substring(tpath.length()-3, tpath.length()).equalsIgnoreCase("png")
                        && !tpath.substring(tpath.length()-3, tpath.length()).equalsIgnoreCase("jpg")){
                    JOptionPane.showMessageDialog(null, "Only Image_Files are acceptable!");
                    return;
                }
                setImage();
                
            } catch (Exception e) {
            }
            CTRL = false;
            S = false;
        }
        if (Z) {
            try {
                JFileChooser fff = new JFileChooser();
                fff.setMultiSelectionEnabled(false);

                fff.showSaveDialog(null);
                String path = fff.getSelectedFile().getAbsolutePath() + ".png";
                File ff = new File(path);
                ImageIO.write(img, "PNG", ff);
            } catch (Exception e) {
            }

            Z = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_CONTROL) {
            this.CTRL = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_S) {
            this.S = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_Z) {
            Z = true;
        }

        if (ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }

        if (ke.getKeyCode() == KeyEvent.VK_R) {
            y = 0;
            x = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_CONTROL) {
            this.CTRL = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_S) {
            this.S = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_Z) {
            Z = false;
        }

        if (ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
    }

    public static BufferedImage getImage() {
        return Pan.img;
    }

    public static void setImage(int[][][] p) {
        Graphics gg = img.createGraphics();
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {
                gg.setColor(new Color(p[y][x][0], p[y][x][1], p[y][x][2]));
                gg.drawRect(x, y, 1, 1);
            }
        }
    }

    public static void setOilImage(int[][][] p) {
        Graphics gg = img.getGraphics();
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {
                gg.setColor(new Color(p[y][x][0]));
                gg.fillRect(x, y, 1, 1);
            }
        }
    }
    
    public static void arrange(){
        for(int y=0; y<img.getHeight(); y++){
            for(int x=0; x<img.getWidth();x++){
                Color c = new Color(img.getRGB(x, y));
                pixels[y][x][0]=c.getRed();
                pixels[y][x][1]=c.getGreen();
                pixels[y][x][2]=c.getBlue();
            }
        }
    }
    
    public static void setImage(){
        try{
            img=ImageIO.read(Pan.imgFile);
            Image it=img.getScaledInstance(Pan.width, Pan.height, Image.SCALE_SMOOTH);
            img=new BufferedImage(Pan.width, Pan.height, BufferedImage.TYPE_INT_RGB);
            Graphics gg=img.createGraphics();
            gg.drawImage(it, 0, 0, null);
            pixels = new int[img.getHeight()][img.getWidth()][3];
            for(int yy=0;yy<img.getHeight();yy++){
                for(int xx=0;xx<img.getWidth();xx++){
                    pixels[yy][xx][0]=new Color(img.getRGB(xx, yy)).getRed();
                    pixels[yy][xx][1]=new Color(img.getRGB(xx, yy)).getGreen();
                    pixels[yy][xx][2]=new Color(img.getRGB(xx, yy)).getBlue();
                    
                }
            }
            
        }catch(Exception e){JOptionPane.showMessageDialog(null, e+"\n"+Arrays.toString(e.getStackTrace()));}
    }

}
