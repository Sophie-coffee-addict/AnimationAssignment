package BugWorld;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Plant extends Circle {

	private float df;
	private String name;

	public Plant(int x, int y, int radius, String name, float df, String imageName) {
		super(x, y, radius);
	
		this.name = name;
		this.df = df;
		
		 if (imageName.equals("grass.png")) {
	    	   this.setFill(new ImagePattern(new Image("grass.png")));
	    	  } else if (imageName.equals("blueberryTree.png")) {
	    	   this.setFill(new ImagePattern(new Image("blueberryTree.png")));
	    	  } else if (imageName.equals("flower.png")) {
	    		  this.setFill(new ImagePattern(new Image("flower.png")));
	    	  } else {
	    	   this.setFill(new ImagePattern(new Image("tree.png")));
	    	  }
	}

	public float getDf() {
		return df;
	}

	public void setDf(float df) {
		this.df = df;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void grow() {
//		if (this.getRadius() < 60) {
			this.setRadius(this.getRadius() + this.getDf());
//		}
	}
	
}
