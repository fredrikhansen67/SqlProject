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
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SqlGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3437348080974745086L;
	/**
	 * 
	 */
	private JTextField nametextField;
	private JTable table;
	private SqlController sc = new SqlController();
	private JTextField rowTextField;
	private SqlCityTableModel cityModel;
	
	public SqlGUI() {
		getContentPane().setBackground(SystemColor.desktop);
		setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		setTitle("World search tool");
		
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(SystemColor.desktop);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		rowTextField = new JTextField();
		rowTextField.setEditable(false);
		rowTextField.setBackground(SystemColor.desktop);
		rowTextField.setForeground(SystemColor.window);
		southPanel.add(rowTextField);
		rowTextField.setColumns(60);
		
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
		JButton btnDeleteCity = new JButton("Delete City");
		btnDeleteCity.setEnabled(false);
		/**
		 * JButton Search
		 */
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setColumnSelectionAllowed(false);
				table.setRowSelectionAllowed(true);
				
				String lName = nametextField.getText();
				List<City> result=null;
				try{
					if(lName!=null && lName.trim().length()>0){
						result = sc.getAllCitiesFromList(lName);
					}
					else{
						result = sc.getAllCitiesFromList();
					}
					 cityModel = new SqlCityTableModel(result);
					table.setModel(cityModel);
					
					for(City cc:result){
						System.out.println(""+cc.getName()+" "+cc.getPopulation());
					}

					table.getColumnModel().getColumn(0).setMaxWidth(50);
					table.getColumnModel().getColumn(2).setMaxWidth(50);
//					Position the text in the cells with a cell renderer.
					DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
					centerRenderer.setHorizontalAlignment( JLabel.LEFT );
					table.setDefaultRenderer(String.class, centerRenderer);

				}catch(Exception e){System.out.println("Exception [GUI] :" +e);}
			}
		});
		btnSearch.setBackground(SystemColor.textHighlight);
		northPanel.add(btnSearch);
		
		JButton btnUpdateSelected = new JButton("Update selected");
		btnUpdateSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("* "+table.getValueAt(table.getSelectedRow(), 1).toString());
				new UpdateCityGUI(sc, table.getValueAt(table.getSelectedRow(), 0).toString(), table.getValueAt(table.getSelectedRow(), 1).toString(), table.getValueAt(table.getSelectedRow(), 2).toString(), table.getValueAt(table.getSelectedRow(), 3).toString(), table.getValueAt(table.getSelectedRow(), 4).toString());
			}
		});
		btnUpdateSelected.setFont(new Font("Tahoma", Font.BOLD, 11));
		northPanel.add(btnUpdateSelected);
		
		
		JButton btnNewCity = new JButton("New City");
		btnNewCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddNewCityGUI(sc);
			}
		});
		btnNewCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		northPanel.add(btnNewCity);
		
		
		btnDeleteCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sc.deleteItemFromCity((int)table.getValueAt(table.getSelectedRow(), 0));
				cityModel.fireTableDataChanged();
				table.repaint();
			}
		});
		btnDeleteCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeleteCity.setBackground(new Color(220, 20, 60));
		northPanel.add(btnDeleteCity);
		
		JScrollPane scrollPane = new JScrollPane();

		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setBackground(Color.black);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow()>=0)
					btnDeleteCity.setEnabled(true);
//				Pick the ID from table and get the country from the other table
				rowTextField.setText(sc.getRowSelectedContentFromCountry(table.getValueAt(table.getSelectedRow(), 2).toString()));
			}
		});
		table.setForeground(SystemColor.textHighlightText);
		table.setBackground(SystemColor.desktop);
		
		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
		this.setPreferredSize(new Dimension(800,400));
		this.pack();
        this.update(this.getGraphics());
        this.setVisible(true);
	}

}
