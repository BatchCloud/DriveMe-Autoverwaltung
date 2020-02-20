package driveMe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Rectangle;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import driveMe.customers.model.Customer;
import driveMe.customers.service.CustomerService;
import driveMe.constants.DriveMeConstants;
import driveMe.vehicles.model.Vehicle;
import driveMe.vehicles.service.VehicleService;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;


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
		
		//Main Body Content Panel for Vehicle
		JPanel bodyContentPanel = new JPanel();
		bodyContentPanel.setBackground(Color.WHITE);
		bodyContentPanel.setPreferredSize(new Dimension(10, 90));
		bodyContentPanel.setLayout(new BorderLayout(0, 0));
		
		setUpHeader(bodyContentPanel);
		bodyContentPanel.setLayout(new CardLayout(0, 0));
		
		bodyContentPanel.add(customerContent(), "name_47788877080200");
		mainFrame.getContentPane().add(bodyContentPanel);
	}
	private void setUpHeader(JPanel bodyContentPanel) 
	{
		//Setup header pannel
		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(10, 90));
		headerPanel.setMinimumSize(new Dimension(10, 128));
		headerPanel.setBackground(SystemColor.windowBorder);
		mainFrame.getContentPane().add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		setUpHeaderTop(bodyContentPanel,headerPanel);
		setUpHeaderBottom(headerPanel);
	}

	
	private void setUpHeaderTop(JPanel bodyContentPanel,JPanel headerPanel) {
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
		vehicleButton.setForeground(primaryColor);
		vehicleButton.setBackground(secondColor);
		vehicleButton.setMargin(new Insets(0, 0, 0, 0));
		vehicleButton.setPreferredSize(new Dimension(150, 35));
		headerTop.add(vehicleButton);
		
	
		JPanel customerContent = customerContent();
		JPanel vehicleContent = vehicleContent();
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButton.setBackground(secondColor);	
				customerButton.setForeground(primaryColor);
				vehicleButton.setBackground(primaryColor);	
				vehicleButton.setForeground(Color.WHITE);
				bodyContentPanel.removeAll();
				bodyContentPanel.repaint();
				bodyContentPanel.revalidate();
				bodyContentPanel.add(customerContent);
				bodyContentPanel.repaint();
				bodyContentPanel.revalidate();
				
			}
		});
		vehicleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButton.setBackground(primaryColor);	
				customerButton.setForeground(Color.WHITE);
				vehicleButton.setBackground(secondColor);	
				vehicleButton.setForeground(primaryColor);
				bodyContentPanel.removeAll();
				bodyContentPanel.repaint();
				bodyContentPanel.revalidate();
				bodyContentPanel.add(vehicleContent);
				bodyContentPanel.repaint();
				bodyContentPanel.revalidate();
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

	private JPanel vehicleContent() 
	{
		JPanel  vehicleBodyContentPanel = new JPanel();
		vehicleBodyContentPanel.setBackground(Color.WHITE);
		vehicleBodyContentPanel.setLayout(new BorderLayout(0, 0));
		vehicleBodyContentPanel.setPreferredSize(new Dimension(10, 90));
		
			//Vehicle Panel Align West 
			JPanel vehiclePanelWest = new JPanel();
			
			vehiclePanelWest.setMinimumSize(new Dimension(10, 128));
			vehiclePanelWest.setPreferredSize(new Dimension(400, 10));
			vehiclePanelWest.setBackground(SystemColor.GREEN);
			vehiclePanelWest.setLayout(new BorderLayout(0, 0));

	
			JPanel scrollPane = new JPanel();
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setLayout(new MigLayout("", "[320px]", "[100px]"));
			
			JScrollPane scrollPaneContainer = new JScrollPane(scrollPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneContainer.setBorder(null);
			scrollPaneContainer.setBounds(new Rectangle(0, 0, 0, 20));
			scrollPaneContainer.setPreferredSize(new Dimension(0, 200));
			
			ArrayList<Vehicle> vehicles = VehicleService.findVehiclesByAll();
			int i = 0;
			for (Vehicle currentVehicle : vehicles)
			{
				String position = "cell 0 "+ i + ",grow";
				scrollPane.add(createVehiclePanel(currentVehicle), position );
				i++;
			}
			
			vehiclePanelWest.add(scrollPaneContainer, BorderLayout.CENTER);
			
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
			
		vehicleBodyContentPanel.add(vehiclePanelWest, BorderLayout.WEST);
		
		
			//Placeholder for bodyContentPanel NORTH
			JPanel placeholderNorth = new JPanel();
			placeholderNorth.setBackground(Color.WHITE);
			placeholderNorth.setPreferredSize(new Dimension(100, 30));
			
		vehicleBodyContentPanel.add(placeholderNorth, BorderLayout.NORTH);
			
		
			//Placeholder for bodyContentPanel SOUTH
			JPanel placeholderSouth = new JPanel();
			placeholderSouth.setBackground(Color.WHITE);
			placeholderSouth.setPreferredSize(new Dimension(30, 30));
		
		vehicleBodyContentPanel.add(placeholderSouth, BorderLayout.SOUTH);
		
		
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
			
		vehicleBodyContentPanel.add(vehiclePanelCenter, BorderLayout.CENTER);
			
		return vehicleBodyContentPanel;
	} 
	
	private JPanel customerContent() 
	{
		JPanel customerBodyContentPanel = new JPanel();
		customerBodyContentPanel.setBackground(Color.WHITE);
		customerBodyContentPanel.setLayout(new BorderLayout(0, 0));
		customerBodyContentPanel.setPreferredSize(new Dimension(10, 90));

		JPanel scrollPane = new JPanel();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setLayout(new MigLayout("", "[320px]", "[100px]"));
		
		JScrollPane scrollPaneContainer = new JScrollPane(scrollPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneContainer.setBorder(null);
		scrollPaneContainer.setBounds(new Rectangle(0, 0, 0, 20));
		scrollPaneContainer.setPreferredSize(new Dimension(0, 200));
		
		ArrayList<Customer> customer = CustomerService.findCostumerByAll();
		int i = 0;
		for (Customer currentCustomer : customer)
		{
			String position = "cell "+ i + " 0,grow";
			
			JPanel test1 = new JPanel();
			scrollPane.setBackground(Color.WHITE);
			
			
			scrollPane.add(test1, position );
			i++;
		}
		
		customerBodyContentPanel.add(scrollPaneContainer, BorderLayout.CENTER);
		

		//Placeholder for customerBodyContentPanel WEST
		JPanel placeholderWest = new JPanel();
		placeholderWest.setBackground(Color.WHITE);
		placeholderWest.setPreferredSize(new Dimension(30, 10));
		
		customerBodyContentPanel.add(placeholderWest, BorderLayout.WEST);
		
		//Placeholder for customerBodyContentPanel EAST
		JPanel placeholderWEST = new JPanel();
		placeholderWEST.setBackground(Color.WHITE);
		placeholderWEST.setPreferredSize(new Dimension(30, 10));

		customerBodyContentPanel.add(placeholderWEST, BorderLayout.EAST);
	
		//Placeholder for customerBodyContentPanel NORTH
		JPanel placeholderNorth = new JPanel();
		placeholderNorth.setBackground(Color.WHITE);
		placeholderNorth.setPreferredSize(new Dimension(100, 30));
		
		customerBodyContentPanel.add(placeholderNorth, BorderLayout.NORTH);
		
		//Placeholder for customerBodyContentPanel SOUTH
		JPanel placeholderSouth = new JPanel();
		placeholderSouth.setBackground(Color.WHITE);
		placeholderSouth.setPreferredSize(new Dimension(30, 30));
	
		customerBodyContentPanel.add(placeholderSouth, BorderLayout.SOUTH);

		return customerBodyContentPanel;
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
		moreDetailsBtn.setIcon(resizeImageIcon("standort.png", 15, 15));
		moreDetailsBtn.setBorder(null);
		moreDetailsBtn.setPreferredSize(new Dimension(10, 10));
		moreDetailsBtn.setBounds(294, 11, 26, 23);
		innerVehiclePanel.add(moreDetailsBtn);
		
		JButton reservationBtn = new JButton("");
		reservationBtn.setIcon(resizeImageIcon("menu.png", 15, 15));
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
	
	private ImageIcon resizeImageIcon(String url, int heigth, int width) 
	{
		ImageIcon imageIcon = new ImageIcon(MainController.class.getResource(DriveMeConstants.Picture.PATH + url));
		
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(heigth, width,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return imageIcon = new ImageIcon(newimg);
	}
}
