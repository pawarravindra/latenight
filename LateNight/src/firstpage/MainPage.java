package firstpage;

import history.History;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;

import borrow.Borrowings;
import administration.Admin;
import mainpage.Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JEditorPane;

import org.apache.commons.lang.StringUtils;

import javax.swing.JList;

//import submain.Submain;

public class MainPage {

	private JFrame frame2;
	private static JTextField txtCustName;
	private JTextField txtMob;
	private JTextField txtQuantity;
	private JTextField txtSubTotal;
	private JTextField txtGst;
	private JTextField txtDiscount;
	private JTextField txtNetTotal;
	private JTable table;
	JTextArea textArea;
	JButton btnAdd;
	DefaultTableModel model;
	
	/* Double total=0.0;
	 Double gst=9.0,gstamt=0.0;
	 Double discount=0.0;
	 Double result;
	 Double netTotal;*/
	
	JComboBox<String> comboBox;
	 String s;

	
	Connection connection = null;
	java.sql.Statement statement = null;
	ResultSet resultSet = null;
	private JTextField txtAddress;
	private JTextField txtDelivery;
	private JTextField txtContainer;
	private JTextField txtPaid;
	private JTextField txtDue;
	
	ArrayList<String> items = new ArrayList<String>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
		//DatabaseName();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		
		
		
		frame2 = new JFrame();
		frame2.getContentPane().setBackground(new Color(255, 228, 225));
		frame2.getContentPane().setLayout(null);
		frame2.setVisible(true);
		frame2.setTitle("LateNight Food");
		frame2.setResizable(false);
		Image img=new ImageIcon("C:\\LNDatabase\\logo.jpg").getImage();
		frame2.setIconImage(img);
		
		JPanel panelPersonel = new JPanel();
		panelPersonel.setForeground(new Color(139, 69, 19));
		panelPersonel.setBackground(new Color(255, 228, 225));
		panelPersonel.setBorder(null);
		panelPersonel.setBounds(10, 11, 725, 183);
		frame2.getContentPane().add(panelPersonel);
		panelPersonel.setLayout(null);
		
		JLabel lblBillNo = new JLabel("Bill No:");
		lblBillNo.setForeground(new Color(139, 69, 19));
		lblBillNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBillNo.setBounds(330, 55, 65, 14);
		panelPersonel.add(lblBillNo);
		
		JLabel lblCustName = new JLabel("Customer Name:");
		lblCustName.setForeground(new Color(139, 69, 19));
		lblCustName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCustName.setBounds(330, 81, 132, 14);
		panelPersonel.add(lblCustName);
		
		JLabel lblCustMob = new JLabel("Mobile No.:");
		lblCustMob.setForeground(new Color(139, 69, 19));
		lblCustMob.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCustMob.setBounds(330, 107, 92, 14);
		panelPersonel.add(lblCustMob);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setForeground(new Color(139, 69, 19));
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(584, 36, 59, 14);
		panelPersonel.add(lblDate);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setForeground(new Color(139, 69, 19));
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTime.setBounds(584, 57, 59, 14);
		panelPersonel.add(lblTime);
		
		txtCustName = new JTextField();
		//setupAutoComplete(txtCustName, items);
		txtCustName.setBackground(new Color(255, 255, 255));
		txtCustName.setBounds(453, 78, 170, 20);
		panelPersonel.add(txtCustName);
		txtCustName.setColumns(10);
		
		/*Locale[] locales = Locale.getAvailableLocales();
        for (int i = 0; i < locales.length; i++) {

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
	    			String sql="select * from Details";
	    			resultSet=statement.executeQuery(sql);
	    			while(resultSet.next())
	    			{
	    				
	    			String na=resultSet.getString("CustomerName");
	    			 items.add(na);
	    			}
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
	    		
	    	
	
	        
	}    */
	  
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
		
		//databaseName();
		txtMob = new JTextField();
		txtMob.setBackground(new Color(255, 255, 255));
		
		/*txtCustName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				switch(evt.getKeyCode())
				{
				case KeyEvent.VK_BACK_SPACE:
					break;
				case KeyEvent.VK_ENTER:
					txtCustName.setText(txtCustName.getText());
					break;
				default :
					EventQueue.invokeLater(new Runnable(){
						public void run()
						{
							String txt=txtCustName.getText();
							auto(txt);
						}
						
					});
				}
			}
		});*/
		
		
		txtMob.setBounds(453, 105, 170, 20);
		panelPersonel.add(txtMob);
		txtMob.setColumns(10);
		
		
		
		
		
		
		
		/*txtMob.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				switch(evt.getKeyCode())
				{
				case KeyEvent.VK_BACK_SPACE:
					break;
				case KeyEvent.VK_ENTER:
					txtMob.setText(txtMob.getText());
					break;
				default :
					EventQueue.invokeLater(new Runnable(){
						public void run()
						{
							String txt=txtMob.getText();
							auto(txt);
						}
						
					});
				}
				
			}
		});*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		 final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");
		 LocalDateTime now = LocalDateTime.now();
		 
		 
		JLabel lblDateValue = new JLabel("");
		lblDateValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDateValue.setForeground(new Color(139, 69, 19));
		lblDateValue.setBounds(624, 36, 91, 14);
		panelPersonel.add(lblDateValue);
		lblDateValue.setText(dtf.format(now));
		
		
		final DateTimeFormatter ttf = DateTimeFormatter.ofPattern("HH:mm:ss");
			
		JLabel lblTimeValue = new JLabel("");
		lblTimeValue.setForeground(new Color(139, 69, 19));
		lblTimeValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTimeValue.setText(ttf.format(now));
		lblTimeValue.setBounds(629, 58, 71, 14);
		panelPersonel.add(lblTimeValue);
		
		
		
		
		JLabel lblOrderIdVal = new JLabel("");
		lblOrderIdVal.setBackground(new Color(255, 235, 205));
		lblOrderIdVal.setBounds(387, 53, 76, 19);
		panelPersonel.add(lblOrderIdVal);
		JButton btnNewOrder = new JButton("New Order");
		btnNewOrder.setToolTipText("");
		
		txtCustName.setEnabled(false);
		txtMob.setEnabled(false);
		
		//txtQuantity.setEnabled(false);
		btnNewOrder.addActionListener(new ActionListener() {
			int clicked=0;
			public void actionPerformed(ActionEvent e) {
				
				txtCustName.setEnabled(true);
				txtMob.setEnabled(true);
				
				
				//txtQuantity.setEnabled(true);
				
				clicked++;
				lblOrderIdVal.setText(""+clicked);
				
				

				try {

					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				}
				catch(ClassNotFoundException cnfex) {

					System.out.println("Problem in loading or registering MS Access JDBC driver");
					cnfex.printStackTrace();
				}
				
				try 
				{

					String msAccessDBName = "C:/LNDatabase/LateNight.accdb";
					String dbURL = "jdbc:ucanaccess://" + msAccessDBName; 

					// Step 2.A: Create and get connection using DriverManager class
					connection = DriverManager.getConnection(dbURL); 
					statement= connection.createStatement();
					// Step 2.C: Executing SQL & retrieve data into ResultSet
			
					String sql="select BillId from Details";
					resultSet=statement.executeQuery(sql);
					while(resultSet.next())
					{
						int a=1;
						int i=resultSet.getInt("BillId");
						int c=a+i;
						
						lblOrderIdVal.setText(c+"");
						
					}
					
					
					
					//resultSet = statement.executeQuery("SELECT * FROM Menu");

					//System.out.println("ID\tName\t\t\tAge\tMatches");
					//System.out.println("==\t================\t===\t=======");

					// processing returned data and printing into console
					/*while(resultSet.next()) {
						System.out.println(resultSet.getInt(1) + "\t" + 
								resultSet.getString(2)+"\t"+resultSet.getInt(3));
					}*/
				}
				catch(SQLException sqlex)
				{
					sqlex.printStackTrace();
				}
				catch(NullPointerException ex)
				{
					ex.printStackTrace();
				}
				finally 
				{

					// Step 3: Closing database connection
					try
					{
						if(null != connection)
						{

							// cleanup resources, once after processing
							resultSet.close();
							statement.close();

							// and then finally close connection
							connection.close();
						}
					}
					catch (SQLException sqlex) 
					{
						sqlex.printStackTrace();
					}
				}
				
				model.setRowCount(0);
				textArea.setText(null);
				txtCustName.setText(null);
				txtMob.setText(null);
				//txtQuantity.setText();
				txtSubTotal.setText(null);
				//txtGst.setText(null);
				//txtDiscount.setText(null);
				txtNetTotal.setText(null);
				//ctxtPaid.setText(null);
				txtDue.setText(null);
				txtAddress.setText(null);
			
				
			}
		});
		btnNewOrder.setBackground(new Color(139, 0, 0));
		btnNewOrder.setForeground(new Color(255, 255, 255));
		btnNewOrder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewOrder.setBounds(331, 11, 119, 25);
		panelPersonel.add(btnNewOrder);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setForeground(new Color(139, 69, 19));
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress.setBounds(331, 134, 92, 14);
		panelPersonel.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBackground(Color.WHITE);
		txtAddress.setBounds(453, 133, 262, 20);
		panelPersonel.add(txtAddress);
		
		JLabel lblNewLabel_3 = new JLabel(new ImageIcon(((new ImageIcon("C:\\LNDatabase\\logo.jpg")).getImage()).getScaledInstance(132, 183, java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel_3.setBounds(0, 0, 132, 183);
		panelPersonel.add(lblNewLabel_3);
		
		JButton btnAdministration = new JButton("Administration");
		btnAdministration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame2.dispose();
				Admin sub=new Admin();
				
				
				
			}
		});
		btnAdministration.setForeground(Color.WHITE);
		btnAdministration.setBackground(new Color(139, 0, 0));
		btnAdministration.setBounds(143, 11, 161, 50);
		panelPersonel.add(btnAdministration);
		
		JButton btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame2.dispose();
				History hist=new History();
				
				
				
			}
		});
		btnHistory.setForeground(Color.WHITE);
		btnHistory.setBackground(new Color(139, 0, 0));
		btnHistory.setBounds(143, 70, 161, 50);
		panelPersonel.add(btnHistory);
		
		JButton btnborrowings = new JButton("Borrowings");
		btnborrowings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				frame2.dispose();
				Borrowings hist=new Borrowings();
			}
		});
		btnborrowings.setForeground(Color.WHITE);
		btnborrowings.setBackground(new Color(139, 0, 0));
		btnborrowings.setBounds(143, 128, 161, 50);
		panelPersonel.add(btnborrowings);
		
		
		
		JPanel panelTable = new JPanel();
		//panelTable.setBackground(new Color(255, 235, 205));
		panelTable.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelTable.setBounds(10, 206, 315, 404);
		frame2.getContentPane().add(panelTable);
		panelTable.setLayout(null);
		
		table = new JTable();
		//table.setBackground(Color.WHITE);
		//table.setForeground(Color.BLACK);
		
		Object[] columns={"Item Name","Unit Price", "Quan","Total"};
		model=new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		/*table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item Name", "Unit Price", "Quan", "Total"
			}
		));*/
		table.getColumnModel().getColumn(0).setPreferredWidth(160);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		
		
		JButton btnRemove = new JButton("Remove Selected Item");
		btnRemove.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				 
				int i=table.getSelectedRow();
				if(i>=0)
				{
					try
					{	
						Double total=0.0,total1=0.0;
						Double gst=9.0,gstamt=0.0;
						Double discount=0.0;
						Double result;
						Double netTotal;
					 
						
				
						 for (int j = 0; j <=i; j++)
						 {
						
							 total1=total1 + Double.parseDouble((String)model.getValueAt(j,3).toString());
							 total=total1 - Double.parseDouble((String)model.getValueAt(i,3).toString());
							 txtSubTotal.setText(Double.toString(total));
			             
			           
							 result=(total-(Double.parseDouble((String)txtDiscount.getText().toString())/100)*total);
			             
							 gstamt=(gst/100)*result;
							 //gstamt.setPrecision(4);
							 gstamt = Math.round(gstamt*100)/100.0d;
							 txtGst.setText(Double.toString(gstamt));
							 netTotal=result+gstamt;
							 txtNetTotal.setText(Double.toString(netTotal));
			             
						 }	
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					model.removeRow(i);
				}
					 
				else
				{
					JOptionPane.showMessageDialog(null, "Please select row.","Delete Error",JOptionPane.ERROR_MESSAGE);
					//System.out.println("Delete Error");
				}
			}
		});
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.setBackground(new Color(139, 0, 0));
		btnRemove.setBounds(10, 357, 295, 36);
		panelTable.add(btnRemove);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 295, 339);
		panelTable.add(scrollPane);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		Font font=new Font(" ", 1, 22);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
		
		JPanel panelBill = new JPanel();
		panelBill.setBackground(new Color(255, 228, 225));
		panelBill.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelBill.setBounds(335, 206, 400, 404);
		frame2.getContentPane().add(panelBill);
		panelBill.setLayout(null);
		
		JLabel lblItem = new JLabel("Add Item:");
		lblItem.setForeground(new Color(139, 69, 19));
		lblItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItem.setBounds(10, 11, 84, 24);
		panelBill.add(lblItem);
		
		
		/*comboBox.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		            textField.setText((String) comboBox.getSelectedItem());
		        }
		    }
		});*/
		
		
		btnAdd = new JButton("Add");
		
		Object [] row=new Object[4];
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{

				
				
				
				try {

					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				}
				catch(ClassNotFoundException cnfex) {

					System.out.println("Problem in loading or registering MS Access JDBC driver");
					cnfex.printStackTrace();
				}
				
				try 
				{

					String msAccessDBName = "C:/LNDatabase/LateNight.accdb";
					String dbURL = "jdbc:ucanaccess://" + msAccessDBName; 

					// Step 2.A: Create and get connection using DriverManager class
					connection = DriverManager.getConnection(dbURL); 
					statement= connection.createStatement();
					// Step 2.C: Executing SQL & retrieve data into ResultSet
			
					String sql="select Price from Menu WHERE ItemName= '" + comboBox.getSelectedItem()+ "' ";
					resultSet=statement.executeQuery(sql);
					while(resultSet.next())
					{
						
						row[1]=	resultSet.getString("Price");
						//System.out.println(resultSet.getInt(1));
						
						row[0]=comboBox.getSelectedItem().toString();
						row[2]=txtQuantity.getText();
						
						
						
						model.addRow(row);
						
					}
					
					
					
					//resultSet = statement.executeQuery("SELECT * FROM Menu");

					//System.out.println("ID\tName\t\t\tAge\tMatches");
					//System.out.println("==\t================\t===\t=======");

					// processing returned data and printing into console
					/*while(resultSet.next()) {
						System.out.println(resultSet.getInt(1) + "\t" + 
								resultSet.getString(2)+"\t"+resultSet.getInt(3));
					}*/
				}
				catch(SQLException sqlex)
				{
					sqlex.printStackTrace();
				}
				catch(NullPointerException ex)
				{
					ex.printStackTrace();
				}
				finally 
				{

					// Step 3: Closing database connection
					try
					{
						if(null != connection)
						{

							// cleanup resources, once after processing
							resultSet.close();
							statement.close();

							// and then finally close connection
							connection.close();
						}
					}
					catch (SQLException sqlex) 
					{
						sqlex.printStackTrace();
					}
				}
				
				
				
				
				
				
				
				
				
				 for (int i = 0; i <model.getRowCount(); i++) {
                     Double d= Double.parseDouble((String) model.getValueAt(i,1));
                     Double d2= Double.parseDouble((String) model.getValueAt(i,2));
                    Double d3=d*d2;
                    model.setValueAt(d3,i,3);
				 }
				 try
				 {
					 Double total=0.0;
					 Double gst=0.0,gstamt=0.0;
					 Double del=0.0;
					 Double cont=0.0;
					 Double discount=0.0;
					 Double result;
					 Double netTotal;
					 gst=Double.parseDouble((String)txtGst.getText().toString());
					 for (int i = 0; i <model.getRowCount(); i++){
						 
		             total=total+Double.parseDouble((String)model.getValueAt(i,3).toString())+Double.parseDouble((String)txtDelivery.getText().toString())+Double.parseDouble((String)txtContainer.getText().toString());
		             txtSubTotal.setText(Double.toString(total));
		             
		           
		             result=(total-(Double.parseDouble((String)txtDiscount.getText().toString())/100)*total);
		             
		             gstamt=(gst/100)*result;
		             //gstamt.setPrecision(4);
		             gstamt = Math.round(gstamt*100)/100.0d;
		        //     txtGst.setText(Double.toString(gstamt));
		             netTotal=result+gstamt;
		             txtNetTotal.setText(Double.toString(netTotal));
		             
				 }
				
				
				
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
				
			
				
				
				
				
			}
		});
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(139, 0, 0));
		btnAdd.setBounds(312, 38, 84, 37);
		panelBill.add(btnAdd);
		
		JLabel lblNewLabel_1 = new JLabel("Quantity:");
		lblNewLabel_1.setForeground(new Color(139, 69, 19));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(225, 11, 84, 24);
		panelBill.add(lblNewLabel_1);
		
		txtQuantity = new JTextField();
		txtQuantity.setText("1");
		txtQuantity.setBounds(224, 38, 78, 37);
		panelBill.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		
		
		JButton btnGenerate = new JButton("Save & Generate Reciept");
		btnGenerate.addActionListener(new ActionListener() {
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
					String sql = "INSERT INTO Details (BillId, CustomerName, MobileNo, Address, Total, Paid, Due) " +
							 "Values ('"+lblOrderIdVal.getText()+"'," +
							 	"'"+txtCustName.getText()+"'," +
							 	"'"+txtMob.getText()+"'," +
							 	"'"+txtAddress.getText()+"'," +
							 	"'"+txtNetTotal.getText()+"'," +
							 	"'"+txtPaid.getText()+"'," +
							 		"'"+txtDue.getText()+"')";
					
					statement.executeUpdate(sql);
					
					
					
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
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			
				textArea.setText("\n\t  LateNight Food");
				
				try
				{
				textArea.append("\n\n   Call:020-69777979/Mob:8793777979");
				textArea.append("\n Nandanvan Colony, Near Mauli Chowk,\n Datta Mandir Road,Wakad, Pune-411057");
				textArea.append("\n -----------------------------------------------------------\n");
				
				textArea.append("\n    Name: "+txtCustName.getText());
				textArea.append("\n    Mobile No.: "+txtMob.getText());
				textArea.append("\n    Date: "+lblDateValue.getText());
				textArea.append("         Time: "+lblTimeValue.getText());
				textArea.append("\n    Bill No.: "+lblOrderIdVal.getText());
				textArea.append("\n    Address.: "+txtAddress.getText());
				
				textArea.append("\n --------------------------------------------------------------\n");
				 
				String s1=String.format(" %-15s%14s%6s%8s\n", "Item Name","Quan","Rate","Amount");
				textArea.append(s1);
				textArea.append(" --------------------------------------------------------------\n");
				 
				
				
				Formatter f=new Formatter();
				
				
				 for (int i = 0; i <model.getRowCount(); i++) {
					 String s=(String)model.getValueAt(i, 0);
					 int l=s.length();
					
					 String pad = String.format("%-18s", s).replace(' ', '_');
                     int d= Integer.parseInt((String) model.getValueAt(i,1));
                     int d2= Integer.parseInt((String) model.getValueAt(i,2));
                    int d3=d*d2;
                    //model.setValueAt(d3,i,3);
                    String s2=String.format(" %-15.17s %7s %10d %8d\n", pad,d2,d,d3);
                    textArea.append(s2);
                    //f.format("%-15.15s %15d %10d %8d\n",s,d2,d,d3);
                    //textArea.append(s2);
				 }
				 textArea.append(" --------------------------------------------------------------\n");
				 
				 textArea.append("\n                              Sub Total: "+txtSubTotal.getText());
				 textArea.append("\n                             G.S.T.: "+txtGst.getText());
				 textArea.append("\n                             Discount(%): "+txtDiscount.getText());
				 textArea.append("\n \n                            *Net Total: "+txtNetTotal.getText());
				 textArea.append("\n                                     Paid: "+txtPaid.getText());
				 textArea.append("\n                                      Due: "+txtDue.getText());
				 textArea.append("\n\n                 Thank You ! Visit Again !!\n");
				 
				 
				 
				}
				catch(Exception e) 
				{ 
					e.printStackTrace();
				}
				 
				 try
					{
					 	
					 
						String path="C:/LNDatabase/Menu.txt";
						File file=new File(path);
						if(!file.exists())
						{
							file.createNewFile();
						}
						
						FileWriter fw=new FileWriter(file,true);
						
						BufferedWriter br=new BufferedWriter(fw);
						//PrintWriter bw=new PrintWriter(fw);
						
						fw.append(textArea.getText());
						fw.append("=========================================================================\n");
						br.close();
						fw.close();
							
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				
			}
		});
		btnGenerate.setForeground(new Color(255, 255, 255));
		btnGenerate.setBackground(new Color(139, 0, 0));
		btnGenerate.setBounds(58, 351, 181, 38);
		panelBill.add(btnGenerate);
		
		JButton btnHome = new JButton("Log Out");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(frame2,"Do you want to Exit","Exiting",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
				frame2.dispose();
				Main sub=new Main();
				}
			}
		});
		btnHome.setForeground(new Color(255, 255, 255));
		btnHome.setBackground(new Color(139, 0, 0));
		btnHome.setBounds(249, 351, 98, 38);
		panelBill.add(btnHome);
		
		JLabel lblTotal = new JLabel("Sub-Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotal.setForeground(new Color(139, 69, 19));
		lblTotal.setBounds(180, 82, 75, 24);
		panelBill.add(lblTotal);
		
		JLabel lblGst = new JLabel("GST:");
		lblGst.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGst.setForeground(new Color(139, 69, 19));
		lblGst.setBounds(219, 170, 36, 24);
		panelBill.add(lblGst);
		
		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiscount.setForeground(new Color(139, 69, 19));
		lblDiscount.setBounds(188, 202, 67, 24);
		panelBill.add(lblDiscount);
		
		JLabel lblNet = new JLabel("Net Amount:");
		lblNet.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNet.setForeground(new Color(139, 69, 19));
		lblNet.setBounds(151, 237, 104, 24);
		panelBill.add(lblNet);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setBounds(265, 86, 131, 20);
		panelBill.add(txtSubTotal);
		txtSubTotal.setColumns(10);
		
		txtGst = new JTextField();
		txtGst.setText("0");
		txtGst.setColumns(10);
		txtGst.setBounds(265, 174, 131, 20);
		panelBill.add(txtGst);
		
		txtDiscount = new JTextField();
		txtDiscount.setText("0");
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(265, 206, 131, 20);
		panelBill.add(txtDiscount);
		
		txtNetTotal = new JTextField();
		txtNetTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtNetTotal.setColumns(10);
		txtNetTotal.setBounds(265, 236, 131, 25);
		panelBill.add(txtNetTotal);
		
		JLabel lblNewLabel_2 = new JLabel("Payment Method");
		lblNewLabel_2.setForeground(new Color(139, 69, 19));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 79, 126, 24);
		panelBill.add(lblNewLabel_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(139, 69, 19));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(146, 86, -1, 121);
		panelBill.add(separator_1);
		
		JButton btnCash = new JButton(new ImageIcon(((new ImageIcon("C:\\LNDatabase\\cash.jpg")).getImage()).getScaledInstance(119, 47, java.awt.Image.SCALE_SMOOTH)));
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		//btnCash.setIcon(new ImageIcon("img/cash.JPG"));
		btnCash.setForeground(new Color(0, 0, 0));
		btnCash.setBackground(new Color(255, 140, 0));
		btnCash.setBounds(10, 101, 119, 47);
		panelBill.add(btnCash);
		
		JButton btnCard = new JButton(new ImageIcon(((new ImageIcon("C:\\LNDatabase\\card.png")).getImage()).getScaledInstance(57, 47, java.awt.Image.SCALE_SMOOTH)));
		//btnNewButton_19.setIcon(new ImageIcon("img/card.png"));
		btnCard.setBounds(10, 151, 57, 47);
		panelBill.add(btnCard);
		
		JButton btnPaytm = new JButton(new ImageIcon(((new ImageIcon("C:\\LNDatabase\\paytm.png")).getImage()).getScaledInstance(57, 47, java.awt.Image.SCALE_SMOOTH)));
		//btnPaytm.setIcon(new ImageIcon("img/paytm.png"));
		btnPaytm.setBounds(71, 151, 57, 47);
		panelBill.add(btnPaytm);
		
		
		
		
		
		comboBox=new JComboBox<>();
		DefaultComboBoxModel<String>model1=new DefaultComboBoxModel<>();
		//AutoCompleteDecorator.decorate(comboBox);
		comboBox.setEditable(true);
		comboBox.setBounds(10, 38, 204, 37);
		panelBill.add(comboBox);
		AutoCompletion.enable(comboBox);
		
		JLabel lblDelivery = new JLabel("Delivery:");
		lblDelivery.setForeground(new Color(139, 69, 19));
		lblDelivery.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDelivery.setBounds(190, 113, 63, 24);
		panelBill.add(lblDelivery);
		
		txtDelivery = new JTextField();
		txtDelivery.setText("0");
		txtDelivery.setColumns(10);
		txtDelivery.setBounds(265, 117, 131, 20);
		panelBill.add(txtDelivery);
		
		JLabel lblContainer = new JLabel("Container:");
		lblContainer.setForeground(new Color(139, 69, 19));
		lblContainer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContainer.setBounds(180, 141, 75, 24);
		panelBill.add(lblContainer);
		
		txtContainer = new JTextField();
		txtContainer.setText("0");
		txtContainer.setColumns(10);
		txtContainer.setBounds(265, 145, 131, 20);
		panelBill.add(txtContainer);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(139, 69, 19));
		separator.setBounds(138, 86, 7, 179);
		panelBill.add(separator);
		
		JButton btnPaymentDue = new JButton("Payment Due");
		btnPaymentDue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				double netTotal=0.0;
				double paid=0.0;
				double due=0.0;
				
				paid=Double.parseDouble((String)txtPaid.getText().toString());
				netTotal=Double.parseDouble((String)txtNetTotal.getText().toString());
				due=netTotal-paid;
				txtDue.setText(Double.toString(due));
				
				
				
			}
		});
		btnPaymentDue.setBounds(10, 201, 119, 47);
		panelBill.add(btnPaymentDue);
		
		JLabel lblPaid = new JLabel("Paid:");
		lblPaid.setForeground(new Color(139, 69, 19));
		lblPaid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPaid.setBounds(217, 272, 36, 24);
		panelBill.add(lblPaid);
		
		txtPaid = new JTextField();
		txtPaid.setText("0");
		txtPaid.setColumns(10);
		txtPaid.setBounds(265, 274, 131, 20);
		panelBill.add(txtPaid);
		
		JLabel lblDue = new JLabel("Due:");
		lblDue.setForeground(new Color(139, 69, 19));
		lblDue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDue.setBounds(219, 305, 36, 24);
		panelBill.add(lblDue);
		
		txtDue = new JTextField();
		txtDue.setColumns(10);
		txtDue.setBounds(265, 307, 131, 20);
		panelBill.add(txtDue);
		
		/*JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(0, 274, 200, 50);
		panelBill.add(editorPane);
		editorPane.setContentType("text/html");
		*/
		
		
		
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
			String sql="select * from Menu";
			resultSet=statement.executeQuery(sql);
			while(resultSet.next())
			{
				comboBox.addItem(resultSet.getString("ItemName"));
			
			}
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
		
	
		
	
		
		
	
	
	
		

		
		
		JPanel panelReciept = new JPanel();
		panelReciept.setBackground(new Color(255, 228, 225));
		panelReciept.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelReciept.setBounds(745, 11, 314, 599);
		frame2.getContentPane().add(panelReciept);
		panelReciept.setLayout(null);
		
		
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("Reciept");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(139, 69, 19));
		lblNewLabel.setBounds(128, 11, 58, 33);
		panelReciept.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 51, 294, 537);
		panelReciept.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 9));
		//textArea.setContentType("text/html");
		textArea.setEditable(false);
		scrollPane_1.setViewportView(textArea);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				PrintSupport.printComponent(textArea);
			}
		});
		btnPrint.setForeground(new Color(255, 255, 255));
		btnPrint.setBackground(new Color(139, 0, 0));
		btnPrint.setBounds(10, 11, 76, 29);
		panelReciept.add(btnPrint);
		frame2.setBounds(150, 50, 1075, 650);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
	
	/* private static boolean isAdjusting(JComboBox cbInput) {
	        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
	            return (Boolean) cbInput.getClientProperty("is_adjusting");
	        }
	        return false;
	    }

	    private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
	        cbInput.putClientProperty("is_adjusting", adjusting);
	    }

	    public static void setupAutoComplete(final JTextField txtCustName, final ArrayList<String> items) {
	        final DefaultComboBoxModel model = new DefaultComboBoxModel();
	        final JComboBox cbInput = new JComboBox(model) {
	            public Dimension getPreferredSize() {
	                return new Dimension(super.getPreferredSize().width, 0);
	            }
	        };
	        setAdjusting(cbInput, false);
	        for (String item : items) {
	            model.addElement(item);
	        }
	        cbInput.setSelectedItem(null);
	        cbInput.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (!isAdjusting(cbInput)) {
	                    if (cbInput.getSelectedItem() != null) {
	                    	txtCustName.setText(cbInput.getSelectedItem().toString());
	                    }
	                }
	            }
	        });

	        txtCustName.addKeyListener(new KeyAdapter() {

	            @Override
	            public void keyPressed(KeyEvent e) {
	                setAdjusting(cbInput, true);
	                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	                    if (cbInput.isPopupVisible()) {
	                        e.setKeyCode(KeyEvent.VK_ENTER);
	                    }
	                }
	                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
	                    e.setSource(cbInput);
	                    cbInput.dispatchEvent(e);
	                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                    	txtCustName.setText(cbInput.getSelectedItem().toString());
	                        cbInput.setPopupVisible(false);
	                    }
	                }
	                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	                    cbInput.setPopupVisible(false);
	                }
	                setAdjusting(cbInput, false);
	            }
	        });
	        txtCustName.getDocument().addDocumentListener(new DocumentListener() {
	            public void insertUpdate1(DocumentEvent e) {
	                updateList();
	            }

	            public void removeUpdate1(DocumentEvent e) {
	                updateList();
	            }

	            public void changedUpdate1(DocumentEvent e) {
	                updateList();
	            }

	            private void updateList() {
	                setAdjusting(cbInput, true);
	                model.removeAllElements();
	                String input = txtCustName.getText();
	                if (!input.isEmpty()) {
	                    for (String item : items) {
	                        if (item.toLowerCase().startsWith(input.toLowerCase())) {
	                            model.addElement(item);
	                        }
	                    }
	                }
	                cbInput.setPopupVisible(model.getSize() > 0);
	                setAdjusting(cbInput, false);
	            }

				@Override
				public void changedUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					
				}
	        });
	        txtCustName.setLayout(new BorderLayout());
	        txtCustName.add(cbInput, BorderLayout.SOUTH);
	
	
	
	
	
	
	    }*/
}