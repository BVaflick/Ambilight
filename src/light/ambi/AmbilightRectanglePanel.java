package light.ambi;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AmbilightRectanglePanel extends JPanel {

    public static int HORIZONTAL_AREAS_COUNT = 8;

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
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int index = 0;
        updateColorArray();
        for(Rectangle2D rect : rectangleArray) {
            g2d.setColor(colorArray[index]);
            g2d.fill3DRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight(), true);
            index++;
        }
    }

    private void updateColorArray() {
        this.colorArray = new Color[this.rectangleArray.size()];
        long timeout = System.currentTimeMillis();
        int colorIndex = 0;
        try {
            this.screenShot = getScreenShot();
            for(int xArea = 0; xArea < HORIZONTAL_AREAS_COUNT; xArea++) {
                for(int yArea = 0; yArea < VERTICAL_AREAS_COUNT; yArea++) {
                    if(xArea == 0 || yArea == 0 || xArea == HORIZONTAL_AREAS_COUNT-1 || yArea == VERTICAL_AREAS_COUNT-1) {
                        colorArray[colorIndex] = getAverageColor(xArea, yArea);
                        colorIndex++;
                    }
                }
            }
            System.out.println(System.currentTimeMillis() - timeout);
        }catch(Exception ex){
            System.out.println("SMTH's wrong: ");
            ex.printStackTrace();
        }
    }

    private BufferedImage getScreenShot() throws AWTException {
        Robot robot = new Robot();
        Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(area);
    }

    private Color getAverageColor(int xArea, int yArea) {
        int areaWidth = screenShot.getWidth() / HORIZONTAL_AREAS_COUNT;
        int areaHeight = screenShot.getHeight() / VERTICAL_AREAS_COUNT;
        int pixelRGB;
        int pixelCounter = 0;
        int[] RGBArray = {0,0,0};
        for(int column = (areaWidth * xArea); column < (areaWidth * (xArea+1)); column += 4) {
            for(int row = (areaHeight * yArea); row < (areaHeight * (yArea+1)); row += 4) {
                pixelRGB = screenShot.getRGB(column, row);
                addRGBToArray(pixelRGB, RGBArray);
                pixelCounter++;
            }
        }
        return new Color(RGBArray[0] / pixelCounter, RGBArray[1] / pixelCounter, RGBArray[2] / pixelCounter);
    }

    private void addRGBToArray(int rgb, int[] RGBArray) {
        for(int i = 0; i < 3; i++){
            RGBArray[2-i] += (rgb >> (i*8)) & 255;
        }
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

