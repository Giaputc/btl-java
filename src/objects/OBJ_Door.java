package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SupperObject{
    public  OBJ_Door(){
        name="Door";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
        collision=true;
}
}
