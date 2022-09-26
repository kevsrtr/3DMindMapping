package business;
//based on: https://netzwerg.ch/blog/2015/03/22/javafx-3d-line/
import javafx.geometry.Point3D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import business.ShapeNode;


public class ConnectUtility {

	private ConnectUtility() {

	}


	public static void connect(ShapeNode start, ShapeNode end) {
		if(start.getConnections().contains(end)) {
			return;
		}
		Cylinder cylinder= new Cylinder();

		Point3D point1 = new Point3D(start.getNode().translateXProperty().doubleValue(),start.getNode().translateYProperty().doubleValue(),start.getNode().translateZProperty().doubleValue());
		Point3D point2 = new Point3D(end.getNode().translateXProperty().doubleValue(),end.getNode().translateYProperty().doubleValue(),end.getNode().translateZProperty().doubleValue());

		Point3D aligment = point1.subtract(point2);

		Point3D ax = aligment.crossProduct(0, 1,0);



		Point3D pivot = new Point3D(point1.midpoint(point2).getX(),point1.midpoint(point2).getY(),point1.midpoint(point2).getZ());


		cylinder.setHeight(aligment.magnitude());
		cylinder.setRadius(5);
		cylinder.translateXProperty().set(pivot.getX());
		cylinder.translateYProperty().set(pivot.getY());
		cylinder.translateZProperty().set(pivot.getZ());

		double angle = Math.acos(aligment.normalize().dotProduct(0,1,0));//normalize etmeden dene

		Rotate rotate= new Rotate(-Math.toDegrees(angle), ax);
		cylinder.getTransforms().add(rotate);
		start.getNote().getGroup().getChildren().add(cylinder);




		//add the connection to node's connection lists
		start.getBonds().add(cylinder);
		end.getBonds().add(cylinder);

		//
		start.getConnections().add(end);
		end.getConnections().add(start);

		//set delete popup for bond
		ContextMenu popup = new ContextMenu();

		Menu delete = new Menu("Delete");
		popup.getItems().add(delete);


		cylinder.setOnMousePressed(event -> popup.show(cylinder,cylinder.translateXProperty().doubleValue(),cylinder.translateYProperty().doubleValue()));
		popup.getItems().get(0).setOnAction(event -> {
			//delete from start node
			int index = start.getBonds().indexOf(cylinder);//?
			start.getConnections().remove(index);
			start.getBonds().remove(index);
			//delete from end node
			index = end.getBonds().indexOf(cylinder);
			end.getBonds().remove(index);
			end.getConnections().remove(index);
			//delete from group
			start.getNote().getGroup().getChildren().remove(cylinder);
		});


	}
}
