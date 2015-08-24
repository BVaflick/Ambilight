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

    private BufferedImage screenShot;

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
        int index = 0;
        for(Rectangle2D rect : rectangleArray){
            //g2d.setColor(new Color(Math.random()));
            g2d.fill3DRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight(), true);
        }

    }

    private void updateColorArray(){
        this.colorArray = new Color[this.rectangleArray.size()];
        long timeout = System.currentTimeMillis();
        int colorIndex = 0;
        try{
            this.screenShot = getScreenShot();
            for(int xArea = 0; xArea < VERTICAL_AREAS_COUNT; xArea++){
                for(int yArea = 0; yArea < HORIZONTAL_AREAS_COUNT; yArea++){
                    colorArray[colorIndex] = getAverageColor(xArea, yArea);
                    colorIndex++;
                }
            }

        }catch(Exception ex){}

    }

    private BufferedImage getScreenShot() throws AWTException{
        Robot robot = new Robot();
        Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(area);
    }

    private Color getAverageColor(int x, int y){
        int areaWidth = screenShot.getWidth() / HORIZONTAL_AREAS_COUNT;
        int areaHeight = screenShot.getHeight() / VERTICAL_AREAS_COUNT;
        return Color.red;
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

