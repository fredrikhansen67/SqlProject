package SqlProject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SqlController {
	
	private SqlCityDAO city;
	private SqlCountryDAO countryDAO;
	private SqlCountryLanguage language;
	
	
	public SqlController() {
		try{
			city = new SqlCityDAO();
			countryDAO = new SqlCountryDAO();
			setCountry(new SqlCountryDAO());
			setLanguage(new SqlCountryLanguage());
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
	
	public String getRowSelectedContentFromCountry(String countryCode){
//		List<country> rList=null;
		String str="?";
		try{			
		 str =countryDAO.getSelected(countryCode);
		 return str;
		}catch(Exception e){System.out.println("Exception [SqlController] getRowSelectedContentFromCountry :"+e);return " ? ";}
		
	
	}
	
	public void addNewCitytoDB(String name, String countryCode, String district, int population){
		try{
			int lastIndex = city.getNewIndexInCityDB();
			city.addNewCityToDB(new City(lastIndex,  name, countryCode, district,  population));
		}catch(Exception e){System.out.println("Exception [addNewCitytoDB] :"+e);}
		
	}
	public void updateNewCitytoDB(int id,String name, String countryCode, String district, int population){
		try{
			city.updateCityToDB(new City(id,  name, countryCode, district,  population));
		}catch(Exception e){System.out.println("Exception [addNewCitytoDB] :"+e);}
		
	}
	
	public List<?> getCountryCodeList(){
		try{
			return countryDAO.getCountryCodes();
		}catch(Exception e){System.out.println("Exception [getCountryCodeList] :"+e);return null;}
	}
	
	public void deleteItemFromCity(int id){
		city.deleteCity(id);
	}

	public SqlCountryDAO getCountry() {
		return countryDAO;
	}

	public void setCountry(SqlCountryDAO sqlCountry) {
		this.countryDAO = sqlCountry;
	}

	public SqlCountryLanguage getLanguage() {
		return language;
	}

	public void setLanguage(SqlCountryLanguage language) {
		this.language = language;
	}
	
	

}
