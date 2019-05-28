import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Player extends Rectangle{
public Color colorTemp;

private int dx,dy;

public Player(int x, int y, int width, int height, int dx, int dy) {
setBounds(x,y,width,height);
this.dx=dx;
this.dy=dy;
}


public void tick() {
this.x+=dx;
this.y+=dy;
}


public void render(Graphics g) {
g.setColor(colorTemp = new Color(244,241,66,255));
g.fillOval(x, y, 20, 20);

}


public Rectangle getBounds() {
return null;
}


public void setDx(int dx) {
	this.dx = dx;
}


public void setDy(int dy) {
	this.dy = dy;
}
}
