package control;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entity.Battery;
import entity.Consts;
import entity.ElectricVehicle;
import entity.Location;
import entity.Status;
import entity.TypeVehicle;
import entity.Vehicle;

public class ElectricVehicleLogic {
	
	private static ElectricVehicleLogic _instance;

	private ElectricVehicleLogic() {
	}

	public static ElectricVehicleLogic getInstance() {
		if (_instance == null)
			_instance = new ElectricVehicleLogic();
		return _instance;
	}

	public void importElectricVehicleFromXML(String path) throws ParseException {
		
    	try {
			Document doc = DocumentBuilderFactory.newInstance()
								.newDocumentBuilder().parse(new File(path));
			
			doc.getDocumentElement().normalize();
			NodeList nl = doc.getElementsByTagName("ElectricVehicle");
			
			int errors = 0;
			for (int i = 0; i < nl.getLength(); i++) {
				if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) nl.item(i);
				
			//    	ParkingStop p = new ParkingStop();
		//			p.setIdParkingStop(el.getElementsByTagName("idParkingStop").item(0).getTextContent());
				String idVehicle =	el.getAttribute("ID");
				String dateOfPro =	el.getElementsByTagName("DateOfPro").item(0).getTextContent();
				//Date DateOfPro1 = new SimpleDateFormat("dd/MM/yyyy").parse(DateOfPro); 
				java.util.Date dateOfPro1 = new SimpleDateFormat("dd-MM-yyyy").parse(dateOfPro); 
				java.sql.Date dateOfPro2 = new java.sql.Date(dateOfPro1.getTime());
				String CodeManufacturer = el.getElementsByTagName("CodeManufacturer").item(0).getTextContent();
				String CodeBattery =	el.getElementsByTagName("CodeBattery").item(0).getTextContent();
				
				
				ElectricVehicle v = new ElectricVehicle(idVehicle, dateOfPro2, CodeBattery,CodeManufacturer );
				System.out.println("electric "+v);

					if (
							!manipulateElectricVehicle(v, entity.Consts.Manipulation.INSERT) && 
							!manipulateElectricVehicle(v, entity.Consts.Manipulation.UPDATE))
						errors++;
				}
			}
			
			System.out.println((errors == 0) ? "ElectricVehicle data imported successfully!" : 
				String.format("ElectricVehicle data imported with %d errors!", errors));
			
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
    }
	
	  private void allocateElectricVehicleParams(CallableStatement stmt, ElectricVehicle v, entity.Consts.Manipulation m) throws SQLException {
	    	int i = 1;
	    	
	    	if (!m.equals(entity.Consts.Manipulation.UPDATE)) {
	    		stmt.setString(i++, v.getIdVehicle());
	    		
	    		if (m.equals(entity.Consts.Manipulation.DELETE))
	    			return;
	    	}
	    	
	    	stmt.setDate(i++, v.getDateOfPro());
	    	stmt.setString(i++, v.getIdManufacturer());
	    	stmt.setString(i++, v.getIdBattery());
	    		
	    
	    	
	    	if (m.equals(entity.Consts.Manipulation.UPDATE))
	    		stmt.setString(i, v.getIdVehicle());
	    }
	
	  public boolean manipulateElectricVehicle(ElectricVehicle v, entity.Consts.Manipulation manipulation) {
			try {
				
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(entity.Consts.CONN_STR);
						CallableStatement stmt = conn.prepareCall(
								(manipulation.equals(entity.Consts.Manipulation.UPDATE)) ? 
										
										entity.Consts.UPDATE_ELECTRICVEHICLE : 
											(manipulation.equals(entity.Consts.Manipulation.INSERT)) ? 
													entity.Consts.INSERT_ELECTRICVEHICLE : 
														entity.Consts.DELETE_ELECTRICVEHICLE)) {
					
					allocateElectricVehicleParams(stmt, v, manipulation);
					stmt.executeUpdate();
					return true;
				} catch (SQLException e) {
				//e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
			}
			
			return false;
	
	
	  }
	  
	  public ArrayList<Battery> getBatteriesFromParkingStop(String lastParkingStop) {
		  System.out.println("@@@@@@" +lastParkingStop );
			ArrayList<Battery> results = new ArrayList<Battery>();
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						PreparedStatement stmt = conn.prepareStatement(Consts.GET_BATTERIES_FROM_PARKING_STOP);) {
					
					stmt.setString(1, lastParkingStop);
				
					
				
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						int i = 1;
					
						String idBattery=null;
						Double rateBattery = null;
						Location location = null;
						Double drivingDistancePossi = null;
						
						
						String idBattery1=rs.getString(i++);
						System.out.println(idBattery1+"-----");
						String rateBattery1= rs.getString(i++);
						System.out.println(rateBattery1+"-----");
						String location1= rs.getString(i++);
						System.out.println(location1+"-----");
						String drivingDistancePossi1= rs.getString(i++);
						System.out.println(drivingDistancePossi1+"-----");
						
						 rateBattery=  Double.valueOf(rateBattery1);
						 drivingDistancePossi=  Double.valueOf(drivingDistancePossi1);
						 
						 if (location1.equals("inVehicle")) {
							System.out.println("here");
							location= Location.INVEHICLE;
						 }
						else if (location1.equals("inCharging"))
							location= Location.INCHARGING;
						
						
						System.out.println("!!!!!!!!!!!!batteris!!!!!!!!!!!!!!!!!!!!!!");
						results.add(new Battery(idBattery1, rateBattery, location, drivingDistancePossi));	
						System.out.println(results);
					}
					
					for(Battery i:results)
						System.out.println(i);
					
							} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return results;
		}
}
