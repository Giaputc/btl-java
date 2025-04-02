package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
   public Tile[] tile;
  public int mapTileNum[][];


    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/map/world01.txt");
    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/title/grass.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/title/water.png"));
            tile[2].collision=true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/title/wall.png"));
            tile[1].collision=true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/title/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/title/tree.png"));
            tile[4].collision=true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/title/sand.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String FileName){
        try{
            InputStream is = getClass().getResourceAsStream(FileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for(int row = 0 ; row < gp.maxWorldRow; row++){
                String line  = br.readLine();
                for(int col = 0; col < gp.maxWorldCol; col ++){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row ] = num;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2){

        for(int row = 0; row < gp.maxWorldRow; row ++){
            for(int col = 0; col < gp.maxWorldCol; col ++){
                int tileNum = mapTileNum[col][row];
                int worldX = col * gp.tileSize;
                int worldY = row * gp.tileSize;
                int ScreenX = worldX - gp.player.worldX + gp.player.ScreenX;
                int ScreenY = worldY - gp.player.worldY + gp.player.ScreenY;
                if(worldX + gp.tileSize> gp.player.worldX - gp.player.ScreenX && worldX  - gp.tileSize< gp.player.worldX + gp.player.worldX  && worldY + gp.tileSize > gp.player.worldY - gp.player.ScreenY && worldY - gp.tileSize< gp.player.worldY + gp.player.ScreenY){
                    g2.drawImage(tile[tileNum].image,ScreenX,ScreenY,gp.tileSize,gp.tileSize,null);
                }
            }
        }
    }
}