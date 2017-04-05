package SqlProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlCity {
	
	private Connection connect;
	
	public SqlCity() throws FileNotFoundException, IOException, SQLException{
		
		/**
		 * Setting up the connection to the databas by reading the properties fail
		 */
		Properties prop = new Properties();
		prop.load(new FileInputStream("connect.properties"));		
		connect = DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("user"), prop.getProperty("password"));
		
	}

}
