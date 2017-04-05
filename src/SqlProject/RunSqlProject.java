package SqlProject;

public class RunSqlProject {

	/**
	 * Starta upp SqlGUI
	 * @param args
	 */
	public static void main(String[] args) {
//		SqlController sc = new SqlController();
		

	       javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {	            	
	    			//sc.initSystem();
	            	SqlGUI myGui = new SqlGUI();          	
	            }
	        });   
		
	}

}
