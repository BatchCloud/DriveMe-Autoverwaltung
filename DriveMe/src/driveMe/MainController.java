package driveMe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import driveMe.vehicles.model.Vehicle;
import driveMe.vehicles.service.VehicleService;
import net.miginfocom.swing.MigLayout;


public class MainController {

public JFrame mainFrame=new JFrame("DriveMe");
	
	private Color primaryColor = new Color(105,157,217);
	private Color secondColor = new Color(238,238,238);
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
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
		JButton customerButton = new JButton("Kunden");
		customerButton.setForeground(Color.WHITE);
		customerButton.setBorder(null);
		customerButton.setBackground(primaryColor);
		customerButton.setMargin(new Insets(0, 0, 0, 0));
		customerButton.setPreferredSize(new Dimension(150, 35));
		
		JButton vehicleButton = new JButton("Fahrzeuge");
		vehicleButton.setBorder(null);
		vehicleButton.setForeground(Color.BLACK);
		vehicleButton.setBackground(secondColor);
		vehicleButton.setMargin(new Insets(0, 0, 0, 0));
		vehicleButton.setPreferredSize(new Dimension(150, 35));
		headerTop.add(vehicleButton);
		
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.updateComponentTreeUI(mainFrame.getContentPane().add(customerContent(customerButton, vehicleButton)));
			}
		});
		vehicleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.updateComponentTreeUI(mainFrame.getContentPane().add(vehicleContent(customerButton, vehicleButton)));
			}
		});
		headerTop.add(customerButton);
	}
	
	private void setUpHeaderBottom(JPanel headerPanel) 
	{
		//Set header bottom
		JPanel headerBottom = new JPanel();
		headerPanel.add(headerBottom, BorderLayout.CENTER);
		headerBottom.setPreferredSize(new Dimension(10, 20));
		headerBottom.setMinimumSize(new Dimension(10, 27));
		headerBottom.setBackground(secondColor);
		headerBottom.setLayout(new BorderLayout(0, 0));
		
		//Set leftSidePanel to header bottom
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setPreferredSize(new Dimension(200, 10));
		headerBottom.add(leftSidePanel, BorderLayout.WEST);

		//Set rightSidePanel to header bottom
		JPanel rightSidePanel = new JPanel();
		rightSidePanel.setPreferredSize(new Dimension(200, 10));
		rightSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		headerBottom.add(rightSidePanel, BorderLayout.EAST);
		//Add compobox to rightSidePanel
		addComboBoxToPanel(rightSidePanel);
		
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
		midSidePanel.add(textField);
		textField.setColumns(50);
	}

	private JPanel vehicleContent(JButton customerButton, JButton vehicleButton) 
	{
		JPanel vehiclePanel = new JPanel();
		vehiclePanel.setPreferredSize(new Dimension(10, 90));
		vehiclePanel.setMinimumSize(new Dimension(10, 128));
		vehiclePanel.setBackground(SystemColor.WHITE);
		vehiclePanel.setLayout(new BorderLayout(0, 0));
		vehiclePanel.setLayout(new MigLayout("", "[320px]", "[100px]"));
		
		customerButton.setForeground(Color.WHITE);
		customerButton.setBackground(primaryColor);
		vehicleButton.setForeground(Color.BLACK);
		vehicleButton.setBackground(secondColor);
		
		ArrayList<Vehicle> vehicles = VehicleService.findVehiclesByAll();
		int i = 0;
		for (Vehicle currentVehicle : vehicles)
		{
			String position = "cell 0 "+ i + ",grow";
			vehiclePanel.add(createVehiclePanel(currentVehicle), position );
			i++;
		}
		
		return vehiclePanel;
	} 
	
	private JPanel customerContent(JButton customerButton, JButton vehicleButton) 
	{
		JPanel customerPanel = new JPanel();
		customerPanel.setPreferredSize(new Dimension(10, 90));
		customerPanel.setMinimumSize(new Dimension(10, 128));
		customerPanel.setBackground(SystemColor.RED);
		customerPanel.setLayout(new BorderLayout(0, 0));
		
		customerButton.setBackground(secondColor);
		vehicleButton.setBackground(primaryColor);
		customerButton.setForeground(Color.BLACK);
		vehicleButton.setForeground(Color.WHITE );
		
		return customerPanel;
	}
	
	private void addComboBoxToPanel(JPanel panel)
	{
		JComboBox<String> comboBox = new JComboBox<String>();
		
		comboBox.setForeground(Color.WHITE);
		comboBox.setBorder(null);
		comboBox.setBackground(primaryColor);
		comboBox.setPreferredSize(new Dimension(145, 25));
		comboBox.addItem("Filtern");
		comboBox.addItem("test");
		
		panel.add(comboBox);
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
//		moreDetailsBtn.setIcon(resizeImageIcon1(new ImageIcon(DriveMe.class.getResource("/test121/standort.png")), 15, 15));
		moreDetailsBtn.setBorder(null);
		moreDetailsBtn.setPreferredSize(new Dimension(10, 10));
		moreDetailsBtn.setBounds(294, 11, 26, 23);
		innerVehiclePanel.add(moreDetailsBtn);
		
		JButton reservationBtn = new JButton("");
//		reservationBtn.setIcon(resizeImageIcon1(new ImageIcon(DriveMe.class.getResource("/test121/menu.png")), 15, 15));
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
	
	private ImageIcon resizeImageIcon1(ImageIcon imageIcon, int heigth, int width) 
	{
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(heigth, width,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return imageIcon = new ImageIcon(newimg);
	}
}
