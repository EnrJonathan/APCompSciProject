import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
	
	
ArrayList<GameObject> object = new ArrayList<GameObject>();

private boolean up = false, down = false, right = false, left = false;

private boolean r = false;

private boolean i = false;

private boolean restart = false;

public void tick() {

for(int i = 0; i < object.size(); i++) {

GameObject tempObject = object.get(i);

tempObject.tick();

}

}


public ArrayList<GameObject> getObject() {
	return object;
}


public void setObject(ArrayList<GameObject> object) {
	this.object = object;
}


public boolean isUp() {
	return up;
}


public void setUp(boolean up) {
	this.up = up;
}


public boolean isDown() {
	return down;
}


public void setDown(boolean down) {
	this.down = down;
}


public boolean isLeft() {
	return left;
}


public void setLeft(boolean left) {
	this.left = left;
}


public boolean isRight() {
	return right;
}


public void setRight(boolean right) {
	this.right = right;
}


public void render (Graphics g) {

for(int i = 0; i < object.size(); i++) {

GameObject tempObject = object.get(i);

tempObject.render(g);

}

}


public void addObject(GameObject tempObject) {

object.add(tempObject);

}

public void setFirst(GameObject tempObject) {

object.add(0,tempObject);

}



public void removeObject(GameObject tempObject) {

object.remove(tempObject);
}



public boolean isReload() {
	return r;
}


public void setReload(boolean r) {
	this.r = r;
}


public boolean isInteract() {
	return i;
}


public void setInteract(boolean i) {
	this.i = i;
}

public boolean isReset() {
	return restart;
}


public void setReset(boolean restart) {
	this.restart = restart;
}


}


