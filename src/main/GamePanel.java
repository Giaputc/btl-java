package  main;

import entity.player;
import objects.SupperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.Dimension;


public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int scale = 3;

    final public int tileSize = originalTileSize * scale;
    final public int maxScreenRow = 12;
    final public int maxScreenCol = 16;
    final public int screenWidth = tileSize * maxScreenCol; //768pixel
    final public int screenHeight = tileSize * maxScreenRow;//567pixel
   KeyHandle keyH = new KeyHandle();
   Sound music=new Sound();
   Sound se=new Sound();
   public player player = new player(this,keyH);
    Thread gameThread;
    public SupperObject obj[]=new SupperObject[10];
    public  CollisionChecker cChecker=new CollisionChecker(this);
    public AssetSetter aSetter=new AssetSetter(this);
    TileManager tileM = new TileManager(this);
    public UI ui=new UI(this);
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void setupGame(){
        aSetter.setObeject();
        playMusic(0);
    }
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxScreenCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;


    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // Thời gian vẽ mỗi khung hình (nanoseconds)
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update() {
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //Tile
        tileM.draw(g2);
        //object
        for (int i=0;i<obj.length;i++){
            if(obj[i]!=null){
                obj[i].draw(g2,this);
            }
        }
        //player
        player.draw(g2);
    //UI
        ui.draw(g2);
        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public  void  stopMusic(){
        music.stop();
    }
    public  void playSE(int i){
        se.setFile(i);
        se.play();
    }
}