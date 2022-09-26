package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean leftPressed,upPressed,rightPressed,downPressed;
//    GamePanel gp;
//    KeyHandler(GamePanel gp){
//        this.gp = gp;
//    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case 37 -> leftPressed = true;
            case 38 -> upPressed = true;
            case 39 -> rightPressed = true;
            case 40 -> downPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case 37 -> leftPressed = false;
            case 38 -> upPressed = false;
            case 39 -> rightPressed = false;
            case 40 -> downPressed = false;
        }
    }
}
