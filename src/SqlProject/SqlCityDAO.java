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
	public void updateCityToDB(City city){
		cityList = new ArrayList<City>();
		PreparedStatement pstmt =null;
		
		String insertSQL = "UPDATE city set Name =?, CountryCode=?, District=?, Population=? where ID like "+city.getId()+";";
				
		try{
			pstmt = connect.prepareStatement(insertSQL);
			pstmt.setString(1, city.getName());
			pstmt.setString(2, city.getCountryCode());
			pstmt.setString(3, city.getDistrict());
			pstmt.setInt(4, city.getPopulation());
			pstmt.executeUpdate();
		}catch(Exception e){System.out.println("Exception [SqlCityDAO] updateCityToDB :" +e);}
	}
	
	public void addNewCityToDB(City city){
		cityList = new ArrayList<City>();
		PreparedStatement pstmt =null;
		
		String insertSQL = "INSERT INTO city (ID, Name, CountryCode, District, Population) VALUES (?, ?, ?, ?, ?)";
				
		try{
			pstmt = connect.prepareStatement(insertSQL);
			pstmt.setInt(1, city.getId());
			pstmt.setString(2, city.getName());
			pstmt.setString(3, city.getCountryCode());
			pstmt.setString(4, city.getDistrict());
			pstmt.setInt(5, city.getPopulation());
			pstmt.executeUpdate();
		}catch(Exception e){System.out.println("Exception [SqlCityDAO] addNewCityToDB :" +e);}
	}
	
	public int getNewIndexInCityDB() throws SQLException{
		cityList = new ArrayList<City>();
		Statement stmt =null;
		ResultSet rs = null;
		int index=0;
		try{
			stmt = connect.createStatement();
			rs = stmt.executeQuery("SELECT ID FROM City ORDER BY ID DESC LIMIT 1;");
			while(rs.next()){
				System.out.println("RES :"+rs.getInt(1));
				index = rs.getInt(1)+1;
			}
			
			
		}catch(Exception e){System.out.println("Exception [SqlCityDAO] getAll:" +e);}
		finally{
			cityList=null;
			rs.close();
			stmt.close();
		}
		return index;
	}
	
	public void deleteCity(int id){
		PreparedStatement stmt =null;
		try{
			stmt = connect.prepareStatement("DELETE from city where id like ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}catch(Exception e){System.out.println("Exception [SqlCityDAO] delete :" +e);}
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
			
		}catch(Exception e){System.out.println("Exception [SqlCityDAO] getAll:" +e);return cityList;}
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
