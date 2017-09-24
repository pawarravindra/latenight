package mainpage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import firstpage.MainPage;

public class Main {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;


	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}
	
	
	
	public void design()
	{
		frame = new JFrame();
		frame.setBackground(new Color(255, 0, 255));
		frame.setVisible(true);
		frame.setResizable(false);
	
		Image img=new ImageIcon("C:\\LNDatabase\\logo.jpg").getImage();
		frame.setIconImage(img);
		frame.setTitle("LateNight Food");
		frame.setBounds(300, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblfood = new JLabel("Food");
		lblfood.setForeground(new Color(255, 255, 0));
		lblfood.setFont(new Font("AR JULIAN", Font.PLAIN, 45));
		lblfood.setBounds(219, 70, 126, 34);
		frame.getContentPane().add(lblfood);
	
		//frame.setContentPane(new JLabel(new ImageIcon("img/a.jpg")));
		
		JLabel lblMidNight = new JLabel("LateNight");
		lblMidNight.setForeground(new Color(255, 255, 0));
		lblMidNight.setFont(new Font("AR JULIAN", Font.PLAIN, 45));
		lblMidNight.setBounds(80, 30, 229, 41);
		frame.getContentPane().add(lblMidNight);
		
		JLabel lblUserName = new JLabel("Username :");
		lblUserName.setForeground(new Color(0, 255, 255));
		lblUserName.setFont(new Font("Calibri", Font.BOLD, 16));
		lblUserName.setBounds(172, 159, 82, 28);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(new Color(0, 255, 255));
		lblPassword.setFont(new Font("Calibri", Font.BOLD, 16));
		lblPassword.setBounds(172, 196, 74, 23);
		frame.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(256, 161, 154, 24);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(256, 196, 154, 23);
		frame.getContentPane().add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(255, 255, 224));
		btnLogin.setBackground(new Color(128, 0, 0));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username =txtUsername.getText();
				String password =txtPassword.getText();
				
				if(password.contains("latenight")&&username.contains("latenight")){
					txtUsername.setText(null);
					txtPassword.setText(null);
					frame.dispose();
					MainPage first=new MainPage();
					//MainPage.main(null);
					
				}
				else{
					JOptionPane.showMessageDialog(null,"Invalid Login Details","Login Error",JOptionPane.ERROR_MESSAGE);
					txtUsername.setText(null);
					txtPassword.setText(null);
				}
				
			}
		});
		btnLogin.setBounds(149, 243, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBorderPainted(false);
		btnReset.setForeground(new Color(255, 255, 224));
		btnReset.setBackground(new Color(128, 0, 0));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUsername.setText(null);
				txtPassword.setText(null);
			}
		});
		btnReset.setBounds(242, 243, 89, 23);
		frame.getContentPane().add(btnReset);
		
		JButton btnClose = new JButton("Close");
		btnClose.setForeground(new Color(255, 255, 224));
		btnClose.setBackground(new Color(128, 0, 0));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame,"Do you want to exit","Login System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
				System.exit(0);	
				
				}
			}
		});
		btnClose.setBounds(335, 243, 89, 23);
		frame.getContentPane().add(btnClose);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(139, 139, 305, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(139, 230, 305, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblNewLabel = new JLabel("backlabel");
		lblNewLabel.setIcon(new ImageIcon("C:\\LNDatabase\\b.jpg"));
		lblNewLabel.setBounds(0, 0, 600, 421);
		frame.getContentPane().add(lblNewLabel);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		design();
	}
}
