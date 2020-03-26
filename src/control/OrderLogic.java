package control;
import entity.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.print.DocFlavor.STRING;
import javax.swing.text.html.parser.Entity;

import org.apache.log4j.BasicConfigurator;

import com.healthmarketscience.jackcess.complex.ComplexValue.Id;

import entity.Vehicle;
import entity.Status;
import entity.TypeVehicle;
import entity.Battery;
import entity.Consts;
import entity.ElectricVehicle;
import entity.Location;
import entity.Manufacturer;
import entity.ParkingStop;
import entity.Renter;

public class OrderLogic {

	private static OrderLogic _instance;

	private OrderLogic() {
	}


	public static OrderLogic getInstance() {
		BasicConfigurator.configure();
		if (_instance == null)
			_instance = new OrderLogic();
		return _instance;
		
	}

	/**
	 * fetches all customers from DB file.
     * @return ArrayList of vehicle.
	 */
	public ArrayList<Vehicle> getVehicles() {
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		System.out.println("begin");
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_VEHICLE);
					ResultSet rs = stmt.executeQuery())
			
			{
				while (rs.next()) {
					int i = 1;
					TypeVehicle type=null;
					Status statusVehicle = null;
					
					String idVehicleString=rs.getString(i++);
					String typeVehicleString= rs.getString(i++);
					String statusv= rs.getString(i++);
					String idLastParking= rs.getString(i++);
					
					if(typeVehicleString.equals("Bicycle"))
						type= TypeVehicle.BYCICLE;
					else if (typeVehicleString.equals("ElectricScooter"))
						type= TypeVehicle.ELECTRICSCOOTER;
					else if (typeVehicleString.equals("ElectricBicycle"))
						type= TypeVehicle.ELECTRICBICYCLE;
					
					if(statusv.equals("Free"))
						statusVehicle = Status.FREE;
					else if (statusv.equals("Rent"))
						statusVehicle = Status.RENT;
					else if (statusv.equals("Charging"))
						statusVehicle = Status.CHARGING;
					else if (statusv.equals("Moving"))
						statusVehicle = Status.MOVING;
					
					results.add(new Vehicle(idVehicleString,type , statusVehicle, idLastParking));
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	//	System.out.println("hi4");
	//	System.out.println(results);
		return results;
	}
	

	public ArrayList<Vehicle> getVehiclesByPSType() {
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
	//	System.out.println("begin");
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_VEHICLE);
					ResultSet rs = stmt.executeQuery())
			
			{
				while (rs.next()) {
					int i = 1;
					TypeVehicle type=null;
					Status statusVehicle = null;
					
					String idVehicleString=rs.getString(i++);
					String typeVehicleString= rs.getString(i++);
					String statusv= rs.getString(i++);
					String idLastParking= rs.getString(i++);
					
					if(typeVehicleString.equals("Bicycle"))
						type= TypeVehicle.BYCICLE;
					else if (typeVehicleString.equals("ElectricScooter"))
						type= TypeVehicle.ELECTRICSCOOTER;
					else if (typeVehicleString.equals("ElectricBicycle"))
						type= TypeVehicle.ELECTRICBICYCLE;
					
					if(statusv.equals("Free"))
						statusVehicle = Status.FREE;
					else if (statusv.equals("Rent"))
						statusVehicle = Status.RENT;
					else if (statusv.equals("Charging"))
						statusVehicle = Status.CHARGING;
					else if (statusv.equals("Moving"))
						statusVehicle = Status.MOVING;
					
					results.add(new Vehicle(idVehicleString,type , statusVehicle, idLastParking));
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//System.out.println("hi4");
		//System.out.println(results);
		return results;
	}

	/**
	 * fetches all customers from DB file.
     * @return ArrayList of vehicle.
	 * @throws ParseException 
	 */
	
	 public ArrayList<ElectricVehicle> getElectricVehicles(){
		ArrayList<ElectricVehicle> results = new ArrayList<ElectricVehicle>();
	//	System.out.println("begin Electric vehicle");
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_ELECTRIC_VEHICLE);
					ResultSet rs = stmt.executeQuery())
			
			{
				while (rs.next()) {
					int i = 1;
					String idVehicleString=rs.getString(i++);
					Date dateOfProdata=rs.getDate(i++);
					String idManufacturerdata=rs.getString(i++);
					Double drivingDistanceForVehicle=rs.getDouble(i++);
					String idBattery = rs.getString(i++);
					String type = rs.getString(i++);
					String statusVehicle = rs.getString(i++);				
					String LastParkingStop = rs.getString(i++);
					
								
					
				
					
					TypeVehicle typeV = null;
					if(type.equals("ELECTRICBICYCLE"))
						typeV = entity.TypeVehicle.ELECTRICBICYCLE;
					else if(type.equals("ELECTRICSCOOTER"))
						typeV = entity.TypeVehicle.ELECTRICSCOOTER;
					
					Status statEVehicle = null;
					if(statusVehicle.equals("CHARCHING"))
						statEVehicle=entity.Status.CHARGING;
					else if(statusVehicle.equals("FREE"))
						statEVehicle=entity.Status.FREE;
					else if(statusVehicle.equals("MOVING"))
						statEVehicle=entity.Status.MOVING;
					else if(statusVehicle.equals("RENT"))
						statEVehicle=entity.Status.RENT;
						
					results.add(new ElectricVehicle(idVehicleString, typeV, statEVehicle,
							LastParkingStop, dateOfProdata, idManufacturerdata,
							drivingDistanceForVehicle, idBattery));
				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//System.out.println("hi4");
	//	System.out.println(results);
		return results;
	} 
	/**
	 * fetches all customers from DB file.
     * @return ArrayList of renter.
	 */
	public ArrayList<Renter> getRenters() {
		ArrayList<Renter> results = new ArrayList<Renter>();
	//	System.out.println("begin renter");
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_RENTER);
					ResultSet rs = stmt.executeQuery())
			{
				while (rs.next()) {
					int i = 1;
					
					int phoneNum= rs.getInt(i++);
					Date dateOfBirth= rs.getDate(i++);
					String renterFName= rs.getString(i++);
					String renterLName=rs.getString(i++);
					String idRenter= rs.getString(i++);
					String email= rs.getString(i++);				
					Boolean charger= rs.getBoolean(i++);
					
					results.add(new Renter(renterLName, renterFName, idRenter, email, phoneNum, dateOfBirth, charger));		
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	//	System.out.println("hi4");
	//	System.out.println(results);
		return results;
	}
	
	/**
	 * fetches all customers from DB file.
     * @return ArrayList of renter.
	 */
	public ArrayList<Manufacturer> getManufacturers() {
		ArrayList<Manufacturer> results = new ArrayList<Manufacturer>();
	//	System.out.println("begin Manufacturer");
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_MANUFACTURER);
					ResultSet rs = stmt.executeQuery())
			
			{
				while (rs.next()) {
					int i = 1;
					
					String idManufacturer=rs.getString(i++);
					String lNameManufactur= rs.getString(i++);
					String fNameManufactur= rs.getString(i++);
					String phoneManufactur= rs.getString(i++);
					
					
				
					results.add(new Manufacturer(idManufacturer, lNameManufactur, fNameManufactur, phoneManufactur));		
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	//	System.out.println("hi4");
	//	System.out.println(results);
		return results;
	}
	
	
	
	public ArrayList<ParkingStop> getParkingStop(){
		ArrayList<ParkingStop> results = new ArrayList<ParkingStop>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_PARKINGSTOP);
					ResultSet rs = stmt.executeQuery())
			
			{
				while (rs.next()) {
					int i = 1;
					
					String idParkingStop=rs.getString(i++);
					String nameParkingStop= rs.getString(i++);
					String cityParkingStop= rs.getString(i++);
					String streetParkingStop= rs.getString(i++);
					double coorXParkingStop= rs.getDouble(i++);
					double coorYParkingStop= rs.getDouble(i++);
					int capacityParkingStop= rs.getInt(i++);
					int correntCapParkingStop= rs.getInt(i++);
					int savedStopParkingStop= rs.getInt(i++);
/**/				int freeParkingStop= rs.getInt(i++);
					
					results.add(new ParkingStop(idParkingStop, cityParkingStop, streetParkingStop, coorXParkingStop, coorYParkingStop, capacityParkingStop, correntCapParkingStop, savedStopParkingStop, nameParkingStop));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public ArrayList<Battery> getBattery(){
		ArrayList<Battery> results = new ArrayList<Battery>();
		Location location = null;
	//	System.out.println("begin Battery");
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_BATTERY);
					ResultSet rs = stmt.executeQuery())
			
			{
				while (rs.next()) {
					int i = 1;
					
					String idBattery=rs.getString(i++);
					Double rateBattery= rs.getDouble(i++);
					String locationBattery= rs.getString(i++);
					Double drivingDistancePossiBattery= rs.getDouble(i++);
			
					
					if(locationBattery.equals("OUT OF ORDER"))
						location = Location.OUTOFORDER;
					if(locationBattery.equals("INCHARGING"))
						location = Location.INCHARGING;
					if(locationBattery.equals("INVEHICLE"))
						location = Location.INVEHICLE;

					
					results.add(new Battery(idBattery, rateBattery, location, drivingDistancePossiBattery));				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//System.out.println("hi PS");
		//System.out.println(results);
		return results;
	}
	
	public ArrayList<String> getStreetByCity(String city){
		String street;
		ArrayList<String> streets=new ArrayList<String>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_STREET_BY_CITY);){
					stmt.setString(1, city);
					ResultSet rs = stmt.executeQuery();
					
					
					while (rs.next()) {
						int i = 1;
						System.out.println("shalom");
						street=rs.getString(i++);
						streets.add(street);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return streets;
	}
	
	public ParkingStop getParkingStopbyName(String namePs){
		
		ParkingStop ps=null;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_REN_ONE__PS_BY_NAME);)
					{
					stmt.setString(1, namePs);
					ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int i = 1;
					
					String idParkingStop=rs.getString(i++);
					String nameParkingStop= rs.getString(i++);
					String cityParkingStop= rs.getString(i++);
					String streetParkingStop= rs.getString(i++);
					double coorXParkingStop= rs.getDouble(i++);
					double coorYParkingStop= rs.getDouble(i++);
					int capacityParkingStop= rs.getInt(i++);
					int correntCapParkingStop= rs.getInt(i++);
					int savedStopParkingStop= rs.getInt(i++);
					int freeParkingStop= rs.getInt(i++);
					
					 ps=new ParkingStop(idParkingStop, cityParkingStop, streetParkingStop, coorXParkingStop, coorYParkingStop, capacityParkingStop, correntCapParkingStop, savedStopParkingStop, nameParkingStop);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ps;
	}
	 			
		/*------------------RENT VEHICLE ----------------*/
		public ArrayList<RentVehicle> getRentVehicle(){
			ArrayList<RentVehicle> results = new ArrayList<RentVehicle>();
			//System.out.println("begin rentvehicle");
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_RENT_VEHICLE);
						ResultSet rs = stmt.executeQuery())
				
				{
					while (rs.next()) {
						int i = 1;
						String idRenter=rs.getString(i++);
						String idVehicle= rs.getString(i++);
						Date startDateRentvehicle= rs.getDate(i++);
						Date endDateERentVehicle= rs.getDate(i++);
						Double cost=rs.getDouble(i++);
						
					
						results.add(new RentVehicle(idRenter, idVehicle, startDateRentvehicle, endDateERentVehicle,cost ));
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		//	System.out.println("hi PS");
		//	System.out.println(results);
			return results;
		}
		
		/*------------------GET ALL CITY OF PS ----------------*/
		public ArrayList<String> getCityofPS(){
			ArrayList<String> results = new ArrayList<String>();
			//System.out.println("begin rentvehicle");
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
						PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_CityOfPS);
						ResultSet rs = stmt.executeQuery())
				
				{
					while (rs.next()) {
						int i = 1;
						
						String a=rs.getString(i++);
					//	if(!a.equals(rs.getString(i-1)))		
							results.add(a);
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		//	System.out.println("hi PS");
		//	System.out.println(results);
			return results;
		}
		

		
	
}