package business;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import business.DragUtility;
import business.PopupMenu;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.shape.Cylinder;
import javafx.scene.text.Font;


public class ShapeNode {
	private static ShapeNode target;
	private static boolean connectFlag= false;
	private static int shapeCounter=-1;//save the value to a text file
	protected int type;//1 for sphere,2 for cylinder, 3 for box.
	protected Node node;
	protected ArrayList<Cylinder> bonds = new ArrayList<Cylinder>(5);
	protected ArrayList<ShapeNode> connections = new ArrayList<ShapeNode>(5);
	protected Note note;
	protected Label label;
	private TextArea textExtra;
	private Scene extraScene;
	private TextArea textEdit;
	private Scene editScene;


	PopupMenu popup;

	private int id;

	ShapeNode(){

		textExtra = new TextArea();
		textExtra.setTextFormatter(new TextFormatter<String>(change -> 
		change.getControlNewText().length() <= 300 ? change : null));
		extraScene = new Scene(textExtra);

		textEdit = new TextArea();
		editScene = new Scene(textEdit);
		textEdit.setTextFormatter(new TextFormatter<String>(change -> 
		change.getControlNewText().length() <= 40 ? change : null));

	}

	ShapeNode(Note note,Node node){

		this();
		id = ++shapeCounter;
		this.node = node;
		this.note = note;
		note.getElements().add(this);
		note.getElementIds().add(id);
		note.getGroup().getChildren().add(node);

		////set the initial position
		node.translateXProperty().set(20);
		node.translateYProperty().set(20);

		label=new Label();

		note.getGroup().getChildren().add(label);

		label.setFont(Font.font(20));
		label.translateXProperty().bind(node.translateXProperty().subtract(55));

		label.translateYProperty().bind(node.translateYProperty().subtract(40));
		label.translateZProperty().bind(node.translateZProperty().add(-105));
		label.setMaxWidth(135);
		label.setWrapText(true);
		label.textProperty().bind(textEdit.textProperty());

		node.translateZProperty().set(note.getSubscene().getCamera().getTranslateZ());
		this.popup = new PopupMenu(this);
		DragUtility.makeDraggable(this);

	}

	ShapeNode(ResultSet rs,Note note, Node node) throws SQLException{

		this();
		this.node = node;
		this.note = note;
		id = rs.getInt(1);//setting id 
		note.getElements().add(this);
		note.getElementIds().add(id);
		note.getGroup().getChildren().add(node);


		//set the position of the node
		node.translateXProperty().set(rs.getInt(4));		
		node.translateYProperty().set(rs.getInt(5));
		node.translateZProperty().set(rs.getInt(6));


		label = new Label(rs.getString(7)); //set the label of the node
		note.getGroup().getChildren().add(label);
		label.translateXProperty().bind(node.translateXProperty().subtract(55));
		label.translateYProperty().bind(node.translateYProperty().subtract(40));
		label.translateZProperty().bind(node.translateZProperty().add(-105));
		label.setFont(Font.font(20));
		label.setMaxWidth(135);	
		label.setWrapText(true);
		label.textProperty().bind(textEdit.textProperty());

		textExtra.setText(rs.getString(8));//set the extra of the node.
		this.popup = new PopupMenu(this);
		DragUtility.makeDraggable(this);
		
		textEdit.setText(rs.getString(7));

	}



	public static int getShapeCounter() {
		return shapeCounter;
	}

	public static void setShapeCounter(int shapeCounter) {
		ShapeNode.shapeCounter = shapeCounter;
	}

	public boolean isConnectFlag() {
		return connectFlag;
	}

	public static void setTarget(ShapeNode target) {
		ShapeNode.target = target;
	}


	public ArrayList<Cylinder> getBonds() {
		return bonds;
	}

	public void setBonds(ArrayList<Cylinder> bonds) {
		this.bonds = bonds;
	}


	public ArrayList<ShapeNode> getConnections() {
		return connections;
	}

	public void setConnections(ArrayList<ShapeNode> connections) {
		this.connections = connections;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}


	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public static void setConnectFlag(boolean flag) {
		connectFlag = flag;
	}

	public static ShapeNode getTarget() {
		return target;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public TextArea getTextExtra() {
		return textExtra;
	}

	public void setTextExtra(TextArea textExtra) {
		this.textExtra = textExtra;
	}


	public Scene getExtraScene() {
		return extraScene;
	}

	public void setExtraScene(Scene extraScene) {
		this.extraScene = extraScene;
	}

	public Scene getEditScene() {
		return editScene;
	}

	public void setEditScene(Scene editScene) {
		this.editScene = editScene;
	}


}
