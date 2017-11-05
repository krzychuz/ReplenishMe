package gui;

import javax.swing.*;

/**
 * Created by Krzysiek on 05.11.2017.
 */
public class BrowseMrpList extends JFrame {

    public BrowseMrpList(){
        initUI();
    }

    private void initUI() {
        setTitle("ReplenishMe - Main menu");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
