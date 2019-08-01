import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Chairs extends GameObject {
	
	private BufferedImage chair_image;

	public Chairs(int x, int y, ID id,SpriteSheet ss) {
		super(x, y, id,ss);
		chair_image=ss.grabImage(1,7,30,30);
		}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(chair_image,x,y,null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle (x,y,30,30) ;
	}

}
