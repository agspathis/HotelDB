package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import main.TableColumnAdjuster;


import com.mysql.jdbc.PreparedStatement;

/**
 * This class implements the behavior of executing a select statement and 
 * shows the result of the query in a separate window. Also calls Table Column
 * Adjuster to configure the size of the columns of the result
 * 
 * @author
 *
 */
public class DatabaseSelect extends Database {

    public DatabaseSelect(JTextArea textArea, String command) {
	super(command);
	this.textArea = textArea;
	// execute(command);
    }

    @Override
    public boolean execute() {

	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
	    ps = (PreparedStatement) connection.prepareStatement(command);
	    rs = ps.executeQuery();

	    textArea.append(command+"\n");
	    textArea.append("Query executed\n");

	    //show result
	    showResult(rs);
			
	    return true;

	} catch (SQLException e) {
	    textArea.append(e.getErrorCode() + "\n" + e.getMessage() + "\n"
			    + e.getSQLState() + "\n");
	    return false;
	} finally {

	    try {
		if (ps != null) {
		    ps.close();
		}
		if (connection != null) {
		    closeConnection();
		}

	    } catch (SQLException ex) {
		textArea.append(ex.getErrorCode() + "\n" + ex.getMessage()
				+ "\n" + ex.getSQLState() + "\n");
	    }
	}

    }

    private DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

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
	
    private void showResult(ResultSet rs) throws SQLException{
	//creates the table
	JTable table = new JTable(buildTableModel(rs));
	table.setEnabled(false);
					
	//abjust table size
	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	TableColumnAdjuster tca = new TableColumnAdjuster(table);
	tca.adjustColumns();	
			
	//JFrame
	JFrame view = new JFrame("View");
	view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	//add to frame
	JScrollPane pane = new JScrollPane(table);
	view.add(pane);
		
	//settings
	view.setVisible(true);
	view.setSize(table.getWidth(), 400);
    }

}
