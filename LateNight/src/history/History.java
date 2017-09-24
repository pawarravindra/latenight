package history;

import java.awt.Color;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

//import submain.Submain;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.text.Document;
//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;
//import org.jdom.Document;

import javax.swing.JTextArea;

import firstpage.MainPage;

public class History {
	JTextArea txtHistory;

	private JFrame frame4;
	private final Action action = new SwingAction();
	private JTextField txtDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					History window = new History();
					window.frame4.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public History() {
		
		initialize();
	}
	class MyHighlighterPainter extends DefaultHighlighter.DefaultHighlightPainter {

		public MyHighlighterPainter(Color color) {
			super(color);
			// TODO Auto-generated constructor stub
		}
		
	}
	Highlighter.HighlightPainter MyHighlighterPainter=new MyHighlighterPainter(Color.cyan);
	
	public void removeHighter(JTextComponent textcomponent)
	{
		Highlighter highlights=textcomponent.getHighlighter();
		Highlighter.Highlight[] hilets=highlights.getHighlights();
		
		for(int i=0;i<hilets.length;i++)
		{
			if(hilets[i].getPainter() instanceof MyHighlighterPainter)
			{
				highlights.removeHighlight(hilets[i]);
			}
		}
	}
	
	
	
	public void highligh(JTextComponent textcomponent,String pattern) 
	{
		removeHighter(textcomponent);
		try {
			Highlighter highlight=textcomponent.getHighlighter();
			Document doc=textcomponent.getDocument();
			String text=doc.getText(0, doc.getLength());
			int pos = 0;
			
			while((pos=text.toUpperCase().indexOf(pattern.toUpperCase(),pos))>=0) {
				highlight.addHighlight(pos, pos+pattern.length(), MyHighlighterPainter);
				pos+=pattern.length();
			}
				
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame4 = new JFrame();
		frame4.setBounds(150, 50, 1075, 650);
		frame4.getContentPane().setBackground(new Color(255, 228, 225));
		frame4.setVisible(true);
		frame4.setTitle("LateNight Food");
		frame4.setResizable(false);
		Image img=new ImageIcon("C:/LNDatabase/logo.jpg").getImage();
		frame4.setIconImage(img);
		
		
		
		
		
		
		frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame4.getContentPane().setLayout(null);
		frame4.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 147, 1059, 463);
		panel.setBackground(new Color(255, 228, 225));
		panel.setForeground(new Color(0, 0, 0));
		frame4.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 0, 972, 383);
		panel.add(scrollPane);
		
		txtHistory = new JTextArea();
		txtHistory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHistory.setForeground(new Color(139, 69, 19));
		txtHistory.setBackground(new Color(255, 228, 225));
		scrollPane.setViewportView(txtHistory);
		
		Object[] columns={"Bill No.","Customer Name","Mobile No.","Total","Address"};
		DefaultTableModel model=new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(58, 34, 70, 30);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame4.dispose();
				MainPage sub=new MainPage();
			}
		});
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(139, 0, 0));
		frame4.getContentPane().add(btnHome);
		
		JButton btnViewDetails = new JButton("View History");
		btnViewDetails.setBounds(58, 88, 160, 30);
		frame4.getContentPane().add(btnViewDetails);
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 try
					{
					 	
					 
						String path="C:/LNDatabase/Menu.txt";
						File file=new File(path);
						if(!file.exists())
						{
							file.createNewFile();
						}
						
						FileReader fw=new FileReader(file);
						
						BufferedReader br=new BufferedReader(fw);
						txtHistory.read(br,null);
						txtHistory.requestFocus();
						
						
						br.close();
						fw.close();
							
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				 
				 
				 
				
				
				
			}
		});
		btnViewDetails.setForeground(Color.WHITE);
		btnViewDetails.setBackground(new Color(139, 0, 0));
		
		txtDate = new JTextField();
		txtDate.setBounds(250, 95, 136, 20);
		frame4.getContentPane().add(txtDate);
		txtDate.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.setBackground(new Color(139, 0, 0));
		btnFind.setForeground(new Color(255, 255, 255));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				highligh(txtHistory,txtDate.getText());
				
			}
		});
		btnFind.setBounds(420, 88, 70, 30);
		frame4.getContentPane().add(btnFind);
		
		JLabel lblEnterDateddmmyyyy = new JLabel("Enter Mobile No.:");
		lblEnterDateddmmyyyy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterDateddmmyyyy.setForeground(new Color(139, 69, 19));
		lblEnterDateddmmyyyy.setBounds(250, 64, 213, 20);
		frame4.getContentPane().add(lblEnterDateddmmyyyy);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
