package administration;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

//import submain.Submain;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.Statement;
import java.io.BufferedWriter;
import java.io.File;
import java.sql.*;

import javax.swing.JComboBox;

import firstpage.MainPage;
import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Admin {

	private JFrame frame3;
	private JTable tableAdd;
	private JTextField txtItemName;
	private JTextField txtItemPrice;
	private JTable tableHist;
	private JTextField txtId;
	
	Connection connection = null;
	java.sql.Statement statement = null;
	ResultSet resultSet = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.frame3.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Admin() {
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame3 = new JFrame();
		
	
		
		
		frame3.setBounds(150, 50, 1075, 650);
		frame3.getContentPane().setBackground(new Color(255, 228, 225));
		frame3.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 228, 225));
		tabbedPane.setBounds(0, 43, 1069, 578);
		frame3.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		tabbedPane.addTab("Add item", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 76, 328, 427);
		panel.add(scrollPane);
		
		tableAdd = new JTable();
		tableAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		tableAdd.setBackground(Color.WHITE);
		tableAdd.setForeground(Color.BLACK);
		
		
		
		
		Object[] columns={"Id","Item Name","Price"};
		DefaultTableModel model=new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		tableAdd.setModel(model);
		
		
		
	/*	model.setColumnIdentifiers(columns);
		tableAdd.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Item Name", "Price"
			}
		));*/
		tableAdd.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableAdd.getColumnModel().getColumn(1).setPreferredWidth(150);
				scrollPane.setViewportView(tableAdd);
				
				JLabel lblNewLabel = new JLabel("Create New Menu Item");
				lblNewLabel.setForeground(new Color(139, 69, 19));
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblNewLabel.setBounds(471, 82, 216, 37);
				panel.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("Item Name:");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_1.setForeground(new Color(139, 69, 19));
				lblNewLabel_1.setBounds(454, 185, 93, 31);
				panel.add(lblNewLabel_1);
				
				JLabel lblNewLabel_2 = new JLabel("Item Price:");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_2.setForeground(new Color(139, 69, 19));
				lblNewLabel_2.setBounds(454, 227, 93, 31);
				panel.add(lblNewLabel_2);
				
				txtItemName = new JTextField();
				txtItemName.setBounds(557, 187, 160, 30);
				panel.add(txtItemName);
				txtItemName.setColumns(10);
				
				txtItemPrice = new JTextField();
				txtItemPrice.setBounds(557, 229, 160, 30);
				panel.add(txtItemPrice);
				txtItemPrice.setColumns(10);
				
				JButton btnNewButton = new JButton("Add Item");
				Object [] row=new Object[4];
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						row[0]=txtId.getText();
						row[1]=txtItemName.getText();
						row[2]=txtItemPrice.getText();
						
						model.addRow(row);
						
						try {

							Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						}
						catch(ClassNotFoundException cnfex) {

							System.out.println("Problem in loading or registering MS Access JDBC driver");
							cnfex.printStackTrace();
						}

						try {

							String msAccessDBName = "C:/LNDatabase/LateNight.accdb";
							String dbURL = "jdbc:ucanaccess://" + msAccessDBName; 

							// Step 2.A: Create and get connection using DriverManager class
							connection = DriverManager.getConnection(dbURL); 
							statement= connection.createStatement();
							// Step 2.C: Executing SQL & retrieve data into ResultSet
							String sql = "INSERT INTO Menu (Id,ItemName,Price) " +
									 "Values ('"+txtId.getText()+"'," +
									 "'"+txtItemName.getText()+"'," +
									 "'"+txtItemPrice.getText()+"')";
							statement.executeUpdate(sql);
						
							
							String q="Select * from Menu";
							//statement=connection.createStatement();
							resultSet=statement.executeQuery(q);
							tableAdd.setModel(DbUtils.resultSetToTableModel(resultSet));
							 
							clearControls();
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							//resultSet = statement.executeQuery("SELECT * FROM Menu");

							//System.out.println("ID\tName\t\t\tAge\tMatches");
							//System.out.println("==\t================\t===\t=======");

							// processing returned data and printing into console
							/*while(resultSet.next()) {
								System.out.println(resultSet.getInt(1) + "\t" + 
										resultSet.getString(2)+"\t"+resultSet.getInt(3));
							}*/
						}
						catch(SQLException sqlex){
							sqlex.printStackTrace();
						}
						catch(NullPointerException ex)
						{
							ex.printStackTrace();
						}
						finally {

							// Step 3: Closing database connection
							try {
								if(null != connection) {

									// cleanup resources, once after processing
									resultSet.close();
									statement.close();

									// and then finally close connection
									connection.close();
								}
							}
							catch (SQLException sqlex) {
								sqlex.printStackTrace();
							}
						}
						
					
						
	
						
						/*row[0]=txtItemName.getText();
						row[1]=txtItemPrice.getText();
						model.addRow(row);
						
						try
						{
							
							File file=new File("C:\\Users\\Vitthal\\Desktop\\MidNightBillingProject\\Menu.txt");
							if(!file.exists())
							{
								file.createNewFile();
							}
							
							FileWriter fw=new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw=new BufferedWriter(fw);
							bw.write("\nItem Name\t\tPrice\n");
							bw.write("----------------------------\n");
							for(int i=0; i<tableAdd.getRowCount();i++)
							{
								for(int j=0;j<tableAdd.getColumnCount();j++)
								{
									bw.write(tableAdd.getModel().getValueAt(i, j)+" \t\t");
								}
								bw.write("\n______________\n");
							}
							bw.close();
							fw.close();
								
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}*/
						
					}

					private void clearControls() {
						txtId.setText(null);
						txtItemName.setText(null);
						txtItemPrice.setText(null);
						
						
					}
				});
				btnNewButton.setBackground(new Color(139, 0, 0));
				btnNewButton.setForeground(new Color(255, 255, 255));
				btnNewButton.setBounds(464, 295, 120, 30);
				panel.add(btnNewButton);
				
				JButton btnDeleteItem = new JButton("Delete Item");
				btnDeleteItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int i=tableAdd.getSelectedRow();
						if(i>=0)
						{
							model.removeRow(i);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please Select row.","Delete Error",JOptionPane.ERROR_MESSAGE);
							//System.out.println("Delete Error");
						}
						
						
					}
				});
				btnDeleteItem.setBackground(new Color(139, 0, 0));
				btnDeleteItem.setForeground(new Color(255, 255, 255));
				btnDeleteItem.setBounds(593, 295, 120, 30);
				panel.add(btnDeleteItem);
				
				JButton btnNewButton_1 = new JButton("Home");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame3.dispose();
						MainPage m=new MainPage();
					}
				});
				btnNewButton_1.setForeground(new Color(255, 255, 255));
				btnNewButton_1.setBackground(new Color(139, 0, 0));
				btnNewButton_1.setBounds(48, 23, 70, 30);
				panel.add(btnNewButton_1);
				
				JLabel lblCourse = new JLabel("Id:");
				lblCourse.setForeground(new Color(139, 69, 19));
				lblCourse.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblCourse.setBounds(454, 146, 93, 28);
				panel.add(lblCourse);
				
				txtId = new JTextField();
				txtId.setColumns(10);
				txtId.setBounds(557, 146, 160, 30);
				panel.add(txtId);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 228, 225));
		tabbedPane.addTab("Order Management", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame3.dispose();
				MainPage sub=new MainPage();
			}
		});
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(139, 0, 0));
		btnHome.setBounds(48, 23, 70, 30);
		panel_1.add(btnHome);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(127, 77, 824, 372);
		panel_1.add(scrollPane_1);
		
		tableHist = new JTable();
		tableHist.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				
			}
		));
		scrollPane_1.setViewportView(tableHist);
		
		JButton btnDeleteSelectedItem = new JButton("View Orders");
		btnDeleteSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				try {

					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				}
				catch(ClassNotFoundException cnfex) {

					System.out.println("Problem in loading or registering MS Access JDBC driver");
					cnfex.printStackTrace();
				}

				try {

					String msAccessDBName = "C:/LNDatabase/LateNight.accdb";
					String dbURL = "jdbc:ucanaccess://" + msAccessDBName; 

					// Step 2.A: Create and get connection using DriverManager class
					connection = DriverManager.getConnection(dbURL); 
					statement= connection.createStatement();
					// Step 2.C: Executing SQL & retrieve data into ResultSet
					/*String sql = "INSERT INTO Menu (Id,ItemName,Price) " +
							 "Values ('"+txtId.getText()+"'," +
							 "'"+txtItemName.getText()+"'," +
							 "'"+txtItemPrice.getText()+"')";
					statement.executeUpdate(sql);*/
				
					
					String q="Select * from Details";
					//statement=connection.createStatement();
					resultSet=statement.executeQuery(q);
					tableHist.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					//resultSet = statement.executeQuery("SELECT * FROM Menu");

					//System.out.println("ID\tName\t\t\tAge\tMatches");
					//System.out.println("==\t================\t===\t=======");

					// processing returned data and printing into console
					/*while(resultSet.next()) {
						System.out.println(resultSet.getInt(1) + "\t" + 
								resultSet.getString(2)+"\t"+resultSet.getInt(3));
					}*/
				}
				catch(SQLException sqlex){
					sqlex.printStackTrace();
				}
				catch(NullPointerException ex)
				{
					ex.printStackTrace();
				}
				finally {

					// Step 3: Closing database connection
					try {
						if(null != connection) {

							// cleanup resources, once after processing
							resultSet.close();
							statement.close();

							// and then finally close connection
							connection.close();
						}
					}
					catch (SQLException sqlex) {
						sqlex.printStackTrace();
					}
				}
				
			}
		});
		btnDeleteSelectedItem.setBackground(new Color(139, 0, 0));
		btnDeleteSelectedItem.setForeground(new Color(255, 255, 255));
		btnDeleteSelectedItem.setBounds(473, 484, 160, 35);
		panel_1.add(btnDeleteSelectedItem);
		frame3.setVisible(true);
		frame3.setTitle("LateNight Food");
		frame3.setResizable(false);
		Image img=new ImageIcon("C:/LNDatabase/logo.jpg").getImage();
		frame3.setIconImage(img);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
