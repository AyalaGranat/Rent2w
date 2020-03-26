package boundry; 

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import control.LoginLogic;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AddUser extends Frame {
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_1;

	public AddUser() {

		JFrame frame = new JFrame("Veiw Path");  
		JPanel panel = new JPanel();

		frame.getContentPane().add(panel);  
		frame.setSize(409, 482);  
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setVisible(true);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(90, 71, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setBounds(90, 174, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_3.setBounds(90, 225, 86, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_4.setBounds(90, 271, 86, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("charger");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxNewCheckBox.setBounds(79, 359, 97, 23);
		panel.add(chckbxNewCheckBox);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_5.setBounds(225, 360, 86, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("phone:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(90, 46, 46, 14);
		panel.add(lblNewLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(90, 127, 165, 30);
		panel.add(dateChooser);
		
		JLabel lblDateOfBirth = new JLabel("date of birth:");
		lblDateOfBirth.setForeground(new Color(255, 255, 255));
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDateOfBirth.setBounds(90, 102, 86, 14);
		panel.add(lblDateOfBirth);
		
		JLabel lblFname = new JLabel("first name:");
		lblFname.setForeground(new Color(255, 255, 255));
		lblFname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFname.setBounds(90, 159, 86, 14);
		panel.add(lblFname);
		
		JLabel lblLastName = new JLabel("last name:");
		lblLastName.setForeground(new Color(255, 255, 255));
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(90, 205, 86, 14);
		panel.add(lblLastName);
		
		JLabel lblEmail = new JLabel("email :");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(90, 256, 86, 14);
		panel.add(lblEmail);
		
		JLabel lblNewLabel_1 = new JLabel("pasword:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(225, 335, 86, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Add Me!");
		btnNewButton.setBackground(new Color(60, 179, 113));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(264, 407, 119, 23);
		panel.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setBounds(90, 321, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Adress");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(90, 302, 86, 14);
		panel.add(lblNewLabel_2);
		
		btnNewButton.addActionListener(new ActionListener() {
			

			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				java.util.Date d11=dateChooser.getDate();
				Date date21=null;
				date21= new java.sql.Date(d11.getTime());
				String chooseString="";
				if(chckbxNewCheckBox.isSelected())
					chooseString="yes";
				else {
					chooseString="no";
				}
				try {
				LoginLogic.getInstance().addNewUser(textField.getText(),date21, textField_2.getText(),textField_3.getText(),textField_1.getText(),textField_4.getText(),chooseString,  textField_5.getText() );
				JOptionPane.showMessageDialog(frame,"the add user is succeeded");

				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(frame,"the add user is not succeeded, please choose other date of birth or phone");
				}
			}
		});
		JButton btnBack = new JButton("back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
	      btnBack.setBounds(27, 409, 89, 23);
	      panel.add(btnBack);
	      
	      JLabel lblPleaseEnterYour = new JLabel("please enter your details below:");
	      lblPleaseEnterYour.setForeground(new Color(255, 255, 255));
	      lblPleaseEnterYour.setFont(new Font("Tahoma", Font.BOLD, 15));
	      lblPleaseEnterYour.setBounds(79, 11, 270, 30);
	      panel.add(lblPleaseEnterYour);
	      
	      JLabel lblNewLabel_3 = new JLabel("New label");
	      lblNewLabel_3.setForeground(new Color(255, 255, 255));
	      lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Acer\\Desktop\\rent2W_bonus_206038515_315680397\\rent2W_bonus\\Rent2WScreen\\pic\\5ftx7ft.jpg"));
	      lblNewLabel_3.setBounds(0, 0, 405, 450);
	      panel.add(lblNewLabel_3);
	      
	      JLabel label = new JLabel("New label");
	      label.setBounds(0, 0, 46, 14);
	      panel.add(label);
	      btnBack.addActionListener(new ActionListener() {
	  		
	  		@Override
	  		public void actionPerformed(ActionEvent e) {
	  			Login login = new Login();
	  			frame.setVisible(false);
	  		}
	      });
	}
}
