package SqlProject;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class SqlGUI extends JFrame{
	private JTextField nametextField;
	private JTable table;
	private SqlController sc = new SqlController();
	
	public SqlGUI() {
		setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		setTitle("World search tool");
		
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) northPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		northPanel.setBackground(Color.BLACK);
		northPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(northPanel, BorderLayout.NORTH);
		
		JLabel getNameLabel = new JLabel("Enter name:");
		getNameLabel.setForeground(Color.WHITE);	
		getNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		nametextField = new JTextField();
		nametextField.setHorizontalAlignment(SwingConstants.LEFT);
		nametextField.setBackground(Color.WHITE);
		nametextField.setColumns(10);
		
		northPanel.add(getNameLabel);
		northPanel.add(nametextField);
		/**
		 * JButton Search
		 */
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked!");
				String lName = nametextField.getText();
				List<City> result=null;
				try{
					if(lName!=null && lName.trim().length()>0){
						result = sc.getAllCitiesFromList(lName);
					}
					else{
						result = sc.getAllCitiesFromList();
					}
					SqlCityTableModel cityModel = new SqlCityTableModel(result);
					table.setModel(cityModel);
					
					for(City tmp:result){
						System.out.println(""+tmp);
					}
				}catch(Exception e){System.out.println("Exception [GUI] :" +e);}
			}
		});
		btnSearch.setBackground(SystemColor.textHighlight);
		northPanel.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
		
		this.setSize(new Dimension(200, 100));
		this.pack();
        this.update(this.getGraphics());
        this.setVisible(true);
	}

}
