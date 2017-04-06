package SqlProject;

import javax.swing.JFrame;

public class RunSqlProject {

	/**
	 * Starta upp SqlGUI
	 * @param args
	 */
	public static void main(String[] args) {
		

	       javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {	            	
	            	SqlGUI myGui = new SqlGUI();    
	            	myGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            }
	        });   
		
	}

}
