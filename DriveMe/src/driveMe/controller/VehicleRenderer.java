package driveMe.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import org.apache.commons.lang.StringUtils;

import driveMe.MainRenderer;
import driveMe.MapPanel;
import driveMe.constants.DriveMeConstants;
import driveMe.util.DriveMeUtil;
import driveMe.vehicles.model.Vehicle;
import net.miginfocom.swing.MigLayout;

public class VehicleRenderer extends MainRenderer{

	private static  JPanel vehicleBodyContentPanel = new JPanel();
	
	private DriveMeUtil driveMeUtil = new DriveMeUtil();
	
	private JPanel scrollPane;
	private JPanel vehiclePanelWest;
	private JPanel vehicleContentPanel;
	private JScrollPane scrollPaneContainer;
	
	public JPanel vehicleHeaderBottom(JFrame mainFrame, boolean vehiclePageActive) 
	{
		JPanel headerBottom = new JPanel();
		headerBottom.setPreferredSize(new Dimension(10, 20));
		headerBottom.setMinimumSize(new Dimension(10, 27));
		headerBottom.setBackground(DriveMeConstants.Colour.secondColor);
		headerBottom.setLayout(new BorderLayout(0, 0));
		
		//Set leftSidePanel to header bottom
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setPreferredSize(new Dimension(330, 10));
		headerBottom.add(leftSidePanel, BorderLayout.WEST);
		
		JButton btnFahrzeugAnlegen = new JButton("Fahrzeug Anlegen");
		btnFahrzeugAnlegen.setForeground(Color.WHITE);
		btnFahrzeugAnlegen.setPreferredSize(new Dimension(145, 25));
		btnFahrzeugAnlegen.setMargin(new Insets(0, 0, 0, 0));
		btnFahrzeugAnlegen.setBorder(null);
		btnFahrzeugAnlegen.setBackground(DriveMeConstants.Colour.primaryColor);
		btnFahrzeugAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				JPanel p = new JPanel(); 
//		        p.setPreferredSize(new Dimension(400,400));
//		        p.setBackground(Color.blue);
//
//				p.setBounds( (mainFrame.getWidth() / 2) - 400,  (mainFrame.getHeight()/ 2) - 400 , 400,400 );
//	        	p.setVisible(true);
//	        	bodyJLayeredPane.add(p, JLayeredPane.POPUP_LAYER);
				
				driveMeUtil.applyNewVehicle();
			}
		});
		leftSidePanel.add(btnFahrzeugAnlegen);
		
		JButton btnFahrzeugsettings = new JButton("Fahrzeuge �ndern");
		btnFahrzeugsettings.setPreferredSize(new Dimension(145, 25));
		btnFahrzeugsettings.setMargin(new Insets(0, 0, 0, 0));
		btnFahrzeugsettings.setForeground(Color.WHITE);
		btnFahrzeugsettings.setBorder(null);
		btnFahrzeugsettings.setBackground(DriveMeConstants.Colour.primaryColor);
		leftSidePanel.add(btnFahrzeugsettings);

		//Set rightSidePanel to header bottom
		JPanel rightSidePanel = new JPanel();
		rightSidePanel.setPreferredSize(new Dimension(200, 10));
		rightSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		headerBottom.add(rightSidePanel, BorderLayout.EAST);
		//Add compobox to rightSidePanel
		driveMeUtil.addComboBoxToPanel(rightSidePanel);
		
		//Set topSidePanel to header bottom
		JPanel topSidePanel = new JPanel();
		headerBottom.add(topSidePanel, BorderLayout.NORTH);

		//Set bottomSidePanel to header bottom		
		JPanel bottomSidePanel = new JPanel();
		headerBottom.add(bottomSidePanel, BorderLayout.SOUTH);
		
		//Set midSidePanel to header bottom
		JPanel midSidePanel = new JPanel();
		midSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		headerBottom.add(midSidePanel, BorderLayout.CENTER);
		//Set textField to midSidePanel
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(400, 25));
		textField.setSize(new Dimension(400, 24));
		textField.setColumns(50);
		textField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            	String input = textField.getText();
            		if(vehiclePageActive)
	            	{
            			refreshVehiclePanel(input);
	            	}
	            }
	    });
		midSidePanel.add(textField);
		
		return headerBottom;
	}
	
	public JPanel getVehicleContent(ArrayList<Vehicle> vehicels) 
	{
//		JPanel  vehicleBodyContentPanel = new JPanel();
		VehicleRenderer.vehicleBodyContentPanel.setBackground(Color.WHITE);
		VehicleRenderer.vehicleBodyContentPanel.setLayout(new BorderLayout(0, 0));
		VehicleRenderer.vehicleBodyContentPanel.setPreferredSize(new Dimension(10, 90));
		
		vehicleContentPanel = createVehiclePanelWest(vehicels);
		VehicleRenderer.vehicleBodyContentPanel.add(vehicleContentPanel, BorderLayout.WEST);
//		VehicleRenderer.vehicleBodyContentPanel.putClientProperty(vehicleContentPanel, DriveMeConstants.VehicleContent.VEHICLE_KEY);

		VehicleRenderer.vehicleBodyContentPanel.add(driveMeUtil.createPlaceholderPanel(new Dimension(30, 10)), BorderLayout.NORTH);
			
		VehicleRenderer.vehicleBodyContentPanel.add(driveMeUtil.createPlaceholderPanel(new Dimension(30, 10)), BorderLayout.SOUTH);
		
		JPanel vehicleBodyContentMap = createMapPanel();
		VehicleRenderer.vehicleBodyContentPanel.add(vehicleBodyContentMap, BorderLayout.CENTER);
//		VehicleRenderer.vehicleBodyContentPanel.putClientProperty(vehicleBodyContentMap, DriveMeConstants.VehicleContent.MAP_KEY);
		
		return VehicleRenderer.vehicleBodyContentPanel;
	}
	
	private JPanel createVehiclePanel(Vehicle currentVehicle) 
	{
		JPanel vehiclePanel = new JPanel();
		vehiclePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel innerVehiclePanel = new JPanel();
		innerVehiclePanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		innerVehiclePanel.setPreferredSize(new Dimension(10, 50));
		innerVehiclePanel.setLayout(null);
			
		JLabel vehicleDetails = new JLabel(currentVehicle.getBrand() + " " + currentVehicle.getModel());
		vehicleDetails.setBounds(59, 11, 200, 14);
		innerVehiclePanel.add(vehicleDetails);
	
		JLabel vehicleLocation = new JLabel("Beinberger Weg 11, 75394 Oberreichenbach");
		vehicleLocation.setFont(new Font("Tahoma", Font.PLAIN, 9));
		vehicleLocation.setBounds(59, 25, 261, 14);
		innerVehiclePanel.add(vehicleLocation);
	
		JButton moreDetailsBtn = new JButton("");
		moreDetailsBtn.setIcon(driveMeUtil.resizeImageIcon("standort.png", 15, 15));
		moreDetailsBtn.setBorder(null);
		moreDetailsBtn.setPreferredSize(new Dimension(10, 10));
		moreDetailsBtn.setBounds(294, 11, 26, 23);
		innerVehiclePanel.add(moreDetailsBtn);
		
		JButton reservationBtn = new JButton("");
		reservationBtn.setIcon(driveMeUtil.resizeImageIcon("menu.png", 15, 15));
		reservationBtn.setBorder(null);
		reservationBtn.setPreferredSize(new Dimension(10, 10));
		reservationBtn.setBounds(269, 11, 26, 23);
		innerVehiclePanel.add(reservationBtn);
				
		vehiclePanel.add(innerVehiclePanel, BorderLayout.NORTH);

		JPanel carDetails = new JPanel();
		carDetails.setLayout(null);
		
		JLabel lblReserved = new JLabel("Keine Buchung");
		lblReserved.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblReserved.setBounds(25, 25, 103, 14);
		carDetails.add(lblReserved);
		
		JLabel lblLocation = new JLabel("Aktueller Standort: " + currentVehicle.getLatitude() + " " + currentVehicle.getLongitude());
		lblLocation.setBounds(25, 11, 48, 14);
		carDetails.add(lblLocation);
			
//			JLabel lblReserved = new JLabel("Reserviert von: " + currentVehicle.getReservedFrom() + " bis: " + currentVehicle.getReservedTo());
//			lblReserved.setFont(new Font("Tahoma", Font.PLAIN, 9));
//			lblReserved.setBounds(25, 25, 103, 14);
//			carDetails.add(lblReserved);
//			
//			JLabel lblLocation = new JLabel("Aktueller Standort: ");
//			lblLocation.setBounds(25, 11, 48, 14);
//			carDetails.add(lblLocation);
				
		vehiclePanel.add(carDetails, BorderLayout.CENTER);
			
		return vehiclePanel;
	}

	private JPanel createVehiclePanelWest(ArrayList<Vehicle> vehicles)
	{
		//Vehicle Panel Align West 
		vehiclePanelWest = new JPanel();
		
		vehiclePanelWest.setMinimumSize(new Dimension(10, 128));
		vehiclePanelWest.setPreferredSize(new Dimension(400, 10));
		vehiclePanelWest.setBackground(SystemColor.GREEN);
		vehiclePanelWest.setLayout(new BorderLayout(0, 0));

		vehiclePanelWest.add(createScrollPaneWithVehicles(vehicles), BorderLayout.CENTER);
		
		//Placeholder for vehiclePanel
		JPanel placeholderWest = new JPanel();
		placeholderWest.setBackground(Color.WHITE);
		placeholderWest.setPreferredSize(new Dimension(30, 10));
		vehiclePanelWest.add(placeholderWest, BorderLayout.WEST);
		
		//Placeholder for vehiclePanel
		JPanel placeholderCenter = new JPanel();
		placeholderCenter.setBackground(Color.WHITE);
		placeholderCenter.setPreferredSize(new Dimension(10, 10));
		vehiclePanelWest.add(placeholderCenter, BorderLayout.EAST);
		return vehiclePanelWest;
	}
	
	private JPanel createMapPanel()
	{
		// Center Panel Map
		JPanel vehiclePanelCenter = new JPanel();
		vehiclePanelCenter.setMinimumSize(new Dimension(10, 128));
		vehiclePanelCenter.setPreferredSize(new Dimension(400, 10));
		vehiclePanelCenter.setBackground(SystemColor.GREEN);
		vehiclePanelCenter.setLayout(new BorderLayout(0, 0));
		
		vehiclePanelCenter.add(new MapPanel());
		
		//Placeholder for vehiclePanelCenter
		JPanel placeholderEAST = new JPanel();
		placeholderEAST.setBackground(Color.WHITE);
		placeholderEAST.setPreferredSize(new Dimension(30, 10));
		vehiclePanelCenter.add(placeholderEAST, BorderLayout.EAST);
		
		return vehiclePanelCenter;
	}
	
	private JScrollPane createScrollPaneWithVehicles(ArrayList<Vehicle> vehicles)
	{
		scrollPane = new JPanel();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setLayout(new MigLayout("", "[320px]", "[100px]"));
		
		if(vehicles != null)
		{
			int i = 0;
			for (Vehicle currentVehicle : vehicles)
			{
				String position = "cell 0 "+ i + ",grow";
				scrollPane.add(createVehiclePanel(currentVehicle), position );
				i++;
			}
		}
		
		scrollPaneContainer = new JScrollPane(scrollPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneContainer.setBorder(null);
		scrollPaneContainer.setBounds(new Rectangle(0, 0, 0, 20));
		scrollPaneContainer.setPreferredSize(new Dimension(0, 200));
		scrollPaneContainer.getVerticalScrollBar().setUnitIncrement(13);
		
		return scrollPaneContainer;
	}
	
	private void refreshVehiclePanel(String searchInput)
	{
		ArrayList<Vehicle> filteredVehicles = null;
		if(allVehicles != null)
		{
			filteredVehicles = new ArrayList<Vehicle>();
			for(Vehicle currentVehicle : allVehicles)
			{
				if(StringUtils.containsIgnoreCase(currentVehicle.getBrand() , searchInput) || StringUtils.containsIgnoreCase(currentVehicle.getModel() , searchInput))
				{
					filteredVehicles.add(currentVehicle);					
				}
			}
		}
//		Object vehiclePanelWest = VehicleRenderer.vehicleBodyContentPanel.getClientProperty(DriveMeConstants.VehicleContent.VEHICLE_KEY);
//		
//		if(vehiclePanelWest instanceof JPanel)
//		{
		driveMeUtil.clearAndSetContent(vehicleContentPanel, createVehiclePanelWest(filteredVehicles) );
		vehicleContentPanel.setBackground(Color.RED);
		scrollPaneContainer.setBorder(null);
//		}
//		driveMeUtil.clearAndSetContent(bodyContentPanel, getVehicleContent(filteredVehicles));
	}
}