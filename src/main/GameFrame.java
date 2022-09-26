package main;

import javax.swing.*;

public class GameFrame extends JFrame {

    GameFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("2D Adventure");
        this.add(new GamePanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
