package control;

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
import entity.ParkingStop;
import entity.Status;

public class ParkingStopLogic {
	
	private static ParkingStopLogic instance;
	
	private ParkingStopLogic() {
		org.apache.log4j.BasicConfigurator.configure();
	}
	
	public static ParkingStopLogic getInstance() {
		if (instance == null)
			instance = new ParkingStopLogic();
		return instance;
	}
	
	
	public void importParkingStopFromXML(String path) {
		
    	try {
			Document doc = DocumentBuilderFactory.newInstance()
								.newDocumentBuilder().parse(new File(path));
			
			doc.getDocumentElement().normalize();
			NodeList nl = doc.getElementsByTagName("ParkingStop");
			
			int errors = 0;
			for (int i = 0; i < nl.getLength(); i++) {
				if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) nl.item(i);
					
			//    	ParkingStop p = new ParkingStop();
		//			p.setIdParkingStop(el.getElementsByTagName("idParkingStop").item(0).getTextContent());
					String idParkingStop =	el.getAttribute("ID");
				String city =	el.getElementsByTagName("City").item(0).getTextContent();
				String street =	el.getElementsByTagName("Street").item(0).getTextContent();
				String coorX =  el.getElementsByTagName("CorX").item(0).getTextContent();
				Double coorX1=  Double.valueOf(coorX);
				String coorY = el.getElementsByTagName("CorY").item(0).getTextContent();
				Double coorY1=  Double.valueOf(coorY);
				String capacity =	el.getElementsByTagName("CapacityVehicle").item(0).getTextContent();
				int capacity1 = Integer.parseInt(capacity);
				
		
//				String correntCap = el.getElementsByTagName("CorrentCap").item(0).getTextContent();
//				int correntCap1 = Integer.parseInt(correntCap);
//				String savedSpot = el.getElementsByTagName("SavedSpot").item(0).getTextContent();
//				int savedSpot1 = Integer.parseInt(savedSpot);
//			String nameParkingStop = el.getElementsByTagName("NameParkingStop").item(0).getTextContent();
		//		String city =	el.getAttribute("city");
//				String street =	el.getAttribute("street");
//				String coorX =	el.getAttribute("coorX");
//				Double coorX1= new Double(coorX).doubleValue();
//				String coorY =	el.getAttribute("coorY");
//				Double coorY1=  Double.valueOf(coorY);
//				String capacity =	el.getAttribute("capacity");
//				int capacity1 = Integer.parseInt(capacity);
//				String correntCap =	el.getAttribute("correntCap");
//				int correntCap1 = Integer.parseInt(correntCap);
//				String savedSpot =	el.getAttribute("savedSpot");
//				int savedSpot1 = Integer.parseInt(savedSpot);
//				String nameParkingStop =	el.getAttribute("nameParkingStop");
				
				
	//			String city =	el.getElementsByTagName("city").item(0).getTextContent();
//				String street =	el.getElementsByTagName("street").item(0).getTextContent();
//				String coorX =  el.getElementsByTagName("coorX").item(0).getTextContent();
//				Double coorX1=  Double.valueOf(coorX);
//				String coorY = el.getElementsByTagName("coorY").item(0).getTextContent();
//				Double coorY1=  Double.valueOf(coorY);
//				String capacity =	el.getElementsByTagName("capacity").item(0).getTextContent();
//				int capacity1 = Integer.parseInt(capacity);
//				String correntCap = el.getElementsByTagName("correntCap").item(0).getTextContent();
//				int correntCap1 = Integer.parseInt(correntCap);
//				String savedSpot = el.getElementsByTagName("savedSpot").item(0).getTextContent();
//				int savedSpot1 = Integer.parseInt(savedSpot);
//				String nameParkingStop = el.getElementsByTagName("nameParkingStop").item(0).getTextContent();
				
				ParkingStop p = new ParkingStop(idParkingStop,city, street, coorX1, coorY1, capacity1);
					
//					ParkingStop p = new ParkingStop(el.getAttribute("idParkingStop"), 
//							el.getElementsByTagName("city").item(0).getTextContent(),
//							el.getElementsByTagName("street").item(0).getTextContent(),
//							(Double)el.getElementsByTagName("coorX").item(0).getTextContent(),
//							el.getElementsByTagName("coorY").item(0).getTextContent(),
//							el.getElementsByTagName("capacity").item(0).getTextContent(),
//							el.getElementsByTagName("correntCap").item(0).getTextContent(),
//							el.getElementsByTagName("savedSpot").item(0).getTextContent(),
//							el.getElementsByTagName("nameParkingStop").item(0).getTextContent());
					if (!manipulateParkingStop(p, entity.Consts.Manipulation.INSERT) && 
							!manipulateParkingStop(p, entity.Consts.Manipulation.UPDATE))
						errors++;
				}
			}
			
			System.out.println((errors == 0) ? "ParkingStop data imported successfully!" : 
				String.format("ParkingStop data imported with %d errors!", errors));
			
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
    }
	
	  private void allocateParkingStopParams(CallableStatement stmt, ParkingStop p, entity.Consts.Manipulation m) throws SQLException {
	    	int i = 1;
	    	
	    	if (!m.equals(entity.Consts.Manipulation.UPDATE)) {
	    		stmt.setString(i++, p.getIdParkingStop());
	    		
	    		if (m.equals(entity.Consts.Manipulation.DELETE))
	    			return;
	    	}
	    	
	    	stmt.setString(i++, p.getCity());
	    	
	    	//if (.getContactName() == null)
	    	//	stmt.setNull(i++, java.sql.Types.VARCHAR);
	    	//else
	    		stmt.setString(i++, p.getStreet());
	    	
	    	//if (c.getContactTitle() == null)
	    	//	stmt.setNull(i++, java.sql.Types.VARCHAR);
	    	//else
	    		stmt.setDouble(i++, p.getCoorX());
	    	
	    	//if (c.getAddress() == null)
	    	//	stmt.setNull(i++, java.sql.Types.VARCHAR);
	    //	else
	    		stmt.setDouble(i++, p.getCoorY());
	    	
//	    	if (c.getCity() == null)
//	    		stmt.setNull(i++, java.sql.Types.VARCHAR);
//	    	else
	    		stmt.setInt(i++, p.getCapacity());
	    	
//	    	if (c.getCountry() == null)
//	    		stmt.setNull(i++, java.sql.Types.VARCHAR);
//	    	else
	//    		stmt.setInt(i++, p.getCorrentCap());
	    	
//	    	if (c.getPhone() == null)
//	    		stmt.setNull(i++, java.sql.Types.VARCHAR);
//	    	else
	//    		stmt.setInt(i++, p.getSavedSpot());
	    	
//	    	if (c.getFax() == null)
//	    		stmt.setNull(i++, java.sql.Types.VARCHAR);
//	    	else
	 //   		stmt.setString(i++, p.getNameParkingStop());
	    	
	    	if (m.equals(entity.Consts.Manipulation.UPDATE))
	    		stmt.setString(i, p.getIdParkingStop());
	    }
	
	  public boolean manipulateParkingStop(ParkingStop p, entity.Consts.Manipulation manipulation) {
			try {
				
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(entity.Consts.CONN_STR);
						CallableStatement stmt = conn.prepareCall(
								(manipulation.equals(entity.Consts.Manipulation.UPDATE)) ? 
										
										entity.Consts.UPDATE_PARKINGSTOP : 
											(manipulation.equals(entity.Consts.Manipulation.INSERT)) ? 
													entity.Consts.INSERT_PARKINGSTOP : 
														entity.Consts.DELETE_PARKINGSTOP)) {
					
					allocateParkingStopParams(stmt, p, manipulation);
					System.out.println("qqqqqqqq");
					System.out.println("this is b"+p);
					stmt.executeUpdate();
					return true;
				} catch (SQLException e) {
			//	e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
			//	e.printStackTrace();
			}
			
			return false;
		}
	  
	public ParkingStop getParkingStopByName(String name) {
		System.out.println("---------"+name);
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.GET_PARKINGSTOP_BY_NAME);){
				
				stmt.setString(1, name);
					ResultSet rs = stmt.executeQuery();
			
			
				while (rs.next()) {
					int i = 1;
					
					String CodePS= rs.getString(i++);
					String nameParkingStop = rs.getString(i++);
					String City= rs.getString(i++);
					String Street= rs.getString(i++);
					Double CoorX= rs.getDouble(i++);
					Double CoorY= rs.getDouble(i++);
					Integer CapacityVehicle= rs.getInt(i++);
					Integer curcapacity= rs.getInt(i++);
					Integer savedSpot= rs.getInt(i++);
					
					
					ParkingStop p = new ParkingStop(CodePS, City,Street,CoorX,CoorY,CapacityVehicle, curcapacity, savedSpot, nameParkingStop); 
					System.out.println(p);
					return p;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	//////////////////////////////////////////////////////need to add///////////////////////////////////////////////////
//	public boolean updateParkingStop(String ID) {
//		try {
//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//					CallableStatement stmt = conn.prepareCall(Consts.UPDATE_PARKINGSTOP)) {
//				int i = 1; 
//			//	stmt.setString(8, +1); 
//			
//				stmt.executeUpdate();
//				return true;
//				//do throws
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	
	
	
}
