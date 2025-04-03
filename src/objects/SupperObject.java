package objects;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
public class SupperObject {
    public BufferedImage image;
    public String name;
    public boolean collision=false;
    public  int worldX,worldY;
    public  Rectangle solidArea =new Rectangle(0,0,48,48);
    public  int solidAreaDefaultX=0;
    public  int solidAreaDeFaultY=0;
    public void draw(Graphics2D g2, GamePanel gp){
        if (image == null) {
            System.out.println(name + " image is null!");
            return;
        }

        int ScreenX = worldX - gp.player.worldX + gp.player.ScreenX;
        int ScreenY = worldY - gp.player.worldY + gp.player.ScreenY;


        if(worldX + gp.tileSize> gp.player.worldX - gp.player.ScreenX && worldX  - gp.tileSize< gp.player.worldX + gp.player.worldX  && worldY + gp.tileSize > gp.player.worldY - gp.player.ScreenY && worldY - gp.tileSize< gp.player.worldY + gp.player.ScreenY){
            g2.drawImage(image,ScreenX,ScreenY,gp.tileSize,gp.tileSize,null);
        }
    }
}
