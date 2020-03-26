package entity;
import java.net.URLDecoder;

/**
 * http://www.javapractices.com/topic/TopicAction.do?Id=2
 */
public final class Consts {
	private Consts() {
		throw new AssertionError();
	}

	protected static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";
	
	
	/*----------------------------------------------SELECT VEHICLE NAME BY PS AND TYPE----------------*/	
	public static final String SQL_SEL_GG="SELECT  Vehicle.idVehicle, Vehicle.type, Vehicle.status, Vehicle.lastParkingStop, ElectricVehicle.dateOfPro,ElectricVehicle.idManufacturer, ElectricVehicle.drivingDistanceForVehicle, ElectricVehicle.idBattery \r\n" + 
			"FROM (ParkingStop INNER JOIN Vehicle ON ParkingStop.IDParkingStop = Vehicle.lastParkingStop) INNER JOIN (Battery INNER JOIN ElectricVehicle ON Battery.idBattery = ElectricVehicle.idBattery) ON Vehicle.idVehicle = ElectricVehicle.idVehicle\r\n" + 
		"WHERE (((ParkingStop.nameParkingStop)=?) AND ((Vehicle.type)=?) AND ((Vehicle.status)=\"Free\") AND ((Battery.rateBattery)>=20))";

	public static final String SQL_SEL_GG_BICYCLE=
			"SELECT  Vehicle.idVehicle, Vehicle.type, Vehicle.status, Vehicle.lastParkingStop, ElectricVehicle.dateOfPro,ElectricVehicle.idManufacturer, ElectricVehicle.drivingDistanceForVehicle, ElectricVehicle.idBattery \r\n" + 
			"FROM (ParkingStop INNER JOIN Vehicle ON ParkingStop.IDParkingStop = Vehicle.lastParkingStop) INNER JOIN (Battery INNER JOIN ElectricVehicle ON Battery.idBattery = ElectricVehicle.idBattery) ON Vehicle.idVehicle = ElectricVehicle.idVehicle\r\n" + 
		"WHERE ((ParkingStop.nameParkingStop)=?) AND ((Vehicle.type)=?) AND ((Vehicle.status)=\"Free\")";
			
	public static final String SQL_SEL_GG_BICYCLE_BY_ADREES="SELECT Vehicle.idVehicle, Vehicle.type, Vehicle.status, Vehicle.lastParkingStop, ParkingStop.city, ParkingStop.street\r\n" + 
			"FROM ParkingStop INNER JOIN Vehicle ON ParkingStop.IDParkingStop = Vehicle.lastParkingStop\r\n" + 
			"WHERE (((Vehicle.type)=?) AND ((Vehicle.status)=\"Free\") AND ((ParkingStop.city)=?) AND ((ParkingStop.street)=?))";
	
	public static final String SQL_SEL_GG_BY_ADREES="SELECT Vehicle.idVehicle, Vehicle.type, Vehicle.status, Vehicle.lastParkingStop, ElectricVehicle.dateOfPro, ElectricVehicle.idManufacturer, ElectricVehicle.drivingDistanceForVehicle,ElectricVehicle.idBattery\r\n" + 
			"FROM Battery INNER JOIN (ParkingStop INNER JOIN (Vehicle INNER JOIN ElectricVehicle ON Vehicle.idVehicle = ElectricVehicle.idVehicle) ON ParkingStop.IDParkingStop = Vehicle.lastParkingStop) ON Battery.idBattery = ElectricVehicle.idBattery\r\n" + 
			"WHERE (((Vehicle.type)=?) AND ((Vehicle.status)=\"Free\") AND ((ParkingStop.city)=?) AND ((ParkingStop.street)=?) AND ((Battery.rateBattery)>=20));\r\n" + 
			"";

	/*----------------------------------------- VEHICLE QUERIES -------------------------------*/
	public static final String SQL_SEL_VEHICLE = "SELECT * FROM Vehicle";
	public static final String INSERT_VEHICLE ="INSERT INTO Vehicle ( idVehicle, type, lastParkingStop)"
			+"VALUES (?, ?, ?)";
	public static final String UPDATE_VEHICLE = "{call qryUpdateVehicle(?, ?, ?)}";
	public static final String SQL_DEL_VEHICLE = "{ call qryDelVehicle(?) }"; // in vehiclelogic 
	//public static final String SQL_INS_VEHICLE = "{ call qryInsEmployee(?,?,?,?) }";
	public static final String SQL_UPD_STATUS_VEHICLE1 = /*"{ call qryUpdStatusVehicle(?,?) }";// in vehiclelogic */
	"UPDATE Vehicle SET Vehicle.status = ?"+
			"WHERE ((Vehicle.idVehicle)=?)";
	public static final String GET_VEHICLE_BY_RENTER = "{call qryGetVehicleByRenter(?)}";
	
	/*----------------------------------------- ELECTRIC VEHICLE QUERIES -------------------------------*/
	public static final String SQL_SEL_ELECTRIC_VEHICLE ="SELECT ElectricVehicle.idVehicle, ElectricVehicle.dateOfPro, ElectricVehicle.idManufacturer, ElectricVehicle.drivingDistanceForVehicle, ElectricVehicle.idBattery, Vehicle.type, Vehicle.status, Vehicle.lastParkingStop\r\n" + 
			"FROM Vehicle INNER JOIN ElectricVehicle ON Vehicle.idVehicle = ElectricVehicle.idVehicle";
	
	public static final String INSERT_ELECTRICVEHICLE ="INSERT INTO ElectricVehicle ( idVehicle, dateOfPro, idManufacturer, idBattery)"
			+"VALUES (?, ?, ?, ?)";
	public static final String UPDATE_ELECTRICVEHICLE = "{call qryUpdateElectricVehicle(?, ?, ?, ?)}";
	
	public static final String DELETE_ELECTRICVEHICLE = "{call qryDelElctricVehicle(?)}";
	/*-----------------------------------------  RENTER VEHICLE QUERIES -------------------------------*/
	public static final String SQL_SEL_RENT_VEHICLE = "SELECT * FROM RentVehicle";
	//qryInsRentVehicle
	public static final String SQL_SEL_GET_PS_BY_NAME = "SELECT ParkingStop.nameParkingStop\r\n" + 
			"FROM ParkingStop";
	public static final String SQL_REN_ONE__PS_BY_NAME = "SELECT * FROM ParkingStop WHERE ((ParkingStop.nameParkingStop)=?)";


	public static final String SQL_UPD_STATUS_VEHICLE = "UPDATE Vehicle SET Vehicle.status =?"+
			"WHERE ((Vehicle.idVehicle)=?)";
	//"{ call qryUpdStatusVehicle(?),(?) }";

	
	public static final String SQL_UPD_CORENTCAP_PS ="UPDATE ParkingStop SET ParkingStop.correntCap =?"
			+"WHERE (((ParkingStop.IDParkingStop)=?))";
	
	public static final String SQL_INS_RENT_VEHICLE ="INSERT INTO RentVehicle ( idVehicle, idRenter, startDateVehicle, endDateVehicle, cost )"
			+"VALUES (?, ?, ?, ?, ?)";
	
	public static final String SQL_GET_STREET_BY_CITY = "{ call getStreetByCity(?) }";

    public static final String SQL_GET_RENTER = "{call getRenter(?,?)}";
	
    public static final String SQL_DEL_VEHICLE_FROM_RENTER = "{call delVehicleFromRenter(?,?)}";
	
    public static final String SQL_UPDATE_VEHICLE_PARKING_STOP = "{call updateVehicleParkingStop(?,?)}";
    
    public static final String SQL_UPDATE_END_DATE = "{call updateEndDate(?,?)}";
    
    public static final String SQL_GET_COST = "{call getCost(?,?)}";
	
	/*-----------------------------------------  RENTER QUERIES -------------------------------*/
	public static final String SQL_SEL_RENTER = "SELECT * FROM Renter";

	public static final String INSERT_USER="INSERT INTO  Renter (  phone, date,  fname, lasname , id,  email,  charger, pasword)"
			  +"VALUES (?,?,?,?,?,?,?,?)";

	/*----------------------------------------- MANUFACTURER QUERIES -------------------------------*/
	public static final String SQL_SEL_MANUFACTURER = "SELECT * FROM Manufacturer";
	
	/*----------------------------------------- BATTERY QUERIES -------------------------------*/
	public static final String SQL_SEL_BATTERY = "SELECT * FROM Battery";
	public static final String SQL_UPD_LOCATION_BATTERY = "UPDATE Battery SET Battery.location =?"+
			"WHERE ((Battery.idBattery)=?)";
	public static final String SQL_INS_CHARGER_BATTERY ="INSERT INTO ChargerBattery ( idRenter, idBattery, startCharageDate, startChargeTime)"
			+"VALUES (?, ?, ?, ?)";
	public static final String GET_VEHICLE_BY_BATTERY = "{ call getVehicleByBattery(?); }";
	/*----------------------------------------- PARKINGSTOP QUERIES -------------------------------*/
	public static final String SQL_SEL_PARKINGSTOP = "SELECT * FROM ParkingStop";
	 public static final String INSERT_PARKINGSTOP ="INSERT INTO ParkingStop ( idParkingStop, city, street, coorX, coorY, capacity )"
		+"VALUES (?, ?, ?,?, ?, ?)"; 
	    public static final String UPDATE_PARKINGSTOP = "{call qryUpdateParkingStop(?, ?, ?,?, ?, ?)}";
	    public static final String DELETE_PARKINGSTOP = "{ call qryDeleteParkingStop(?); }";
	    public static final String GET_PARKINGSTOP_BY_NAME = "{ call getParkingStopByName(?); }";
	    public static final String GET_BATTERIES_FROM_PARKING_STOP = "{ call getBatteryFromParkingStop(?); }";
	    
	/*----------------------------------------all ps by City report-----------------------*/
	public static final String SQL_SEL_PARKINGSTOPbyCity = "SELECT ParkingStop.IDParkingStop, ParkingStop.street, ParkingStop.freeCapacity, ParkingStop.city\r\n" + 
			"FROM ParkingStop" + 
			"WHERE (ParkingStop.city=$P{choose})" + 
			"ORDER BY ParkingStop.street";
	
	/*--------------------get CITY of PS-------------------------*/
	public static final String SQL_SEL_CityOfPS = "SELECT ParkingStop.city\r\n" + 
			"FROM ParkingStop\r\n" + 
			"GROUP BY ParkingStop.city\r\n" + 
			"ORDER BY ParkingStop.city;\r\n" + 
			""; 
			
	  public enum Manipulation {
	    	UPDATE, INSERT, DELETE;
	    }
	  public static final String SELECT_ALL_BATTERIES = "SELECT * FROM battery";
	    public static final String INSERT_BATTERY ="INSERT INTO battery ( idBattery, rateBattery, location, drivingDistancePossi)"
				+"VALUES (?, ?, ?,?)"; 
	    public static final String UPDATE_BATTERY = "{call qryUpdateBattery(?,?,?,?)}";
	 //  public static final String UPDATE_BATTERY ="UPDATE battery SET Battery.rateBattery =? Battery.location = ?, Battery.drivingDistancePossi = ?"
			//	+"WHERE (Battery.idBattery=?)"; 
//	
	    
	
	    
		public static final String DELETE_BATTERY = "{ call qryDeleteBattery(?); }";
	
	/**
	 * find the correct path of the DB file
     * @return the path of the DB file (from eclipse or with runnable file)
	 */
	private static String getDBPath() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			// System.out.println(decoded) - Can help to check the returned path
			if (decoded.contains(".jar")) {
				decoded = decoded.substring(0, decoded.lastIndexOf('/'));
				System.out.println(decoded);
				return decoded + "/database/Rent2W.accdb";
			} else {
				decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
				return decoded + "src/entity/Rent2W.accdb";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}