
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class DamagePerk extends GameObject {
	
	private BufferedImage damagePerk_image;

	public DamagePerk(int x, int y, ID id,SpriteSheet ss) {
		super(x, y, id,ss);
		
		damagePerk_image = ss.grabImage(3, 3, 20, 20);

	}

	
	public void tick() {
		
	}

	
	public void render(Graphics g) {
		g.drawImage(damagePerk_image,x,y,null);
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x,y,20,20);
	}

}