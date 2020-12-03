
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

/**
 *
 * @author Yad Sarwat Mustafa
 */
public class TextField extends JTextField implements KeyListener {

    

    public TextField() {
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if(!Character.isDigit(ke.getKeyChar())){
                ke.consume();
            }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    

}
