
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Yad Sarwat Mustafa
 */
public class Application extends JFrame {

    public static int width, height;
    private final String user = "   " + System.getProperty("user.name") + ": ";
    private final File f = new File("");
    private final String currentPath = f.getAbsolutePath();
    private final Date run = new Date();

    private final Font font;
    private final MatteBorder border = new MatteBorder(5, 5, 5, 5, new Color(255, 200, 0));
    private final Color btnColor = new Color(105, 82, 49);
    private final Color fontColor = new Color(0, 0, 150);

    private final Pan panel;
    private static JTextArea textarea;
    private final JScrollPane sp;
    private final TextField tf;
    private final JButton btnBlur;
    private final JButton btnBlur2;
    private final TextField btnBlurT;
    private final JButton btnOil;
    private final JButton btnOil2;
    private final TextField btnOilT;
    private final JButton btnSetimg;
    private final JButton btnReset;
    private final JButton btnSave;
    private final JButton btnFilter;
    private final JLabel lRed;
    private final JLabel lGreen;
    private final JLabel lBlue;
    private final TextField tsRed;
    private final TextField teRed;
    private final TextField tsGreen;
    private final TextField teGreen;
    private final TextField tsBlue;
    private final TextField teBlue;
    private final JButton btnAdd;

    private final JButton btnGetRed;
    private final JButton btnGetGreen;
    private final JButton btnGetBlue;

    private JLabel l1 = new JLabel();
    private JLabel l2 = new JLabel();
    private JLabel l3 = new JLabel();

    private final BtnActions action = new BtnActions();
    private final Analyze_Engine engine = new Analyze_Engine();

    public Application(Dimension size) {
        Application.width = size.width;
        Application.height = size.height;

        double dtemp = (Double.valueOf(width) / Double.valueOf(height)) * 10;
        font = new Font("Serif", Font.BOLD, (int) dtemp);

        try {

            UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
            for (UIManager.LookAndFeelInfo i : looks) {
                if (i.toString().contains("Nimbus")) {
                    UIManager.setLookAndFeel(i.getClassName());
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        panel = new Pan((width / 20) * 15, (height / 20) * 13);
        panel.setLocation((5), (height / 20) * 2);

        textarea = new JTextArea();
        textarea.setSize(((width / 10) * 8) - 50, (height / 10) * 2);
        textarea.setEditable(false);
        textarea.setLineWrap(true);
        textarea.setFont(this.font);
        textarea.setForeground(new Color(0, 0, 0));
        textarea.setBackground(new Color(150, 150, 150));

        sp = new JScrollPane(textarea);
        sp.setLocation((width / 20) * 4, ((height / 20) * 16) - (height / 20));
        sp.setSize((width / 20) * 15, (height / 20) * 4);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(border);

        tf = new TextField();
        tf.setLocation(15, (height / 20) * 16);
        tf.setSize(((width / 20) * 4) - 30, (height / 20) * 2);
        tf.setBorder(border);
        tf.setFont(font);
        tf.setForeground(new Color(0, 0, 0));
        tf.setBackground(new Color(150, 150, 150));
        tf.setText(user);
        tf.addKeyListener(action.commander(tf, user, currentPath, panel, engine, textarea, run));

        btnSetimg = new JButton("Set Image");
        btnSetimg.setBounds(((width / 20) * 15) + 5, (height / 20) * 2, ((width / 20) * 3), (height / 20));
        btnSetimg.setBackground(Color.GRAY);
        btnSetimg.addActionListener(action.setImage());
        btnSetimg.setFont(this.font);
        btnSetimg.setBackground(btnColor);
        btnSetimg.setForeground(fontColor);

        btnBlur = new JButton("Blur once");
        btnBlur.setBounds(((width / 20) * 15) + 5, (height / 20) * 4, (width / 20) * 3, (height / 20));
        btnBlur.setBackground(Color.GRAY);
        btnBlur.addActionListener(action.blur(null));
        btnBlur.setFont(this.font);
        btnBlur.setBackground(btnColor);
        btnBlur.setForeground(fontColor);

        btnBlurT = new TextField();
        btnBlurT.setText("1");
        btnBlurT.addKeyListener(btnBlurT);
        btnBlurT.setBounds(((width / 20) * 17) + 5, (height / 20) * 5, (width / 20), (height / 20));
        btnBlurT.addKeyListener(btnBlurT);
        btnBlurT.setFont(this.font);
        btnBlurT.setBackground(new Color(75, 75, 75));
        btnBlurT.setForeground(Color.GREEN);

        btnBlur2 = new JButton("Blur");
        btnBlur2.setBounds(((width / 20) * 15) + 5, (height / 20) * 5, (width / 20) * 2, (height / 20));
        btnBlur2.setBackground(Color.GRAY);
        btnBlur2.addActionListener(action.blur(btnBlurT));
        btnBlur2.setFont(this.font);
        btnBlur2.setBackground(btnColor);
        btnBlur2.setForeground(fontColor);

        btnOil = new JButton("Oil_Paint once");
        btnOil.setBounds(((width / 20) * 15) + 5, (height / 20) * 6, (width / 20) * 3, (height / 20));
        btnOil.setBackground(Color.GRAY);
        btnOil.addActionListener(action.paint(null));
        btnOil.setFont(this.font);
        btnOil.setBackground(btnColor);
        btnOil.setForeground(fontColor);

        btnOilT = new TextField();
        btnOilT.setText("1");
        btnOilT.setBounds(((width / 20) * 17) + 5, (height / 20) * 7, (width / 25), (height / 20));
        btnOilT.addKeyListener(btnBlurT);
        btnOilT.setFont(this.font);
        btnOilT.setBackground(new Color(75, 75, 75));
        btnOilT.setForeground(Color.GREEN);
        btnOilT.addKeyListener(btnOilT);

        btnOil2 = new JButton("Oil_Paint");
        btnOil2.setBounds(((width / 20) * 15) + 5, (height / 20) * 7, (width / 20) + (width / 20), (height / 20));
        btnOil2.setBackground(Color.GRAY);
        btnOil2.addActionListener(action.paint(btnOilT));
        btnOil2.setFont(this.font);
        btnOil2.setBackground(btnColor);
        btnOil2.setForeground(fontColor);

        btnSave = new JButton("Save");
        btnSave.setBounds(((width / 20) * 18) + 5, (height / 20) * 2, (width / 20) * 2, (height / 20));
        btnSave.setFont(font);
        btnSave.setBackground(btnColor);
        btnSave.setForeground(fontColor);
        btnSave.addActionListener(action.save());

        lRed = new JLabel();
        lRed.setText("Red:");
        lRed.setHorizontalAlignment(JLabel.CENTER);
        lRed.setFont(font);
        lRed.setForeground(Color.RED);
        lRed.setBounds(((width / 20) * 15) + 5, (height / 20) * 10, width / 20, height / 20);

        tsRed = new TextField();
        tsRed.setText(null);
        tsRed.setBounds((width / 20) * 16, (height / 20) * 10, width / 20, height / 20);
        tsRed.setBackground(new Color(75, 75, 75));
        tsRed.setForeground(Color.GREEN);
        tsRed.addKeyListener(action.filter(tsRed));

        teRed = new TextField();
        teRed.setText(null);
        teRed.setBounds((width / 20) * 17, (height / 20) * 10, width / 20, height / 20);
        teRed.setBackground(new Color(75, 75, 75));
        teRed.setForeground(Color.GREEN);
        teRed.addKeyListener(action.filter(teRed));

        lGreen = new JLabel();
        lGreen.setText("Green:");
        lGreen.setHorizontalAlignment(JLabel.CENTER);
        lGreen.setFont(font);
        lGreen.setForeground(new Color(0, 150, 0));
        lGreen.setBounds(((width / 20) * 15) + 5, (height / 20) * 11, width / 20, height / 20);

        tsGreen = new TextField();
        tsGreen.setText(null);
        tsGreen.setBounds((width / 20) * 16, (height / 20) * 11, width / 20, height / 20);
        tsGreen.setBackground(new Color(75, 75, 75));
        tsGreen.setForeground(Color.GREEN);
        tsGreen.addKeyListener(action.filter(tsGreen));

        teGreen = new TextField();
        teGreen.setText(null);
        teGreen.setBounds((width / 20) * 17, (height / 20) * 11, width / 20, height / 20);
        teGreen.setBackground(new Color(75, 75, 75));
        teGreen.setForeground(Color.GREEN);
        teGreen.addKeyListener(action.filter(teGreen));

        lBlue = new JLabel();
        lBlue.setText("Blue:");
        lBlue.setHorizontalAlignment(JLabel.CENTER);
        lBlue.setFont(font);
        lBlue.setForeground(Color.BLUE);
        lBlue.setBounds(((width / 20) * 15) + 5, (height / 20) * 12, width / 20, height / 20);

        tsBlue = new TextField();
        tsBlue.setText(null);
        tsBlue.setBounds((width / 20) * 16, (height / 20) * 12, width / 20, height / 20);
        tsBlue.setBackground(new Color(75, 75, 75));
        tsBlue.setForeground(Color.GREEN);
        tsBlue.addKeyListener(action.filter(tsBlue));

        teBlue = new TextField();
        teBlue.setText(null);
        teBlue.setBounds((width / 20) * 17, (height / 20) * 12, width / 20, height / 20);
        teBlue.setBackground(new Color(75, 75, 75));
        teBlue.setForeground(Color.GREEN);
        teBlue.addKeyListener(action.filter(teBlue));

        btnFilter = new JButton("Filter");
        btnFilter.setFont(font);
        btnFilter.setBounds(((width / 20) * 15) + 5, (height / 20) * 9, (width / 20) * 2, (height / 20));
        btnFilter.setBackground(btnColor);
        btnFilter.setForeground(fontColor);
        btnFilter.addActionListener(action.filterA(textarea, tsRed, teRed, tsGreen, teGreen, tsBlue, teBlue));

        btnReset = new JButton("Reset Image");
        btnReset.setBounds(((width / 20) * 15) + 5, (height / 20) * 3, ((width / 20) * 3), (height / 20));
        btnReset.setBackground(Color.GRAY);
        btnReset.addActionListener(action.reset(tsRed, teRed, tsGreen, teGreen, tsBlue, teBlue));
        btnReset.setFont(this.font);
        btnReset.setBackground(btnColor);
        btnReset.setForeground(fontColor);

        btnGetRed = new JButton();
        btnGetRed.setFont(font);
        btnGetRed.setText("Get Red");
        btnGetRed.setBounds((width / 20) * 18, (height / 20) * 10, (width / 20) * 2, height / 20);
        btnGetRed.setBackground(btnColor);
        btnGetRed.setForeground(Color.RED);
        btnGetRed.addActionListener(action.getRed());

        btnGetGreen = new JButton();
        btnGetGreen.setFont(font);
        btnGetGreen.setText("Get Green");
        btnGetGreen.setBounds((width / 20) * 18, (height / 20) * 11, (width / 20) * 2, height / 20);
        btnGetGreen.setBackground(btnColor);
        btnGetGreen.setForeground(Color.GREEN);
        btnGetGreen.addActionListener(action.getGreen());

        btnGetBlue = new JButton();
        btnGetBlue.setFont(font);
        btnGetBlue.setText("Get Blue");
        btnGetBlue.setBounds((width / 20) * 18, (height / 20) * 12, (width / 20) * 2, height / 20);
        btnGetBlue.setBackground(btnColor);
        btnGetBlue.setForeground(Color.BLUE);
        btnGetBlue.addActionListener(action.getBlue());
        
        btnAdd=new JButton();
        btnAdd.setFont(font);
        btnAdd.setText("Add Image");
        btnAdd.setBounds(((width/20)*15)+5,(height/20)*14,(width/20)*2,height/20);
        btnAdd.setBackground(btnColor);
        btnAdd.setForeground(fontColor);
        btnAdd.addActionListener(action.add());

        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocation(0, 0);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Yad Sarwat Mustafa");

        this.add(panel);
        this.add(sp);
        this.add(tf);
        this.add(btnSetimg);
        this.add(btnReset);
        this.add(btnBlur);
        this.add(btnBlur2);
        this.add(btnBlurT);
        this.add(btnOil);
        this.add(btnOil2);
        this.add(btnOilT);
        this.add(btnSave);
        this.add(btnFilter);
        this.add(lRed);
        this.add(lGreen);
        this.add(lBlue);
        this.add(tsRed);
        this.add(teRed);
        this.add(tsGreen);
        this.add(teGreen);
        this.add(tsBlue);
        this.add(teBlue);
        this.add(btnGetRed);
        this.add(btnGetGreen);
        this.add(btnGetBlue);
        this.add(btnAdd);

        this.setVisible(true);

        panel.addKeyListener(panel);
        panel.requestFocus();

        l1.setOpaque(true);
        l1.setBackground(new Color(245, 222, 179));
        l1.setLocation(0, 0);
        l1.setSize(width, (height / 10) * 6);

        l2.setOpaque(true);
        l2.setBackground(new Color(222, 184, 140));
        l2.setLocation(0, (height / 10) * 6);
        l2.setSize(width, (height / 10) * 3);

        l3.setOpaque(true);
        l3.setBackground(new Color(220, 210, 120));
        l3.setLocation(0, (height / 10) * 9);
        l3.setSize(width, (height / 10) * 1);

        this.add(l1);
        this.add(l2);
        this.add(l3);

        tf.requestFocusInWindow();

        panel.repaint();
        this.repaint();
        new Thread(panel).start();

    }

}
