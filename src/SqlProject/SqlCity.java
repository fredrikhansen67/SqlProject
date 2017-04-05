package SqlProject;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlCity {
	
	private List cityList;
	private Connection connect;
	
	public SqlCity() throws FileNotFoundException, IOException, SQLException{
		
		/**
		 * Setting up the connection to the databas by reading the properties fail
		 */
//		Properties prop = new Properties();
//		prop.load(new FileInputStream("connect.properties"));
//		connect = DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("user"), prop.getProperty("password"));
//		
	}
	
	public List getAll(){
		cityList.add("kalle");
		cityList.add("35");
		cityList.add("skitsur!");
		return cityList;
	}

}
