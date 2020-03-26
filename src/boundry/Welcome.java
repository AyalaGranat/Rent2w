package boundry;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.BasicConfigurator;

import control.VehicleLogic;
import entity.RentVehicle;
import entity.Renter;
import entity.TypeVehicle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Welcome extends Frame {
	/**
	 * @wbp.nonvisual location=217,169
	 */
	JLabel lblWelcomeToRentw;
	JButton btnRentVehicle;
	JButton btnCreateReport;
	JButton btnRetrunVehicle;
	private JButton btnTakeBattery;
	private JButton btnNewButton;

	public Welcome(Renter renter) {
		BasicConfigurator.configure();
		JFrame frame = new JFrame("welcome");  
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 205, 170));

		frame.getContentPane().add(panel);  
		frame.setSize(500, 409);  
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setVisible(true);  
		panel.setLayout(null);


		lblWelcomeToRentw = new JLabel("please choose your activity:");
		lblWelcomeToRentw.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWelcomeToRentw.setBounds(81, 24, 325, 36);
		panel.add(lblWelcomeToRentw);

		btnRetrunVehicle = new JButton("Retrun Vehicle");
		btnRetrunVehicle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRetrunVehicle.setBounds(50, 74, 155, 57);
		panel.add(btnRetrunVehicle);
		btnRetrunVehicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ReturnVehicle returnVehicle=new ReturnVehicle(renter);
				frame.setVisible(false);
			}
		});
		btnRentVehicle = new JButton("Rent Vehicle");
		btnRentVehicle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRentVehicle.setBounds(229, 74, 146, 57);
		panel.add(btnRentVehicle);

		btnRentVehicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChooseRentVehicle chooseRentVehicle=new ChooseRentVehicle(renter);
				frame.setVisible(false);
			}
		});

		btnCreateReport = new JButton("Create a Report car at a parking stop");
		btnCreateReport.setForeground(new Color(165, 42, 42));
		btnCreateReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreateReport.setBounds(172, 294, 275, 45);


		btnCreateReport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Report report=new Report();
				frame.setVisible(false);
			}
		});
		panel.add(btnCreateReport);

		btnTakeBattery = new JButton("Take Battery");
		btnTakeBattery.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnTakeBattery.setBounds(50, 142, 155, 57);
		btnTakeBattery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TakeBattery takebattery = new TakeBattery(renter);
			}
		});
		panel.add(btnTakeBattery);

		btnNewButton = new JButton("return battery");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(229, 142, 146, 57);

		btnNewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(renter.getCharger());
				if (renter.getCharger())
				{
					JOptionPane.showMessageDialog(frame,"you are not a charger");
				}
				else {
					ReturnBatteris battery=new ReturnBatteris(renter);
					frame.setVisible(false);
				}
			}
		});panel.add(btnNewButton);
		
		JButton btnReturn = new JButton(" return");
		btnReturn.setIcon(null);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login=new Login();
				frame.setVisible(false);
			}
		});
		btnReturn.setForeground(new Color(0, 0, 0));
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReturn.setBounds(10, 314, 135, 25);
		panel.add(btnReturn);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Acer\\Desktop\\rent2W_bonus_206038515_315680397\\rent2W_bonus\\Rent2WScreen\\pic\\bike1_sbrwxu.jpg"));
		lblNewLabel.setBounds(0, -67, 494, 507);
		panel.add(lblNewLabel);

	}
}	      
