package light.ambi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AmbilightDialog extends JDialog {

    private int width;

    private int height;

    private int coordinateX;

    private int coordinateY;

    private AmbilightRectanglePanel rectanglePanel;


    public AmbilightDialog(int width, int height) {
        this.width = width;
        this.height = height;
        setSize(width, height);

        this.rectanglePanel = new AmbilightRectanglePanel(this.getSize(), 6, 4);

        setUndecorated(true);
        setLocationRelativeTo(null);
        getRootPane().setOpaque(false);
        setBackground(new Color(28, 38, 46, 130));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(this.rectanglePanel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                coordinateX = e.getX();
                coordinateY = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation((getX() + (e.getX() - coordinateX)) > ((GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width)-222) ?
                                ((GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width)-202) : (getX() + (e.getX() - coordinateX)),
                        (getY() + (e.getY() - coordinateY)) > ((GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height)- 138) ?
                                ((GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height)-118) : (getY() + (e.getY() - coordinateY)));
            }
        });
    }

    public void setToLowerRight() {
        Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setLocation(screenBounds.width - this.width, screenBounds.height - this.height);
    }

    public AmbilightRectanglePanel getRectanglePanel() {
        return rectanglePanel;
    }
}
