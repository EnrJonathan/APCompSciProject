import java.awt.Color;
import java.awt.Graphics;

import java.awt.Rectangle;



public class Box extends GameObject{

public Color colorTemp;




public Box(int x, int y) {

super(x, y);



velX=1;

}





public void tick() {

x+=velX;

y+=velY;

}





public void render(Graphics g) {

g.setColor(colorTemp = new Color(218,9,233,175));

g.fillRect(x, y, 100, 100);


}





public Rectangle getBounds() {

return null;

}



}


