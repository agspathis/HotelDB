package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

public class DatabaseSelect extends Database{
	
	
	public DatabaseSelect(JTextArea textArea, String command){
		super(command);
		this.textArea = textArea;
		//execute(command);
	}

	@Override
	public boolean execute() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = (PreparedStatement) connection.prepareStatement(command);
			rs = ps.executeQuery();
			
			textArea.append("Query executed\n");
			
			// it creates and displays the table
		    JTable table = new JTable(buildTableModel(rs));
		    JOptionPane.showMessageDialog(null, new JScrollPane(table), "View", 
		    		JOptionPane.DEFAULT_OPTION);
		    return true;
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			textArea.append(e.getErrorCode()+"\n"+e.getMessage()+
							"\n"+e.getSQLState()+"\n");
			return false;
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
	
	private DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}

}
