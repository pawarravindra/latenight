package borrow;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;

import firstpage.MainPage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableModel;

public class Borrowings {

	private JFrame frame;
	
	Connection connection = null;
	java.sql.Statement statement = null;
	ResultSet resultSet = null;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Borrowings window = new Borrowings();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Borrowings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(150, 50, 1075, 650);
		frame.getContentPane().setBackground(new Color(255, 228, 225));
		
		JButton button = new JButton("Home");
		button.setBounds(58, 34, 70, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				frame.dispose();
				MainPage sub=new MainPage();
				
				
			}
		});
		frame.getContentPane().setLayout(null);
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(139, 0, 0));
		frame.getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 136, 959, 444);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Bill id", "Customer Name", "Mobile No.", "Total", "Paid", "Due"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(161);
		scrollPane.setColumnHeaderView(table);
		
		JButton btnViewBorrower = new JButton("View Borrower");
		btnViewBorrower.setBounds(58, 85, 160, 30);
		btnViewBorrower.addActionListener(new ActionListener() {
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
				
					
					String q="Select BIllId, CustomerName,MobileNo, Total,Paid,Due from Details where Due>0";
					//statement=connection.createStatement();
					resultSet=statement.executeQuery(q);
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
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
		btnViewBorrower.setForeground(Color.WHITE);
		btnViewBorrower.setBackground(new Color(139, 0, 0));
		frame.getContentPane().add(btnViewBorrower);
		frame.setVisible(true);
		frame.setTitle("LateNight Food");
		frame.setResizable(false);
		Image img=new ImageIcon("C:/LNDatabase/logo.jpg").getImage();
		frame.setIconImage(img);
		
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
