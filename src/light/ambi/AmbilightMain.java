package light.ambi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AmbilightMain {
    public static void main(String[] args) {
        final AmbilightDialog d= new AmbilightDialog(200,100);
        AmbilightSystemTrayIcon t = new AmbilightSystemTrayIcon("b","Icon.png");
        t.addPopupMenuItem("Exit").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        t.setOpenOnClick(d);
        d.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                d.setVisible(false);
            }
        });
    }
}
