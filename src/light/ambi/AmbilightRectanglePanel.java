package light.ambi;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AmbilightRectanglePanel extends JPanel {

    public static int HORIZONTAL_AREAS_COUNT = 6;

    public static int VERTICAL_AREAS_COUNT = 4;

    private final int indent = 5;

    private int panelWidth;

    private int panelHeight;

    private int rectangleWidth;

    private int rectangleHeight;

    private ArrayList<Rectangle2D> rectangleArray;

    private Color[] colorArray;

    public AmbilightRectanglePanel(Dimension dialogDimension) {
        this.panelWidth = (int) dialogDimension.getWidth();
        this.panelHeight = (int) dialogDimension.getHeight();
        this.rectangleWidth = (this.panelWidth - 2 * indent) / HORIZONTAL_AREAS_COUNT;
        this.rectangleHeight =  (this.panelHeight - 2 * indent) / VERTICAL_AREAS_COUNT;
        createRectArray();
        updateColorArray();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        for(Rectangle2D rect : rectangleArray){
            g2d.fill3DRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight(), true);
        }

    }

    private void updateColorArray(){
        this.colorArray = new Color[this.rectangleArray.size()];
        long timeout = System.currentTimeMillis();
        try{
            BufferedImage screenShot = getScreenShot();

        }catch(Exception ex){}

    }

    public BufferedImage getScreenShot() throws AWTException{
        Robot robot = new Robot();
        Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(area);
    }

    private void createRectArray() {
        this.rectangleArray = new ArrayList<Rectangle2D>();
        for(int i = indent; i <= (panelWidth - indent - rectangleWidth); i += rectangleWidth) {
            for(int j = indent; j <= (panelHeight - indent - rectangleHeight); j += rectangleHeight) {
                if( i == indent | j == indent | i == panelWidth - rectangleWidth - indent | j == panelHeight - rectangleHeight - indent)
                    this.rectangleArray.add(new Rectangle2D.Double(i,j,rectangleWidth,rectangleHeight));
            }
        }
    }
}

