package BugWorld;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BugWorldEventHandler implements EventHandler<MouseEvent> {

	public BugWorldEventHandler(Bug b) {
		
	}

	public BugWorldEventHandler() {

	}

	@Override
	public void handle(MouseEvent event) {
	    try {
	       
	        if (event.getSource() instanceof Bug) {
	            Bug bug = (Bug) event.getSource();
	            String bugInfo = bug.toString();
	            Main.bottomLabel.setText(bugInfo);
	        } else {
	            Main.bottomLabel.setText("Unknown source.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
