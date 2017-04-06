package SqlProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlCityDAO implements SqlGenericDAOInterface{
	
	private List<City> cityList;
	private Connection connect;
	
	public SqlCityDAO() throws FileNotFoundException, IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		
		Class.forName("com.mysql.jdbc.Driver");
		/**
		 * Setting up the connection to the databas by reading the properties fail
		 */
		Properties prop = new Properties();
		prop.load(new FileInputStream("connect.properties"));
		connect = DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("user"), prop.getProperty("password"));
//		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "lexicon");
		

	}
	
	public void convertRowToCity(ResultSet rs) throws SQLException{
		while (rs.next()){
			cityList.add(new City(rs.getInt("ID"), rs.getString("name"), rs.getString("countryCode"), rs.getString("district"), rs.getInt("population")));
		}
		
	}
	
	@Override
	public List<City> getAll() throws SQLException{
		cityList = new ArrayList<City>();
		Statement stmt =null;
		ResultSet rs = null;

		try{
			stmt = connect.createStatement();
			rs = stmt.executeQuery("SELECT * from city");
			while(rs.next()){
				convertRowToCity(rs);
			}
			return cityList;
			
		}catch(Exception e){System.out.println("Exception [SqlCityDAO] :" +e);return cityList;}
		finally{
			cityList=null;
			rs.close();
			stmt.close();
		}
	}
	
	@Override
	public List<City> searchItem(String lName) throws Exception{
		cityList = new ArrayList<City>();
		PreparedStatement stmt =null;
		ResultSet rs = null;

		try{
			lName +="%";
			stmt = connect.prepareStatement("SELECT * from city where Name like ?");
			stmt.setString(1, lName);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				convertRowToCity(rs);
			}
			return cityList;
			
		}catch(Exception e){System.out.println("Exception [SqlCityDAO] :" +e);return cityList;}
		finally{
			cityList=null;
			rs.close();
			stmt.close();
		}
	}

}
