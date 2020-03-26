package boundry;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.BasicConfigurator;

import control.LoginLogic;

import entity.Renter;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class Login extends Frame{
	 
		
	JLabel lblWelcomeToRentw;
	JButton btnRentVehicle;
	JButton btnCreateReport;
	private JTextField textField;
	private JPasswordField passwordField;
	
	public Login() {
			BasicConfigurator.configure();
			JFrame frame = new JFrame("Login");
	        frame.setSize(500, 430);  
	        frame.setLocationRelativeTo(null);  
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        frame.getContentPane().setLayout(null);
	        
	        JLabel lblNewLabel = new JLabel("LOGIN");
	        lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
	        lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
	        lblNewLabel.setBounds(180, 78, 68, 36);
	        frame.getContentPane().add(lblNewLabel);
	        
	        JLabel lblNewLabel_1 = new JLabel("User name");
	        lblNewLabel_1.setForeground(new Color(0, 0, 0));
	        lblNewLabel_1.setBackground(new Color(255, 255, 255));
	        lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
	        lblNewLabel_1.setBounds(139, 185, 77, 14);
	        frame.getContentPane().add(lblNewLabel_1);
	        
	        textField = new JTextField();
	        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        textField.setBounds(241, 182, 86, 20);
	        frame.getContentPane().add(textField);
	        textField.setColumns(10);
	        
	        JLabel lblNewLabel_2 = new JLabel("Password");
	        lblNewLabel_2.setForeground(new Color(0, 0, 0));
	        lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
	        lblNewLabel_2.setBounds(139, 216, 77, 14);
	        frame.getContentPane().add(lblNewLabel_2);
	        
	        JLabel lblNewLabel_3 = new JLabel("Welcome to Rent2W");
	        lblNewLabel_3.setBackground(new Color(255, 255, 255));
	        lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 31));
	        lblNewLabel_3.setForeground(new Color(0, 128, 128));
	        lblNewLabel_3.setBounds(105, 49, 277, 36);
	        frame.getContentPane().add(lblNewLabel_3);
	        
	        JButton btnLogin = new JButton("login");
	        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        btnLogin.setBackground(new Color(60, 179, 113));
	        btnLogin.setBounds(192, 255, 89, 23);
	        
	        btnLogin.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	String username = textField.getText();
		        	//char[] password = passwordField.getPassword();
		        //	System.out.println("--------"+password);
		        	String 	password1 = new String (passwordField.getPassword());
		        	
		        	System.out.println("-----"+password1);
		        	
		        	try {
						//5.0 Need a be calculte 
					Renter renter = LoginLogic.getInstance().getRenter(username, password1);
					System.out.println(renter);
					if(renter !=null) {
					Welcome welcome = new Welcome(renter);
					}}
					catch (Exception ex) {
						ex.printStackTrace();
					JOptionPane.showMessageDialog(frame,"try again");
					}
					
					
					
			//	}
		        	
						//Welcome welcome=new Welcome();
		        }
		        	});
	        frame.getContentPane().add(btnLogin);
	        
	        passwordField = new JPasswordField();
	        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        passwordField.setBounds(241, 213, 86, 20);
	        frame.getContentPane().add(passwordField);
	        
	        JButton btnNewButton = new JButton("sign up now >");
	        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        btnNewButton.setBounds(45, 324, 180, 23);
	        frame.getContentPane().add(btnNewButton);
	        
	        JLabel lblNewLabel_4 = new JLabel("");
	        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        lblNewLabel_4.setBackground(new Color(255, 0, 0));
	        lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Acer\\Desktop\\rent2W_bonus_206038515_315680397\\rent2W_bonus\\Rent2WScreen\\pic\\unnamed.jpg"));
	        lblNewLabel_4.setBounds(0, -174, 484, 737);
	        frame.getContentPane().add(lblNewLabel_4);
	        frame.setVisible(true);  
	
	        btnNewButton.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	AddUser adduser = new AddUser();
		        	frame.setVisible(false);
		        }
	        });
	/*
	 * 	btnCreateReport = new JButton("Create Report");
		btnCreateReport.setBounds(206, 169, 179, 31);
		
		
		btnCreateReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Report report=new Report();
				frame.setVisible(false);
			}
		});
		panel.add(btnCreateReport);
				
	}	      
*/	     
	}		    	
}