package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(10, 20, 28, 28);

        setDefault();
        getPlayerImage();
    }

    public void setDefault() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            left1 = ImageIO.read(getClass().getResourceAsStream(("/player/boy_left_1.png")));
            left2 = ImageIO.read(getClass().getResourceAsStream(("/player/boy_left_2.png")));
            up1 = ImageIO.read(getClass().getResourceAsStream(("/player/boy_up_1.png")));
            up2 = ImageIO.read(getClass().getResourceAsStream(("/player/boy_up_2.png")));
            right1 = ImageIO.read(getClass().getResourceAsStream(("/player/boy_right_1.png")));
            right2 = ImageIO.read(getClass().getResourceAsStream(("/player/boy_right_2.png")));
            down1 = ImageIO.read(getClass().getResourceAsStream(("/player/boy_down_1.png")));
            down2 = ImageIO.read(getClass().getResourceAsStream(("/player/boy_down_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.leftPressed || keyH.upPressed || keyH.rightPressed || keyH.downPressed) {
            if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.rightPressed) {
                direction = "right";
            } else if (keyH.downPressed) {
                direction = "down";
            }
            //CHECK TILE COLLISION
            colisionOn = false;
            gp.collisionChecker.checkTile(this);
            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!colisionOn) {
                switch (direction) {
                    case "left" -> worldX -= speed;
                    case "up" -> worldY -= speed;
                    case "right" -> worldX += speed;
                    case "down" -> worldY += speed;

                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                } else image = left2;
            }
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                } else image = up2;
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                } else image = right2;
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                } else image = down2;
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
