package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;

import entity.Consts;

import entity.Manufacturer;
import entity.ParkingStop;
import entity.Renter;

public class LoginLogic {
private static LoginLogic _instance;
	
	private LoginLogic() {
	}


	public static LoginLogic getInstance() {
		BasicConfigurator.configure();
		if (_instance == null)
			_instance = new LoginLogic();
		return _instance;
		
	}
	
	public Renter getRenter(String username, String password) throws SQLException, ParseException {
		
		try {
			
			System.out.println("ttttttttt");
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_GET_RENTER)) {
				stmt.setString(1, username);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
		
				System.out.print(username +password+"");
				
				while (rs.next()) {
					int i = 1;
					
					
					System.out.println("ggggggggggggggggggggggggggggggg");
					String idLname= rs.getString(i++);
					String pass = rs.getString(i++);
					int phoneNum= rs.getInt(i++);
					//Timestamp dateOfBirth =	rs.getTimestamp(i++);
					Date date=rs.getDate(i++);
					//java.sql.Date dateOfBirth1 = new java.sql.Date(dateOfBirth.getTime());
					//Date DateOfPro1 = new SimpleDateFormat("dd/MM/yyyy").parse(DateOfPro); 
			//		java.util.Date dateOfBirth = new SimpleDateFormat("dd-MM-yyyy"); 
				//	java.sql.Date dateOfPro2 = new java.sql.Date(dateOfPro1.getTime());
					
				//	Date dateOfBirth= rs.getDate(i++);
					String renterFname= rs.getString(i++);
					String renterLname= rs.getString(i++);
					
					String email = rs.getString(i++);
					boolean charger = rs.getBoolean(i++);
					
					
					
					Renter renter = new Renter(renterLname, renterFname, username, email, phoneNum, date, charger);
					System.out.println(renter);
					return renter;
				}
			
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		return null;
	}
	
	public boolean addNewUser(String phone,Date date, String fname,String lasname , String id, String email, String charger, String pasword) throws SQLException {
		try {
			
			
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.INSERT_USER)) {
				
				int i = 1;
				stmt.setString(i++, phone);		
				stmt.setDate(i++, date); 
				stmt.setString(i++, fname); 
				stmt.setString(i++, lasname); 
				stmt.setString(i++, id); 
				stmt.setString(i++, email); 
				stmt.setString(i++, charger); 
				stmt.setString(i++, pasword); 
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e1) {
				throw e1;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
//public ArrayList<Manufacturer> getMenufacturer() {
//	ArrayList<Manufacturer> results = new ArrayList<Manufacturer>();
//
//	try {
//		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//		try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//				PreparedStatement stmt = conn.prepareStatement(Consts.GET_MANUFACTURER);
//				ResultSet rs = stmt.executeQuery())
//		
//		{
//			while (rs.next()) {
//				int i = 1;
//				
//				String code= rs.getString(i++);
//				String fname= rs.getString(i++);
//				String lastname= rs.getString(i++);
//				String phone= rs.getString(i++);
//				String email= rs.getString(i++);
//				
//				Manufacturer newmanu= new Manufacturer(code, fname, lastname, phone, email);
//				
//				
//				results.add(newmanu);