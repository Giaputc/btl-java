package  entity;

import main.GamePanel;
import main.KeyHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public  class player extends Entity{
GamePanel gp;
KeyHandle keyH;
public final int ScreenX;
public final int ScreenY;
    public player(GamePanel gp,KeyHandle keyH){
    this.gp = gp;
    this.keyH = keyH;
    ScreenX = gp.screenWidth/2 - (gp.tileSize/2);
    ScreenY = gp.screenHeight/2 - (gp.tileSize/2);
    solidArea=new Rectangle();
    solidArea.x=8;
    solidArea.y=16;
    solidArea.width=32;
    solidArea.height=32;

    setDefaultValue();
    getPlayerImage();
}
public void setDefaultValue(){
    worldX = gp.tileSize*22;
    worldY = gp.tileSize*24;
    speed = 4;
    direction = "down";
    lastDirection = "down0";
    System.out.println("Starting position: (" + worldX + ", " + worldY + ")");

}
public void getPlayerImage(){
    try{
        down0 = ImageIO.read(getClass().getResourceAsStream("/boy/down0.png"));
        down1 = ImageIO.read(getClass().getResourceAsStream("/boy/down1.png"));
        down2 = ImageIO.read(getClass().getResourceAsStream("/boy/down3.png"));
        up0 = ImageIO.read(getClass().getResourceAsStream("/boy/up0.png"));
        up1 = ImageIO.read(getClass().getResourceAsStream("/boy/up1.png"));
        up2 = ImageIO.read(getClass().getResourceAsStream("/boy/up3.png"));
        left0 = ImageIO.read(getClass().getResourceAsStream("/boy/left1.png"));
        left1 = ImageIO.read(getClass().getResourceAsStream("/boy/left2.png"));
        left2 = ImageIO.read(getClass().getResourceAsStream("/boy/left3.png"));
        right0 = ImageIO.read(getClass().getResourceAsStream("/boy/right2.png"));
        right1 = ImageIO.read(getClass().getResourceAsStream("/boy/right1.png"));
        right2 = ImageIO.read(getClass().getResourceAsStream("/boy/right2.png"));
//        down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
//        down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
//        up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
//        up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
//        left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
//        left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
//        right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
//        right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
    }catch (IOException e){
        e.printStackTrace();
    }
}
    public void update() {
        boolean isMoving =false ;
        if(keyH.upPressed || keyH.rightPressed || keyH.leftPressed || keyH.downPressed){
            if(keyH.upPressed){
                direction="up";

            }
            else if(keyH.downPressed){

                direction="down";
            }
            else if(keyH.leftPressed){

                direction="left";
            }
            else  if(keyH.rightPressed){

                direction="right";
            }//Check tile collision
            collisionOn=false;
            gp.cChecker.checkTile(this);
            //if collision is false,Playẻr can move
            if (collisionOn==false){
                switch (direction){
                    case "up": worldY -= speed;break;
                    case "down": worldY += speed;break;
                    case  "left": worldX -= speed;break;
                    case  "right": worldX += speed;break;
                }
            }
            lastDirection = direction;
            isMoving = true;
        spriteCounter ++;
        if(spriteCounter > 10){
           spriteNumber=(spriteNumber == 1)?2:1;
            spriteCounter = 0;
        }
        }
        if(!isMoving){
            spriteNumber = 0;
        }

    }
    public void draw (Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNumber == 1){
                    image = up1;
                }else if(spriteNumber == 2){
                    image = up2;
                }
                break;
            case "left" :
                if(spriteNumber == 1){
                image = left1;
                }else if(spriteNumber == 2){
                image = left2;
                }
                break;
            case  "right":
                if(spriteNumber == 1){
                image = right1;
                }else if(spriteNumber == 2){
                image = right2;
                }
                break;
            case "down":
                if(spriteNumber == 1){
                image = down1;
                }else if(spriteNumber == 2){
                image = down2;
                }
                break;
        }
        if (spriteNumber == 0) { // Khi dừng lại, vẽ frame đầu tiên của hướng cuối cùng
            switch (lastDirection) {
                case "up":
                    image = up0;
                    break;
                case "down":
                    image = down0;
                    break;
                case "left":
                    image = left0;
                    break;
                case "right":
                    image = right0;
                    break;
            }
        }
//        g2.setColor(Color.WHITE);
        g2.drawImage(image,ScreenX,ScreenY,gp.tileSize,gp.tileSize,null);
    }
}
