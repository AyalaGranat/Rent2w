package boundry;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Renter;
import entity.Vehicle;
import java.awt.Font;

public class FinalRent extends Frame {
	public FinalRent(Renter renter, Vehicle vehicle) {

		JFrame frame = new JFrame("Rent2W");
	    JPanel panel = new JPanel();

        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel(renter.getRenterFName()+" your Order vehicle "+ vehicle.getIdVehicle()+" successfully completed");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(24, 53, 450, 152);
        panel.add(lblNewLabel);
        frame.setSize(500, 338);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);
        
        JButton btnBack = new JButton("back");
	      btnBack.setBounds(37, 228, 89, 23);
	      panel.add(btnBack);
	      btnBack.addActionListener(new ActionListener() {
	  		
	  		@Override
	  		public void actionPerformed(ActionEvent e) {
	  			Welcome welcome = new Welcome(renter);
	  		}
	      });
	}
}
