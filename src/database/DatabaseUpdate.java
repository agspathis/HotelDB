package database;

import java.sql.SQLException;

import javax.swing.JTextArea;

import com.mysql.jdbc.PreparedStatement;

public class DatabaseUpdate extends Database{

	public DatabaseUpdate(JTextArea textArea, String command){
		super();
		this.textArea = textArea;
		execute(command);
	}
	
	@Override
	public void execute(String command) {
		PreparedStatement ps = null;
		
		try {
			ps = (PreparedStatement) connection.prepareStatement(command);
			ps.executeUpdate();
			
			textArea.append("Record updated\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			textArea.append(e.getErrorCode()+"\n"+e.getMessage()+
					"\n"+e.getSQLState()+"\n");
			
		}finally {

            try {
                if (ps != null) {
                	ps.close();
                }
                if (connection != null) {
                    closeConnection();
                }

            } catch (SQLException ex) {
            	// TODO Auto-generated catch block
            	textArea.append(ex.getErrorCode()+"\n"+ex.getMessage()+
            			"\n"+ex.getSQLState()+"\n");
            }
        }
		
	}

}
