package control;
import java.io.File;
//import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import entity.Battery;
import entity.Consts;
import entity.ElectricVehicle;
import entity.Location;
import entity.ParkingStop;
import entity.Renter;
import entity.Status;
import entity.Vehicle;



public class BatteryLogic {
	
		private static BatteryLogic instance;
		
		private BatteryLogic() {
			org.apache.log4j.BasicConfigurator.configure();
		}
		
		public static BatteryLogic getInstance() {
			if (instance == null)
				instance = new BatteryLogic();
			return instance;
		}

/*public void exportBatteryToJSON() {
	   try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        try (Connection conn = DriverManager.getConnection(entity.Consts.CONN_STR);
                PreparedStatement stmt = conn.prepareStatement(
                		entity.Consts.SQL_SEL_BATTERY);
                ResultSet rs = stmt.executeQuery()) {
     	   JsonArray battery = new JsonArray();
            while (rs.next()) {
         	   JsonObject customer = new JsonObject();
         	   
         	   for (int i = 1; i < rs.getMetaData().getColumnCount(); i++)
         		   customer.put(rs.getMetaData().getColumnName(i), rs.getString(i));
         	   
         	   customers.add(customer);
            }
            
     	   JsonObject doc = new JsonObject();
     	   doc.put("Customers_info", customers);
            
            File file = new File("json/customers.json");
            file.getParentFile().mkdir();
            
            try (FileWriter writer = new FileWriter(file)) {
         	   writer.write(Jsoner.prettyPrint(doc.toJson()));
         	   writer.flush();
                System.out.println("customers data exported successfully!");
            } catch (IOException e) {
         	   e.printStackTrace();
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }	
}*/
	
	

public void importBatteryFromJSON(String path) {
	

		
		
	try (FileReader reader = new FileReader(new File(path))) {
		JsonObject doc = (JsonObject) Jsoner.deserialize(reader);
		JsonArray batteries = (JsonArray) doc.get("battery_info");
		Iterator<Object> iterator = batteries.iterator();
		int errors = 0;
		
		while (iterator.hasNext()) {
			JsonObject obj = (JsonObject) iterator.next();
			System.out.println(obj.get("rateBattery"));
			
					String idbattery=(String) obj.get("idBattery"); 
					String rate= (String) obj.get("rateBattery");
					Double rate1=Double.valueOf(rate);
					
					
					String loc=(String) obj.get("location");
					Location location=null;
					if(loc.equals("inCharging")) {
						location=Location.INCHARGING;
					}
					else if(loc.equals("inVahicle"))
						location=Location.INVEHICLE;
						
					
					String drive=(String) obj.get("drivingDistancePossi");
					Double drive1=Double.valueOf(drive);
					
					Battery b = new Battery(idbattery,rate1,location,drive1);

			if (!manipulateBattery(b, entity.Consts.Manipulation.INSERT) &&  
					!manipulateBattery(b, entity.Consts.Manipulation.UPDATE))
				System.out.println("aaaaaaaaaaa");
				errors++;
		}
		
		System.out.println((errors == 0) ? "battery data imported successfully!" : 
			String.format("customers data imported with %d errors!", errors));
	} catch (IOException | DeserializationException e) {
		e.printStackTrace();
	}
}

public boolean manipulateBattery(Battery b, entity.Consts.Manipulation manipulation) {
	try {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		try (Connection conn = DriverManager.getConnection(entity.Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall(
						(manipulation.equals(entity.Consts.Manipulation.UPDATE)) ? 
								entity.Consts.UPDATE_BATTERY : 
									(manipulation.equals(entity.Consts.Manipulation.INSERT)) ? 
											entity.Consts.INSERT_BATTERY : 
												entity.Consts.DELETE_BATTERY)) {
			allocateBatteryParams(stmt, b, manipulation);
			System.out.println("this is b"+b);
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

private void allocateBatteryParams(CallableStatement stmt, Battery b, entity.Consts.Manipulation m) throws SQLException {
	int i = 1;
	
	if (!m.equals(entity.Consts.Manipulation.UPDATE)) {
		stmt.setString(i++, b.getIdBattery());
		
		if (m.equals(entity.Consts.Manipulation.DELETE))
			return;
	}
	stmt.setDouble(i++, b.getRateBattery());
	
//	if (c.getContactName() == null)
//		stmt.setNull(i++, java.sql.Types.VARCHAR);
//	else
		stmt.setString(i++, b.getLocation().toString());
	
//	if (c.getContactTitle() == null)
//		stmt.setNull(i++, java.sql.Types.VARCHAR);
//	else
		stmt.setDouble(i++, b.getDrivingDistancePossi());
		if (m.equals(entity.Consts.Manipulation.UPDATE))
			stmt.setString(i, b.getIdBattery());
}

/*
if (!m.equals(Manipulation.UPDATE)) {
	stmt.setString(i++, c.getCustomerID());
	
	if (m.equals(Manipulation.DELETE))
		return;
}
/*
stmt.setString(i++, c.getCompanyName());

if (c.getContactName() == null)
	stmt.setNull(i++, java.sql.Types.VARCHAR);
else
	stmt.setString(i++, c.getContactName());

if (c.getContactTitle() == null)
	stmt.setNull(i++, java.sql.Types.VARCHAR);
else
	stmt.setString(i++, c.getContactTitle());

if (c.getAddress() == null)
	stmt.setNull(i++, java.sql.Types.VARCHAR);
else
	stmt.setString(i++, c.getAddress());

if (c.getCity() == null)
	stmt.setNull(i++, java.sql.Types.VARCHAR);
else
	stmt.setString(i++, c.getCity());

if (c.getCountry() == null)
	stmt.setNull(i++, java.sql.Types.VARCHAR);
else
	stmt.setString(i++, c.getCountry());

if (c.getPhone() == null)
	stmt.setNull(i++, java.sql.Types.VARCHAR);
else
	stmt.setString(i++, c.getPhone());

if (c.getFax() == null)
	stmt.setNull(i++, java.sql.Types.VARCHAR);
else
	stmt.setString(i++, c.getFax());

if (m.equals(Manipulation.UPDATE))
	stmt.setString(i, c.getCustomerID());
}/*/

public boolean addBattery(String idBattery, Double rateBattery, String location, Double drivingDistancePossi) {
	try {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall(Consts.INSERT_BATTERY)) {
			
			int i = 1;
			stmt.setString(i++, idBattery);
			stmt.setDouble(i++, rateBattery); 
			stmt.setString(i++, location); 
			stmt.setDouble(i++, drivingDistancePossi); 
			
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

public boolean updateBattery(String idBattery, Double rateBattery, String location, Double drivingDistancePossi) {
	System.out.println("bbbbbb");
	try {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		System.out.println("cccccc");
		try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall(Consts.UPDATE_BATTERY)) {
			System.out.println("ddddddddb");
			int i = 1;
	
			stmt.setDouble(i++, rateBattery); 
			stmt.setString(i++, location); 
			stmt.setDouble(i++, drivingDistancePossi); 
			stmt.setString(i++, idBattery);
			
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

public boolean updateLocationBattery(String idbattery, Location location) {
	try {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_LOCATION_BATTERY)) {
			int i = 1; 
			stmt.setString(2, idbattery); 
			if(location.equals(Location.INCHARGING)) {
				
				stmt.setString(1, "inCharging"); 
				}
			
			else if(location.equals(Location.INVEHICLE)) {
				
				stmt.setString(1, "inVehicle"); 
				}
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
public boolean addChargerBattery(String idbattery, String IDrenter) {
	
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_CHARGER_BATTERY)) {
				
				int i = 1;
				stmt.setString(i++, IDrenter);
				stmt.setString(i++, idbattery); 
				java.sql.Date date=null;
				java.sql.Timestamp time = null;
				stmt.setDate(i++, date);
				stmt.setTimestamp(i++, time);
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

public ElectricVehicle getVehicleByBattery(String code) {
	System.out.println("---------"+code);
	try {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement(Consts.GET_VEHICLE_BY_BATTERY);){
			
			stmt.setString(1, code);
				ResultSet rs = stmt.executeQuery();
		
		
			while (rs.next()) {
				int i = 1;
				
				String idVehicle= rs.getString(i++);
				Date dateOfPro =rs.getDate(i++);
				String manufacturer = rs.getString(i++);
				Double drivingDistanceForVehicle = rs.getDouble(i++);
				
//				String nameParkingStop = rs.getString(i++);
//				String City= rs.getString(i++);
//				String Street= rs.getString(i++);
//				Double CoorX= rs.getDouble(i++);
//				Double CoorY= rs.getDouble(i++);
//				Integer CapacityVehicle= rs.getInt(i++);
//				Integer curcapacity= rs.getInt(i++);
//				Integer savedSpot= rs.getInt(i++);
				
				
				ElectricVehicle v = new ElectricVehicle(idVehicle, dateOfPro, code, manufacturer); 
				System.out.println(v);
				return v;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	return null;
}
}
