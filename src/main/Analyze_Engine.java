
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Yad Sarwat Mustafa
 */
public class Analyze_Engine {

    String allCommands[] = new String[3];
    Process process = new Process();

    public void start(String allText, String userName, String currentPath, JTextArea textarea, BufferedImage img, Date run) {

        allCommands[0] = "jmkdir";
        allCommands[1] = "jsave";
        allCommands[2] = "jimage";

        if (allText.contains(userName)) {
            String STARTING_POINT = allText.substring(0, userName.length());
            if (STARTING_POINT.equals(userName)) {

                String command = allText.substring(userName.length(), allText.length());
                String root[];
                while ((!command.isEmpty()) && (command.substring(0, 1).equals(" "))) {
                    command = command.substring(1, command.length());
                }

                if (command.length() == 0) {
                    textarea.append(allText + "\n");
                    return;
                }
                root = command.split(" ");
                ArrayList<String> temp = new ArrayList<String>();
                for (int i = 0; i < root.length; i++) {
                    if (root[i].length() != 0) {
                        temp.add(root[i]);
                    }
                }
                root = new String[temp.size()];
                for (int i = 0; i < temp.size(); i++) {
                    root[i] = temp.get(i);
                }

                // complex commands
                if (root.length > 1) {
                    boolean isQuestion = false;
                    String P = null, N = null, L = null;

                    for (String i : root) {
                        if (i.equals("?")) {
                            isQuestion = true;
                            break;
                        }
                    }
                    if (!isQuestion) {
                        if (root[0].equals("jmkdir")) {
                            if (command.contains("-p")) {
                                for (int i = 0; i < root.length; i++) {
                                    if (root[i].equals("-p")) {
                                        P = root[i + 1];
                                        break;
                                    }
                                }
                            } else {
                                P = root[1];
                            }

                            File tfile = new File(P);
                            if (!tfile.isDirectory()) {
                                tfile.mkdirs();
                            } else {
                                textarea.append("Specified path already exists!");
                            }
                            textarea.append(command + "\n");

                        } else if (root[0].equals("jsave")) {

                            if (root.length == 2) {
                                N = root[1];
                                P = currentPath;
                                P = arrangePath(P);
                                if (isImageFile(N, textarea)) {
                                    saveImage(N, P, img);
                                    textarea.append(command + "\n");
                                }
                            } else if (root.length == 3) {
                                if (command.contains("-n") && !command.contains("-p")) {
                                    P = currentPath;
                                    N = root[2];
                                    if (isImageFile(N, textarea)) {
                                        P = arrangePath(P);
                                        saveImage(N, P, img);
                                        textarea.append(command + "\n");
                                    }
                                } else if (!command.contains("-")) {
                                    N = root[1];
                                    P = root[2];
                                    if (isImageFile(N, textarea) && isPath(P, textarea)) {
                                        P = arrangePath(P);
                                        saveImage(N, P, img);
                                        textarea.append(command + "\n");
                                    }
                                }
                            } else if (root.length == 4) {
                                if (command.contains("-n") && !command.contains("-p")) {
                                    for (int i = 0; i < root.length; i++) {
                                        if (root[i].equals("-n")) {
                                            N = root[i + 1];
                                        }
                                    }
                                    P = root[3];
                                    if (isImageFile(N, textarea)) {
                                        P = arrangePath(P);
                                        saveImage(N, P, img);
                                        textarea.append(command + "\n");
                                    }
                                } else if (!command.contains("-n") && command.contains("-p")) {
                                    for (int i = 0; i < root.length; i++) {
                                        if (root[i].equals("-p")) {
                                            P = root[i + 1];
                                        }
                                    }
                                    N = root[1];
                                    if (isImageFile(N, textarea) && isPath(P, textarea)) {
                                        P = arrangePath(P);
                                        saveImage(N, P, img);
                                        textarea.append(command + "\n");
                                    }
                                }
                            } else if (root.length == 5) {
                                if (command.contains("-n") && command.contains("-p")) {
                                    for (int i = 0; i < root.length; i++) {
                                        if (root[i].equals("-p")) {
                                            P = root[i + 1];
                                        }
                                        if (root[i].equals("-n")) {
                                            N = root[i + 1];
                                        }
                                    }
                                    if (isImageFile(N, textarea) && isPath(P, textarea)) {
                                        P = arrangePath(P);
                                        saveImage(N, P, img);
                                        textarea.append(command + "\n");
                                    }

                                } else {
                                    textarea.append("Invalid number of parameters has been used!\n type '?' for help\n");
                                }
                            } else {
                                textarea.append("Invalid Parameters, takes only -p, -n\n");
                            }
                        } else if (root[0].equals("jimage")) {

                            if (root[1].equals("setimg")) {
                                JFileChooser ch = new JFileChooser();
                                ch.setDialogTitle("Select An Image File");
                                ch.showOpenDialog(null);
                                if (ch.getSelectedFile() == null) {
                                    textarea.setText(command + "\n");
                                    return;
                                }
                                if (!isImageFile(ch.getSelectedFile().getAbsolutePath(), textarea)) {
                                    return;
                                }
                                Pan.imgFile = ch.getSelectedFile();
                                if (!isImageFile(Pan.imgFile.getAbsolutePath(), textarea)) {
                                    return;
                                }
                                Pan.setImage();
                                textarea.append(command + "\n");
                                return;
                            }
                            if (Pan.pixels == null) {
                                textarea.append("You need to select an Image first!\n");
                                return;
                            }
                            try {
                                if (root.length >= 2) {

                                    if (root.length == 2) {
                                        L = "1";
                                        if (!commandImage(root, L, textarea)) {
                                            textarea.append("Unknown command with jimage!");
                                        }
                                    } else if (root.length > 2) {
                                        L = root[2];
                                        if (command.contains("-loop")) {
                                            for (int i = 0; i < root.length; i++) {
                                                if (root[i].equals("-loop")) {
                                                    L = root[i + 1];
                                                }
                                            }
                                        }
                                        if (!commandImage(root, L, textarea)) {
                                            textarea.append("Unknown command with jimage!\n");
                                        }
                                    }
                                } else {
                                    textarea.append("Invalid Parameters, jimages takes at least one argument type\n");
                                    return;
                                }
                                textarea.append(command + "\n");

                            } catch (Exception e) {
                            }
                        } else {
                            textarea.append("UN_Known Command ' " + command + " '\n");
                        }
                    } else {
                        Info i = new Info();
                        if (root[0].equals("jsystem")) {
                            textarea.append("   " + i.jsystem + "\n");
                            return;
                        } else if (root[0].equals("jdate")) {
                            textarea.append("   " + i.jdate + "\n");
                            return;
                        } else if (root[0].equals("jpath")) {
                            textarea.append("   " + i.jpath + "\n");
                            return;
                        } else if (root[0].equals("jtime")) {
                            textarea.append("   " + i.jtime + "\n");
                            return;
                        } else if (root[0].equals("jsave")) {
                            textarea.append("   " + i.jsave + "\n");
                            return;
                        } else if (root[0].equals("jclear")) {
                            textarea.append("   " + i.jclear + "\n");
                            return;
                        } else if (root[0].equals("jmkdir")) {
                            textarea.append("   " + i.jmkdir + "\n");
                            return;
                        } else if (root[0].equals("jimage")) {
                            if (root.length == 2) {
                                textarea.append("   " + i.jimage + "\n");
                                return;
                            } else {
                                if (root[1].equals("setimg")) {
                                    textarea.append("   "+i.setimg+"\n");
                                    return;
                                }else if(root[1].equals("reset")){
                                    textarea.append("   "+i.reset+"\n");
                                }else if(root[1].equals("filter")){
                                    textarea.append("   "+i.filter+"\n");
                                    return;
                                }else if(root[1].equals("oilpaint")){
                                    textarea.append("   "+i.oilpaint+"\n");
                                    return;
                                }else if(root[1].equals("blur")){
                                    textarea.append("   "+i.blur+"\n");
                                    return;
                                }else if(root[1].equals("getred")){
                                    textarea.append("   "+i.getRed+"\n");
                                    return;
                                }else if(root[1].equals("getgreen")){
                                    textarea.append("   "+i.getGreen+"\n");
                                    return;
                                }else if(root[1].equals("getblue")){
                                    textarea.append("   "+i.getBlue+"\n");
                                    return;
                                }else if(root[1].equals("add")){
                                    textarea.append("   "+i.add+"\n");
                                    return;
                                }else if(root[1].equals("addred")){
                                    textarea.append("   "+i.addRed+"\n");
                                    return;
                                }else if(root[1].equals("addgreen")){
                                    textarea.append("   "+i.addGreen+"\n");
                                    return;
                                }else if(root[1].equals("addblue")){
                                    textarea.append("   "+i.addBlue+"\n");
                                    return;
                                }else{
                                    textarea.append("jimage does not have any argument of type: "+root[1]
                                    +"\nType jimage ? to get the list of all the arguments that jimage is using.\n");
                                }
                            }
                        } else {
                            textarea.append("   " + root[0] + " command does not exist!\n");
                            return;
                        }
                    }
                } // single commands
                else {
                    if (root[0].equals("jclear")) {
                        textarea.setText(null);
                    } else if (root[0].equals("jsystem")) {
                        textarea.append(System.getProperty("os.name") + "\n");
                    } else if (root[0].equals("jpath")) {
                        File ftemp = new File("");
                        textarea.append(ftemp.getAbsolutePath() + "\n");
                    } else if (root[0].equals("?")) {
                        Info info = new Info();
                        textarea.append(info.allInfo);
                    } else if (root[0].equals("jdate")) {
                        try {
                            Calendar cal = Calendar.getInstance();

                            int max = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                            int min = 1;
                            int isWeek = 1;

                            int day = cal.get(Calendar.DAY_OF_MONTH);

                            String stemp = new SimpleDateFormat("MM-yyyy").format(cal.getTime());
                            String firstDay = "1-" + stemp;
                            Date dtemp = new SimpleDateFormat("dd-MM-yyyy").parse(firstDay);
                            String startDay = new SimpleDateFormat("EEE").format(dtemp);

                            String res = "\tSun\tMon\tTue\tWed\tThu\tFri\tSat\n";

                            for (int i = min; i <= max; i++) {

                                if (i == 1) {
                                    if (startDay.equalsIgnoreCase("sun")) {
                                        res += "";
                                    } else if (startDay.equalsIgnoreCase("mon")) {
                                        res += "\t";
                                        isWeek = 2;
                                    } else if (startDay.equalsIgnoreCase("tue")) {
                                        res += "\t\t";
                                        isWeek = 3;
                                    } else if (startDay.equalsIgnoreCase("wed")) {
                                        res += "\t\t\t";
                                        isWeek = 4;
                                    } else if (startDay.equalsIgnoreCase("thu")) {
                                        res += "\t\t\t\t";
                                        isWeek = 5;
                                    } else if (startDay.equalsIgnoreCase("fri")) {
                                        res += "\t\t\t\t\t";
                                        isWeek = 6;
                                    } else if (startDay.equalsIgnoreCase("sat")) {
                                        res += "\t\t\t\t\t\t";
                                        isWeek = 7;
                                    }
                                }
                                if (isWeek > 7) {
                                    res += "\n";
                                    isWeek = 1;
                                }

                                if (i < 10) {
                                    if (i == day) {
                                        res += "\t (" + i + ") ";
                                    } else {
                                        res += "\t  " + i + "  ";
                                    }
                                } else {
                                    if (i == day) {
                                        res += "\t(" + i + ")";
                                    } else {
                                        res += "\t " + i;
                                    }
                                }
                                isWeek++;
                            }

                            textarea.append(res + "\n");
                            String date = String.valueOf(cal.get(Calendar.DAY_OF_MONTH) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR));
                            textarea.append(date + "\n");
                        } catch (Exception e) {
                            textarea.append(String.valueOf(e) + "\n");
                        }
                    } else if (root[0].equals("jtime")) {
                        Date date = new Date();
                        String time = new SimpleDateFormat("HH : mm : ss").format(date);
                        textarea.append(time + "\n");
                    } else if (root[0].equals("jruntime")) {
                        final long res = new Date().getTime() - run.getTime();
                        final long mili = res;
                        final int sec = (int) (res / 1000);
                        final String result = "The application is running since:\nMiliSeconds: " + res + "\nSeconds: " + sec;
                        textarea.append(result + "\n");
                    } else {

                        for (String cc : allCommands) {
                            if (cc.equals(command)) {
                                textarea.append("No Suitable argumen found with ' " + root[0] + " ' Command!\n");
                                return;
                            }
                        }

                        textarea.append("UN_Known Command ' " + root[0] + " '\n");
                    }
                }

            } else {
                textarea.append("UN_Ordered Command!\n");
            }
        } else {
            textarea.append("UN_Known User!\n");
        }
    }

    private static boolean isImageFile(String s, JTextArea ta) {
        String res[] = s.split("\\.");
        if (res.length == 2) {
            if (!res[1].equals("png") && !res[1].equals("jpg")) {
                ta.append("Only PNG and JPG are allowed!\n");
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private static boolean isPath(String p, JTextArea ta) {
        String tp = p;
        File tf = new File(tp);

        if (tf.isDirectory()) {
            return true;
        } else {
            ta.append("Invalid Directory!\n");
            return false;
        }
    }

    private static String arrangePath(String p) {
        String np = p;
        String os = System.getProperty("os.name");
        if (os.contains("Window")) {
            if (!np.substring(np.length() - 1, np.length()).equals("\\")) {
                np += "\\";
            }
        } else if (os.contains("Linux")) {
            if (!np.substring(np.length() - 1, np.length()).equals("/")) {
                np += "/";
            }
        }
        return np;
    }

    private static String getType(String p) {
        return p.substring(p.indexOf(".") + 1, p.length());
    }

    private static void saveImage(String name, String file, BufferedImage img) {
        try {
            ImageIO.write(img, getType(name), new File(file + name));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public boolean commandImage(String[] c, String nn, JTextArea tt) {
        int n = 0;
        try {
            n = Integer.valueOf(nn);
        } catch (Exception e) {
        }
        if (c[1].equals("blur")) {
            for (int i = 0; i < n; i++) {
                try {
                    Pan.arrange();
                    Pan.pixels = process.blur(Pan.pixels);
                    Pan.setImage(Pan.pixels);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }
            process.printCalculation(tt);
            process.printPixelsAfected(tt);
            return true;
        } else if (c[1].equals("oilpaint")) {

            for (int i = 0; i < n; i++) {
                try {
                    Pan.arrange();
                    Pan.pixels = process.oilPaint(Pan.pixels);
                    Pan.setOilImage(Pan.pixels);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            process.printCalculation(tt);
            process.printPixelsAfected(tt);
            return true;
        } else if (c[1].equals("reset")) {
            Pan.setImage();
            return true;

        } else if (c[1].equals("filter")) {

            int rs = -1, re = -1, gs = -1, ge = -1, bs = -1, be = -1;
            try {
                for (int i = 2; i < c.length; i++) {
                    if (c[i].equals("r")) {
                        if (c[i + 1].contains("_")) {
                            String temp[] = c[i + 1].split("_");
                            rs = Integer.parseInt(temp[0]);
                            re = Integer.parseInt(temp[1]);
                        } else {
                            rs = Integer.parseInt(c[i + 1]);
                        }
                    }
                    if (c[i].equals("g")) {
                        if (c[i + 1].contains("_")) {
                            String temp[] = c[i + 1].split("_");
                            gs = Integer.parseInt(temp[0]);
                            ge = Integer.parseInt(temp[1]);
                        } else {
                            gs = Integer.parseInt(c[i + 1]);
                        }
                    }
                    if (c[i].equals("b")) {
                        if (c[i + 1].contains("_")) {
                            String temp[] = c[i + 1].split("_");
                            bs = Integer.parseInt(temp[0]);
                            be = Integer.parseInt(temp[1]);
                        } else {
                            bs = Integer.parseInt(c[i + 1]);
                        }
                    }
                }
                if (Pan.pixels == null) {
                    return false;
                }
                Pan.arrange();
                Pan.pixels = process.filter(Pan.pixels, rs, re, gs, ge, bs, be);
                Pan.setImage(Pan.pixels);
                process.printCalculation(tt);
                process.printPixelsAfected(tt);
                return true;
            } catch (Exception e) {
                return false;
            }

        } else if (c[1].equals("getred")) {
            Pan.arrange();
            Pan.pixels = process.getRed(Pan.pixels);
            Pan.setImage(Pan.pixels);
            process.printCalculation(tt);
            process.printPixelsAfected(tt);
        } else if (c[1].equals("getgreen")) {
            Pan.arrange();
            Pan.pixels = process.getGreen(Pan.pixels);
            Pan.setImage(Pan.pixels);
            process.printCalculation(tt);
            process.printPixelsAfected(tt);
        } else if (c[1].equals("getblue")) {
            Pan.arrange();
            Pan.pixels = process.getBlue(Pan.pixels);
            Pan.setImage(Pan.pixels);
            process.printCalculation(tt);
            process.printPixelsAfected(tt);
        } else if (c[1].equals("add")) {
            File fTemp = null;
            if (c[2].equals("setimg")) {
                JFileChooser ch = new JFileChooser();
                ch.setMultiSelectionEnabled(false);
                ch.setDialogTitle("Select an Image File!");
                ch.showOpenDialog(null);
                if (ch.getSelectedFile() == null) {
                    return true;
                }
                fTemp = (ch.getSelectedFile());
            } else {
                if (c[2].equals("-p")) {
                    fTemp = new File(c[3]);
                } else {
                    fTemp = new File(c[2]);
                    if(!fTemp.exists()){
                        tt.append("No such File Directory!");
                        return true;
                    }
                }
            }
            double range = 0.0;
            try {
                range = Double.valueOf(c[c.length - 1]);
            } catch (Exception e) {
                range = 0.5;
            }
            try {
                BufferedImage i = ImageIO.read(fTemp);
                Pan.arrange();
                Pan.pixels = process.add(Pan.pixels, i, range);
                Pan.setImage(Pan.pixels);
                process.printCalculation(tt);
                process.printPixelsAfected(tt);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else if (c[1].equals("addred")) {
            File fTemp = null;
            if (c[2].equals("setimg")) {
                JFileChooser ch = new JFileChooser();
                ch.setMultiSelectionEnabled(false);
                ch.setDialogTitle("Select an Image File!");
                ch.showOpenDialog(null);
                if (ch.getSelectedFile() == null) {
                    return true;
                }
                fTemp = (ch.getSelectedFile());
            } else {
                if (c[2].equals("-p")) {
                    fTemp = new File(c[3]);
                } else {
                    fTemp = new File(c[2]);
                    if(!fTemp.exists()){
                        tt.append("No such File Directory!");
                        return true;
                    }
                }
            }
            double range = 0.0;
            try {
                range = Double.valueOf(c[c.length - 1]);
            } catch (Exception e) {
                range = 0.5;
            }
            try {
                BufferedImage i = ImageIO.read(fTemp);
                Pan.arrange();
                Pan.pixels = process.addRed(Pan.pixels, i, range);
                Pan.setImage(Pan.pixels);
                process.printCalculation(tt);
                process.printPixelsAfected(tt);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else if (c[1].equals("addgreen")) {
            File fTemp = null;
            if (c[2].equals("setimg")) {
                JFileChooser ch = new JFileChooser();
                ch.setMultiSelectionEnabled(false);
                ch.setDialogTitle("Select an Image File!");
                ch.showOpenDialog(null);
                if (ch.getSelectedFile() == null) {
                    return true;
                }
                fTemp = (ch.getSelectedFile());
            } else {
                if (c[2].equals("-p")) {
                    fTemp = new File(c[3]);
                } else {
                    fTemp = new File(c[2]);
                    if(!fTemp.exists()){
                        tt.append("No such File Directory!");
                        return true;
                    }
                }
            }
            double range = 0.0;
            try {
                range = Double.valueOf(c[c.length - 1]);
            } catch (Exception e) {
                range = 0.5;
            }
            try {
                BufferedImage i = ImageIO.read(fTemp);
                Pan.arrange();
                Pan.pixels = process.addGreen(Pan.pixels, i, range);
                Pan.setImage(Pan.pixels);
                process.printCalculation(tt);
                process.printPixelsAfected(tt);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else if (c[1].equals("addblue")) {
            File fTemp = null;
            if (c[2].equals("setimg")) {
                JFileChooser ch = new JFileChooser();
                ch.setMultiSelectionEnabled(false);
                ch.setDialogTitle("Select an Image File!");
                ch.showOpenDialog(null);
                if (ch.getSelectedFile() == null) {
                    return true;
                }
                fTemp = (ch.getSelectedFile());
            } else {
                if (c[2].equals("-p")) {
                    fTemp = new File(c[3]);
                } else {
                    fTemp = new File(c[2]);
                    if(!fTemp.exists()){
                        tt.append("No such File Directory!");
                        return true;
                    }
                }
            }
            double range = 0.0;
            try {
                range = Double.valueOf(c[c.length - 1]);
            } catch (Exception e) {
                range = 0.5;
            }
            try {
                BufferedImage i = ImageIO.read(fTemp);
                Pan.arrange();
                Pan.pixels = process.addBlue(Pan.pixels, i, range);
                Pan.setImage(Pan.pixels);
                process.printCalculation(tt);
                process.printPixelsAfected(tt);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
        return false;
    }

}
