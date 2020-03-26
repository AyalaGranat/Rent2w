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
import javax.swing.JTextField;

import control.OrderLogic;
import control.ParkingStopLogic;
import control.VehicleLogic;
import entity.ParkingStop;
import entity.Renter;
import entity.Status;
import entity.TypeVehicle;
import entity.Vehicle;
import java.awt.Color;
import java.awt.Font;

public class ReturnVehicle extends Frame{

	   String currentChooseValue= "";
	    String cuurentChooseRadioButtonString="";
	    String chooseStringStreet="";
	    String chooseVehicleID="";
		JComboBox byStreet;
	 
		public ReturnVehicle(Renter renter) {
		
			
				JFrame frame = new JFrame("return Vehicle");  
		        JPanel panel = new JPanel();
		        panel.setBackground(new Color(248, 248, 255));
		
		        frame.getContentPane().add(panel);  
		       frame.setSize(500, 436);  
		        frame.setLocationRelativeTo(null);  
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		        frame.setVisible(true);  
	          panel.setLayout(null);
	         
	          
	   
	         
	          
	          
	          
	          
	          JLabel lblChooseVehicleType = new JLabel("Vehicle ID");
	          lblChooseVehicleType.setFont(new Font("Tahoma", Font.PLAIN, 14));
	          lblChooseVehicleType.setBounds(23, 61, 150, 14);
	          panel.add(lblChooseVehicleType);
	          
	          JLabel lblReturnVehicle = new JLabel("Return Vehicle");
	          lblReturnVehicle.setFont(new Font("Tahoma", Font.BOLD, 15));
	          lblReturnVehicle.setBounds(165, 22, 150, 14);
	          panel.add(lblReturnVehicle);
	          
	          
	          JLabel lblChooseParkingStop = new JLabel("Choose Parking Stop:");
	          lblChooseParkingStop.setFont(new Font("Tahoma", Font.PLAIN, 14));
	          lblChooseParkingStop.setBounds(42, 156, 150, 23);
	         panel.add(lblChooseParkingStop);
	          
	          JRadioButton rdbtnByName = new JRadioButton("by name");
	          rdbtnByName.setFont(new Font("Tahoma", Font.PLAIN, 11));
	          rdbtnByName.setBounds(324, 192, 100, 23);
	          panel.add(rdbtnByName);
	          
	          
	          JRadioButton rdbtnByAddress = new JRadioButton("by address");
	          rdbtnByAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
	          rdbtnByAddress.setBounds(150, 192, 150, 23);
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
	          byName.setFont(new Font("Tahoma", Font.PLAIN, 11));
	          byName.setBounds(304, 238, 150, 20);
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
	          byAdrress.setFont(new Font("Tahoma", Font.PLAIN, 11));
	          byAdrress.setBounds(105, 237, 158, 23);
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
	          
	          
	          
	          JButton returnVehicle = new JButton("return");
	          returnVehicle.setBounds(292, 299, 89, 23);
	          panel.add(returnVehicle);
	          
	          ArrayList<Vehicle> toName1=VehicleLogic.getInstance().getVehicleById(renter.getIdRenter());
	          List<String> nameArrayList1=new ArrayList<String>();
	          nameArrayList1.add("choose vehicle ID");
	          for(Vehicle name:toName1) {
	        	  nameArrayList1.add(name.getIdVehicle()+"");
	           }
	          Object[]ids=nameArrayList1.toArray();
	          
	          JComboBox comboBox = new JComboBox(ids);
	          comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
	          comboBox.setBounds(105, 60, 115, 20);
	          panel.add(comboBox);
	          comboBox.setVisible(true);
	          
	        comboBox.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						
						if(e.getStateChange()==ItemEvent.SELECTED) {
							
							chooseVehicleID=comboBox.getSelectedItem()+"";
							
				//			ArrayList<String> vehicleids=	OrderLogic.getInstance().getStreetByCity(select);
					//		Object[] stretStrings=vehicleids.toArray(); 
							
							
						//	comboBox = new JComboBox(vehicleids);
					   //     byStreet.setBounds(105, 279, 160, 20);
					   //     panel.add(byStreet);
					    //   chooseStringStreet= byStreet.getSelectedItem()+"";
					        
					
						}
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
			
	      //    chooseVehicleID= comboBox.getSelectedItem()+"";
	          
	          returnVehicle.addActionListener(new ActionListener() {
	        	  public void actionPerformed(ActionEvent e) { 
	        		  
	        				
	        								
	        				//Vehicle v = VehicleLogic.getInstance().getVehicleById(chooseVehicleID);
	        				
	        				
	        				VehicleLogic.getInstance().updateStatusVehicle(chooseVehicleID,Status.FREE);//check it 
	        				
	        			ParkingStop p = ParkingStopLogic.getInstance().getParkingStopByName(currentChooseValue);
	        			System.out.println(p);
	        			
	        			VehicleLogic.getInstance().incrementCorrentCapInPS(p);
	        			
	        			VehicleLogic.getInstance().updateEndDate(chooseVehicleID, renter.getIdRenter());
	        			
	    //  need to add  		//	ParkingStopLogic.getInstance().updateParkingStop(p.getIdParkingStop());
	        			
	 //not working////       			VehicleLogic.getInstance().DeleteVehicleFromRenter(chooseVehicleID, renter.getIdRenter()); 
	        			
	        			VehicleLogic.getInstance().updateParkingStopVehicle(chooseVehicleID, p.getIdParkingStop());
	        			
	        			double cost = VehicleLogic.getInstance().getcost(chooseVehicleID, renter.getIdRenter());
	        			JOptionPane.showMessageDialog(frame,"you returned your vehicle successfully, your payment:" + cost);
	        				
	        			
	        			
	        		
	        		
	        		  
	        	  } 
	          });
	        /////////////////////////////////////////rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr///////////////////////////////  

	     //     comboBox.addItemListener(new ItemListener() {
//				
//				@Override
//				public void itemStateChanged(ItemEvent e) {
//					String choosetype=comboBox.getSelectedItem()+"";
//				}
//			});
	          
	          

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
			
			JButton btnBack = new JButton("back");
		      btnBack.setBounds(37, 355, 89, 23);
		      panel.add(btnBack);
		      btnBack.addActionListener(new ActionListener() {
		  		
		  		@Override
		  		public void actionPerformed(ActionEvent e) {
		  			Welcome welcome = new Welcome(renter);
		  		}
		      });
		
		//	search.addActionListener(new ActionListener() {
				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					ArrayList<Vehicle> returnArrayList=new ArrayList<Vehicle>();
//					if ((comboBox.getSelectedItem().equals("ELECTRIC BICYCLE")||comboBox.getSelectedItem().equals("ELECTRIC SCOOTER"))&&cuurentChooseRadioButtonString.equals("rdbtnByName"))
//					{
//						if(comboBox.getSelectedItem().equals("ELECTRIC SCOOTER")) {
//							returnArrayList =VehicleLogic.getInstance().getVehiclesByPSandName(currentChooseValue,TypeVehicle.ELECTRICSCOOTER);
//						//	System.out.println("this here"+returnArrayList);
//							ResultSearchRent result= new ResultSearchRent(returnArrayList,currentChooseValue);
//							frame.setVisible(false);
//						}
//						else if(comboBox.getSelectedItem().equals("ELECTRIC BICYCLE")) {
//							returnArrayList =VehicleLogic	.getInstance().getVehiclesByPSandName(currentChooseValue,TypeVehicle.ELECTRICBICYCLE);
//							ResultSearchRent result= new ResultSearchRent(returnArrayList, currentChooseValue);
//							frame.setVisible(false);
//						}
//					}
//					else if (comboBox.getSelectedItem().equals("BYCICLE")&&cuurentChooseRadioButtonString.equals("rdbtnByName")) {
//						returnArrayList=VehicleLogic.getInstance().getVehiclesByPSandNameBicycle(currentChooseValue, TypeVehicle.BYCICLE);	
//						ResultSearchRent result= new ResultSearchRent(returnArrayList, currentChooseValue);
//						frame.setVisible(false);	
//						
//					}
//					else if (comboBox.getSelectedItem().equals("BYCICLE")&&cuurentChooseRadioButtonString.equals("rdbtnByAddress")) {
//						String selectrCityString=byAdrress.getSelectedItem()+"";
//						String selsectStreet=byStreet.getSelectedItem()+"";
//						returnArrayList=VehicleLogic.getInstance().getVehiclesByPSandAdressBicycle(selectrCityString,selsectStreet,TypeVehicle.BYCICLE);
//						ResultSearchRent result= new ResultSearchRent(returnArrayList,null);
//						frame.setVisible(false);	
//						}
//					else if (comboBox.getSelectedItem().equals("ELECTRIC BICYCLE")||comboBox.getSelectedItem().equals("ELECTRIC SCOOTER")&&cuurentChooseRadioButtonString.equals("rdbtnByAddress")) 
//					{
//						if(comboBox.getSelectedItem().equals("ELECTRIC SCOOTER")) {
//							String selectrCityString=byAdrress.getSelectedItem()+"";
//							String selsectStreet=byStreet.getSelectedItem()+"";
//							returnArrayList=VehicleLogic.getInstance().getEVehiclesByPSandAdress(selectrCityString,selsectStreet,TypeVehicle.ELECTRICSCOOTER);
//							ResultSearchRent result= new ResultSearchRent(returnArrayList,null);
//							frame.setVisible(false);
//						}
//						else if(comboBox.getSelectedItem().equals("ELECTRIC BICYCLE")) {
//							String selectrCityString=byAdrress.getSelectedItem()+"";
//							String selsectStreet=byStreet.getSelectedItem()+"";
//							returnArrayList=VehicleLogic.getInstance().getEVehiclesByPSandAdress(selectrCityString,selsectStreet,TypeVehicle.ELECTRICBICYCLE);
//							ResultSearchRent result= new ResultSearchRent(returnArrayList,null);
//							frame.setVisible(false);
//						}
//					
//					}
//				
//				else {
//					JOptionPane.showMessageDialog(null,"please choose option");
//					}
//				}
//			});
//			
//search.addActionListener(new ActionListener() {
				
	//			@Override
		//		public void actionPerformed(ActionEvent e) {
			//		if(textfield)
				//}
			
			
			
			
			}
}
