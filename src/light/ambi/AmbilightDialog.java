package light.ambi;

import javax.swing.*;
import java.awt.*;

public class AmbilightDialog extends JDialog {

    private int width;

    private int height;

    public AmbilightDialog(int width, int height) {
        this.width = width;
        this.height = height;
        setSize(width, height);
        setUndecorated(true);
        setLocationRelativeTo(null);
        //setVisible(true);
        getRootPane().setOpaque(false);
        setBackground(new Color(28,38,46,130));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void setToLowerRight(){
        Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setLocation(screenBounds.width-this.width,screenBounds.height-this.height);
    }
}
