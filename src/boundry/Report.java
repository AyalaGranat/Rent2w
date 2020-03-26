package boundry;
import java.awt.Dimension;
import java.awt.EventQueue.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Frame;

import control.*;
import entity.*;
import boundry.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.html.parser.Entity;
import java.awt.Frame;
import java.awt.Frame;
import java.awt.Frame;
import java.awt.*;

public class Report extends Frame  {
	String choose = "a";
	public Report() {
		System.out.println("begin of Report");
		//String[] inArray= {"choose city","tel-aviv", "haifa", "beer sheva", "ashdod", "even-yehuda"};
		
		JFrame report = new JFrame("Create a report");
		report.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 0, 0);
			
		 report.getContentPane().add(panel);  
		 report.setSize(500, 500);  
		 report.setLocationRelativeTo(null);  
		 report.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		 report.setVisible(true);  
       panel.setLayout(null);
			
		JLabel lblCreateReport = new JLabel("Create Report");
		lblCreateReport.setBounds(149, 19, 116, 23);
		report.getContentPane().add(lblCreateReport);
		
		JLabel lblChooseCity = new JLabel("Choose city:");
		lblChooseCity.setBounds(34, 88, 116, 23);
		report.getContentPane().add(lblChooseCity);
		
		
		//get name of city
        ArrayList<String> toName=OrderLogic.getInstance().getCityofPS();
        List<String> nameArrayList=new ArrayList<String>();
        nameArrayList.add("Choose city");
        for(String name:toName) {
      	  nameArrayList.add(name);
         }
        Object[]names=nameArrayList.toArray();
    
   		JComboBox comboBox = new JComboBox(names);
		comboBox.setBounds(180, 85, 99, 29);
		report.getContentPane().add(comboBox);
		
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(287, 243, 131, 31);	
		System.out.println("here");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					System.out.println("in combo box city "+ choose);
					int i = comboBox.getSelectedIndex();
					choose = (String) names[i];
				System.out.println("r="  +choose);
					//ReportLogic.getInstance().compile_J_Report(choose).setVisible(true);
					System.out.println("choose" +choose);
				}
			});
		
		btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
				System.out.println("in btn search "+ choose);
				ReportLogic.getInstance().compile_J_Report(choose).setVisible(true);
				System.out.println("finish compile report");
			}
		});
		
		panel.add(btnSearch);
		JSeparator separator = new JSeparator();
		panel.add(separator);
		report.getContentPane().add(btnSearch);
		System.out.println("end of Report");
		
		
	}
}