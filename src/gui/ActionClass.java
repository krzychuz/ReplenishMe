package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Krzysiek on 05.11.2017.
 */
public class ActionClass implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = (e.getActionCommand());
        switch (action) {
            case "browseMaterialMaster":
                try{
                    GridView gv = new GridView();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            case "browseMrpList":
                MrpListBrowser mrp = new MrpListBrowser();
                break;
            default:
                break;
        }
    }

}
