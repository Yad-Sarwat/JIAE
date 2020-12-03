
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * * * @author Yad Sarwat Mustafa
 */
public class Main {

    public static void main(String[] args) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        Application app = new Application(new Dimension((size.width/20)*19, (size.height/20)*19));
    }

}
