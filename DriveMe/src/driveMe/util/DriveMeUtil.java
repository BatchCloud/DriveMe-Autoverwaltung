package driveMe.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import driveMe.MainRenderer;
import driveMe.constants.DriveMeConstants;
import driveMe.controller.VehicleRenderer;
import driveMe.service.DatabaseService;
import driveMe.vehicles.model.Vehicle;

public class DriveMeUtil extends MainRenderer{
	
	DatabaseService dataBaseService;
	
	public DriveMeUtil()
	{
		dataBaseService = new DatabaseService();
	}
	
	public ImageIcon resizeImageIcon(String url, int heigth, int width) 
	{
		ImageIcon imageIcon = new ImageIcon(MainRenderer.class.getResource(DriveMeConstants.Picture.PATH + url));
		
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(heigth, width,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return imageIcon = new ImageIcon(newimg);
	}
	
	public void addComboBoxToPanel(JPanel panel)
	{
		JComboBox<String> comboBox = new JComboBox<String>();
	
		comboBox.setForeground(Color.WHITE);
		comboBox.setBorder(null);
		comboBox.setBackground(DriveMeConstants.Colour.primaryColor);
		comboBox.setPreferredSize(new Dimension(145, 25));
		comboBox.addItem("Filtern");
		comboBox.addItem("test");

		panel.add(comboBox);
	}
	
	public JPanel clearAndSetContent(JPanel contentPanel, JPanel content, String borderLayout) 
	{
		contentPanel.removeAll();
//		contentPanel.repaint();
//		contentPanel.revalidate();
		
		if(StringUtils.isNotBlank(borderLayout))
		{
			contentPanel.add(content, borderLayout);
		}
		else
		{
			contentPanel.add(content);
		}
		SwingUtilities.updateComponentTreeUI(contentPanel);
		
		return contentPanel;
//		contentPanel.repaint();
//		contentPanel.revalidate();
	}
	
	public void clearAndSetContent(JPanel contentPanel, JPanel content) 
	{
		contentPanel.removeAll();
//		contentPanel.repaint();
//		contentPanel.revalidate();
		
		contentPanel.add(content);
		
		SwingUtilities.updateComponentTreeUI(contentPanel);
//		contentPanel.repaint();
//		contentPanel.revalidate();
	}
	
	public void clearAndSetContentForBodyPanel(JPanel content) 
	{
		bodyContentPanel.removeAll();
//		bodyContentPanel.repaint();
//		bodyContentPanel.revalidate();
		
		bodyContentPanel.add(content);
		SwingUtilities.updateComponentTreeUI(bodyContentPanel);
//		bodyContentPanel.repaint();
//		bodyContentPanel.revalidate();
	}
	
	public JPanel createPlaceholderPanel(Dimension preferedSize)
	{
		//Placeholder for bodyContentPanel NORTH
		JPanel placeholder= new JPanel();
		placeholder.setBackground(Color.WHITE);
		placeholder.setPreferredSize(preferedSize);
		
		return placeholder;
	}
	
	public void applyNewVehicle() 
	{
		JPanel panel = setVehicleFormToPanel();
		
		JOptionPane.showMessageDialog(mainFrame, panel, "Fahrzeug anlegen", JOptionPane.DEFAULT_OPTION);
		
		Component[] filledComponents = panel.getComponents();
		if(ArrayUtils.isNotEmpty(filledComponents))
		{
			Vehicle newVehicle = new Vehicle();
			for(Component currentComponent : filledComponents) {
				if(DriveMeConstants.Database.Vehicle.BRAND.equals(currentComponent.getName())){
					String brandValue = getStringFromSubComponentCombobox(currentComponent);
					newVehicle.setBrand(brandValue);
					String modelValue = getStringFromSubComponentTextField(currentComponent);
					if(StringUtils.isEmpty(modelValue))
					{
						modelValue="";
					}
					newVehicle.setModel(modelValue);
				}
				else if(DriveMeConstants.Database.Vehicle.PS.equals(currentComponent.getName())){
					String psValue = getStringFromSubComponentCombobox(currentComponent);
					int ps = 0;
					if(psValue.matches("[0-9]+"))
					{
						ps = Integer.valueOf(psValue);
					}

					newVehicle.setPs(ps);
				}
				else if(DriveMeConstants.Database.Vehicle.SEATS.equals(currentComponent.getName())){
					String selectedSeat = getStringFromSubComponentCombobox(currentComponent);
					int seatValue = 0;
					if(selectedSeat.matches("[0-9]+"))
					{
						seatValue = Integer.valueOf(selectedSeat);
					}
					newVehicle.setSeats(seatValue);									
				}
				else if(DriveMeConstants.Database.Vehicle.LONGITUDE.equals(currentComponent.getName())){
					String longitudeValue = getStringFromSubComponentTextField(currentComponent);
					if(StringUtils.isEmpty(longitudeValue))
					{
						longitudeValue="";
					}
					newVehicle.setLongitude(longitudeValue);									
				}
				else if(DriveMeConstants.Database.Vehicle.LATITUDE.equals(currentComponent.getName())){
					String latitudeValue = getStringFromSubComponentTextField(currentComponent);
					if(StringUtils.isEmpty(latitudeValue))
					{
						latitudeValue="";
					}
					newVehicle.setLongitude(latitudeValue);									
				}
				else if(DriveMeConstants.Database.Vehicle.IMAGE.equals(currentComponent.getName())){
					String imageValue= getStringFromSubComponentTextField(currentComponent);
					if(StringUtils.isEmpty(imageValue))
					{
						imageValue="";
					}
					newVehicle.setImage(imageValue);									
				}
				else if(DriveMeConstants.Database.Vehicle.FUEL.equals(currentComponent.getName())){
					String fuelValue = getStringFromSubComponentTextField(currentComponent);
					if(StringUtils.isEmpty(fuelValue))
					{
						fuelValue="";
					}
					newVehicle.setFuel(fuelValue);									
				}
			}
			boolean vehicleSaved = dataBaseService.saveVehicle(newVehicle);
			if(vehicleSaved) {
				System.out.println("Jucheii!");
				//TODO
			}
			else {
				System.out.println("Something went wrong while saving the vehicle!");
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
		horsePowerInput.add(new JLabel("Pferdestärke:"));
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
	private String getStringFromSubComponentCombobox(Component currentComponent)
	{
		if(currentComponent instanceof Container) {
			Container subCont = (Container) currentComponent;
			for(Component currSubComponent : subCont.getComponents()) {
				if(currSubComponent instanceof JComboBox) {
					JComboBox<?> brand = (JComboBox<?>) currSubComponent;
					String brandName = brand.getSelectedItem().toString();
					return brandName;
				}
			}
		}
		return "";
	}
	private String getStringFromSubComponentTextField(Component currentComponent)
	{
		if(currentComponent instanceof Container) {
			Container subCont = (Container) currentComponent;
			for(Component currSubComponent : subCont.getComponents()) {
				if(currSubComponent instanceof JTextField) {
					JTextField model = (JTextField) currSubComponent;
					String textFieldInput = model.getText();
					return textFieldInput;
				}
			}
		}
		return "";
	}
}
