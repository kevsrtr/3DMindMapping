package business;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import business.ShapeNode;
import ui.Home;


public class PopupMenu {

	public ContextMenu popup;
	private ShapeNode node;
	public  Menu editText = new Menu("Edit Text");
	public  Menu connect = new Menu("Connect");
	public  Menu delete = new Menu("Delete");
	public Menu extra = new Menu("Extra");

	public PopupMenu(ShapeNode node) {
		this.node = node;
		popup = new ContextMenu();
		popup.getItems().addAll(editText,connect,delete,extra);
		setHandleEditText();
		setHandleConnect();
		setHandleDelete();
		setHandleExtra();
		activatePopup();

		
	}

	public void setHandleEditText() {
		//set the edit text event
		popup.getItems().get(0).setOnAction(event ->{
			Stage textStage = new Stage();
			textStage.setWidth(200);
			textStage.setHeight(200);
			textStage.setScene(node.getEditScene());
			textStage.show();
			popup.hide();

		});
	
	}

	public void setHandleConnect() {
		//set the connection event
		popup.getItems().get(1).setOnAction(event -> {
			if(node.isConnectFlag()==false) {//that means connect is not pressed 
				System.out.println("Connect first");
				ShapeNode.setTarget(node);/*new DragObject(sphere.translateXProperty(),sphere.translateYProperty(),sphere.translateZProperty(),groupIndex);*/
				ShapeNode.setConnectFlag(true);//that means one node is selected for connecting
				System.out.println(node.isConnectFlag());
			}
			else {
				connect();
				ShapeNode.setConnectFlag(false);
				System.out.println("to second");
			}
			popup.hide();

		} );
	}

	public void connect() {
	ConnectUtility.connect(node, ShapeNode.getTarget());
	}

	public void setHandleDelete() {
		//set the delete event
		popup.getItems().get(2).setOnAction(event -> {	    

			
			node.getConnections().forEach(connection ->{

				int index = connection.getConnections().indexOf(node);
				connection.getConnections().remove(index);
				connection.getBonds().remove(index);

			});

			
			
			
			node.getNote().getGroup().getChildren().remove(node.getNode());
			node.getBonds().forEach(bond -> node.getNote().getGroup().getChildren().remove(node.getNote().getGroup().getChildren().indexOf(bond)));
			node.getNote().getGroup().getChildren().remove(node.getLabel());
			node.getBonds().clear();
			node.getConnections().clear();
			node.getNote().getElementIds().remove(node.getNote().getElements().indexOf(node));
			node.getNote().getElements().remove(node);
			popup.hide();

		});
	}

	public void setHandleExtra() {
		popup.getItems().get(3).setOnAction(event ->{
			Stage textStage = new Stage();
			textStage.setWidth(Home.screen_width/4);
			textStage.setHeight(Home.screen_height/3);
			textStage.setScene(node.getExtraScene());
			textStage.show();
			popup.hide();

		});
	}
	
	public void activatePopup() {

	    //add popup menu to the node 
		
	    node.getNode().setOnMouseClicked(e ->
	    {
	    	if(e.getButton() == MouseButton.SECONDARY)
	    	popup.show(node.getNode(),Side.RIGHT, 10, 10);
	    });
	    
	}
	
	public ContextMenu getPopup() {
		return popup;
	}

	public void setPopup(ContextMenu popup) {
		this.popup = popup;
	}


}
