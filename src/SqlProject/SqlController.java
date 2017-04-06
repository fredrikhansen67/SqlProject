package SqlProject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SqlController {
	
	private SqlCityDAO city;
	private SqlCountry country;
	private SqlCountryLanguage language;
	
	
	public SqlController() {
		try{
			city = new SqlCityDAO();
			country = new SqlCountry();
			language = new SqlCountryLanguage();
		}
		catch(FileNotFoundException fe){System.out.println("file Exception"+fe);}
		catch(IOException io){System.out.println("io Exception");}
		catch(SQLException se){System.out.println("sql Exception");} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<City> getAllCitiesFromList(){
		List<City> rList=null;
		try{
		 rList= city.getAll();
		}catch(Exception e){System.out.println("Exception [SqlController] :"+e);}
		return rList;
		
	}
	
	public List<City> getAllCitiesFromList(String name){
		List<City> rList=null;
		try{
		 rList= city.searchItem(name);
		}catch(Exception e){System.out.println("Exception [SqlController] :"+e);}
		return rList;
		
	}
	
	

}
