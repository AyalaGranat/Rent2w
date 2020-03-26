package boundry;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import control.ElectricVehicleLogic;
import control.OrderLogic;
import control.ParkingStopLogic;
import control.VehicleLogic;
import entity.Battery;
import entity.ParkingStop;
import entity.Renter;
import entity.TypeVehicle;
import entity.Vehicle;
import java.awt.Font;
import java.awt.Color;

public class TakeBattery extends Frame {

	String currentChooseValue= "";
    String cuurentChooseRadioButtonString="";
    String chooseStringStreet="";
	JComboBox byStreet;
	
	public TakeBattery(Renter renter) {
		
		
		JFrame frame = new JFrame("Rent Vehicle");  
        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 248, 255));

        frame.getContentPane().add(panel);  
       frame.setSize(500, 429);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);  
      panel.setLayout(null);
     
      

     
      
      
      JLabel lblTakeBattery = new JLabel("Take Battery");
      lblTakeBattery.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblTakeBattery.setBounds(196, 34, 150, 14);
      panel.add(lblTakeBattery);
      
      
      JLabel lblChooseParkingStop = new JLabel("Choose Parking Stop:");
      lblChooseParkingStop.setFont(new Font("Tahoma", Font.PLAIN, 14));
      lblChooseParkingStop.setBounds(43, 61, 150, 32);
     panel.add(lblChooseParkingStop);
      
      JRadioButton rdbtnByName = new JRadioButton("by name");
      rdbtnByName.setBounds(318, 100, 100, 23);
      panel.add(rdbtnByName);
      
      
      JRadioButton rdbtnByAddress = new JRadioButton("by address");
      rdbtnByAddress.setBounds(151, 100, 150, 23);
      panel.add(rdbtnByAddress);
      
      ButtonGroup radioGroup=new ButtonGroup();
      radioGroup.add(rdbtnByAddress);
      radioGroup.add(rdbtnByName);
      
      //get name of ps
      ArrayList<ParkingStop> toName=OrderLogic.getInstance().getParkingStop();
      List<String> nameArrayList=new ArrayList<String>();
      nameArrayList.add("choose name");
      for(ParkingStop name:toName) {
    	  nameArrayList.add(name.getNameParkingStop()+"");
       }
      Object[]names=nameArrayList.toArray();
  
    
      JComboBox byName= new JComboBox(names);
      byName.setBounds(292, 130, 150, 20);
      panel.add(byName);
      byName.setVisible(false);
      
   //   byStreet.setVisible(false);
          
      //get name of ps
      ArrayList<String> toCity=OrderLogic.getInstance().getCityofPS();
      List<String> adressArrayList=new ArrayList<String>();
      adressArrayList.add("choose city");
      for(String city:toCity) {
    	  adressArrayList.add(city+"");
        }
       Object[]cities=adressArrayList.toArray();
      
      JComboBox byAdrress = new JComboBox(cities);
      byAdrress.setBounds(109, 130, 158, 23);
      panel.add(byAdrress);
      byAdrress.setVisible(false);
      
      byAdrress.addItemListener(new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getStateChange()==ItemEvent.SELECTED) {
				
				String select=byAdrress.getSelectedItem()+"";
				ArrayList<String> streets=	OrderLogic.getInstance().getStreetByCity(select);
				Object[] stretStrings=streets.toArray(); 
				
				
				byStreet = new JComboBox(stretStrings);
		        byStreet.setBounds(105, 279, 160, 20);
		        panel.add(byStreet);
		        chooseStringStreet= byStreet.getSelectedItem()+"";
		        
		
			}
		}
	});
      
      
      
      JButton search = new JButton("search");
      search.setBounds(292, 299, 89, 23);
      panel.add(search);
      
      JButton btnBack = new JButton("back");
      btnBack.setBounds(36, 341, 89, 23);
      panel.add(btnBack);
      btnBack.addActionListener(new ActionListener() {
  		
  		@Override
  		public void actionPerformed(ActionEvent e) {
  			Welcome welcome = new Welcome(renter);
  		}
      });

      rdbtnByName.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		byAdrress.setVisible(false);
		byName.setVisible(true);
		cuurentChooseRadioButtonString="rdbtnByName";
		}
	});
      
      
      rdbtnByAddress.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			byName.setVisible(false);
			byAdrress.setVisible(true);
			cuurentChooseRadioButtonString="rdbtnByAddress";
			
			
		}
	});
      
	byName.addItemListener(new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			currentChooseValue=byName.getSelectedItem()+"";
		}
	});
	
	byAdrress.addItemListener(new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			currentChooseValue=byAdrress.getSelectedItem()+"";
		}
	});
	


	search.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Battery> returnArrayList=new ArrayList<Battery>();
			if (cuurentChooseRadioButtonString.equals("rdbtnByName")){
				ParkingStop p = ParkingStopLogic.getInstance().getParkingStopByName(currentChooseValue);
					returnArrayList =ElectricVehicleLogic.getInstance().getBatteriesFromParkingStop(p.getIdParkingStop());
				//	System.out.println("this here"+returnArrayList);
					ResultSearchBattery result= new ResultSearchBattery(returnArrayList, renter);
					frame.setVisible(false);
			}
				
			
			else if (cuurentChooseRadioButtonString.equals("rdbtnByAddress")) {
				String selectrCityString=byAdrress.getSelectedItem()+"";
				String selsectStreet=byStreet.getSelectedItem()+"";
				ParkingStop p = ParkingStopLogic.getInstance().getParkingStopByName(currentChooseValue);
				returnArrayList =ElectricVehicleLogic.getInstance().getBatteriesFromParkingStop(p.getIdParkingStop());				ResultSearchBattery result= new ResultSearchBattery(returnArrayList,renter);
				frame.setVisible(false);	
			}
			
				 
					
				
				
			
	
		
		else {
			JOptionPane.showMessageDialog(null,"please choose option");
			}
		}
	});
	
	

}
}
