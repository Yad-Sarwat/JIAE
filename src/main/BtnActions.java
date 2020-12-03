
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BtnActions {
    
    public ActionListener blur(JTextField ta) {
        ActionListener a1 = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Process process = new Process();
                    int n = 1;
                    if (Pan.getImage() == null) {
                        JOptionPane.showMessageDialog(null, "You need to select an Image First!");
                        return;
                    }
                    if (ta != null) {
                        n = Integer.valueOf(ta.getText());
                    }
                    for (int i = 1; i <= n; i++) {
                        Pan.arrange();
                        Pan.pixels = process.blur(Pan.pixels);
                        Pan.setImage(Pan.pixels);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        };
        return a1;
    }
    
    public ActionListener paint(JTextField ta) {
        ActionListener al = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Process process = new Process();
                    int n = 1;
                    if (Pan.getImage() == null) {
                        JOptionPane.showMessageDialog(null, "You need to select an Image First!");
                        return;
                    }
                    if (ta != null) {
                        n = Integer.valueOf(ta.getText());
                    }
                    for (int i = 1; i <= n; i++) {
                        Pan.arrange();
                        Pan.pixels = process.oilPaint(Pan.pixels);
                        Pan.setOilImage(Pan.pixels);
                    }
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        };
        return al;
    }
    
    public ActionListener setImage() {
        ActionListener a = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    JFileChooser ch = new JFileChooser();
                    ch.setMultiSelectionEnabled(false);
                    ch.showOpenDialog(null);
                    if (ch.getSelectedFile() == null) {
                        return;
                    }
                    if (!isImageFile(ch.getSelectedFile().getAbsolutePath())) {
                        JOptionPane.showMessageDialog(null, "Only image Files are accepted!");
                        return;
                    }
                    Pan.imgFile = ch.getSelectedFile();
                    Pan.setImage();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        };
        
        return a;
    }
    
    public ActionListener reset(TextField tsRed, TextField teRed,
            TextField tsGreen, TextField teGreen,
            TextField tsBlue, TextField teBlue) {
        ActionListener a = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Pan.imgFile == null) {
                    return;
                }
                Pan.setImage();
                tsRed.setText(null);
                teRed.setText(null);
                tsGreen.setText(null);
                teGreen.setText(null);
                tsBlue.setText(null);
                teBlue.setText(null);
            }
        };
        return a;
    }
    
    public ActionListener save() {
        ActionListener res = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Pan.getImage() == null) {
                    JOptionPane.showMessageDialog(null, "You need to Have an Image firts!");
                    return;
                }
                try {
                    JFileChooser ch = new JFileChooser();
                    ch.setMultiSelectionEnabled(false);
                    ch.showSaveDialog(null);
                    File tf = ch.getSelectedFile();
                    String ts[] = tf.getAbsolutePath().split("\\.");
                    
                    if (!ts[1].equals("png") && !ts[1].equals("jpg")) {
                        JOptionPane.showMessageDialog(null, "Only Image files are allowed!");
                        return;
                    }
                    
                    ImageIO.write(Pan.getImage(), ts[1], ch.getSelectedFile());
                    JOptionPane.showMessageDialog(null, "Done", "Completed", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        };
        return res;
    }
    
    public ActionListener filterA(JTextArea tt, TextField tsRed, TextField teRed,
            TextField tsGreen, TextField teGreen,
            TextField tsBlue, TextField teBlue) {
        
        ActionListener res = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Pan.pixels == null) {
                    JOptionPane.showMessageDialog(null, "You need to select an Image  first!");
                    return;
                }
                int rs = -1, re = -1, gs = -1, ge = -1, bs = -1, be = -1;
                try {
                    rs = tsRed.getText().isEmpty() ? -1 : Integer.parseInt(tsRed.getText());
                    re = teRed.getText().isEmpty() ? -1 : Integer.parseInt(teRed.getText());
                    gs = tsGreen.getText().isEmpty() ? -1 : Integer.parseInt(tsGreen.getText());
                    ge = teGreen.getText().isEmpty() ? -1 : Integer.parseInt(teGreen.getText());
                    bs = tsBlue.getText().isEmpty() ? -1 : Integer.parseInt(tsBlue.getText());
                    be = teBlue.getText().isEmpty() ? -1 : Integer.parseInt(teBlue.getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                Process process = new Process();
                Pan.arrange();
                Pan.pixels = process.filter(Pan.pixels, rs, re, gs, ge, bs, be);
                Pan.setImage(Pan.pixels);
                process.printCalculation(tt);
                process.printPixelsAfected(tt);
            }
        };
        return res;
    }
    
    public KeyListener commander(TextField tf, String user, String currentPath, Pan panel, Analyze_Engine engine, JTextArea textarea, Date date) {
        KeyListener res = new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent ke) {
                
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {
                if ((ke.getKeyCode() == KeyEvent.VK_DELETE || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        && tf.getText().length() == user.length()) {
                    ke.consume();
                }
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics gg = img.getGraphics();
                    panel.paint(gg);
                    engine.start(tf.getText(), user, currentPath, textarea, Pan.getImage(), date);
                    tf.setText(user);
                }
            }
            
            @Override
            public void keyReleased(KeyEvent ke) {
                
            }
        };
        return res;
    }
    
    public KeyListener filter(TextField tf) {
        KeyListener res = new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent ke) {
                if (!Character.isDigit(ke.getKeyChar())) {
                    ke.consume();
                }
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {
                
            }
            
            @Override
            public void keyReleased(KeyEvent ke) {
                
            }
        };
        
        return res;
    }
    
    public ActionListener getRed() {
        ActionListener res = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Pan.pixels == null || Pan.getImage() == null) {
                    JOptionPane.showMessageDialog(null, "You need to select an Image  first!");
                    return;
                }
                Process process = new Process();
                Pan.arrange();
                Pan.pixels = process.getRed(Pan.pixels);
                Pan.setImage(Pan.pixels);
            }
        };
        return res;
    }
    
    public ActionListener getGreen() {
        ActionListener res = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Pan.pixels == null || Pan.getImage() == null) {
                    JOptionPane.showMessageDialog(null, "You need to select an Image  first!");
                    return;
                }
                Process process = new Process();
                Pan.arrange();
                Pan.pixels = process.getGreen(Pan.pixels);
                Pan.setImage(Pan.pixels);
            }
        };
        return res;
    }
    
    public ActionListener getBlue() {
        ActionListener res = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Pan.pixels == null || Pan.getImage() == null) {
                    JOptionPane.showMessageDialog(null, "You need to select an Image  first!");
                    return;
                }
                Process process = new Process();
                Pan.arrange();
                Pan.pixels = process.getBlue(Pan.pixels);
                Pan.setImage(Pan.pixels);
            }
        };
        return res;
    }
    
    public ActionListener add() {
        ActionListener res = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                File f = null;
                if (Pan.getImage() == null || Pan.pixels == null) {
                    JOptionPane.showMessageDialog(null, "you need to set the Primary image first then add secondary");
                    return;
                }
                try {
                    JFileChooser ch = new JFileChooser();
                    ch.setDialogTitle("Select an Image File");
                    ch.setMultiSelectionEnabled(false);
                    ch.showOpenDialog(null);
                    f = ch.getSelectedFile();
                    if (!isImageFile(f.getAbsolutePath())) {
                        JOptionPane.showMessageDialog(null, "You need to select only image files!");
                        return;
                    }
                } catch (Exception e) {
                }
                if (f == null) {
                    return;
                }
                JSlider sd = new JSlider();
                sd.setMaximum(100);
                sd.setMinimum(0);
                sd.setMajorTickSpacing(10);
                sd.setPaintTicks(true);
                sd.setPaintLabels(true);
                sd.setPaintTrack(true);
                int res = JOptionPane.showOptionDialog(null, sd, "Select a ratio",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                Double ratio = Double.valueOf(sd.getValue()) / 100.0;
                Process p = new Process();
                Pan.arrange();
                BufferedImage img = null;
                try {
                    img=ImageIO.read(f);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                Pan.pixels = p.add(Pan.pixels, img, ratio);
                Pan.setImage(Pan.pixels);
            }
        };
        return res;
    }
    
    private boolean isImageFile(String x) {
        String res[] = x.split("\\.");
        if (res.length == 2) {
            if (!res[1].equals("png") && !res[1].equals("jpg")) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
