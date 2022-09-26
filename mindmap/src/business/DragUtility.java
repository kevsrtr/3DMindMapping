package business;

import java.util.ArrayList;
import javafx.scene.input.MouseButton;
import business.ShapeNode;


public class DragUtility {

	private DragUtility() {

	}

	public static void makeDraggable(ShapeNode shapeNode) {


		//go to z coordinate of pressed node
		shapeNode.getNode().setOnMousePressed(event ->
		{	
			if(event.getButton() == MouseButton.PRIMARY)

				shapeNode.getNote().getSubscene().getCamera().translateZProperty().set(shapeNode.getNode().translateZProperty().doubleValue());
		});



		shapeNode.getNode().setOnMouseDragged(event -> {

			shapeNode.getNode().setTranslateX(event.getSceneX());
			shapeNode.getNode().setTranslateY(event.getSceneY());
			shapeNode.getNode().setTranslateZ(shapeNode.getNote().getSubscene().getCamera().translateZProperty().doubleValue());

			//re-bond the nodes 
			//1-get the connections
			//2-delete old bonds with connections from ShapeNode and currentGroup
			//3-create new bonds
			ArrayList<ShapeNode> list = new ArrayList<ShapeNode>(shapeNode.getConnections().size());
			shapeNode.getConnections().forEach(node -> { list.add(node);});


			shapeNode.getConnections().forEach(connection ->{

				int index = connection.getConnections().indexOf(shapeNode);

				connection.getConnections().remove(index);
				connection.getBonds().remove(index);

			});

			//remove bonds from screen(group)
			shapeNode.getBonds().forEach(bond -> shapeNode.getNote().getGroup().getChildren().remove(shapeNode.getNote().getGroup().getChildren().indexOf(bond)));
			//remove node's data
			shapeNode.getBonds().clear();
			shapeNode.getConnections().clear();

			//set new connections.

			list.forEach(node -> ConnectUtility.connect(shapeNode, node));


			event.consume();
		});//setOnMouseDraggedEvent





	}

}

