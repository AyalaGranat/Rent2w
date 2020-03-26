package boundry;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Array;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

import org.apache.log4j.BasicConfigurator;
import org.junit.Ignore;

import control.OrderLogic;
import control.VehicleLogic;
import entity.TypeVehicle;
import entity.*;
import java.awt.Color;
import java.awt.Font;

public class ChooseRentVehicle extends Frame  {


    String currentChooseValue= "";
    String cuurentChooseRadioButtonString="";
    String chooseStringStreet="";
	JComboBox byStreet;
 
	public ChooseRentVehicle(Renter renter) {
	
		
			JFrame frame = new JFrame("Rent Vehicle");  
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(248, 248, 255));
	
	        frame.getContentPane().add(panel);  
	       frame.setSize(500, 454);  
	        frame.setLocationRelativeTo(null);  
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        frame.setVisible(true);  
          panel.setLayout(null);
         
          
   
          // מתוךלטפל שיהיה הנתונים TO TYPE
          String[] inArray= {"choose type","ELECTRIC BICYCLE","ELECTRIC SCOOTER","BYCICLE"};
          
          
          
          JLabel lblChooseVehicleType = new JLabel("Choose Vehicle Type:");
          lblChooseVehicleType.setFont(new Font("Tahoma", Font.PLAIN, 14));
          lblChooseVehicleType.setBounds(42, 61, 150, 14);
          panel.add(lblChooseVehicleType);
          
          JLabel lblRentVehicle = new JLabel("Rent Vehicle");
          lblRentVehicle.setFont(new Font("Tahoma", Font.BOLD, 15));
          lblRentVehicle.setBounds(164, 22, 150, 14);
          panel.add(lblRentVehicle);
          
          JComboBox comboBox = new JComboBox(inArray);
          comboBox.setBounds(196, 84, 150, 20);
          panel.add(comboBox);
          
          
          JLabel lblChooseParkingStop = new JLabel("Choose Parking Stop:");
          lblChooseParkingStop.setFont(new Font("Tahoma", Font.PLAIN, 14));
          lblChooseParkingStop.setBounds(42, 165, 150, 20);
         panel.add(lblChooseParkingStop);
          
          JRadioButton rdbtnByName = new JRadioButton("by name");
          rdbtnByName.setBounds(324, 192, 100, 23);
          panel.add(rdbtnByName);
          
          
          JRadioButton rdbtnByAddress = new JRadioButton("by address");
          rdbtnByAddress.setBounds(113, 192, 150, 23);
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
          
          
          
          JButton search = new JButton("search");
          search.setBackground(new Color(152, 251, 152));
          search.setForeground(new Color(0, 0, 0));
          search.setBounds(335, 299, 89, 23);
          panel.add(search);

          comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String choosetype=comboBox.getSelectedItem()+"";
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
				ArrayList<Vehicle> returnArrayList=new ArrayList<Vehicle>();
				if ((comboBox.getSelectedItem().equals("ELECTRIC BICYCLE")||comboBox.getSelectedItem().equals("ELECTRIC SCOOTER"))&&cuurentChooseRadioButtonString.equals("rdbtnByName"))
				{
					if(comboBox.getSelectedItem().equals("ELECTRIC SCOOTER")) {
						returnArrayList =VehicleLogic.getInstance().getVehiclesByPSandName(currentChooseValue,TypeVehicle.ELECTRICSCOOTER);
					//	System.out.println("this here"+returnArrayList);
						ResultSearchRent result= new ResultSearchRent(returnArrayList,currentChooseValue, renter);
						frame.setVisible(false);
					}
					else if(comboBox.getSelectedItem().equals("ELECTRIC BICYCLE")) {
						returnArrayList =VehicleLogic	.getInstance().getVehiclesByPSandName(currentChooseValue,TypeVehicle.ELECTRICBICYCLE);
						ResultSearchRent result= new ResultSearchRent(returnArrayList, currentChooseValue, renter);
						frame.setVisible(false);
					}
				}
				else if (comboBox.getSelectedItem().equals("BYCICLE")&&cuurentChooseRadioButtonString.equals("rdbtnByName")) {
					returnArrayList=VehicleLogic.getInstance().getVehiclesByPSandNameBicycle(currentChooseValue, TypeVehicle.BYCICLE);	
					ResultSearchRent result= new ResultSearchRent(returnArrayList, currentChooseValue, renter);
					frame.setVisible(false);	
					
				}
				else if (comboBox.getSelectedItem().equals("BYCICLE")&&cuurentChooseRadioButtonString.equals("rdbtnByAddress")) {
					String selectrCityString=byAdrress.getSelectedItem()+"";
					String selsectStreet=byStreet.getSelectedItem()+"";
					returnArrayList=VehicleLogic.getInstance().getVehiclesByPSandAdressBicycle(selectrCityString,selsectStreet,TypeVehicle.BYCICLE);
					ResultSearchRent result= new ResultSearchRent(returnArrayList,currentChooseValue, renter);
					frame.setVisible(false);	
					}
				else if (comboBox.getSelectedItem().equals("ELECTRIC BICYCLE")||comboBox.getSelectedItem().equals("ELECTRIC SCOOTER")&&cuurentChooseRadioButtonString.equals("rdbtnByAddress")) 
				{
					if(comboBox.getSelectedItem().equals("ELECTRIC SCOOTER")) {
						String selectrCityString=byAdrress.getSelectedItem()+"";
						String selsectStreet=byStreet.getSelectedItem()+"";
						returnArrayList=VehicleLogic.getInstance().getEVehiclesByPSandAdress(selectrCityString,selsectStreet,TypeVehicle.ELECTRICSCOOTER);
						ResultSearchRent result= new ResultSearchRent(returnArrayList,currentChooseValue, renter);
						frame.setVisible(false);
					}
					else if(comboBox.getSelectedItem().equals("ELECTRIC BICYCLE")) {
						String selectrCityString=byAdrress.getSelectedItem()+"";
						String selsectStreet=byStreet.getSelectedItem()+"";
						returnArrayList=VehicleLogic.getInstance().getEVehiclesByPSandAdress(selectrCityString,selsectStreet,TypeVehicle.ELECTRICBICYCLE);
						ResultSearchRent result= new ResultSearchRent(returnArrayList,currentChooseValue, renter);
						frame.setVisible(false);
					}
				
				}
			
			else {
				JOptionPane.showMessageDialog(null,"please choose option");
				}
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
}
}
			/*
			class ComboItem{
			    private String name;

			    public ComboItem(String name)
			    {
			        this.name = name;
			    }

			    @Override
			    public String toString()
			    {
			        return name;
			    }

			    public String getCity()
			    {
			        return name;
			    }

			}
		
			byName.addItem(new ComboItem("Acre"));
          
          
   
          /*
    }
	/*  public static ChooseRentVehicle getChooseRentVehicle() {
			if (frame == null) {
				frame = new ChooseRentVehicle();
			}
			
			return frame;
		}*/
//}
