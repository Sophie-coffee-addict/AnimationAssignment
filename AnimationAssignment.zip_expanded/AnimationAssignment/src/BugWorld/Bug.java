package BugWorld;

import javafx.scene.image.Image;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Bug extends Circle {
	
	float x = 100, y = 100, dx = -1.5f, dy = -1.5f;
	private String name;

	public Bug(float x, float y, int radius, float dx, float dy, String name, String imageName) {
		super(x, y, radius);
		
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.name = name;

	    if (imageName.equals("ant.png")) {
	    	   this.setFill(new ImagePattern(new Image("ant.png")));
	    	  } else if (imageName.equals("ladybug.png")) {
	    	   this.setFill(new ImagePattern(new Image("ladybug.png")));
	    	  } else if (imageName.equals("butterfly.png")) {
	    	   this.setFill(new ImagePattern(new Image("butterfly.png")));
	    	  } else {
	    	   this.setFill(new ImagePattern(new Image("spider.png")));
	    	  }
	}

	

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

	

	@Override
	public String toString() {
		
		return "Kia ora, my name is " + name + ", " + "and my position is " + x + " , "+y;
	}

	
}
