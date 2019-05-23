import java.awt.Color;
import java.awt.Graphics;

import java.awt.Rectangle;



public class Box extends GameObject{

public Color colorTemp;




public Box(int x, int y) {

super(x, y);



//velY= 2;
velX=1;


}





public void tick() {

x+=velX;

y+=velY;

}





public void render(Graphics g) {

g.setColor(colorTemp = new Color(40,175,40,255));
g.fillRect(x, y, 25, 25);

}





public Rectangle getBounds() {

return null;

}



}


