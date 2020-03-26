package control;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.ietf.jgss.Oid;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.healthmarketscience.jackcess.complex.ComplexValue.Id;

import entity.Consts;
import entity.ElectricVehicle;
import entity.Location;
import entity.ParkingStop;
import entity.Status;
import entity.TypeVehicle;
import entity.Vehicle;



public class VehicleLogic {

		private static VehicleLogic _instance;

		private VehicleLogic() {
		}

		public static VehicleLogic getInstance() {
			if (_instance == null)
				_instance = new VehicleLogic();
			return _instance;
		}
	
		public void importVehicleFromXML(String path) {
			
	    	try {
				Document doc = DocumentBuilderFactory.newInstance()
									.newDocumentBuilder().parse(new File(path));
				
				doc.getDocumentElement().normalize();
				NodeList nl = doc.getElementsByTagName("Vehicle");
				
				int errors = 0;
				for (int i = 0; i < nl.getLength(); i++) {
					if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element el = (Element) nl.item(i);
						
				//    	ParkingStop p = new ParkingStop();
			//			p.setIdParkingStop(el.getElementsByTagName("idParkingStop").item(0).getTextContent());
					String idVehicle =	el.getAttribute("ID");
					String type =	el.getElementsByTagName("type").item(0).getTextContent();
					
					TypeVehicle type1=null;
					if(type.equals("Scooter")) {
						type1=TypeVehicle.ELECTRICSCOOTER;
					}
					else if(type.equals("Bicycle"))
						type1=TypeVehicle.BYCICLE;
					else if(type.equals("ElectricBicycle"))
						type1=TypeVehicle.ELECTRICBICYCLE;
					
					String LastParkingStop =	el.getElementsByTagName("LastParkingStop").item(0).getTextContent();
					
					
					Vehicle v = new Vehicle(idVehicle, type1, LastParkingStop);
						
//						ParkingStop p = new ParkingStop(el.getAttribute("idParkingStop"), 
//								el.getElementsByTagName("city").item(0).getTextContent(),
//								el.getElementsByTagName("street").item(0).getTextContent(),
//								(Double)el.getElementsByTagName("coorX").item(0).getTextContent(),
//								el.getElementsByTagName("coorY").item(0).getTextContent(),
//								el.getElementsByTagName("capacity").item(0).getTextContent(),
//								el.getElementsByTagName("correntCap").item(0).getTextContent(),
//								el.getElementsByTagName("savedSpot").item(0).getTextContent(),
//								el.getElementsByTagName("nameParkingStop").item(0).getTextContent());
						if (!manipulateVehicle(v, entity.Consts.Manipulation.INSERT) && 
								!manipulateVehicle(v, entity.Consts.Manipulation.UPDATE))
							errors++;
					}
				}
				
				System.out.println((errors == 0) ? "Vehicle data imported successfully!" : 
					String.format("Vehicle data imported with %d errors!", errors));
				
			} catch (SAXException | IOException | ParserConfigurationException e) {
				e.printStackTrace();
			}
	    }
		
		  private void allocateParkingStopParams(CallableStatement stmt, Vehicle v, entity.Consts.Manipulation m) throws SQLException {
		    	int i = 1;
		    	
		    	if (!m.equals(entity.Consts.Manipulation.UPDATE)) {
		    		stmt.setString(i++, v.getIdVehicle());
		    		
		    		if (m.equals(entity.Consts.Manipulation.DELETE))
		    			return;
		    	}
		    	String type1=null;
				if(v.getType().toString().equals("ELECTRICSCOOTER")) {
					type1="Scooter";
				}
				else if(v.getType().toString().equals("BYCICLE"))
					type1="Bicycle";
				else if(v.getType().toString().equals("ELECTRICBICYCLE"))
					type1="ElectricBicycle  ";
				
		        	
		    	stmt.setString(i++, type1.toString());
		    	
		    	//if (.getContactName() == null)
		    	//	stmt.setNull(i++, java.sql.Types.VARCHAR);
		    	//else
		    		stmt.setString(i++, v.getLastParkingStop());
		    	
		    
		    	
		    	if (m.equals(entity.Consts.Manipulation.UPDATE))
		    		stmt.setString(i, v.getIdVehicle());
		    }
		
		  public boolean manipulateVehicle(Vehicle v, entity.Consts.Manipulation manipulation) {
				try {
					
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					try (Connection conn = DriverManager.getConnection(entity.Consts.CONN_STR);
							CallableStatement stmt = conn.prepareCall(
									(manipulation.equals(entity.Consts.Manipulation.UPDATE)) ? 
											
											entity.Consts.UPDATE_VEHICLE : 
												(manipulation.equals(entity.Consts.Manipulation.INSERT)) ? 
														entity.Consts.INSERT_VEHICLE : 
															entity.Consts.SQL_DEL_VEHICLE)) {
						
						allocateParkingStopParams(stmt, v, manipulation);
						System.out.println("qqqqqqqq");
						System.out.println("this is b"+v);
						stmt.executeUpdate();
						return true;
					} catch (SQLException e) {
					//e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
				//	e.printStackTrace();
				}
				
				return false;
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
	/**
	 * Delete the selected Vehicle in form.
	 * return true if the deletion was successful, else - return false
	 * @param employeeID - the employee to delete from DB
     * @return 
	 */
	public boolean deleteVehicle(String idVehicle) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_DEL_VEHICLE)) {
				
				stmt.setString(1, idVehicle);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Editing a exist Vehicle with the parameters received from the form.
	 * return true if the update was successful, else - return false
     * @return 
	 */
	public boolean updateStatusVehicle(String idVehicle, Status statusVehicle) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_STATUS_VEHICLE)) {
				int i = 1; 
				stmt.setString(2, idVehicle); 
				if(statusVehicle.equals(Status.CHARGING)) {
					System.out.println("hi charging");
					stmt.setString(1, "Charging"); 
					}
				else if (statusVehicle.equals(Status.FREE))
					stmt.setString(1, "Free"); 
				else if (statusVehicle.equals(Status.MOVING))
					stmt.setString(1, "Moving"); 
				else if (statusVehicle.equals(Status.RENT))
					stmt.setString(1, "Rent"); 
				stmt.executeUpdate();
				return true;
				//do throws
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	

	/**
	 * Editing a exist Vehicle with the parameters received from the form.
	 * return true if the update was successful, else - return false
     * @return 
	 */	public boolean updateCorrentCapInPS(ParkingStop ps){
		 System.out.println("get: "+ps);
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_CORENTCAP_PS)) {
					int i = 1; 
					if(ps.getIdParkingStop()!=null)
					stmt.setString(2, ps.getIdParkingStop()); 
					int newCapa=ps.getCorrentCap();
					newCapa=newCapa-1;
					stmt.setInt(1, newCapa); 
					stmt.executeUpdate();
					return true;
				
					//do throws
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return false;
		}
	 
	 /**
		 * Editing a exist Vehicle with the parameters received from the form.
		 * return true if the update was successful, else - return false
	     * @return 
		 */	public boolean incrementCorrentCapInPS(ParkingStop ps){
			 System.out.println("get: "+ps);
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
							CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_CORENTCAP_PS)) {
						int i = 1; 
						if(ps.getIdParkingStop()!=null)
						stmt.setString(2, ps.getIdParkingStop()); 
						int newCapa=ps.getCorrentCap();
						newCapa=newCapa+1;
						stmt.setInt(1, newCapa); 
						stmt.executeUpdate();
						return true;
					
						//do throws
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return false;
			}
	
	
		
		
		
		
		/*SQL_GET_STREET_BY_CITY*/
		/**
		 * Adding a new Employee with the parameters received from the form.
		 * return true if the insertion was successful, else - return false
	     * @return 
		 */
		public boolean addRentVehicle(String idRent, String idVehicle) {
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_RENT_VEHICLE)) {
					
					int i = 1;
					stmt.setString(i++, idVehicle);
					stmt.setString(i++, idRent); 
					java.sql.Date date=null;
					stmt.setDate(i++, date);
					stmt.setDate(i++, date);
					stmt.setInt(i++, 0);
					stmt.executeUpdate();
					return true;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return false;
		}
	
		
		public ArrayList<Vehicle> getVehiclesByPSandName(String name, TypeVehicle typeVehicle) {
			ArrayList<Vehicle> results = new ArrayList<Vehicle>();
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_GG);) {
					
					stmt.setString(1, name);
				
					if (typeVehicle.equals(TypeVehicle.ELECTRICBICYCLE)) {
						stmt.setString(2, "ElectricBicycle");
				//	System.out.println("ele");
						}
					else if (typeVehicle.equals(TypeVehicle.ELECTRICSCOOTER)) {
						stmt.setString(2, "ElectricScooter");
				//	System.out.println("ele");
					}
				
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						int i = 1;
					
						TypeVehicle type=null;
						Status statusVehicle = null;
						Date dateOfProdata=null;
						String idManufacturerdata=null;
						Double drivingDistanceForVehicle=200.2;
						String idBattery=null;
						
						String idVehicleString=rs.getString(i++);
						System.out.println(idVehicleString+"-----");
						String typeVehicleString= rs.getString(i++);
						System.out.println(typeVehicleString+"-----");
						String statusv= rs.getString(i++);
						System.out.println(statusv+"-----");
						String idLastParking= rs.getString(i++);
						System.out.println(idLastParking+"-----");
						
						 dateOfProdata=rs.getDate(i++); 
							System.out.println(dateOfProdata+"-----");
						 idManufacturerdata=rs.getString(i++);
							System.out.println(idManufacturerdata+"-----");
						 drivingDistanceForVehicle=rs.getDouble(i++);
							System.out.println(drivingDistanceForVehicle+"-----");
						 idBattery = rs.getString(i++);
							System.out.println(idBattery+"-----");
						 
						 if (typeVehicleString.equals("ElectricScooter")) {
							System.out.println("here");
							 type= typeVehicle.ELECTRICSCOOTER;
						 }
						else if (typeVehicleString.equals("ElectricBicycle"))
							type= typeVehicle.ELECTRICBICYCLE;
						
						if(statusv.equals("Free"))
							statusVehicle = Status.FREE;
						else if (statusv.equals("Rent"))
							statusVehicle = Status.RENT;
						else if (statusv.equals("Charging"))
							statusVehicle = Status.CHARGING;
						else if (statusv.equals("Moving"))
							statusVehicle = Status.MOVING;
						
						results.add(new ElectricVehicle(idVehicleString, type, statusVehicle, idLastParking, dateOfProdata, idManufacturerdata, drivingDistanceForVehicle, idBattery));	
					}
					
					for(Vehicle i:results)
						System.out.println(i);
					
							} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return results;
		}
		
		public ArrayList<Vehicle> getVehiclesByPSandNameBicycle(String name, TypeVehicle typeVehicle) {
			ArrayList<Vehicle> results = new ArrayList<Vehicle>();
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_GG_BICYCLE);) {
					stmt.setString(1, name);
					if (typeVehicle.equals(TypeVehicle.BYCICLE)) {
						stmt.setString(2, "Bicycle");
					
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						int i = 1;
					
						TypeVehicle type=null;
						Status statusVehicle = null;

				
						String idVehicleString=rs.getString(i++);
					//	System.out.println(idVehicleString+"-----");
						String typeVehicleString= rs.getString(i++);
					//	System.out.println(typeVehicleString+"-----");
						String statusv= rs.getString(i++);
					//	System.out.println(statusv+"-----");
						String idLastParking= rs.getString(i++);
					//	System.out.println(idLastParking+"-----");
						 
						if (typeVehicleString.equals("Bicycle")) 
							 type= typeVehicle.BYCICLE;
						 
						if(statusv.equals("Free"))
							statusVehicle = Status.FREE;
						else if (statusv.equals("Rent"))
							statusVehicle = Status.RENT;
						else if (statusv.equals("Charging"))
							statusVehicle = Status.CHARGING;
						else if (statusv.equals("Moving"))
							statusVehicle = Status.MOVING;
						
						results.add(new Vehicle(idVehicleString, type, statusVehicle, idLastParking));	
					}
					
					for(Vehicle i:results)
						System.out.println("BICYCLE"+i);
					}}	
				 catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return results;
		}
		
		public ArrayList<Vehicle> getVehiclesByPSandAdressBicycle(String city, String street, TypeVehicle typeVehicle) {
			ArrayList<Vehicle> results = new ArrayList<Vehicle>();
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_GG_BICYCLE_BY_ADREES);) {
					
					if (typeVehicle.equals(TypeVehicle.BYCICLE)) {
						stmt.setString(1, "Bicycle");
						stmt.setString(2, city);
						stmt.setString(3, street);
					
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						int i = 1;
					
						TypeVehicle type=null;
						Status statusVehicle = null;

						String idVehicleString=rs.getString(i++);
					//	System.out.println(idVehicleString+"-----");
						String typeVehicleString= rs.getString(i++);
					//	System.out.println(typeVehicleString+"-----");
						String statusv= rs.getString(i++);
					//	System.out.println(statusv+"-----");
						String idLastParking= rs.getString(i++);
					//	System.out.println(idLastParking+"-----");
						 
						if (typeVehicleString.equals("Bicycle")) 
							 type= typeVehicle.BYCICLE;
						 
						if(statusv.equals("Free"))
							statusVehicle = Status.FREE;
						else if (statusv.equals("Rent"))
							statusVehicle = Status.RENT;
						else if (statusv.equals("Charging"))
							statusVehicle = Status.CHARGING;
						else if (statusv.equals("Moving"))
							statusVehicle = Status.MOVING;
						System.out.println(city+street);
						results.add(new Vehicle(idVehicleString, type, statusVehicle, idLastParking));	
					}
					
					for(Vehicle i:results)
						System.out.println("BICYCLE"+i);
					}}	
				 catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return results;
		}
		
		public ArrayList<Vehicle> getEVehiclesByPSandAdress(String city,String street, TypeVehicle typeVehicle) {
			ArrayList<Vehicle> results = new ArrayList<Vehicle>();
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_GG_BY_ADREES);) {
					
					
				
					if (typeVehicle.equals(TypeVehicle.ELECTRICBICYCLE)) {
						stmt.setString(1, "ElectricBicycle");
						}
					else if (typeVehicle.equals(TypeVehicle.ELECTRICSCOOTER)) {
						stmt.setString(1, "ElectricScooter");
					}
					stmt.setString(2, city);
					stmt.setString(3, street);
					
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						int i = 1;
					
						TypeVehicle type=null;
						Status statusVehicle = null;
						java.sql.Date dateOfProdata=null;
						String idManufacturerdata=null;
						Double drivingDistanceForVehicle=200.2;
						String idBattery=null;
						
						String idVehicleString=rs.getString(i++);
						System.out.println(idVehicleString+"-----");
						String typeVehicleString= rs.getString(i++);
						System.out.println(typeVehicleString+"-----");
						String statusv= rs.getString(i++);
						System.out.println(statusv+"-----");
						String idLastParking= rs.getString(i++);
						System.out.println(idLastParking+"-----");
						
						 dateOfProdata=rs.getDate(i++); 
							System.out.println(dateOfProdata+"-----");
						 idManufacturerdata=rs.getString(i++);
							System.out.println(idManufacturerdata+"-----");
						 drivingDistanceForVehicle=rs.getDouble(i++);
							System.out.println(drivingDistanceForVehicle+"-----");
						 idBattery = rs.getString(i++);
							System.out.println(idBattery+"-----");
						 
						 if (typeVehicleString.equals("ElectricScooter")) {
							System.out.println("here");
							 type= typeVehicle.ELECTRICSCOOTER;
						 }
						else if (typeVehicleString.equals("ElectricBicycle"))
							type= typeVehicle.ELECTRICBICYCLE;
						
						if(statusv.equals("Free"))
							statusVehicle = Status.FREE;
						else if (statusv.equals("Rent"))
							statusVehicle = Status.RENT;
						else if (statusv.equals("Charging"))
							statusVehicle = Status.CHARGING;
						else if (statusv.equals("Moving"))
							statusVehicle = Status.MOVING;
						
						results.add(new ElectricVehicle(idVehicleString, type, statusVehicle, idLastParking, dateOfProdata, idManufacturerdata, drivingDistanceForVehicle, idBattery));	}
					
					for(Vehicle i:results)
						System.out.println(i);
					
							} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return results;
		}

		public ArrayList<Vehicle> getVehicleById(String IDRenter) {
			ArrayList<Vehicle> results = new ArrayList<Vehicle>();
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						PreparedStatement stmt = conn.prepareStatement(Consts.GET_VEHICLE_BY_RENTER);) {
					
					
				
				
					stmt.setString(1, IDRenter);
					
					
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						int i = 1;
						String idVehicle = null;
						TypeVehicle type=null;
						Status statusVehicle = null;
						String lastParkingStop = null;
						
						String idVehicle1=rs.getString(i++);
						System.out.println(idVehicle1+"-----");
						String TypeVehicle1= rs.getString(i++);
						System.out.println(TypeVehicle1+"-----");
						String statusVehicle1= rs.getString(i++);
						System.out.println(statusVehicle1+"-----");
						String lastParkingStop1= rs.getString(i++);
						System.out.println(lastParkingStop1+"-----");
						
						if (TypeVehicle1.equals("ElectricScooter")) {
							System.out.println("here");
							 type= TypeVehicle.ELECTRICSCOOTER;
						 }
						else if (TypeVehicle1.equals("ElectricBicycle"))
							type= TypeVehicle.ELECTRICBICYCLE;
						 
						else if (TypeVehicle1.equals("Bicycle"))
							type= TypeVehicle.BYCICLE;
						
						if(statusVehicle1.equals("Free"))
							statusVehicle = Status.FREE;
						else if (statusVehicle1.equals("Rent"))
							statusVehicle = Status.RENT;
						else if (statusVehicle1.equals("Charging"))
							statusVehicle = Status.CHARGING;
						else if (statusVehicle1.equals("Moving"))
							statusVehicle = Status.MOVING;
						
						
						
						results.add(new Vehicle(idVehicle1, type, statusVehicle, lastParkingStop1 ));	}
					
					for(Vehicle i:results)
						System.out.println(i);
					
							} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return results;
		
		
		}
		public boolean DeleteVehicleFromRenter(String VehicleID, String IDRenter) {
			System.out.println("ooooooooooo" +VehicleID+ IDRenter );
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						CallableStatement stmt = conn.prepareCall(Consts.SQL_DEL_VEHICLE_FROM_RENTER)) {
					int i = 1; 
					 
					stmt.setString(1, VehicleID);
					stmt.setString(2, IDRenter);
					stmt.executeQuery();
					return true;
					//do throws
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return false;
		}

		public boolean updateParkingStopVehicle(String idVehicle, String parkingStopName) {
			System.out.println("mmmmmmmm"+idVehicle+parkingStopName);
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						CallableStatement stmt = conn.prepareCall(Consts.SQL_UPDATE_VEHICLE_PARKING_STOP)) {
					int i = 1; 
					stmt.setString(1, parkingStopName); 
					stmt.setString(2, idVehicle); 
					
					stmt.executeUpdate();
					return true;
					//do throws
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		public boolean updateEndDate(String idVehicle, String idrenter) {
			System.out.println("mmmmmmmm"+idVehicle+idrenter);
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						CallableStatement stmt = conn.prepareCall(Consts.SQL_UPDATE_END_DATE)) {
					int i = 1; 
					stmt.setString(1, idVehicle); 
					stmt.setString(2, idrenter); 
					//stmt.setString(3, idrenter); 
					//DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					//LocalDateTime now = LocalDateTime.now();
					//java.sql.Date d = now;
					//stmt.setString(1, date.format(now));
					stmt.executeUpdate();
					return true;
					//do throws
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return false;
		}	
		public double getcost(String idvehicle, String idrenter) {
			
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_COST);){
					
					stmt.setString(1, idvehicle);
					stmt.setString(2, idrenter);
						ResultSet rs = stmt.executeQuery();
				
				
					while (rs.next()) {
						int i = 1;
						
						
						Double cost= rs.getDouble(i++);
						
						
						
						//ParkingStop p = new ParkingStop(CodePS, City,Street,CoorX,CoorY,CapacityVehicle, curcapacity, savedSpot, nameParkingStop); 
						//System.out.println(p);
						return cost;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return 0;
		}
 
}
