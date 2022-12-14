package business;

import java.sql.SQLException;
import java.util.ArrayList;
import persistence.Database;

public class NoteBook{
	private static NoteBook noteBook = null;
	private ArrayList<Note> notes = new ArrayList<Note>(5);
	
	private NoteBook() {
		
	}

	
	
	public static NoteBook getInstance() {
        if (noteBook == null)
            noteBook = new NoteBook();
  
        return noteBook;
       }
	
	public static void getSavedNotes() {
		try {
			Database.loadNotes();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getSavedShapes() {
		try {
			Database.loadShapeNodes();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getSavedConenctions() {
		try {
			Database.loadConnections();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createNoteBookInDB() {
		try {
			Database.initialWorks();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Note> getnotes() {
		return notes;
	}

	public void setnotes(ArrayList<Note> notebook) {
		this.notes = notebook;
	}
	
}

