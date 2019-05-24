import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Rectangle;


public class Zombie extends GameObject{
public Color colorTemp;


public Zombie(int x, int y) {
super(x, y);
//velY= 2;
velX=(int)(Math.random()*5)+1;
}


public void tick() {
x+=velX;
y+=velY;
}


public void render(Graphics g) {
Font fontTemp2;
g.setColor(colorTemp = new Color(40,175,40,255));
g.fillRect(x, y, 10, 10);
g.setFont(fontTemp2 = new Font("LucidaSans", Font.BOLD, 7));
g.setColor(Color.white);
g.drawString("Z",x+2,y+5);
}


public Rectangle getBounds() {
return null;
}
}


