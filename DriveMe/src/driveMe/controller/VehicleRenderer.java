package driveMe.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.io.IOException;
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

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import driveMe.MainRenderer;
import driveMe.MapPanel;
import driveMe.constants.DriveMeConstants;
import driveMe.vehicles.model.Vehicle;
import driveMe.vehicles.service.VehicleService;
import net.miginfocom.swing.MigLayout;

public class VehicleRenderer extends MainRenderer{

	private static  JPanel vehicleBodyContentPanel = new JPanel();
	
	private JPanel scrollPane;
	private JPanel vehiclePanelWest;
	private JPanel vehicleContentPanel;
	private JScrollPane scrollPaneContainer;
	private MapPanel map = new MapPanel();
	
	private VehicleService vehicleService= new VehicleService();
	
	public VehicleRenderer () 
	{
	}
	
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
				applyNewVehicle(mainFrame);
			}
		});
		leftSidePanel.add(btnFahrzeugAnlegen);
		
//		SebS - disable Vehicle edit button 
//		JButton btnFahrzeugsettings = new JButton("Fahrzeuge ‰ndern");
//		btnFahrzeugsettings.setPreferredSize(new Dimension(145, 25));
//		btnFahrzeugsettings.setMargin(new Insets(0, 0, 0, 0));
//		btnFahrzeugsettings.setForeground(DriveMeConstants.Colour.secondColor);
//		btnFahrzeugsettings.setBorder(null);
//		btnFahrzeugsettings.setBackground(DriveMeConstants.Colour.secondColor);
//		leftSidePanel.add(btnFahrzeugsettings);
		JPanel placeholder = new JPanel();
		placeholder.setPreferredSize(new Dimension(145, 25));
		leftSidePanel.add(placeholder);

		//Set rightSidePanel to header bottom
		JPanel rightSidePanel = new JPanel();
		rightSidePanel.setPreferredSize(new Dimension(200, 10));
		rightSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		headerBottom.add(rightSidePanel, BorderLayout.EAST);
//		//Add compobox to rightSidePanel
//		driveMeUtil.addComboBoxToPanel(rightSidePanel);
		//rightSidePanel.add(placeholder);
		
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
            			searchVehicles(input);
	            	}
	            }
	    });
		midSidePanel.add(textField);
		
		return headerBottom;
	}
	
	public JPanel getVehicleContent(ArrayList<Vehicle> vehicels) 
	{
		VehicleRenderer.vehicleBodyContentPanel.setBackground(Color.WHITE);
		VehicleRenderer.vehicleBodyContentPanel.setLayout(new BorderLayout(0, 0));
		VehicleRenderer.vehicleBodyContentPanel.setPreferredSize(new Dimension(10, 90));
		
		vehicleContentPanel = createVehiclePanelWest(vehicels);
		VehicleRenderer.vehicleBodyContentPanel.add(vehicleContentPanel, BorderLayout.WEST);

		VehicleRenderer.vehicleBodyContentPanel.add(driveMeUtil.createPlaceholderPanel(new Dimension(30, 10)), BorderLayout.NORTH);
			
		VehicleRenderer.vehicleBodyContentPanel.add(driveMeUtil.createPlaceholderPanel(new Dimension(30, 10)), BorderLayout.SOUTH);
		
		JPanel vehicleBodyContentMap = createMapPanel();
		VehicleRenderer.vehicleBodyContentPanel.add(vehicleBodyContentMap, BorderLayout.CENTER);
		
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
		
		JLabel vehicleIcon = new JLabel("");
		try {
			vehicleIcon.setIcon(driveMeUtil.resizeImageIconURL(currentVehicle.getImage(), 40, 30));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		vehicleIcon.setBounds(5,5,50,40);
		innerVehiclePanel.add(vehicleIcon);
			
		JLabel vehicleDetails = new JLabel(currentVehicle.getBrand() + " " + currentVehicle.getModel());
		vehicleDetails.setBounds(59, 11, 200, 14);
		innerVehiclePanel.add(vehicleDetails);
	
		
		
		JLabel vehicleLocation = new JLabel("Musterstraﬂe 1, 71209 Musterstadt");
		vehicleLocation.setFont(new Font("Tahoma", Font.PLAIN, 9));
		vehicleLocation.setBounds(59, 25, 261, 14);
		innerVehiclePanel.add(vehicleLocation);
	
		JButton moreDetailsBtn = new JButton("");
		moreDetailsBtn.setIcon(driveMeUtil.resizeImageIcon("standort.png", 15, 15));
		moreDetailsBtn.setBorder(null);
		moreDetailsBtn.setPreferredSize(new Dimension(10, 10));
		moreDetailsBtn.setBounds(294, 11, 26, 23);
		moreDetailsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vehicleService.jumpToLocation(map, currentVehicle);
			}
		});
		innerVehiclePanel.add(moreDetailsBtn);
		
//		SebS - remove menu button
//		JButton reservationBtn = new JButton("");
//		reservationBtn.setIcon(driveMeUtil.resizeImageIcon("menu.png", 15, 15));
//		reservationBtn.setBorder(null);
//		reservationBtn.setPreferredSize(new Dimension(10, 10));
//		reservationBtn.setBounds(269, 11, 26, 23);
//		innerVehiclePanel.add(reservationBtn);
				
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
		
		
		vehiclePanelCenter.add(map);
		
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
	
	private void searchVehicles(String searchInput)
	{
		ArrayList<Vehicle> filteredVehicles = null;
		ArrayList<Vehicle> allVehicles = VehicleService.findAllVehicles();
		
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

		refreshVehiclePanel(filteredVehicles);
	}
	
	public void refreshVehiclePanel(ArrayList<Vehicle> vehicles)
	{
		driveMeUtil.clearAndSetContent(vehicleContentPanel, createVehiclePanelWest(vehicles) );
		vehicleContentPanel.setBackground(Color.RED);
		scrollPaneContainer.setBorder(null);
	}
	
	public void applyNewVehicle(JFrame mainFrame) 
	{
		JPanel panel = setVehicleFormToPanel();
		
		int exit = JOptionPane.showConfirmDialog(mainFrame, panel, "Fahrzeug anlegen", JOptionPane.DEFAULT_OPTION);

		if (exit == JOptionPane.YES_OPTION){
			Component[] filledComponents = panel.getComponents();
			if(ArrayUtils.isNotEmpty(filledComponents))
			{
				Vehicle newVehicle = new Vehicle();
				for(Component currentComponent : filledComponents) {
					if(DriveMeConstants.Database.Vehicle.BRAND.equals(currentComponent.getName())){
						String brandValue = driveMeUtil.getStringFromSubComponentCombobox(currentComponent);
						newVehicle.setBrand(brandValue);
						String modelValue = driveMeUtil.getStringFromSubComponentTextField(currentComponent);
						if(StringUtils.isEmpty(modelValue))
						{
							modelValue="";
						}
						newVehicle.setModel(modelValue);
					}
					else if(DriveMeConstants.Database.Vehicle.PS.equals(currentComponent.getName())){
						String psValue = driveMeUtil.getStringFromSubComponentCombobox(currentComponent);
						int ps = 0;
						if(psValue.matches("[0-9]+"))
						{
							ps = Integer.valueOf(psValue);
						}

						newVehicle.setPs(ps);
					}
					else if(DriveMeConstants.Database.Vehicle.SEATS.equals(currentComponent.getName())){
						String selectedSeat = driveMeUtil.getStringFromSubComponentCombobox(currentComponent);
						int seatValue = 0;
						if(selectedSeat.matches("[0-9]+"))
						{
							seatValue = Integer.valueOf(selectedSeat);
						}
						newVehicle.setSeats(seatValue);									
					}
					else if(DriveMeConstants.Database.Vehicle.LONGITUDE.equals(currentComponent.getName())){
						String longitudeValue = driveMeUtil.getStringFromSubComponentTextField(currentComponent);
						if(StringUtils.isEmpty(longitudeValue))
						{
							longitudeValue="0.0";
						}
						newVehicle.setLongitude(longitudeValue);									
					}
					else if(DriveMeConstants.Database.Vehicle.LATITUDE.equals(currentComponent.getName())){
						String latitudeValue = driveMeUtil.getStringFromSubComponentTextField(currentComponent);
						if(StringUtils.isEmpty(latitudeValue))
						{
							latitudeValue="0.0";
						}
						newVehicle.setLatitude(latitudeValue);									
					}
					else if(DriveMeConstants.Database.Vehicle.IMAGE.equals(currentComponent.getName())){
						String imageValue= driveMeUtil.getStringFromSubComponentTextField(currentComponent);
						if(StringUtils.isEmpty(imageValue))
						{
							imageValue="https://www.huk-autowelt.de/images/Gebrauchtwagensuche/HUK_Auto-kaufen_Gebrauchtwagen.png";
						}
						newVehicle.setImage(imageValue);									
					}
					else if(DriveMeConstants.Database.Vehicle.FUEL.equals(currentComponent.getName())){
						String fuelValue = driveMeUtil.getStringFromSubComponentTextField(currentComponent);
						if(StringUtils.isEmpty(fuelValue))
						{
							fuelValue="";
						}
						newVehicle.setFuel(fuelValue);									
					}
				}
				boolean vehicleSaved = vehicleService.saveVehicle(newVehicle);
				if(vehicleSaved) {
					System.out.println("Jucheii!");
					//TODO
					ArrayList<Vehicle> allVehicles = VehicleService.findAllVehicles();
					refreshVehiclePanel(allVehicles);
				}
				else {
					System.out.println("Something went wrong while saving the vehicle!");
				}
			} 
		}
	}
	
	private JPanel setVehicleFormToPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		
		//Vehicle Brands
		JPanel brandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		List<String> brands = new ArrayList<String>();
		brands.addAll(Arrays.asList("Benz","Volvo", "VW"));
		
		JComboBox<String> vehicleSelection = new JComboBox<String>();
		vehicleSelection.setForeground(Color.BLACK);
		vehicleSelection.setBorder(null);
		vehicleSelection.setBackground(Color.WHITE);
		vehicleSelection.setPreferredSize(new Dimension(145, 25));
		for(String brand : brands) {
			vehicleSelection.addItem(brand);					
		}
		brandPanel.add(vehicleSelection);
		JTextField modelTextField = new JTextField(20);
		modelTextField.setName(DriveMeConstants.Database.Vehicle.MODEL);
		brandPanel.add(modelTextField);
		brandPanel.setName(DriveMeConstants.Database.Vehicle.BRAND);
		panel.add(brandPanel);

		//PS input
		JPanel horsePowerInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
		horsePowerInput.add(new JLabel("Pferdest‰rke:"));
		horsePowerInput.add(new JTextField(10));
		horsePowerInput.setName(DriveMeConstants.Database.Vehicle.PS);
		panel.add(horsePowerInput);
		
		//Seat input
		JPanel seatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		seatPanel.add(new JLabel("Anzahl der Sitze:"));
		List<String> seats = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8"));
		
		JComboBox<String> seatSelection = new JComboBox<String>();
		seatSelection.setForeground(Color.BLACK);
		seatSelection.setBorder(null);
		seatSelection.setBackground(Color.WHITE);
		seatSelection.setPreferredSize(new Dimension(145, 25));
		for(String seat : seats) {
			seatSelection.addItem(seat);					
		}
		seatPanel.add(seatSelection);
		seatPanel.setName(DriveMeConstants.Database.Vehicle.SEATS);
		panel.add(seatPanel);
		
		//Longitude
		JPanel longitudeInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
		longitudeInput.add(new JLabel("Longitude:"));
		longitudeInput.add(new JTextField(10));
		longitudeInput.setName(DriveMeConstants.Database.Vehicle.LONGITUDE);
		panel.add(longitudeInput);
		
		//Latitude
		JPanel latitudeInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
		latitudeInput.add(new JLabel("Latitude:"));
		latitudeInput.add(new JTextField(10));
		latitudeInput.setName(DriveMeConstants.Database.Vehicle.LATITUDE);
		panel.add(latitudeInput);
		
		//Image Link
		JPanel imageInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
		imageInput.add(new JLabel("Bild:"));
		imageInput.add(new JTextField(10));
		imageInput.setName(DriveMeConstants.Database.Vehicle.IMAGE);
		panel.add(imageInput);
		
		
		//Fuel variant
		JPanel fuelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		seatPanel.add(new JLabel("Anzahl der Sitze:"));
		List<String> fuel = new ArrayList<String>(Arrays.asList("Benzin","Diesel"));
		
		JComboBox<String> fuelSelection = new JComboBox<String>();
		fuelSelection.setForeground(Color.BLACK);
		fuelSelection.setBorder(null);
		fuelSelection.setBackground(Color.WHITE);
		fuelSelection.setPreferredSize(new Dimension(145, 25));
		for(String currentFuel : fuel) {
			fuelSelection.addItem(currentFuel);					
		}
		fuelPanel.add(fuelSelection);
		fuelPanel.setName(DriveMeConstants.Database.Vehicle.SEATS);
		panel.add(fuelPanel);
		
		//Fuel 
		JPanel fuelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fuelInput.add(new JLabel("Liter:"));
		fuelInput.add(new JTextField(10));
		fuelInput.setName(DriveMeConstants.Database.Vehicle.FUEL);
		panel.add(fuelInput);

		return panel;
	}
	
}
