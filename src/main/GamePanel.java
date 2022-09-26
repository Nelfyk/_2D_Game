package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize*scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize; // 768 pixels
    final int screenHeight = maxScreenRow * tileSize; // 576 pixels
    //FPS
    final int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    // Set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setFocusable(true);
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setDoubleBuffered(true);
        startGameThread();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override // !!!GAME LOOP!!! Option #2. ("Delta/Accumulator" method)
    public void run() {
        double drawInterval=1000000000/FPS; // 0.1666 seconds
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;
        while (gameThread!=null){        //пока поток существует
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            lastTime=currentTime;
            if(delta>=1){
                update(); // 1 UPDATE: update information such as character positions
                repaint(); // 2 DRAW: draw the screen with the update information
                delta--;
                System.out.println();
            }
        }
    }
    public void update(){
        if(keyH.leftPressed){
            playerX-=playerSpeed;
        } else if(keyH.upPressed){
            playerY-=playerSpeed;
        } else if(keyH.rightPressed){
            playerX+=playerSpeed;
        } else if(keyH.downPressed){
            playerY+=playerSpeed;
        }
    }
    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX,playerY,tileSize,tileSize);
        g2.dispose();
    }
}
