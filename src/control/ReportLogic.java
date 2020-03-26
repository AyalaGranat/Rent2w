package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import entity.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;


public class ReportLogic {
	 private static ReportLogic instance;
	    
	    /**
	     * private constructor for singleton purposes.
	     */
	    private ReportLogic() {}
	    
	    
	    /**
	     * Singleton getter.
	     * @return instance of this.
	     */
	    public static ReportLogic getInstance() {
	        if (instance == null)
	            instance = new ReportLogic();
	        return instance;
	    }
	   	       
	    public JFrame compile_J_Report(String choose) {
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
	            	System.out.println("in compile_J_Report");
	            	HashMap<String, Object> params = new HashMap<>();
	            	params.put("choose", choose);
	            	JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("../boundry/J_Report.jasper"),params, conn);
	                //        new HashMap<String, Object>(), conn);
	            	System.out.println("after report");
	                JFrame frame = new JFrame("Parking Stop Result");
	                frame.getContentPane().add(new JRViewer(print));
	                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	                frame.pack();
	                
	                return frame;
	            } catch (SQLException | JRException | NullPointerException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
}