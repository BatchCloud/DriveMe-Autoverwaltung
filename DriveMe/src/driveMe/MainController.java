package driveMe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import driveMe.vehicles.service.VehiclesService;


public class MainController {

public JFrame mainFrame=new JFrame("DriveMe");
	
	private Color primaryColor = new Color(105,157,217);
	private Color secondColor = new Color(238,238,238);
	
	
	protected void initialize() {

		//Setup Main frame
//		mainFrame.setType(Type.POPUP);
		mainFrame.setBackground(primaryColor);
		mainFrame.getContentPane().setMinimumSize(new Dimension(20, 16));
		mainFrame.setBounds(100, 100, 1106, 661);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		setUpHeader();
	}

	private void setUpHeader() 
	{
		//Setup header pannel
		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(10, 90));
		headerPanel.setMinimumSize(new Dimension(10, 128));
		headerPanel.setBackground(SystemColor.windowBorder);
		mainFrame.getContentPane().add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		setUpHeaderTop(headerPanel);
		setUpHeaderBottom(headerPanel); //TODO
	}
	
	private void setUpHeaderTop(JPanel headerPanel) {
		//Setup header top to header pannel
		JPanel headerTop = new JPanel();
		headerPanel.add(headerTop, BorderLayout.NORTH);
		headerTop.setMaximumSize(new Dimension(32767, 0));
		headerTop.setMinimumSize(new Dimension(0, 0));
		headerTop.setPreferredSize(new Dimension(0, 35));
		headerTop.setBackground(primaryColor);
		
		//Set FlowLayout that buttons at headerTop are left justified
		FlowLayout headerTopFlowLayout = (FlowLayout) headerTop.getLayout();
		headerTopFlowLayout.setVgap(0);
		headerTopFlowLayout.setHgap(0);
		headerTopFlowLayout.setAlignment(FlowLayout.LEFT);
		
		//Add Buttons to header
		JButton vehicleButton = new JButton("Fahrzeuge");
		vehicleButton.setBorder(null);
		vehicleButton.setBackground(secondColor);
		vehicleButton.setMargin(new Insets(0, 0, 0, 0));
		vehicleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().add(vehicleContent());
			}
		});
		vehicleButton.setPreferredSize(new Dimension(150, 35));
		headerTop.add(vehicleButton);
		
		JButton customerButton = new JButton("Kunden");
		customerButton.setForeground(Color.WHITE);
		customerButton.setBorder(null);
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.getContentPane().removeAll();
	//						add customer page to contentPane
				mainFrame.getContentPane().add(customerContent());
			}
		});
		customerButton.setBackground(primaryColor);
		customerButton.setMargin(new Insets(0, 0, 0, 0));
		customerButton.setPreferredSize(new Dimension(150, 35));
		headerTop.add(customerButton);
	}
	
	private void setUpHeaderBottom(JPanel headerPanel) 
	{
		//TODO
	}

	private JPanel vehicleContent() 
	{
		JPanel vehiclePanel = new JPanel();
		vehiclePanel.setPreferredSize(new Dimension(10, 90));
		vehiclePanel.setMinimumSize(new Dimension(10, 128));
		vehiclePanel.setBackground(SystemColor.windowBorder);
		vehiclePanel.setLayout(new BorderLayout(0, 0));
		
		return vehiclePanel;
	} 
	
	private JPanel customerContent() 
	{
		JPanel customerPanel = new JPanel();
		customerPanel.setPreferredSize(new Dimension(10, 90));
		customerPanel.setMinimumSize(new Dimension(10, 128));
		customerPanel.setBackground(SystemColor.windowBorder);
		customerPanel.setLayout(new BorderLayout(0, 0));
		
		
		return customerPanel;
	}
}
