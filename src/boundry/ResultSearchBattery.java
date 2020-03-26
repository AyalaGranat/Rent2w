package boundry;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import control.BatteryLogic;
import control.OrderLogic;
import control.VehicleLogic;
import entity.Battery;
import entity.Location;
import entity.ParkingStop;
import entity.Renter;
import entity.Status;
import entity.Vehicle;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

public class ResultSearchBattery extends Frame{

	private DefaultTableModel tableModel;
	private String[] headerStrings= new String[] {"park Id", "city"};
	private JTable table;
	private JScrollBar scrollBar;
	
	
		public ResultSearchBattery(ArrayList<Battery> returnArrayList, Renter renter) {
		

			JFrame frame = new JFrame("Rent2W");
		    JPanel panel = new JPanel();
		    panel.setBackground(new Color(248, 248, 255));
	
	        frame.getContentPane().add(panel);  
	        frame.setSize(500, 405);  
	        frame.setLocationRelativeTo(null);  
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        frame.setVisible(true); 
	        panel.setLayout(null);

		
		
		
		
		JLabel lblAllFreeVehicle = new JLabel("All Free Batteries in the Area");
		lblAllFreeVehicle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAllFreeVehicle.setBounds(102, 11, 228, 28);
		panel.add(lblAllFreeVehicle);
		
		  ArrayList<String> batteriesArrayList=new ArrayList<String>();
	     for(Battery battery:returnArrayList) {
	   	  batteriesArrayList.add(battery.toString()+"");
	        }
	       Object[]batteriessString=batteriesArrayList.toArray();
          
		JList list = new JList(batteriessString);
		list.setBorder(new LineBorder(new Color(220, 220, 220), 4, true));
		list.setBounds(29, 78, 429, 167);
		panel.add(list);
		list.setVisibleRowCount(20);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	//	add(new JScrollBar(list));
		
		
		
		
		JButton btnSelect = new JButton("select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
							
		//1	Renter renter=OrderLogic.getInstance().getRenters().get(0);//only fot example
			
			Battery batteryChoosToRent=returnArrayList.get(list.getSelectedIndex());
			BatteryLogic.getInstance().updateLocationBattery(batteryChoosToRent.getIdBattery(), Location.INCHARGING);//check it 
			ParkingStop ps=null;
			BatteryLogic.getInstance().addChargerBattery(batteryChoosToRent.getIdBattery(), renter.getIdRenter());
			JOptionPane.showMessageDialog(frame,"you rent the battery successfully");
			//if(renter!=null) {
			//	ps = OrderLogic.getInstance().getParkingStopbyName(renter);  
			//	VehicleLogic.getInstance().updateCorrentCapInPS(ps);
			}
//			else {
//				ArrayList<ParkingStop>parkingStops=OrderLogic.getInstance().getParkingStop();
//				ps=parkingStops.get(list.getSelectedIndex());
//		1		VehicleLogic.getInstance().updateCorrentCapInPS(ps);
//				
				
		//	}
			
		//	VehicleLogic.getInstance().addRentVehicle(renter.getIdRenter(), vehicleChoosToRent.getIdVehicle());
//			FinalRent finalRent= new FinalRent(renter ,vehicleChoosToRent);
//			frame.setVisible(false);
			
			
		});
		btnSelect.setBounds(335, 256, 100, 23);
		panel.add(btnSelect);
	
		JButton btnBack = new JButton("back");
	      btnBack.setBounds(29, 314, 89, 23);
	      panel.add(btnBack);
	      
	      JLabel lblPleaseChoose = new JLabel("please choose:");
	      lblPleaseChoose.setFont(new Font("Tahoma", Font.PLAIN, 15));
	      lblPleaseChoose.setBounds(29, 50, 228, 28);
	      panel.add(lblPleaseChoose);
	      btnBack.addActionListener(new ActionListener() {
	  		
	  		@Override
	  		public void actionPerformed(ActionEvent e) {
	  		TakeBattery	battery = new TakeBattery(renter);
	  		}
	      });
		
	}

}
