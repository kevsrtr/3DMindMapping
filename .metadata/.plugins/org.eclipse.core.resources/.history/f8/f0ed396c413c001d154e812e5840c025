package business;





import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.shape.Box;




public class BoxNode extends ShapeNode{
	Box box;
	
	
	public BoxNode(Note note){
		super(note,new Box(160,160,160));
		type = 3;
	}
	
	public BoxNode(ResultSet rs,Note note) throws SQLException {
		super(rs,note,new Box(160,160,160));
		type = 3;
		
	}
}
