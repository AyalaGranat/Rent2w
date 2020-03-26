package boundry;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import boundry.*;
import control.BatteryLogic;
import control.ElectricVehicleLogic;
import control.ParkingStopLogic;
import control.VehicleLogic;

public class Main extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		BatteryLogic.getInstance().importBatteryFromJSON("json/battery.json."); //("json/customers.json")
	//	BatteryLogic.getInstance().addBattery("200", 50.0, "inVehicle", 200.0);
	//	BatteryLogic.getInstance().updateBattery("100", 80.0, "inVehicle", 200.0);
		ParkingStopLogic.getInstance().importParkingStopFromXML("xml/parkingStop.xml");
		VehicleLogic.getInstance().importVehicleFromXML("xml/Vehicle.xml");
		ElectricVehicleLogic.getInstance().importElectricVehicleFromXML("xml/ElectricVehicle.xml");

		Login menu = new Login();
	}
}
