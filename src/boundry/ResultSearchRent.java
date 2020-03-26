package boundry;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.junit.experimental.theories.FromDataPoints;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.healthmarketscience.jackcess.complex.ComplexValue.Id;

import control.OrderLogic;
import control.VehicleLogic;
import entity.ParkingStop;
import entity.Renter;
import entity.Status;
import entity.TypeVehicle;
import entity.Vehicle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Scrollbar;

public class ResultSearchRent extends Frame {
	
	private DefaultTableModel tableModel;
	private String[] headerStrings= new String[] {"park Id", "city"};
	private JTable table;
	private JScrollBar scrollBar;
	
	
		public ResultSearchRent(ArrayList<Vehicle> arrayVehicles, String nameOfPS, Renter renter) {
		

			JFrame frame = new JFrame("Rent2W");
		    JPanel panel = new JPanel();
		    panel.setBackground(new Color(248, 248, 255));
	
	        frame.getContentPane().add(panel);  
	        frame.setSize(500, 395);  
	        frame.setLocationRelativeTo(null);  
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        frame.setVisible(true); 
	        panel.setLayout(null);

		
		
		
		
		JLabel lblAllFreeVehicle = new JLabel("All Free Vehicle in "+nameOfPS);
		lblAllFreeVehicle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAllFreeVehicle.setBounds(112, 11, 214, 14);
		panel.add(lblAllFreeVehicle);
		
		  ArrayList<String> vehiclesArrayList=new ArrayList<String>();
	      for(Vehicle vehicle:arrayVehicles) {
	    	  vehiclesArrayList.add(vehicle.toString()+"");
	        }
	       Object[]vehiclesString=vehiclesArrayList.toArray();
          
		JList list = new JList(vehiclesString);
		list.setBorder(new LineBorder(new Color(230, 230, 250), 4, true));
		list.setBounds(29, 80, 429, 167);
		panel.add(list);
		list.setVisibleRowCount(20);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	//	add(new JScrollBar(list));
		
		
		
		
		JButton btnSelect = new JButton("select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
							
			//Renter renter=OrderLogic.getInstance().getRenters().get(0);//only fot example
			
			Vehicle vehicleChoosToRent=arrayVehicles.get(list.getSelectedIndex());
			VehicleLogic.getInstance().updateStatusVehicle(vehicleChoosToRent.getIdVehicle(),Status.RENT);//check it 
			ParkingStop ps=null;
			if(nameOfPS!=null) {
				ps = OrderLogic.getInstance().getParkingStopbyName(nameOfPS);  
				VehicleLogic.getInstance().updateCorrentCapInPS(ps);
			}
			else {
				ArrayList<ParkingStop>parkingStops=OrderLogic.getInstance().getParkingStop();
				ps=parkingStops.get(list.getSelectedIndex());
				VehicleLogic.getInstance().updateCorrentCapInPS(ps);
				
				
			}
			
			VehicleLogic.getInstance().addRentVehicle(renter.getIdRenter(), vehicleChoosToRent.getIdVehicle());
			FinalRent finalRent= new FinalRent(renter ,vehicleChoosToRent);
			frame.setVisible(false);
			
			}
		});
		btnSelect.setBounds(321, 274, 100, 23);
		panel.add(btnSelect);
		btnSelect.setBounds(321, 274, 100, 23);
		panel.add(btnSelect);
	
		JButton btnBack = new JButton("back");
	      btnBack.setBounds(29, 308, 89, 23);
	      panel.add(btnBack);
	      
	      JLabel lblPleaseChoose = new JLabel("please choose:");
	      lblPleaseChoose.setFont(new Font("Tahoma", Font.BOLD, 15));
	      lblPleaseChoose.setBounds(29, 46, 214, 23);
	      panel.add(lblPleaseChoose);
	      btnBack.addActionListener(new ActionListener() {
	  		
	  		@Override
	  		public void actionPerformed(ActionEvent e) {
	  		ChooseRentVehicle	rent = new ChooseRentVehicle(renter);
	  		}
	      });
		
		
	}
}
