package SqlProject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SqlController {
	
	private SqlCity city;
	private SqlCountry country;
	private SqlCountryLanguage language;
	
	
	public SqlController() {
		try{
			city = new SqlCity();
			country = new SqlCountry();
			language = new SqlCountryLanguage();
		}
		catch(FileNotFoundException fe){System.out.println("file Exception"+fe);}
		catch(IOException io){System.out.println("io Exception");}
		catch(SQLException se){System.out.println("sql Exception");}
	}
	
	public List<String> getAllCitiesFromList(){
		return (List) city.getAll();
	}
	
	

}
