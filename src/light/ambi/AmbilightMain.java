package light.ambi;

import java.awt.event.*;

public class AmbilightMain {

    private AmbilightDialog mainDialog;

    private AmbilightSystemTrayIcon trayIcon;

    public AmbilightMain(AmbilightDialog dialog, AmbilightSystemTrayIcon trayIcon){
        this.mainDialog = dialog;
        this.trayIcon = trayIcon;
    }

    public void ololo(){}

    public static void main(String[] args) {

        final AmbilightMain benjilight = new AmbilightMain(new AmbilightDialog(202,118), new AmbilightSystemTrayIcon("benjilight","Icon.png"));

        benjilight.getTrayIcon().addPopupMenuItem("Exit").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /*final Thread repaintThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    benjilight.getDialog().getRectanglePanel().repaint();
                    try {
                        Thread.sleep(40);
                    } catch (Exception ex) {}
                }
            }
        });
        repaintThread.start();*/

        benjilight.getDialog().addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                benjilight.getDialog().setVisible(false);

            }
        });

        benjilight.getTrayIcon().getTrayIcon().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                benjilight.getDialog().setVisible(true);
                benjilight.getDialog().setToLowerRight();
                benjilight.getDialog().getRectanglePanel().repaint();
            }
        });
    }

    public AmbilightDialog getDialog() {
        return mainDialog;
    }

    public AmbilightSystemTrayIcon getTrayIcon() {
        return trayIcon;
    }
}
