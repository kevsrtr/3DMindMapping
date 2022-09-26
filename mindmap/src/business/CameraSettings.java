package business;

import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.input.KeyEvent;



public class CameraSettings implements EventHandler<KeyEvent> {
	
	Camera camera;
	
	public CameraSettings(Camera camera){
		this.camera = camera;
	}

	@Override
	public void handle(KeyEvent event) {
		
		 switch (event.getCode()) {
         case W:
        	 camera.translateZProperty().set(camera.getTranslateZ() + 100);
           
             break;
         case S:
        	 camera.translateZProperty().set(camera.getTranslateZ() - 100);      
             break;
          default:
		 }
		 
		
	}

	
}
