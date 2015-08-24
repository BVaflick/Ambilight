package light.ambi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AmbilightSystemTrayIcon {

    private PopupMenu popupMenu;

    private SystemTray systemTray;

    private TrayIcon trayIcon;

    private Image icon;

    private String title;

    public AmbilightSystemTrayIcon(String title, String iconImageAddress) {
        this.title = title;
        icon = new ImageIcon(this.getClass().getResource(iconImageAddress)).getImage();
        systemTray = SystemTray.getSystemTray();

        trayIcon = new TrayIcon(this.icon, this.title);
        popupMenu = new PopupMenu();
        trayIcon.setPopupMenu(popupMenu);
        try {
            systemTray.add(trayIcon);
        }catch(AWTException e){}

    }

    public MenuItem addPopupMenuItem(String itemName){
        MenuItem item = new MenuItem(itemName);
        popupMenu.add(item);
        return item;
    }
    public TrayIcon getTrayIcon(){
        return this.trayIcon;
    }

    public void setOpenOnClick(final AmbilightDialog dialog){
        this.trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(true);
                dialog.setToLowerRight();
            }
        });
    }
}
