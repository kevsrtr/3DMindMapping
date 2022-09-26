package business;


import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.shape.Cylinder;

import javafx.scene.text.Text;



public class CylinderNode extends ShapeNode{
	
	Cylinder cylinder;
	Text text;
	
	
	public CylinderNode(Note note){
		super(note,new Cylinder(100,120));
		type = 2;
		
	}
	
	public CylinderNode(ResultSet rs, Note note) throws SQLException {
		super(rs,note,new Cylinder(100,120));
		type = 2;
		
	}
	
}
