package boundry;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.ElectricVehicle;
import entity.Location;
import entity.Renter;
import javax.swing.JTextField;

import control.BatteryLogic;

import javax.swing.JButton;
import java.awt.Color;

public class ReturnBatteris extends Frame {
	private JTextField textField;
	public ReturnBatteris(Renter renter) {
		
			
			JFrame frame = new JFrame("return Batteries");  
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(248, 248, 255));
	
	        frame.getContentPane().add(panel);  
	       frame.setSize(500, 338);  
	        frame.setLocationRelativeTo(null);  
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        frame.setVisible(true);  
          panel.setLayout(null);
          
          JLabel lblNewLabel = new JLabel("insert your battery code");
          lblNewLabel.setBounds(33, 29, 165, 14);
          panel.add(lblNewLabel);
          
          textField = new JTextField();
          textField.setBounds(208, 26, 86, 20);
          panel.add(textField);
          textField.setColumns(10);
          
          JButton btnSearch = new JButton("search");
          btnSearch.setBounds(33, 70, 89, 23);
          panel.add(btnSearch);
          
          JButton btnNewButton = new JButton("return battery");
          
          	
          btnNewButton.setBounds(33, 197, 128, 23);
          panel.add(btnNewButton);
      
          btnSearch.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent e) { 
        		  String code = textField.getText();
        		 ElectricVehicle v = BatteryLogic.getInstance().getVehicleByBattery(code);
        		 if (v == null) {
        			 JOptionPane.showMessageDialog(frame,"try again" );
        		 }
        		 else
        		 JOptionPane.showMessageDialog(frame,"you need to return your battery to:" + v);
        	  }
          });
          btnNewButton.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent e) { 
        		  String code = textField.getText();
        		 BatteryLogic.getInstance().updateLocationBattery(code, Location.INVEHICLE);
        		 if (code== null) {
        			 JOptionPane.showMessageDialog(frame,"insert code");
        		 }
        		 JOptionPane.showMessageDialog(frame,"you returned your battery");
}
});
          JButton btnBack = new JButton("back");
          btnBack.setBounds(33, 262, 89, 23);
          panel.add(btnBack);
          btnBack.addActionListener(new ActionListener() {
      		
      		@Override
      		public void actionPerformed(ActionEvent e) {
      			Welcome welcome = new Welcome(renter);
      		}
          });
	}
}