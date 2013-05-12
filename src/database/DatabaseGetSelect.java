package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextArea;

import com.mysql.jdbc.PreparedStatement;

public class DatabaseGetSelect extends Database {

	ResultSet rs = null;

	public DatabaseGetSelect(JTextArea textArea, String command) {
		super(command);
		this.textArea = textArea;
		// execute(command);
	}

	@Override
	public boolean execute() {

		PreparedStatement ps = null;

		try {
			ps = (PreparedStatement) connection.prepareStatement(command);
			rs = ps.executeQuery();
			textArea.append(command+"\n");
			return true;
		} catch (SQLException e) {
			textArea.append(e.getErrorCode() + "\n" + e.getMessage() + "\n"
					+ e.getSQLState() + "\n");
			return false;
		}
	}

	public ResultSet getResult() {
		if (connectionState == true && execute()) {
			return this.rs;
		} else {
			return null;
		}
	}

	public void closeAll() {
		if (rs != null) {
			try {
				rs.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		closeConnection();

	}

}
