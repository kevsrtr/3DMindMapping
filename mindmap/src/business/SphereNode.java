package business;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;


public class SphereNode extends ShapeNode {
	
	
	public SphereNode(Note note){
		super(note,new Sphere(100));
		type = 1;
	
	}
	
	public SphereNode(ResultSet rs,Note note) throws SQLException {
		super(rs,note,new Sphere(100));
		type = 1;
		
	}
	
	
}
	

